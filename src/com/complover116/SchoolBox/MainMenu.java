package com.complover116.SchoolBox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.VideoView;


public class MainMenu extends Activity {
static Question[] quests = new Question[20];
static int qnum;
int uf = 1;
long myDownloadReference;
AlertDialog dialog;
static MediaPlayer mediaPlayer;
static MediaPlayer ButtonSound;
static VideoView VV;
int fails = 0;
static AlertDialog downloaddialog;
long downloadid;
byte dltype;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
		
        this.setContentView(R.layout.activity_main_menu);
		VV = (VideoView)findViewById(R.id.VidVi);
		VV.setVideoURI(Uri.parse("android.resource://com.complover116.SchoolBox/"+R.raw.preview));

		
    }
    @Override
	public void onResume() {
		super.onResume();
		LoadMedia();
		VV.start();
		mediaPlayer.start();
	}
	public void onPause() {
		super.onPause();
		mediaPlayer.stop();
		
	}
    public void onSaveInstanceState(Bundle instanceState) {
     super.onSaveInstanceState(instanceState);
    }
    public void FUpdateClick(View view) {
    	doupdate();
    }
    public void doupdate(){
    	dltype = 1;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
	builder.setMessage("Downloading the update...")
       .setTitle("Loading...");
	builder.setCancelable(false);
	AlertDialog dialogy = builder.create();
	dialogy.show();
	downloaddialog = dialogy;
	   String url = "https://dl.dropboxusercontent.com/s/883c70y8dssdxmo/Schoolbox.apk?dl=1&token_hash=AAEksOhJv2FKZHrrHxQwfrgdm98extYxjHDbNSpCw3HsSg";
	   DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
	   request.setDescription("SchoolBox update file");
	   request.setTitle("ScholBox update");
	   // in order for this if to run, you must use the android 3.2 to compile your app
	   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
	       request.allowScanningByMediaScanner();
	       request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
	   }
	   
	   Uri destination = Uri.fromFile(new File(getExternalFilesDir( null ).getPath()+"/SchoolBox.apk"));
	   
	   request.setDestinationUri(destination);

	   // get download service and enqueue file
	   DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
	   manager.enqueue(request);
    }
    public void buttonpress(View view){
    	final MainMenu self = this;
    	byte result = ResCheck();
    	if(result == -1) {
    		AlertDialog.Builder builder = new AlertDialog.Builder(this);
    		builder.setMessage("The questions file is entirely corrupt. The program can't fix it, but you could choose a diferent file... Click \"Auto-install\" to automatically get a working one")
    	       .setTitle("Failed to load Questions");
    		builder.setCancelable(false);
    		// Add the buttons
    		builder.setPositiveButton("Configure...", new DialogInterface.OnClickListener() {
    		           public void onClick(DialogInterface dialog, int id) {
    		               // User clicked OK button
    		           }
    		       });
    		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
    		           public void onClick(DialogInterface dialog, int id) {
    		               // User cancelled the dialog
    		           }
    		       });
    		builder.setNeutralButton("Auto-install", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
			       		AlertDialog.Builder builder = new AlertDialog.Builder(self);
			    		builder.setMessage("Downloading the question file...")
			    	       .setTitle("Loading...");
			    		builder.setCancelable(false);
			    		AlertDialog dialogy = builder.create();
			    		dialogy.show();
			        	dltype = 0;
			    		downloaddialog = dialogy;
			        	   String url = "https://dl.dropboxusercontent.com/s/264e5lte2cxpxy3/Question.txt?dl=1&token_hash=AAEG1_pdHNZ1cQMfrmyVljsTliw_Ft4R6JUsmIPH7cYxog";
			        	   DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
			        	   request.setDescription("SchoolBox default questions file");
			        	   request.setTitle("ScholBox questions");
			        	   // in order for this if to run, you must use the android 3.2 to compile your app
			        	   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			        	       request.allowScanningByMediaScanner();
			        	       request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
			        	   }
			        	   
			        	   Uri destination = Uri.fromFile(new File(getExternalFilesDir( null ).getPath()+"/Questions.scb"));
			        	   
			        	   request.setDestinationUri(destination);

			        	   // get download service and enqueue file
			        	   DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
			        	   manager.enqueue(request);
			        	   
			               // User cancelled the dialog
			           }
		       });
    		// Set other dialog properties

    		// Create the AlertDialog
    		AlertDialog dialog = builder.create();
    		dialog.show();
    	}
    	if(result == 0) {
    		fails = fails + 1;
    		if(fails > 1) {
        		AlertDialog.Builder builder = new AlertDialog.Builder(this);
        		builder.setMessage("We still don't have access to the questions. Looks as if there is something wrong with file storage permissions. Unfortunately, the program cannot help you there : (")
        	       .setTitle("Failed to load Questions");
        		builder.setCancelable(false);
        		// Add the buttons
        		builder.setPositiveButton("Exit...", new DialogInterface.OnClickListener() {
        		           public void onClick(DialogInterface dialog, int id) {
        		               self.exit(null);
        		           }
        		       });
        		builder.setNegativeButton("WAIT! I CAN SOLVE IT!", new DialogInterface.OnClickListener() {
 		           public void onClick(DialogInterface dialog, int id) {
 		               // User cancelled the dialog
 		           }
 		       });
        		AlertDialog dialog = builder.create();
        		dialog.show();
    		} else {
    		AlertDialog.Builder builder = new AlertDialog.Builder(this);
    		builder.setMessage("Loading questions failed. Perhaps, it is the first time you open this app? If you haven't configured your question list yet, click \"Configure\". If you want, you can click \"Auto-install\", which will download and install demo questions.")
    	       .setTitle("Failed to load Questions");
    		builder.setCancelable(false);
    		// Add the buttons
    		builder.setPositiveButton("Configure...", new DialogInterface.OnClickListener() {
    		           public void onClick(DialogInterface dialog, int id) {
    		               // User clicked OK button
    		           }
    		       });
    		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
    		           public void onClick(DialogInterface dialog, int id) {
    		               // User cancelled the dialog
    		           }
    		       });
    		builder.setNeutralButton("Auto-install", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		       		AlertDialog.Builder builder = new AlertDialog.Builder(self);
		    		builder.setMessage("Downloading the question file...")
		    	       .setTitle("Loading...");
		    		builder.setCancelable(false);
		    		AlertDialog dialogy = builder.create();
		    		dialogy.show();
		        	dltype = 0;
		    		downloaddialog = dialogy;
		        	   String url = "https://dl.dropboxusercontent.com/s/264e5lte2cxpxy3/Question.txt?dl=1&token_hash=AAEG1_pdHNZ1cQMfrmyVljsTliw_Ft4R6JUsmIPH7cYxog";
		        	   DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
		        	   request.setDescription("SchoolBox default questions file");
		        	   request.setTitle("ScholBox questions");
		        	   // in order for this if to run, you must use the android 3.2 to compile your app
		        	   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
		        	       request.allowScanningByMediaScanner();
		        	       request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
		        	   }
		        	   
		        	   Uri destination = Uri.fromFile(new File(getExternalFilesDir( null ).getPath()+"/Questions.scb"));
		        	   
		        	   request.setDestinationUri(destination);

		        	   // get download service and enqueue file
		        	   DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
		        	   manager.enqueue(request);
		        	   
		               // User cancelled the dialog
		           }
		       });
    		// Set other dialog properties
    		
    		// Create the AlertDialog
    		AlertDialog dialog = builder.create();
    		dialog.show();
    		}
    	} else if (result == 1){
    	mediaPlayer.pause();
    	ButtonSound.start();
    	Intent intent = new Intent(this, Grammar.class);
    	startActivity(intent);
    	}
    }
        @Override
    public void onBackPressed() {
      
    }
	public void exit(View view) {
		mediaPlayer.stop();
		this.finish();
	}
	public void LoadMedia() {

	       Grammar.mediaPlayer = MediaPlayer.create(this.getBaseContext(), R.drawable.fud);
			Grammar.mediaPlayer.setLooping(true);
	        Log.d("STATUS", "Fud loaded");
	        Grammar.yesmp = MediaPlayer.create(this.getBaseContext(), R.raw.yes);
	        Log.d("STATUS", "Yes loaded");
	        Grammar.nomp = MediaPlayer.create(this.getBaseContext(), R.raw.no);
	        Log.d("STATUS", "No loaded");
	        Grammar.ButtonSound = MediaPlayer.create(this.getBaseContext(), R.raw.button);
	        Log.d("STATUS", "Button loaded");
	        MainMenu.mediaPlayer = MediaPlayer.create(this.getBaseContext(), R.raw.lal);
			MainMenu.mediaPlayer.setLooping(true);
	        Log.d("STATUS", "Lal loaded");
	        MainMenu.ButtonSound = MediaPlayer.create(this.getBaseContext(), R.raw.button);
	        //TODO fix sounds loading twice!
	        Log.d("STATUS", "Button loaded TODO Fix loading twice!");
	        GrammarTestResult.ButtonSound = MediaPlayer.create(this.getBaseContext(), R.raw.button);
	}
	public byte ResCheck() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Parsing resources...")
	       .setTitle("Loading...");
		AlertDialog dialog = builder.create();
		dialog.show();
		
		File file = new File(getExternalFilesDir( null ).getPath()+"/Questions.scb");
		if(file.length() == 0) {
			return 0;
		}
		StringBuilder text = new StringBuilder();
        int Qc = 0;
        int DataLine = 1;
        String CQText = null;
        String CQVar1 = null;
        String CQVar2 = null;
        String CQVar3 = null;
        String CQVar4 = null;
        String type = null;
        int typ = 0;
 
		try {
		    BufferedReader br = new BufferedReader(new FileReader(file));
		    String line;

		    while ((line = br.readLine()) != null) {
		        text.append(line);
		        text.append('\n');
		        switch (DataLine) {
		        case 1:
		        CQText = line;
		        DataLine = 2;
		        break;
		        case 2:
			        CQVar1 = line;registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
			        DataLine = 3;
		        break;
		        case 3:
			        CQVar2 = line;
			        DataLine = 4;
		        break;
		        case 4:
			        CQVar3 = line;
			        DataLine = 5;
		        break;
		        case 5:
			        CQVar4 = line;
			        DataLine = 6;
		        break;
		        case 6:
	type = line;
	DataLine = 7;
              if(type.equalsIgnoreCase("1")) {
              typ = 1;
              }
              else if(type.equalsIgnoreCase("2")) {
              typ = 2;
              }
              else if(type.equalsIgnoreCase("3")) {
              typ = 3;
              }else if(type.equalsIgnoreCase("4")) {
              typ = 4;
              }else {
                Log.e("InRes", "Type Failure: Expected a number, got |"+line+"| at Question "+Qc);
                DataLine = 0;
              }
              
            break;
            case 7:
			        DataLine = 1;
			        Qc ++;
			        if(line.equalsIgnoreCase("1")) {
			        MainMenu.quests[Qc] = new Question(CQText, CQVar1, CQVar2, CQVar3, CQVar4, 1, typ);
			        }
			        else if(line.equalsIgnoreCase("2")) {
			        MainMenu.quests[Qc] = new Question(CQText, CQVar1, CQVar2, CQVar3, CQVar4, 2, typ);
			        }
			        else if(line.equalsIgnoreCase("3")) {
			        MainMenu.quests[Qc] = new Question(CQText, CQVar1, CQVar2, CQVar3, CQVar4, 3, typ);
			        }else if(line.equalsIgnoreCase("4")) {
			        MainMenu.quests[Qc] = new Question(CQText, CQVar1, CQVar2, CQVar3, CQVar4, 4, typ);
			        }else {
			        	Log.e("InRes", "CQAns Failure: Expected a number, got |"+line+"| at Question "+Qc);
			        	Qc --;
			        }
		        break;
		        default:
		        	Log.e("InRes", "DataLine Failure");
		        	break;
		        }
		    }
		}
		catch (IOException e) {
		    Log.e("InRes", "Something went wrong...");
		    return 0;
		}
        
        MainMenu.qnum = Qc;
		Log.d("inres", "Checked resources, "+Qc+" Questions loaded.");
		if(quests[1] == null) {
			return -1;
		} else {
			return 1;
		}
	}
	
	
	BroadcastReceiver onComplete=new BroadcastReceiver() {
	    public void onReceive(Context ctxt, Intent intent) {
	        // Do Something
	    	downloaddialog.dismiss();
	    	if(dltype == 0) {
	    	ResCheck();
	    	Toast.makeText(getApplicationContext(), "Installation succeeded. You can start now!", Toast.LENGTH_SHORT).show();
	    	}
	    	if(dltype == 1) {
	    	Toast.makeText(getApplicationContext(), "Select \"Install\" when prompted!", Toast.LENGTH_LONG).show();
	    	 Intent intenty = new Intent(Intent.ACTION_VIEW);
	    	    intent.setData(Uri.parse("file://"+new File(getExternalFilesDir( null ).getPath()+"/SchoolBox.apk")));
	    	    intent.setType("application/vnd.android.package-archive");
	    	    startActivity(intenty);

	    	}
	    }
	};
}

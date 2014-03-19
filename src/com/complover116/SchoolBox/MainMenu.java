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
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.VideoView;


public class MainMenu extends Activity {
static Question[] quests = new Question[20];
static int qnum;
static String type1;
static String type2;
static String type3;
static String type4;
int uf = 1;
long myDownloadReference;
AlertDialog dialog;
static MediaPlayer mediaPlayer;
static MediaPlayer ButtonSound;
static VideoView VV;
int fails = 0;
static AlertDialog downloaddialog;
long downloadid;
static byte dltype = -1;
static byte check = 1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		
        this.setContentView(R.layout.activity_main_menu);
		VV = (VideoView)findViewById(R.id.VidVi);
		VV.setVideoURI(Uri.parse("android.resource://com.complover116.SchoolBox/"+R.raw.preview));
		registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
		check = 0;
		((Switch)findViewById(R.id.switch2)).setChecked(true);
		
    }
    @Override
	public void onResume() {
		super.onResume();
		LoadMedia();
		VV.start();
		mediaPlayer.start();
	}
    @Override
	public void onDestroy() {
    	super.onDestroy();
		unregisterReceiver(onComplete);
	}
    @Override
	public void onPause() {
		super.onPause();
		mediaPlayer.stop();
		
	}
    public void toggleCheck(View view) {
        boolean on = ((Switch)findViewById(R.id.switch2)).isChecked();
        MainMenu.ButtonSound.start();
        if (on) {
        	check = 0;
        } else {
            check = 1;
        }
    }
    public void onSaveInstanceState(Bundle instanceState) {
     super.onSaveInstanceState(instanceState);
    }
    public void FUpdateClick(View view) {
    	doupdate();
    }
    public void confQuestions() {
    	ButtonSound.start();
    	if(isNetworkAvailable()) {
    	Intent intent = new Intent(this, Install.class);
    	startActivity(intent);
    	}
    }
    public void configClick(View view) {
    	confQuestions();
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
	   String url = "https://dl.dropboxusercontent.com/s/sdx1mcer9fqt1xe/Russian-yandex-2014.txt?dl=1&token_hash=AAFM_4v4cNNnJLRAQtzXBuCScPKIxCYOWfjHILoO2LWX2Q";
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
    		builder.setMessage("Файл вопросов поврежден. Это может быть вызвано сбоем при закачке файла, попыткой его отредактировать впоследствии или неправильным составлением файла. Вы можете скачать другой файл вопросов для работы с ним.")
    	       .setTitle("Критическая ошибка!");
    		builder.setCancelable(false);
    		// Add the buttons
    		builder.setPositiveButton("Выбрать файл...", new DialogInterface.OnClickListener() {
    		           public void onClick(DialogInterface dialog, int id) {
    		               confQuestions();
    		           }
    		       });
    		builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
    		           public void onClick(DialogInterface dialog, int id) {
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
        		builder.setMessage("Файл вопросов недоступен. Это может быть вызвано особенностями прошивки или блокирующими программами. Программа не может помочь вам здесь.")
        	       .setTitle("Критическая ошибка");
        		builder.setCancelable(false);
        		// Add the buttons
        		builder.setPositiveButton("Выход", new DialogInterface.OnClickListener() {
        		           public void onClick(DialogInterface dialog, int id) {
        		               self.exit(null);
        		           }
        		       });
        		builder.setNegativeButton("Вернуться в программу", new DialogInterface.OnClickListener() {
 		           public void onClick(DialogInterface dialog, int id) {
 		               // User cancelled the dialog
 		           }
 		       });
        		AlertDialog dialog = builder.create();
        		dialog.show();
    		} else {
    		AlertDialog.Builder builder = new AlertDialog.Builder(this);
    		builder.setMessage("Файл вопросов отсутсвует. В этом нет ничего страшного - вы можете скачать любой файл вопросов из меню закачки вопросов.")
    	       .setTitle("Файл вопросов отсутствует");
    		builder.setCancelable(false);
    		// Add the buttons
    		builder.setPositiveButton("Выбрать файл...", new DialogInterface.OnClickListener() {
    		           public void onClick(DialogInterface dialog, int id) {
    		        	   confQuestions();
    		           }
    		       });
    		builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
    		           public void onClick(DialogInterface dialog, int id) {
    		               // User cancelled the dialog
    		        	   fails = fails - 1;
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
	        Log.d("STATUS", "Button loaded");
	        MainMenu.mediaPlayer = MediaPlayer.create(this.getBaseContext(), R.raw.lal);
			MainMenu.mediaPlayer.setLooping(true);
	        Log.d("STATUS", "Lal loaded");
	        MainMenu.ButtonSound = MediaPlayer.create(this.getBaseContext(), R.raw.button);
	        //TODO fix sounds loading twice!
	        Log.d("STATUS", "Button loaded");
	        GrammarTestResult.ButtonSound = MediaPlayer.create(this.getBaseContext(), R.raw.button);
	}
	public byte ResCheck() {
		
		File file = new File(getExternalFilesDir( null ).getPath()+"/Questions.scb");
		if(file.length() == 0) {
			return 0;
		}
		StringBuilder text = new StringBuilder();
        int Qc = 0;
        int DataLine = -4;
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
		    	DataLine++;
		        text.append(line);
		        text.append('\n');
		        switch (DataLine) {
		        case -3:
		        type1 = line;
		        Log.i("InRes", "Type1 set to |"+type1+"|");
		        break;
		        case -2:
		        type2 = line;
		        Log.i("InRes", "Type2 set to |"+type2+"|");
		        break;
		        case -1:
		        type3 = line;
		        Log.i("InRes", "Type3 set to |"+type3+"|");
		        break;
		        case 0:
		        type4 = line;
		        Log.i("InRes", "Type4 set to |"+type4+"|");
		        break;
		        case 1:
		        CQText = line;
		        break;
		        case 2:
			        CQVar1 = line;
		        break;
		        case 3:
			        CQVar2 = line;
		        break;
		        case 4:
			        CQVar3 = line;
		        break;
		        case 5:
			        CQVar4 = line;
		        break;
		        case 6:
	type = line;
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
			        DataLine = 0;
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
		        	Log.e("InRes", "DataLine Failure, DataLine is |"+DataLine+"|");
		        	break;
		        }
		    }
		}
		catch (IOException e) {
		    Log.e("InRes", "Something went wrong...");
		    return 0;
		}
        
        MainMenu.qnum = Qc;
		Log.d("InRes", "Checked resources, "+Qc+" Questions loaded.");
		if(quests[1] == null) {
			return -1;
		} else {
			return 1;
		}
	}
	
	
	BroadcastReceiver onComplete=new BroadcastReceiver() {
	    public void onReceive(Context ctxt, Intent intent) {
	        // Do Something
	    	
	    	if(dltype == 0) {
	    		downloaddialog.dismiss();
	    	ResCheck();
	    	Toast.makeText(getApplicationContext(), "Установка успешна. Можно начинать!", Toast.LENGTH_LONG).show();
	    	dltype = -1;
	    	}
	    	if(dltype == 3) {
	    	ResCheck();
	    	Toast.makeText(getApplicationContext(), "Установка успешна. Можно начинать!", Toast.LENGTH_LONG).show();
	    	dltype = -1;
	    	}
	    	if(dltype == 1) {
	    	downloaddialog.dismiss();
	    	dltype = -1;
	    	Toast.makeText(getApplicationContext(), "Select \"Install\" when prompted!", Toast.LENGTH_LONG).show();
	    	 Intent intenty = new Intent(Intent.ACTION_VIEW);
	    	    intent.setData(Uri.parse("file://"+new File(getExternalFilesDir( null ).getPath()+"/SchoolBox.apk")));
	    	    intent.setType("application/vnd.android.package-archive");
	    	    startActivity(intenty);

	    	}
	    }
	};
	
	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    boolean sees = activeNetworkInfo != null && activeNetworkInfo.isConnected();
	    if(!sees) {
    		AlertDialog.Builder builder = new AlertDialog.Builder(this);
    		builder.setMessage("Соединение с интернетом отсутствует. Соединение необходимо для использования данной функции. Подключитесь к сети и попробуйте еще раз.")
    	       .setTitle("Нет сети");
    		// Add the buttons
    		builder.setPositiveButton("ОК", new DialogInterface.OnClickListener() {
    		           public void onClick(DialogInterface dialog, int id) {
    		               // User clicked OK button
    		           }
    		       });
    		AlertDialog dialog = builder.create();
    		dialog.show();
	    }
	    return sees;
	}
}

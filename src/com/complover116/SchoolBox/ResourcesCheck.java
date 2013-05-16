package com.complover116.SchoolBox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import android.media.MediaPlayer;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class ResourcesCheck extends Activity {
	static Question[] quests = new Question[10];
	static int qnum = 2;
	int uf = 1;
	AlertDialog dialog;
	long myDownloadReference;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("STATUS", "Loading Screeen image...");
		setContentView(R.layout.activity_resources_check);
		Log.d("STATUS", "Loaded screen image!");
		File sdcard = Environment.getExternalStorageDirectory();
		File file = new File(sdcard,"Questions.txt");
		StringBuilder text = new StringBuilder();
        int Qc = 0;
        int DataLine = 1;
        String CQText = null;
        String CQVar1 = null;
        String CQVar2 = null;
        String CQVar3 = null;
        String CQVar4 = null;
        String type = null;
        int typ = null;
        Grammar.mediaPlayer = MediaPlayer.create(this.getBaseContext(), R.drawable.fud);
        Log.d("STATUS", "Fud loaded");
        Grammar.yesmp = MediaPlayer.create(this.getBaseContext(), R.raw.yes);
        Log.d("STATUS", "Yes loaded");
        Grammar.nomp = MediaPlayer.create(this.getBaseContext(), R.raw.no);
        Log.d("STATUS", "No loaded");
        Grammar.ButtonSound = MediaPlayer.create(this.getBaseContext(), R.raw.button);
        Log.d("STATUS", "Button loaded");
        MainMenu.mediaPlayer = MediaPlayer.create(this.getBaseContext(), R.raw.lal);
        Log.d("STATUS", "Lal loaded");
        MainMenu.ButtonSound = MediaPlayer.create(this.getBaseContext(), R.raw.button);
        //TODO fix sounds loading twice!
        Log.d("STATUS", "Button loaded TODO Fix loading twice!" + text);
        GrammarTestResult.ButtonSound = MediaPlayer.create(this.getBaseContext(), R.raw.button);
        Log.d("STATUS", "Button loaded TODO Fix loading twice!" + text);
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
			        CQVar1 = line;
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
              }
              DataLine = 7;
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
		}
        
        MainMenu.qnum = Qc;
		Log.d("STATUS", "Checked resources:" + text);
		Intent intik = new Intent(this, MainMenu.class);
		startActivity(intik);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_resources_check, menu);
		return true;
	}
}

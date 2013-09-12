package com.complover116.SchoolBox;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Grammar extends Activity {
	int[] rightans;
	String[] quests;
	String[] pans1;
	String[] pans2;
	String[] pans3;
	int cquest = 1;
	int questnum=3;
	static int wrongans;
	static MediaPlayer mediaPlayer;
	static MediaPlayer yesmp;
	static MediaPlayer nomp;
	static MediaPlayer ButtonSound;
	static int ATypeCount = 0;
        static int BTypeCount = 0;
        static int CTypeCount = 0;
        static int DTypeCount = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
    	rightans = new int[questnum+2];
    	quests = new String[questnum+3];
    	pans1 = new String[questnum+3];
    	pans2 = new String[questnum+3];
    	pans3 = new String[questnum+3];
        setContentView(R.layout.activity_grammar);
        TextView qt = (TextView) this.findViewById(R.id.Question);
        TextView textView1 = (TextView) this.findViewById(R.id.textView1);
        TextView textView2 = (TextView) this.findViewById(R.id.textView2);
        TextView textView3 = (TextView) this.findViewById(R.id.textView3);
        TextView textView4 = (TextView) this.findViewById(R.id.textView4);
        TextView ptw = (TextView) this.findViewById(R.id.progress);
        TextView wrngans = (TextView) this.findViewById(R.id.wrongansn);
        qt.setText(MainMenu.quests[cquest].question);
        textView1.setText(MainMenu.quests[cquest].pans1);
        textView2.setText(MainMenu.quests[cquest].pans2);
        textView3.setText(MainMenu.quests[cquest].pans3);
        textView4.setText(MainMenu.quests[cquest].pans4);
        ptw.setText(cquest+"/"+MainMenu.qnum);
        wrngans.setText("Ошибки:"+wrongans+"");
    	mediaPlayer.start(); // no need to call prepare(); create() does that for you
    }
    public void onBackPressed() {
    	
    }
    public void nextStep(View view) {
    	ButtonSound.start();
    	Button but = (Button) view;
    	switch (but.getId()) {
    	case(R.id.ans1):
    		if(MainMenu.quests[cquest].rans!=1){
    		wrongans++;	
    		nomp.start();
           switch(MainMenu.quests[cquest].type) {
          case 1:
          ATypeCount ++;
          break;
         case 2:
          BTypeCount ++;
          break;
          case 3:
          CTypeCount ++;
          break;
          case 4:
          DTypeCount ++;
          break;
        }
    		} else {
    			yesmp.start();
    		}
    		break;
    	case(R.id.ans2):
    		if(MainMenu.quests[cquest].rans!=2){
    		wrongans++;	
    		nomp.start();
    		           switch(MainMenu.quests[cquest].type) {
          case 1:
          ATypeCount ++;
          break;
         case 2:
          BTypeCount ++;
          break;
          case 3:
          CTypeCount ++;
          break;
          case 4:
          DTypeCount ++;
          break;
        }
    		} else {
    			yesmp.start();
    		}
    		break;
    	case(R.id.ans3):
    		if(MainMenu.quests[cquest].rans!=3){
    		wrongans++;	
    		nomp.start();
    		           switch(MainMenu.quests[cquest].type) {
          case 1:
          ATypeCount ++;
          break;
         case 2:
          BTypeCount ++;
          break;
          case 3:
          CTypeCount ++;
          break;
          case 4:
          DTypeCount ++;
          break;
        }
    		} else {
    			yesmp.start();
    		}
    		break;
    	case(R.id.ans4):
    		if(MainMenu.quests[cquest].rans!=4){
    		wrongans++;	
    		nomp.start();
    		           switch(MainMenu.quests[cquest].type) {
          case 1:
          ATypeCount ++;
          break;
         case 2:
          BTypeCount ++;
          break;
          case 3:
          CTypeCount ++;
          break;
          case 4:
          DTypeCount ++;
          break;
        }
    		} else {
    			yesmp.start();
    		}
    		break;
    	}
    	if(cquest==MainMenu.qnum){
    		TextView qt = (TextView) this.findViewById(R.id.Question);
    		qt.setText("DEBUG: Gluk kakoy-to...");
    		qt.setTextColor(Color.rgb(0, 255, 0));
    		Intent intik = new Intent(this, GrammarTestResult.class);
    		startActivityForResult(intik, 1);
    	} else {
    	cquest++;
        TextView textView1 = (TextView) this.findViewById(R.id.textView1);
        TextView textView2 = (TextView) this.findViewById(R.id.textView2);
        TextView textView3 = (TextView) this.findViewById(R.id.textView3);
        TextView textView4 = (TextView) this.findViewById(R.id.textView4);
        TextView qt = (TextView) this.findViewById(R.id.Question);
        TextView ptw = (TextView) this.findViewById(R.id.progress);
        TextView wrngans = (TextView) this.findViewById(R.id.wrongansn);
        qt.setText(MainMenu.quests[cquest].question);
        textView1.setText(MainMenu.quests[cquest].pans1);
        textView2.setText(MainMenu.quests[cquest].pans2);
        textView3.setText(MainMenu.quests[cquest].pans3);
        textView4.setText(MainMenu.quests[cquest].pans4);
        ptw.setText(cquest+"/"+MainMenu.qnum);
        wrngans.setText("Ошибки:"+wrongans+"");
    	}
    }
    public void GoToMainMenu(View poot) {
    	ButtonSound.start();
    	mediaPlayer.pause();
	this.wrongans = 0;
this.finish();
    }
	protected void onActivityResult(int rc, int resc, Intent data) {
		this.finish();
	}
}

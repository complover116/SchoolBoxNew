package com.complover116.SchoolBox;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GrammarTestResult extends Activity {
	static MediaPlayer ButtonSound;
	/**
	 * @param args
	 */
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.activity_grammar_result);
    	Grammar.mediaPlayer.pause();
    	TextView QR = (TextView)findViewById(R.id.QR);
    	QR.setText(MainMenu.qnum - Grammar.wrongans+"/"+MainMenu.qnum);
    	TextView PRC = (TextView)findViewById(R.id.PRC);
    	PRC.setText(((MainMenu.qnum - Grammar.wrongans)*100/MainMenu.qnum)+"%");
		TextView err1 = (TextView)findViewById(R.id.Err1);
		err1.setText(MainMenu.type1+":" + Grammar.ATypeCount);
		TextView err2 = (TextView)findViewById(R.id.Err2);
		err2.setText(MainMenu.type2+":" + Grammar.BTypeCount);
		TextView err3 = (TextView)findViewById(R.id.Err3);
		err3.setText(MainMenu.type3+":" + Grammar.CTypeCount);
		TextView err4 = (TextView)findViewById(R.id.Err4);
		err4.setText(MainMenu.type4+":" + Grammar.DTypeCount);
  }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
    public void onBackPressed() {
    }
    public void GoToMainMenu(View toop) {
    	ButtonSound.start();
    	Grammar.wrongans = 0;
    	this.finish();
    }
    	public void finishirovat() {
		this.finish();
	}
}

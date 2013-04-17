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
    	Grammar.mediaPlayer.stop();
    	TextView QR = (TextView)findViewById(R.id.QR);
    	QR.setText(MainMenu.qnum - Grammar.wrongans+"/"+MainMenu.qnum);
    	TextView PRC = (TextView)findViewById(R.id.PRC);
    	PRC.setText(((MainMenu.qnum - Grammar.wrongans)*100/MainMenu.qnum)+"%");
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
    public void onBackPressed() {
    }
    public void GoToMainMenu(View toop) {
    	ButtonSound.start();
    	Grammar.wrongans = 0;
    	Intent samiykrutoy = new Intent(this, MainMenu.class);
    	startActivity(samiykrutoy);
    }
}

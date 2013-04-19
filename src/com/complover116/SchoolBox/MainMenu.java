package com.complover116.SchoolBox;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.VideoView;


public class MainMenu extends Activity {
static Question[] quests = new Question[20];
static int qnum;
int uf = 1;
long myDownloadReference;
AlertDialog dialog;
static MediaPlayer mediaPlayer;
static MediaPlayer ButtonSound;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main_menu);
    	mediaPlayer.start(); // no need to call prepare(); create() does that for you
		VideoView VV = (VideoView)findViewById(R.id.VidVi);
		VV.setVideoURI(Uri.parse("android.resource://com.complover116.SchoolBox/"+R.raw.preview));
		VV.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }
    public void onSaveInstanceState(Bundle instanceState) {
     super.onSaveInstanceState(instanceState);
    }
    
    public void buttonpress(View view){
    	mediaPlayer.stop();
    	ButtonSound.start();
    	Intent intent = new Intent(this, Grammar.class);
    	startActivity(intent);
    }
    
}

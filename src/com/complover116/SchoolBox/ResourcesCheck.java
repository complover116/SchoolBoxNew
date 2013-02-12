package com.complover116.SchoolBox;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
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
        Intent intent = new Intent(this, MainMenu.class);
        for(int i=1; i<10; i++) {
        	Log.d("STATUS","Checked resources:OK FU" + i);
        }
        setContentView(R.layout.activity_resources_check);
        Log.d("STATUS","Checked resources:OK");
        Intent intik = new Intent(this, MainMenu.class);
        startActivity(intik);
        ImageView androidIV = (ImageView)findViewById(R.id.tstIV);
        View vi = findViewById(R.id.Spinner);
        androidIV.setBackgroundResource(R.drawable.tstanim);
        AnimationDrawable androidAnimation =
        (AnimationDrawable) androidIV.getBackground();
        androidAnimation.start();
        Log.d("STATUS","YAY");
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_resources_check, menu);
        return true;
    }
}

package com.complover116.SchoolBox;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GrammarB extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent pootis = new Intent(this, GrammarTestResult.class);
        startActivity(pootis);
    }
    public void onBackPressed() {
    	
    }
        public void finishirovat() {
		this.finish();
	}
}

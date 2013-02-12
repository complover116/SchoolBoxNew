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
static int qnum = 3;
int uf = 1;
long myDownloadReference;
AlertDialog dialog;
MediaPlayer mediaPlayer;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main_menu);
		quests[1] = new Question("¬ каком слове верно выделена буква, обозначающа€ ударный гласный звук?", "донельзя","взяла","граждјнство", "нјверх", 3);
		quests[2] = new Question("”кажите пример с ошибкой в образовании формы слова.", "¬ не€сном,  рассе€нном свете ночи открылись перед нами ¬≈Ћ»„≈—“¬≈ЌЌџ≈ и прекрасные перспективы ѕетербурга:  Ќева,  набережна€, каналы, дворцы.","4","60", "70", 3);
		quests[2] = new Question("как вы думаете, в чем заключаетс€ сама€-сама€ главна€ особенность обновлени€, котора€ исправила один очень непри€тный глюк?", "Ќаверное, в этом обновлении по€вилс€ один новый вопрос!","Ќиет! ќбновлени€ не было!","Ѕ≈— ќЌ≈„Ќјя длина вопросов! ѕррокрутка!", "„то?", 3);
		quests[3] = new Question(" ак пишетс€ —...бака?", "сјбака","сџбака","—Ѕака", "сќбака", 4);
		mediaPlayer = MediaPlayer.create(this.getBaseContext(), R.raw.lal);
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
    	Intent intent = new Intent(this, Grammar.class);
    	startActivity(intent);
    }
    
}

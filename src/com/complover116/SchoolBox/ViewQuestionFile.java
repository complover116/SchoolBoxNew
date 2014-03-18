package com.complover116.SchoolBox;

import java.io.File;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ViewQuestionFile extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_question_file);
		((TextView)this.findViewById(R.id.detdesc)).setText(Install.desc[Install.selQ]);
		((TextView)this.findViewById(R.id.name)).setText(Install.tempnames[Install.selQ]);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_question_file, menu);
		return true;
	}
	public void back(View view) {
		MainMenu.ButtonSound.start();
		this.finish();
	}
	public void download(View view) {
		MainMenu.ButtonSound.start();
		Toast.makeText(getApplicationContext(), "Скачиваю файл вопросов...!", Toast.LENGTH_SHORT).show();
    	MainMenu.dltype = 3;
		File file = new File(getExternalFilesDir( null ).getPath()+"/Questions.scb");
		file.delete();
    	   String url = Install.address[Install.selQ];
    	   DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
    	   request.setDescription("SchoolBox question file");
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
   		this.finish();
	}
}

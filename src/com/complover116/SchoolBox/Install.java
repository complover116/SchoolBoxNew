package com.complover116.SchoolBox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Install extends Activity {
	static String tempnames[] = new String[100];
	static String desc[] = new String[100];
	static String address[] = new String[100];
	static int selQ = 0;
	static AlertDialog downloaddialog;
	static ArrayList<String> names;
	ArrayAdapter<String> adapter;
	TextView DetDesc;
    @Override
	public void onDestroy() {
    	super.onDestroy();
		unregisterReceiver(onComplete);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_install);
		
		registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
		// получаем экземпляр элемента ListView
		ListView lv = (ListView)findViewById(R.id.listView1);

		// определяем массив типа String
		tempnames[0] = "КРИТИЧЕСКАЯ ОШИБКА";
		dl();
		
		int i = 0;
		names = new ArrayList<String>();
		while(tempnames[i] != null) {
			names.add(0, tempnames[i]);
			i ++;
		}
		adapter = new ArrayAdapter<String>(this,	android.R.layout.simple_list_item_1, names);
		// используем адаптер данных

		final Install self = this;
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
					long id) {
				Toast.makeText(getApplicationContext(), ((TextView) itemClicked).getText(),
				        Toast.LENGTH_SHORT).show();
				
				selQ = position;
		    	MainMenu.ButtonSound.start();
		    	Intent intent = new Intent(self, ViewQuestionFile.class);
		    	startActivity(intent);
			}
		});
		
		lv.setAdapter(adapter);
	}
	public void back(View view) {
		this.finish();
	}

	public boolean dl(){
   		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Скачиваю список файлов...")
	       .setTitle("Пожалуйста, подождите...");
		builder.setCancelable(false);
		AlertDialog dialogy = builder.create();
		dialogy.show();
    	MainMenu.dltype = 2;
		File file = new File(getExternalFilesDir( null ).getPath()+"/QList.scb");
		file.delete();
		downloaddialog = dialogy;
    	   String url = "https://dl.dropboxusercontent.com/s/6f8b9blb8k59pgl/Questionlist.txt?dl=1&token_hash=AAFCZOGowOCmcVxdqGtZlXyDcd0oenrp32DjoC75PTQNVg";
    	   DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
    	   request.setDescription("SchoolBox question list");
    	   request.setTitle("ScholBox data");
    	   // in order for this if to run, you must use the android 3.2 to compile your app
    	   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
    	       request.allowScanningByMediaScanner();
    	       request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
    	   }
    	   
    	   Uri destination = Uri.fromFile(new File(getExternalFilesDir( null ).getPath()+"/QList.scb"));
    	   
    	   request.setDestinationUri(destination);

    	   // get download service and enqueue file
    	   DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
    	   manager.enqueue(request);
		return true;
    	   
           // User cancelled the dialog
	}
	public boolean refresh() {

		try {
			Log.i("DlStuff", "==========BEGIN!==========");
			File file = new File(getExternalFilesDir( null ).getPath()+"/QList.scb");
			BufferedReader Cin = new BufferedReader(new FileReader(file));
			String templine;
			String tempname = null;
			String tempdesc = null;
			byte DataLine = 0;
			int num = 0;
			while((templine = Cin.readLine()) != null) {
				DataLine ++;
				Log.i("DlStuff", "Processing dataline "+DataLine);
				Log.i("DlStuff", "Text:"+templine);
				switch (DataLine) {
				case 1:
				tempname = templine;
				break;
				case 2:
				tempdesc = templine;
				break;
				case 3:
				tempnames[num] = tempname;
				desc[num] = tempdesc;
				address[num] = templine;
				Log.i("DlStuff", "Registered QFile N"+num);
				num ++;
				DataLine = 0;
				break;
				default:
				Log.e("DlStuff", "Dataline failure!");
				break;
				}
			}
			Log.i("DlStuff", "Reached the end! Conversion taking place...");
			int i = 0;
			names.clear();
			while(tempnames[i] != null) {
				names.add(tempnames[i]);
				i ++;
			}
			Log.i("DlStuff", "Conversion complete! N0:" + names.get(0));
			adapter.notifyDataSetChanged();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	BroadcastReceiver onComplete=new BroadcastReceiver() {
	    public void onReceive(Context ctxt, Intent intent) {
	        // Do Something

	    	if(MainMenu.dltype == 2) {
	    		downloaddialog.dismiss();
	    		refresh();
	    		MainMenu.dltype = -1;
	    	}
	    }
	};
}

package com.complover116.SchoolBox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class Install extends Activity {
	String name[] = new String[100];
	String desc[] = new String[100];
	String address[] = new String[100];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_install);
	}


	public boolean loadlist() {
		
		return true;
	}
}

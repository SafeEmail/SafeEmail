package com.example.lockpatterview;

import com.example.lockpatterview.LockPatterView.OnPatterChangeLister;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.widget.TextView;
import android.app.ActionBar;
import android.app.Activity;

public class MainActivity extends FragmentActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportFragmentManager().beginTransaction().replace(android.R.id.content, PasswordFragment.newInstance(PasswordFragment.TYPE_SETTING)).commit();
	}
}

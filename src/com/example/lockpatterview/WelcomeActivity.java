package com.example.lockpatterview;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

public class WelcomeActivity extends FragmentActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				SharedPreferences sp = getSharedPreferences("sp", Context.MODE_PRIVATE);
				String passwordStr = sp.getString("password", "");
				//没有设置密码
				if(TextUtils.isEmpty(passwordStr)){
					startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
					finish();
				} 
				//密码检察
				else{
					getSupportFragmentManager().beginTransaction().replace(android.R.id.content, PasswordFragment.newInstance(PasswordFragment.TYPE_CHECK)).commit();

				}
				
			}
		}, 1000);
		
	}
}

package com.example.lockpatterview;

import com.example.lockpatterview.LockPatterView.OnPatterChangeLister;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PasswordFragment extends Fragment implements OnPatterChangeLister, OnClickListener {
	
	public static final String TYPE_SETTING = "setting";
	public static final String TYPE_CHECK = "check";

	
	private static final String ARG_TYPE ="type";
	
	private ViewGroup container;
	private LockPatterView lock;
	private TextView text;
	private LinearLayout btnLayout;
	private String passwordStr;
	
	public static PasswordFragment newInstance(String typeStr){
		PasswordFragment fragment = new PasswordFragment();
		Bundle args = new Bundle();
		args.putString(ARG_TYPE, typeStr);
		fragment.setArguments(args);
		return fragment;
	}

	
	
	public void PasswordFragment(){
		
	}
	
	public void onCreate(Bundle saveInstanceState){
		super.onCreate(saveInstanceState);
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup contaniner,Bundle saveInstanceState){
		View contentView = inflater.inflate(R.layout.fragment_password, container,false);
		text = (TextView) contentView.findViewById(R.id.fragment_password_lock_hint);
		lock = (LockPatterView) contentView.findViewById(R.id.fragment_password_lock);
		btnLayout = (LinearLayout) contentView.findViewById(R.id.fragment_password_btn_layout);
		//设置密码
		if(getArguments() != null) {
			if(TYPE_SETTING.equals(getArguments().getString(ARG_TYPE))){
				btnLayout.setVisibility(View.VISIBLE);
			}
		}
		contentView.findViewById(R.id.fragment_password_btn_commit).setOnClickListener(this);
		lock.SetOnPatterChangeLister(this);
		return contentView;
	}



	@Override
	public void onPatterChange(String passwordStr) {
		this.passwordStr = passwordStr;
		if (!TextUtils.isEmpty(passwordStr)) {
			text.setText(passwordStr);
			//密码检察
			if(getArguments() != null) {
				if(TYPE_CHECK.equals(getArguments().getString(ARG_TYPE))){
					SharedPreferences sp = getActivity().getSharedPreferences("sp",Context.MODE_PRIVATE);
					//检察成功
					if(passwordStr.equals(sp.getString("password", ""))) {
						getActivity().startActivity(new Intent(getActivity(),MainActivity.class));
						getActivity().finish();
					}
					//检查失败
					else {
						text.setText("密码错误");
						lock.resetPoint();
					}
				}
			}
		}
		else {
			text.setText("密码错误");
			lock.errorPoint();
			}
	}



	@Override
	public void onPatterStart(boolean isStart) {
		if (isStart) {
			text.setText("请绘制图案");
		}	
	}

 

	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.fragment_password_btn_commit:
			SharedPreferences sp = getActivity().getSharedPreferences("sp", Context.MODE_PRIVATE);
			sp.edit().putString("password", passwordStr).commit();
			getActivity().finish(); 
			break;
		}
	}
	 
}  
    

    

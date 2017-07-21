package com.example.day1_shared_intent;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {
	/**
	 * // 判断是否第一次显示这个界面，一定要先从里面读取数据，做判断，
	 * 如果是第一次启动，肯定没有数据；如果不是第一次启动，肯定保存的是已经更改的数据；
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getData();
		findViewById(R.id.mytext).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this, MainActivity2.class));
			}
		});
	}

	private void getData() {
		// TODO Auto-generated method stub
		SharedPreferences sp = getSharedPreferences("first_start", MODE_PRIVATE);
		boolean b = sp.getBoolean("isfirst", true);
		if (b) {// 是第一次启动，显示text；同时更改第一次启动的标志位为FALSE；
			findViewById(R.id.showtext).setVisibility(View.VISIBLE);
			sp.edit().putBoolean("isfirst", false).commit();
		} else {
			startActivity(new Intent(MainActivity.this, MainActivity2.class));
			finish();
		}
	}

}

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
	 * // �ж��Ƿ��һ����ʾ������棬һ��Ҫ�ȴ������ȡ���ݣ����жϣ�
	 * ����ǵ�һ���������϶�û�����ݣ�������ǵ�һ���������϶���������Ѿ����ĵ����ݣ�
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
		if (b) {// �ǵ�һ����������ʾtext��ͬʱ���ĵ�һ�������ı�־λΪFALSE��
			findViewById(R.id.showtext).setVisibility(View.VISIBLE);
			sp.edit().putBoolean("isfirst", false).commit();
		} else {
			startActivity(new Intent(MainActivity.this, MainActivity2.class));
			finish();
		}
	}

}

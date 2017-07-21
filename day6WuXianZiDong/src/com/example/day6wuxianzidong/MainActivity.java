package com.example.day6wuxianzidong;

import java.util.ArrayList;
import java.util.List;




import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;


public class MainActivity extends Activity {
private ViewPager viewPager;
private RadioGroup radioGroup;
private List<Integer> list;
private Handler handler=new Handler(){
	private int i;

	public void handleMessage(android.os.Message msg) {
		i =viewPager.getCurrentItem();
		viewPager.setCurrentItem(i+1);
	};
};
    @SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        viewPager=(ViewPager) findViewById(R.id.viewpager);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        list = new ArrayList<Integer>();
        
        list.add(R.drawable.a1);
        list.add(R.drawable.a2);
        list.add(R.drawable.a3);
        list.add(R.drawable.a4);
        MyAdapter adapter=new MyAdapter(list, this);
        viewPager.setAdapter(adapter);
        
        new Thread(){
        	public void run() {
        		while (true) {
					try {
						sleep(1000);
						handler.sendEmptyMessage(0);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
        	};
        }.start();
        viewPager.setCurrentItem(list.size()*10000);
        viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				switch (position%list.size()) {
				case 0:
					radioGroup.check(R.id.rbtn1);
					break;
				case 1:
					radioGroup.check(R.id.rbtn2);
					break;
				case 2:
					radioGroup.check(R.id.rbtn3);
					break;
				case 3:
					radioGroup.check(R.id.rbtn4);
					break;
				default:
					break;
				}
			}
			
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int state) {
				// TODO Auto-generated method stub
				
			}
		});

    }

   
}

package com.example.day6wuxianzidong;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class MyAdapter extends PagerAdapter {
private List<Integer> list;
private Context context;

	public MyAdapter(List<Integer> list, Context context) {
	super();
	this.list = list;
	this.context = context;
}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		// TODO Auto-generated method stub
		return view==object;
	}
@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
	ImageView imageView=new ImageView(context);
	imageView.setImageResource(list.get(position%list.size()));//实现无限循环
	imageView.setScaleType(ScaleType.FIT_XY);
	container.addView(imageView);
	
		return imageView;
	}
@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		container.removeView((View) object);
	}
}

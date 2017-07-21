package com.example.day4bujiandonghua;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		img = (ImageView) findViewById(R.id.img);

	}

	public void weiyi(View view) {
		TranslateAnimation ta = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.5f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, -0.5f);
		ta.setDuration(3000);
		ta.setFillAfter(true);
		// ta.setRepeatCount(5);//飞的次数
		// ta.setRepeatMode(Animation.REVERSE);//restart 不来回,reverse 来回飞
		ta.setInterpolator(new AccelerateInterpolator());
		img.setAnimation(ta);
	}

	public void suofang(View view) {
		ScaleAnimation sa = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		sa.setDuration(3000);
		sa.setRepeatCount(5);
		img.setAnimation(sa);
	}

	public void touming(View view) {
		AlphaAnimation aa = new AlphaAnimation(1.0f, 0.0f);
		aa.setDuration(3000);
		img.setAnimation(aa);
	}

	public void xuanzuan(View view) {
		RotateAnimation ra = new RotateAnimation(0.0f, 720.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		ra.setDuration(3000);
		img.setAnimation(ra);
	}

	public void kaishi(View view) {
		AnimationSet as = new AnimationSet(true);

		TranslateAnimation ta = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.5f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, -0.5f);
		ScaleAnimation sa = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		AlphaAnimation aa = new AlphaAnimation(1.0f, 0.0f);
		RotateAnimation ra = new RotateAnimation(0.0f, 720.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);

		as.addAnimation(sa);
		as.addAnimation(aa);
		as.addAnimation(ra);
		as.addAnimation(ta);
		as.setDuration(3000);
		img.setAnimation(as);
	}
}

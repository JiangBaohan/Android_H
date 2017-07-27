package com.example.moothdemo3;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;


public class MainActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.framelayout,new Frame01()).commit();

        //getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new Frame01()).commit();
        RadioGroup radioGroup= (RadioGroup) findViewById(R.id.rg);
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.radio01:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new Frame01()).commit();
                break;
            case R.id.radio02:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new Frame02()).commit();
                break;
            case R.id.radio03:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new Frame03()).commit();
                break;
            case R.id.radio04:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new Frame04()).commit();
                break;
            default:
                break;
        }
    }
}

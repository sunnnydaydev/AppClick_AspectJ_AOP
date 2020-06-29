package com.sunnyday.appclick_aspectj_aop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import com.sunnyday.appclick_aspectj_aop.aspectj.MyAspectJ;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @MyAspectJ
    public void AspectJTest(View view) {
        Log.d(TAG, "AspectJ Test!!!");
        SystemClock.sleep(500);
    }
}


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
        AspectJTest();

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "test!");
            }
        });
    }


    public void AspectJTest() {
        Log.d(TAG, "AspectJ Test!!!");
    }
}


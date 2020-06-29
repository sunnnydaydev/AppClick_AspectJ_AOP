package com.sunnyday.appclick_aspectj_aop.aspectj;

import android.nfc.Tag;
import android.util.Log;
import android.view.View;

import com.sunnyday.appclick_aspectj_aop.MainActivity;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Create by SunnyDay on 18:29 2020/06/29
 */
@Aspect
public class ViewOnClickListenerAspectj {

     //@Pointcut("execution(* android.view.View.OnClickListener.onClick(android.view.View)")
     @Pointcut("execution(void android.view.View.OnClickListener.onClick(..))")
    public void pointCut() {
    }


    @Before("execution(void android.view.View.OnClickListener.onClick(..))")
    public void before(JoinPoint joinPoint) {
        Log.i(MainActivity.TAG, "onViewClickAspectJ: ");
    }
    @Around("execution(void android.view.View.OnClickListener.onClick(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint){
        try {
            proceedingJoinPoint.proceed();
            Log.i(MainActivity.TAG, "around: ");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
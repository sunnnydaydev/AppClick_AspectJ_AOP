package com.sunnyday.appclick_aspectj_aop.aspectj;

import android.util.Log;



import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;


import static com.sunnyday.appclick_aspectj_aop.MainActivity.TAG;

/**
 * Created by zb on 2020/6/22 13:43
 */
@Aspect
public class AspectJTest {
    @Pointcut("execution(* android.view.View.OnClickListener.onClick(android.view.View)")
    //@Pointcut("execution(* com.sunnyday.appclick_aspectj_aop.MainActivity.AspectJTest(..))")
   // @Pointcut("execution(@com.sunnyday.appclick_aspectj_aop.aspectj.MyAspectJ * *(..))") //使用注解的方式
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        Log.d(TAG, "before method execute...");
    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Log.d(TAG, "around method execute...");
        long startTime = System.currentTimeMillis();
        try {
            // around 会默认拦截被切入的方法。可以使用proceed方法手动释放下原方法。
            proceedingJoinPoint.proceed(); // proceedingJoinPoint.proceed()此方法之前的代码会在切点之前执行，此方法之后的代码会在切点之后执行。
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            long endTime = System.currentTimeMillis();
            long during = endTime - startTime;
            Log.d(TAG, "方法耗时：" +during + "ms");
        }

    }

    @After("pointCut()")
    public void after(JoinPoint joinPoint) {
        Log.d(TAG, "after method execute...");

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature(); // 获得切点方法的信息
        String methodName = methodSignature.getName();//获得方法名
        Method method = methodSignature.getMethod();//获得方法的Method 对象

        Class returnType = methodSignature.getReturnType();//返回值类型
        Class declaringType = methodSignature.getDeclaringType();// 方法所在类

        String[] parameterNames = methodSignature.getParameterNames();// 参数名
        Class[] parameterTypes = methodSignature.getParameterTypes();// 参数类型

        Log.d(TAG, "methodName:" + methodName);
        Log.d(TAG, "Method:" + method.getName());
        Log.d(TAG, "returnType:" + returnType.getSimpleName());
        Log.d(TAG, "declaringType:" + declaringType.getSimpleName());

        for (String name : parameterNames) {
            Log.i(TAG, "parameterName: " + name);
        }
        for (Class type : parameterTypes) {
            Log.i(TAG, "parameterTypes: " + type.getSimpleName());
        }

    }

    @AfterReturning("pointCut()")
    public void AfterReturning(JoinPoint joinPoint, Object returnValue) {
        Log.d(TAG, "afterReturning, execution after return value ...");
    }

    @AfterThrowing(value = "pointCut()", throwing = "ex")
    public void afterThrowing(Throwable ex) {
        Log.i(TAG, "afterThrowing: ");
        Log.d(TAG, "ex = " + ex.getMessage());
    }
}

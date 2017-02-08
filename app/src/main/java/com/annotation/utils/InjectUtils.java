package com.annotation.utils;

import android.app.Activity;
import android.view.View;

import com.annotation.core.BindClick;
import com.annotation.core.BindView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by xiaoshuai on 2017/2/8.
 */

public class InjectUtils {
    public static void inject(final Activity activity) {
        //遍历属性,对设置注解的view进行初始化
        Class<Activity> activityClass= (Class<Activity>) activity.getClass();
        Field fields[]=activityClass.getDeclaredFields();
        for(Field field:fields){
            if(field.isAnnotationPresent(BindView.class)){
                int viewId=field.getAnnotation(BindView.class).value();
                View view=activity.findViewById(viewId);
                try {
                    //这一行代码是必须的，否则直接调用set方法不生效
                    field.setAccessible(true);
                    field.set(activity,view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        //遍历方法 将设置注解的方法绑定到相应的view的点击事件中
        Method methods[]=activityClass.getDeclaredMethods();
        for(final Method method:methods){
            BindClick bindClick=method.getAnnotation(BindClick.class);
            if(bindClick!=null){
                int viewId=bindClick.value();
                activity.findViewById(viewId).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            //调用该方法
                            method.setAccessible(true);
                            method.invoke(activity);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }
}

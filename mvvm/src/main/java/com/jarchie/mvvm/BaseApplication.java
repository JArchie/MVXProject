package com.jarchie.mvvm;

import android.app.Application;
import android.content.Context;

import com.jarchie.mvvm.utils.LaunchTime;

import rxhttp.wrapper.param.RxHttp;

/**
 * 作者: 乔布奇
 * 日期: 2020-03-19 20:45
 * 邮箱: jarchie520@gmail.com
 * 描述: Application类
 */
public class BaseApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        LaunchTime.startRecord();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RxHttp.setDebug(true);
    }
}

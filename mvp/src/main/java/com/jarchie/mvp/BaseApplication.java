package com.jarchie.mvp;

import android.app.Application;

import rxhttp.wrapper.param.RxHttp;

/**
 * 作者: 乔布奇
 * 日期: 2020-03-17 09:59
 * 邮箱: jarchie520@gmail.com
 * 描述: Application类
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RxHttp.setDebug(true);
    }
}

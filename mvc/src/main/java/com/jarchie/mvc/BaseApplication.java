package com.jarchie.mvc;

import android.app.Application;

import rxhttp.wrapper.param.RxHttp;

/**
 * 作者: 乔布奇
 * 日期: 2020-03-16 09:21
 * 邮箱: jarchie520@gmail.com
 * 描述: 自定义Application类
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RxHttp.setDebug(true);
    }

}

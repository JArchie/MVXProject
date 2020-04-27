package com.jarchie.mvc.controller;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jarchie.mvc.R;

/**
 * 作者: 乔布奇
 * 日期: 2020-04-07 22:29
 * 邮箱: jarchie520@gmail.com
 * 描述: ClassLoader测试
 */
public class ClassLoaderTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_loader);

        ClassLoader classLoader = getClassLoader();
        if (classLoader != null) {
            Log.i("Jarchie-Current", "classLoader:" + classLoader.toString());
            while (classLoader.getParent() != null) {
                classLoader = classLoader.getParent();
                Log.i("Jarchie-Parent", "classLoader:" + classLoader.toString());
            }
        }
    }
}

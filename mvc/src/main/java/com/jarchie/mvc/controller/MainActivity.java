package com.jarchie.mvc.controller;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jarchie.mvc.R;
import com.jarchie.mvc.adapter.WxArticleAdapter;
import com.jarchie.mvc.constants.Constant;
import com.jarchie.mvc.model.WxArticleBean;
import com.jarchie.mvc.utils.LaunchTime;
import com.jarchie.mvc.view.LoadingView;
import com.rxjava.rxlife.RxLife;

import io.reactivex.android.schedulers.AndroidSchedulers;
import rxhttp.wrapper.param.RxHttp;

/**
 * 作者: 乔布奇
 * 日期: 2020-03-16 09:18
 * 邮箱: jarchie520@gmail.com
 * 描述: MVC模式搭建项目主页
 */
public class MainActivity extends AppCompatActivity {
    private LoadingView mLoadingView;
    private RecyclerView mRecycler;
    private WxArticleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecycler = findViewById(R.id.mRecycler);
        mLoadingView = new LoadingView(this);
        initData();
    }

    //初始化数据
    @SuppressLint("NewApi")
    private void initData() {
        RxHttp.get(Constant.GET_ARTICAL_LIST)
                .asObject(WxArticleBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mLoadingView.show())
                .doFinally(() -> mLoadingView.hide())
                .as(RxLife.as(this))
                .subscribe(wxArticleBean -> {
                    if (wxArticleBean.getData().getDatas().size() > 0) {
                        mAdapter = new WxArticleAdapter(this, wxArticleBean.getData().getDatas());
                        mRecycler.setAdapter(mAdapter);
                        mRecycler.setLayoutManager(new LinearLayoutManager(this));
                        mRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
                        mAdapter.setOnItemClickListener((bean, view, position) -> Toast.makeText(this, "当前点击的是第" + (position + 1) + "个条目", Toast.LENGTH_SHORT).show());
                    }
                }, throwable -> Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show());
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        LaunchTime.endRecord("onWindowFocusChanged");
    }
}

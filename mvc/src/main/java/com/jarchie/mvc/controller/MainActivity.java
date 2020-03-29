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
import com.jarchie.mvc.view.LoadingView;
import com.rxjava.rxlife.RxLife;

import java.util.ArrayList;
import java.util.List;

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
    private WxArticleAdapter mAdapter;
    private List<WxArticleBean.DataBean.DatasBean> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
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
                        mList.addAll(wxArticleBean.getData().getDatas());
                        mAdapter.notifyDataSetChanged();
                    }
                }, throwable -> Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show());
    }

    //初始化点击事件
    private void initListener() {
        mAdapter.setOnItemClickListener((bean, view, position) -> Toast.makeText(this, "当前点击的是第" + (position + 1) + "个条目", Toast.LENGTH_SHORT).show());
    }

    //初始化View
    private void initView() {
        RecyclerView mRecycler = findViewById(R.id.mRecycler);
        mLoadingView = new LoadingView(this);
        if (mAdapter == null) {
            mAdapter = new WxArticleAdapter(this, mList);
            mRecycler.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}

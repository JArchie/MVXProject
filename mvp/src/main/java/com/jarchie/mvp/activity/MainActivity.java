package com.jarchie.mvp.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jarchie.mvp.R;
import com.jarchie.mvp.adapter.WxArticleAdapter;
import com.jarchie.mvp.bean.WxArticleBean;
import com.jarchie.mvp.customview.LoadingView;
import com.jarchie.mvp.presenter.WxArticlePresenter;
import com.jarchie.mvp.view.WxArticleView;

import java.util.List;
/**
 * 作者: 乔布奇
 * 日期: 2020-03-17 09:50
 * 邮箱: jarchie520@gmail.com
 * 描述: 主Activity页面
 */
@SuppressLint("NewApi")
public class MainActivity extends AppCompatActivity implements WxArticleView {
    private RecyclerView mRecycler;
    private LoadingView mLoadingView;
    private WxArticleAdapter mAdapter;
    private WxArticlePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new WxArticlePresenter(this);
        mPresenter.attachView(this);
        initView();
        initData();
    }

    //初始化数据
    private void initData() {
        mPresenter.requestWxArticleData();
    }

    //初始化监听事件
    private void initListener() {
        mAdapter.setOnItemClickListener((bean, view, position) -> Toast.makeText(MainActivity.this, "当前点击的是第" + (position + 1) + "个条目", Toast.LENGTH_SHORT).show());
    }

    //初始化View
    private void initView() {
        mRecycler = findViewById(R.id.mRecycler);
        mLoadingView = new LoadingView(this);
    }

    @Override
    public void onLoadSuccess(List<WxArticleBean.DataBean.DatasBean> mList) {
        if (mAdapter == null) {
            mAdapter = new WxArticleAdapter(this, mList);
            mRecycler.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        initListener();
    }

    @Override
    public void onShowLoading() {
        mLoadingView.show();
    }

    @Override
    public void onDismissLoading() {
        mLoadingView.hide();
    }

    @Override
    public void onLoadError(String errorMsg) {
        mLoadingView.hide();
        Toast.makeText(MainActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadEmpty() {
        //可以显示个空布局，这里暂未处理
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }
}

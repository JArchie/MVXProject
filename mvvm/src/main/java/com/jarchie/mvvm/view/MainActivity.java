package com.jarchie.mvvm.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jarchie.mvvm.R;
import com.jarchie.mvvm.adapter.WxArticleAdapter;
import com.jarchie.mvvm.customview.LoadingView;
import com.jarchie.mvvm.databinding.ActivityMainBinding;
import com.jarchie.mvvm.model.WxArticleBean;
import com.jarchie.mvvm.utils.LaunchTime;
import com.jarchie.mvvm.viewmodel.MainViewModel;

import java.util.List;

/**
 * 作者: 乔布奇
 * 日期: 2020-03-17 09:35
 * 邮箱: jarchie520@gmail.com
 * 描述: 主Activity页面
 */
public class MainActivity extends AppCompatActivity implements MainViewModel.DataListener {
    private LoadingView mLoadingView;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mLoadingView = new LoadingView(this);
        MainViewModel viewModel = new MainViewModel(this,this);
        binding.setViewModel(viewModel);
    }

    @Override
    public void onDataChanged(List<WxArticleBean.DataBean.DatasBean> list) {
        WxArticleAdapter mAdapter = new WxArticleAdapter(this,list);
        binding.mRecycler.setAdapter(mAdapter);
        binding.mRecycler.setLayoutManager(new LinearLayoutManager(this));
        binding.mRecycler.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }

    @Override
    public void onShowLoading() {
        mLoadingView.show();
    }

    @SuppressLint("NewApi")
    @Override
    public void onDismissLoading() {
        mLoadingView.hide();
    }

    @Override
    public void onShowError(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        LaunchTime.endRecord("onWindowFocusChanged");
    }
}
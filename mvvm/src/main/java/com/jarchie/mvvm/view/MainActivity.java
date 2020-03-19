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
import com.jarchie.mvvm.viewmodel.MainViewModel;

import java.util.List;

/**
 * 主Activity页面
 */
public class MainActivity extends AppCompatActivity implements MainViewModel.DataListener {
    private LoadingView mLoadingView;
    private ActivityMainBinding binding;
    private WxArticleAdapter mAdapter;

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
        if (mAdapter == null){
            mAdapter = new WxArticleAdapter(this,list);
            binding.mRecycler.setAdapter(mAdapter);
        }else {
            mAdapter.notifyDataSetChanged();
        }
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

}
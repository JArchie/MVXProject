package com.jarchie.mvc.controller;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jarchie.lib_common.dialog.CommonDialog;
import com.jarchie.lib_common.titlebar.DefaultTitleBar;
import com.jarchie.mvc.R;
import com.jarchie.mvc.adapter.WxArticleAdapter;
import com.jarchie.mvc.constants.Constant;
import com.jarchie.mvc.model.WxArticleBean;
import com.jarchie.mvc.utils.DensityUtil;
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
        new DefaultTitleBar.Builder(this)
                .setTitle("玩安卓")
                .setRightIcon(R.drawable.share)
                .setRightClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,"鸿洋牛逼",Toast.LENGTH_LONG).show();
                    }
                })
                .build();
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
                        mAdapter.setOnItemClickListener((bean, view, position) -> {
                            if (position == 0){
                                CommonDialog dialog = new CommonDialog.Builder(MainActivity.this)
                                        .setContentView(R.layout.dialog_test_0)
                                        .setCancelable(true)
                                        .fromBottom(true)
                                        .fullWidth()
                                        .setText(R.id.mTitle,"Android高级进阶")
                                        .create();
                                dialog.setOnclickListener(R.id.mConfirm, v -> {
                                    Toast.makeText(MainActivity.this,"点击确定了",Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                });
                                dialog.setOnclickListener(R.id.mCancel, v -> {
                                    Toast.makeText(MainActivity.this,"点击取消了",Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                });
                                dialog.show();
                            }else if (position == 1){
                                new CommonDialog.Builder(MainActivity.this)
                                        .setContentView(R.layout.dialog_test_1)
                                        .setWidthAndHeight(DensityUtil.dp2px(300), LinearLayout.LayoutParams.WRAP_CONTENT)
                                        .addDefaultAnimation()
                                        .create()
                                        .show();
                            }else {
                                Toast.makeText(MainActivity.this, "当前点击的是第" + (position + 1) + "个条目", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }, throwable -> Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show());
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        LaunchTime.endRecord("onWindowFocusChanged");
    }
}

package com.jarchie.lib_common.titlebar;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 作者: 乔布奇
 * 日期: 2020-05-06 21:54
 * 邮箱: jarchie520@gmail.com
 * 描述: 头部标题基类
 */
public abstract class AbsTitleBar<P extends AbsTitleBar.Builder.AbsTitleParams> implements ITitleBar {

    private P mParams;
    private View mTitleView;

    public AbsTitleBar(P params) {
        this.mParams = params;
        createAndBindView();
    }

    public P getParams() {
        return mParams;
    }

    public void setText(int viewId, String text) {
        TextView textView = findViewById(viewId);
        if (!TextUtils.isEmpty(text)) {
            textView.setVisibility(View.VISIBLE);
            textView.setText(text);
        }
    }

    public void setIcon(int viewId,int resId){
        ImageView imageView = findViewById(viewId);
        imageView.setImageResource(resId);
    }

    public void setOnClickListener(int viewId, View.OnClickListener listener) {
        findViewById(viewId).setOnClickListener(listener);
    }

    public <T extends View> T findViewById(int viewId) {
        return (T) mTitleView.findViewById(viewId);
    }

    //绑定和创建View
    private void createAndBindView() {
        //处理无id情况，AppCompatActivity可以这样设置
        if (mParams.mParent == null) {
            //获取Activity的根布局
            ViewGroup activityRoot = (ViewGroup) ((Activity) mParams.mContext).findViewById(android.R.id.content);
            mParams.mParent = (ViewGroup) activityRoot.getChildAt(0);
        }
        if (mParams.mParent == null) return;
        //创建View
        mTitleView = LayoutInflater.from(mParams.mContext).inflate(bindLayoutId(), mParams.mParent, false);
        //添加，添加到第0个位置
        mParams.mParent.addView(mTitleView, 0);
        applyView();
    }

    public abstract static class Builder {

        public Builder(Context context, ViewGroup parent) {
        }

        public abstract AbsTitleBar build();

        public static class AbsTitleParams {
            public Context mContext;
            public ViewGroup mParent;

            public AbsTitleParams(Context context, ViewGroup parent) {
                this.mContext = context;
                this.mParent = parent;
            }
        }
    }
}

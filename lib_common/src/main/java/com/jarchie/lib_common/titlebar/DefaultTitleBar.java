package com.jarchie.lib_common.titlebar;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jarchie.lib_common.R;

/**
 * 作者: 乔布奇
 * 日期: 2020-05-06 22:12
 * 邮箱: jarchie520@gmail.com
 * 描述:
 */
public class DefaultTitleBar extends AbsTitleBar<DefaultTitleBar.Builder.DefaultTitleParams> {

    public DefaultTitleBar(DefaultTitleBar.Builder.DefaultTitleParams params) {
        super(params);
    }

    @Override
    public int bindLayoutId() {
        return R.layout.title_bar;
    }

    @Override
    public void applyView() {
        //绑定效果
        setText(R.id.title, getParams().mTitle);
        setText(R.id.right_text, getParams().mRightText);
        setIcon(R.id.right_icon,getParams().mRightIcon);
        setOnClickListener(R.id.back,getParams().mLeftClickListener);
        setOnClickListener(R.id.right_text, getParams().mRightClickListener);
        setOnClickListener(R.id.right_icon, getParams().mRightClickListener);
    }

    public static class Builder extends AbsTitleBar.Builder {
        DefaultTitleParams P;

        public Builder(Context context) {
            super(context, null);
            P = new DefaultTitleParams(context, null);
        }

        public Builder(Context context, ViewGroup parent) {
            super(context, parent);
            P = new DefaultTitleParams(context, parent);
        }

        @Override
        public DefaultTitleBar build() {
            DefaultTitleBar titleBar = new DefaultTitleBar(P);
            return titleBar;
        }

        //设置标题
        public DefaultTitleBar.Builder setTitle(String title) {
            P.mTitle = title;
            return this;
        }

        //设置右侧文本
        public DefaultTitleBar.Builder setRightText(String rightText) {
            P.mRightText = rightText;
            return this;
        }

        //设置右侧图片
        public DefaultTitleBar.Builder setRightIcon(int rightIcon) {
            P.mRightIcon = rightIcon;
            return this;
        }

        //左侧点击事件
        public DefaultTitleBar.Builder setLeftClickListener(View.OnClickListener leftListener) {
            P.mLeftClickListener = leftListener;
            return this;
        }

        //右侧点击事件
        public DefaultTitleBar.Builder setRightClickListener(View.OnClickListener rightListener) {
            P.mRightClickListener = rightListener;
            return this;
        }

        public static class DefaultTitleParams extends DefaultTitleBar.Builder.AbsTitleParams {

            //放置所有效果
            public String mTitle;
            public String mRightText;
            public int mRightIcon;
            public View.OnClickListener mLeftClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //关闭当前的Activity
                    ((Activity) mContext).finish();
                }
            };
            public View.OnClickListener mRightClickListener;

            public DefaultTitleParams(Context context, ViewGroup parent) {
                super(context, parent);
            }
        }
    }
}

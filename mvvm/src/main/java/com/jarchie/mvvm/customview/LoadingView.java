package com.jarchie.mvvm.customview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.jarchie.mvvm.R;
import com.jarchie.mvvm.manager.DialogManager;
import com.jarchie.mvvm.utils.AnimatorUtil;


/**
 * 作者：created by Jarchie
 * 时间：2019-11-12 22:08:21
 * 邮箱：jarchie520@gmail.com
 * 说明：加载提示框
 */
public class LoadingView {

    private DialogView mLoadingView;
    private ImageView iv_loding;
    private TextView tv_loding_text;
    private ObjectAnimator mAnimator;

    public LoadingView(Context mContext) {
        mLoadingView = DialogManager.getInstance().initView(mContext, R.layout.dialog_loding);
        iv_loding = mLoadingView.findViewById(R.id.iv_loding);
        tv_loding_text = mLoadingView.findViewById(R.id.tv_loding_text);
        mAnimator = AnimatorUtil.rotation(iv_loding);
    }

    /**
     * 设置加载的提示文本
     *
     * @param text
     */
    public void setLoadingText(String text) {
        if (!TextUtils.isEmpty(text)) {
            tv_loding_text.setText(text);
        }
    }

    public void show() {
        mAnimator.start();
        setLoadingText("努力加载中...");
        DialogManager.getInstance().show(mLoadingView);
    }

    public void show(String text) {
        mAnimator.start();
        setLoadingText(text);
        DialogManager.getInstance().show(mLoadingView);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void hide() {
        mAnimator.pause();
        DialogManager.getInstance().hide(mLoadingView);
    }

}

package com.jarchie.lib_common.dialog;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * 作者: 乔布奇
 * 日期: 2020-04-26 22:44
 * 邮箱: jarchie520@gmail.com
 * 描述: Dialog View的辅助处理类
 */
class DialogViewHelper {

    private View mContentView = null;
    //WeakReference防止内存泄漏
    private SparseArray<WeakReference<View>> mViews;

    public DialogViewHelper(Context context, int layoutResId) {
        this();
        mContentView = LayoutInflater.from(context).inflate(layoutResId, null);
    }

    public DialogViewHelper() {
        mViews = new SparseArray<>();
    }

    //设置布局
    public void setContentView(View contentView) {
        this.mContentView = contentView;
    }

    //设置文本
    public void setText(int viewId, CharSequence text) {
        TextView textView = getView(viewId);
        if (textView != null) {
            textView.setText(text);
        }
    }

    //设置点击事件
    public void setOnclickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        if (view != null) {
            view.setOnClickListener(listener);
        }
    }

    //获取ContentView
    public View getContentView() {
        return mContentView;
    }

    //通用fv获取控件
    public  <T extends View> T getView(int viewId) {
        WeakReference<View> viewReference = mViews.get(viewId);
        View view = null;
        if (viewReference != null) {
            view = viewReference.get();
        }
        if (view == null) {
            view = mContentView.findViewById(viewId);
            if (view != null) {
                mViews.put(viewId, new WeakReference<>(view));
            }
        }
        return (T) view;
    }
}

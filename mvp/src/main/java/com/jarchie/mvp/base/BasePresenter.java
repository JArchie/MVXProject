package com.jarchie.mvp.base;

import androidx.lifecycle.LifecycleOwner;

import com.rxjava.rxlife.BaseScope;

/**
 * 作者: 乔布奇
 * 日期: 2020-03-17 09:09
 * 邮箱: jarchie520@gmail.com
 * 描述: 抽象出公共的Presenter层
 */
public class BasePresenter<V extends BaseView> extends BaseScope {

    private V mView;

    public BasePresenter(LifecycleOwner owner) {
        super(owner);
    }

    //绑定View
    public void attachView(V view){
        this.mView = view;
    }

    //解绑View
    public void detachView(){
        this.mView = null;
    }

    //判断View是否绑定，在业务请求时调用该方法进行检查
    protected boolean isViewAttached(){
        return mView!=null;
    }

    //获取连接的View
    public V getView(){
        return mView;
    }

}

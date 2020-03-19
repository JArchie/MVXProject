package com.jarchie.mvp.base;

/**
 * 作者: 乔布奇
 * 日期: 2020-03-17 08:55
 * 邮箱: jarchie520@gmail.com
 * 描述: 抽象出公共View层
 */
public interface BaseView {

    //显示加载框
    void onShowLoading();

    //隐藏加载框
    void onDismissLoading();

    //加载失败回调
    void onLoadError(String errorMsg);

    //加载数据为空
    void onLoadEmpty();

}

package com.jarchie.mvp.view;

import com.jarchie.mvp.base.BaseView;
import com.jarchie.mvp.bean.WxArticleBean;

import java.util.List;

/**
 * 作者: 乔布奇
 * 日期: 2020-03-17 09:48
 * 邮箱: jarchie520@gmail.com
 * 描述:
 */
public interface WxArticleView extends BaseView {
    //加载成功
    void onLoadSuccess(List<WxArticleBean.DataBean.DatasBean> mList);
}

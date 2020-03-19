package com.jarchie.mvvm.viewmodel;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.jarchie.mvvm.model.WxArticleBean;

/**
 * 作者: 乔布奇
 * 日期: 2020-03-19 22:01
 * 邮箱: jarchie520@gmail.com
 * 描述: 列表条目数据处理层
 */
public class ItemVIewModel {

    private Context mContext;
    private WxArticleBean.DataBean.DatasBean bean;

    public ItemVIewModel(Context context, WxArticleBean.DataBean.DatasBean bean) {
        this.mContext = context;
        this.bean = bean;
    }

    public void setDatasBean(WxArticleBean.DataBean.DatasBean bean) {
        this.bean = bean;
    }

    //获取标题
    public String getTitle() {
        return TextUtils.isEmpty(bean.getTitle()) ? "暂无" : bean.getTitle();
    }

    //获取来源
    public String getSuperChapterName() {
        return TextUtils.isEmpty(bean.getSuperChapterName()) ? "暂无" : bean.getSuperChapterName();
    }

    //获取推荐人
    public String getChapterName() {
        return TextUtils.isEmpty(bean.getChapterName()) ? "推荐人：暂无" : "推荐人：" + bean.getChapterName();
    }

    //获取链接地址
    public String getLink() {
        return TextUtils.isEmpty(bean.getLink()) ? "地址：暂无" : "地址：" + bean.getLink();
    }

    //条目的点击事件
    public void showToast() {
        Toast.makeText(mContext, "当前点击的是第" + bean.getPosition() + "个条目", Toast.LENGTH_SHORT).show();
    }

}

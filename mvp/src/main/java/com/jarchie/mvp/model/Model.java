package com.jarchie.mvp.model;

import com.jarchie.mvp.bean.WxArticleBean;
import com.jarchie.mvp.constants.Constant;

import io.reactivex.Observable;
import rxhttp.wrapper.param.RxHttp;

/**
 * 作者: 乔布奇
 * 日期: 2020-03-17 10:46
 * 邮箱: jarchie520@gmail.com
 * 描述: 公共的Model类，返回各个请求的Obervable
 */
public class Model {

    //加载文章列表
    public static Observable<WxArticleBean> requestWxArticle(){
        return RxHttp.get(Constant.GET_ARTICAL_LIST)
                .asObject(WxArticleBean.class);
    }

}

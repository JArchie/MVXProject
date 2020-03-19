package com.jarchie.mvp.presenter;

import androidx.lifecycle.LifecycleOwner;

import com.jarchie.mvp.base.BasePresenter;
import com.jarchie.mvp.model.Model;
import com.jarchie.mvp.view.WxArticleView;
import com.rxjava.rxlife.RxLife;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * 作者: 乔布奇
 * 日期: 2020-03-17 09:50
 * 邮箱: jarchie520@gmail.com
 * 描述: 首页列表的数据处理层
 */
public class WxArticlePresenter extends BasePresenter<WxArticleView> {


    public WxArticlePresenter(LifecycleOwner owner) {
        super(owner);
    }

    public void requestWxArticleData() {
        if (isViewAttached()) {
            Model.requestWxArticle()
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(disposable -> getView().onShowLoading())
                    .doFinally(() -> getView().onDismissLoading())
                    .as(RxLife.as(this))
                    .subscribe(wxArticleBean -> {
                        if (wxArticleBean.getData() != null && wxArticleBean.getData().getDatas().size() > 0) {
                            getView().onLoadSuccess(wxArticleBean.getData().getDatas());
                        } else {
                            getView().onLoadEmpty();
                        }
                    }, throwable -> getView().onLoadError(throwable.getMessage()));
        }
    }

}

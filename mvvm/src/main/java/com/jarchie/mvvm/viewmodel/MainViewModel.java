package com.jarchie.mvvm.viewmodel;

import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.LifecycleOwner;

import com.jarchie.mvvm.constants.Constant;
import com.jarchie.mvvm.model.WxArticleBean;
import com.rxjava.rxlife.BaseScope;
import com.rxjava.rxlife.RxLife;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import rxhttp.wrapper.param.RxHttp;

/**
 * 作者: 乔布奇
 * 日期: 2020-03-19 20:44
 * 邮箱: jarchie520@gmail.com
 * 描述: MainActivity的数据处理层
 */
public class MainViewModel extends BaseScope {

    //空布局是否显示
    public ObservableInt emptyVisibility = new ObservableInt(View.GONE);

    //RecyclerView是否显示
    public ObservableInt recyclerVisibility = new ObservableInt(View.GONE);

    //定义数据回调
    private DataListener mDataListener;
    private List<WxArticleBean.DataBean.DatasBean> mList = new ArrayList<>();

    public MainViewModel(LifecycleOwner owner, DataListener dataListener) {
        super(owner);
        this.mDataListener = dataListener;
        loadArticleData();
    }

    //加载数据
    private void loadArticleData() {
        RxHttp.get(Constant.GET_ARTICAL_LIST)
                .asObject(WxArticleBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mDataListener.onShowLoading())
                .doFinally(() -> mDataListener.onDismissLoading())
                .as(RxLife.as(this))
                .subscribe(wxArticleBean -> {
                    if (wxArticleBean.getData() != null && wxArticleBean.getData().getDatas().size() > 0) {
                        emptyVisibility.set(View.GONE);
                        recyclerVisibility.set(View.VISIBLE);
                        mList.clear();
                        mList.addAll(wxArticleBean.getData().getDatas());
                        mDataListener.onDataChanged(mList);
                    } else {
                        emptyVisibility.set(View.VISIBLE);
                        recyclerVisibility.set(View.GONE);
                    }
                }, throwable -> mDataListener.onShowError(throwable.getMessage()));
    }

    //定义数据回调接口
    public interface DataListener {
        void onDataChanged(List<WxArticleBean.DataBean.DatasBean> list);

        void onShowLoading();

        void onDismissLoading();

        void onShowError(String msg);
    }

}

package com.jarchie.mvvm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.jarchie.mvvm.R;
import com.jarchie.mvvm.databinding.ItemLayoutBinding;
import com.jarchie.mvvm.model.WxArticleBean;
import com.jarchie.mvvm.utils.LaunchTime;
import com.jarchie.mvvm.viewmodel.ItemVIewModel;

import java.util.List;

/**
 * 作者：created by Jarchie
 * 时间：2019-11-24 12:40:51
 * 邮箱：jarchie520@gmail.com
 * 说明：列表数据适配器
 */
public class WxArticleAdapter extends RecyclerView.Adapter<WxArticleAdapter.WxArticleHolder> {

    private Context mContext;
    private List<WxArticleBean.DataBean.DatasBean> mList;
    private boolean mHasRecorded;

    public WxArticleAdapter(Context context, List<WxArticleBean.DataBean.DatasBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @NonNull
    @Override
    public WxArticleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_layout, parent, false);
        return new WxArticleHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final WxArticleHolder holder, final int position) {
        if (position ==0 && !mHasRecorded){
            mHasRecorded = true;
            holder.binding.mAllLayout.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    holder.binding.mAllLayout.getViewTreeObserver().removeOnPreDrawListener(this);
                    LaunchTime.endRecord("FirstShow");
                    return true;
                }
            });
        }
        final WxArticleBean.DataBean.DatasBean bean = mList.get(position);
        bean.setPosition(position);
        holder.bindData(bean);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class WxArticleHolder extends RecyclerView.ViewHolder {
        ItemLayoutBinding binding;

        WxArticleHolder(ItemLayoutBinding binding) {
            super(binding.mAllLayout);
            this.binding = binding;
        }

        //绑定数据
        void bindData(WxArticleBean.DataBean.DatasBean bean) {
            if (binding.getViewModel() == null) {
                binding.setViewModel(new ItemVIewModel(mContext,bean));
            } else {
                binding.getViewModel().setDatasBean(bean);
            }
        }
    }

}

package com.jarchie.mvc.adapter;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jarchie.mvc.R;
import com.jarchie.mvc.model.WxArticleBean;
import com.jarchie.mvc.interfaces.OnItemClickListener;

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

    public WxArticleAdapter(Context context, List<WxArticleBean.DataBean.DatasBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    //点击事件的回调
    private OnItemClickListener<WxArticleBean.DataBean.DatasBean> onItemClickListener;

    //设置回调监听
    public void setOnItemClickListener(OnItemClickListener<WxArticleBean.DataBean.DatasBean> listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public WxArticleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WxArticleHolder(LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final WxArticleHolder holder, final int position) {
        final WxArticleBean.DataBean.DatasBean bean = mList.get(position);
        holder.mTitle.setText(Html.fromHtml(TextUtils.isEmpty(bean.getTitle()) ? "暂无" : bean.getTitle())); //标题
        holder.mSource.setText(TextUtils.isEmpty(
                bean.getSuperChapterName()) ? "暂无" : bean.getSuperChapterName()); //来源
        holder.mReferee.setText(TextUtils.isEmpty(
                bean.getChapterName()) ? "推荐人：暂无" : "推荐人：" + bean.getChapterName()); //推荐人
        holder.mLink.setText(TextUtils.isEmpty(bean.getLink()) ? "地址：暂无" : "地址：" + bean.getLink());

        holder.mBottomLayout.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(bean, holder.mBottomLayout, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class WxArticleHolder extends RecyclerView.ViewHolder{

        final TextView mTitle;
        final TextView mSource;
        final TextView mReferee;
        final TextView mLink;
        final RelativeLayout mTopLayout; //折叠View
        final LinearLayout mBottomLayout; //折叠View
        final LinearLayout mAllLayout; //整体View

        WxArticleHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.mTitle);
            mSource = itemView.findViewById(R.id.mSource);
            mReferee = itemView.findViewById(R.id.mReferee);
            mLink = itemView.findViewById(R.id.mLink);
            mTopLayout = itemView.findViewById(R.id.mTopLayout);
            mBottomLayout = itemView.findViewById(R.id.mBottomLayout);
            mAllLayout = itemView.findViewById(R.id.mAllLayout);
        }
    }

}

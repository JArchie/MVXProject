package com.jarchie.lib_common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.jarchie.lib_common.R;

/**
 * 作者: 乔布奇
 * 日期: 2020-04-26 22:42
 * 邮箱: jarchie520@gmail.com
 * 描述: 自定义通用型Dialog
 */
public class CommonDialog extends Dialog {

    private CommonController mController;

    public CommonDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mController = new CommonController(this, getWindow());
    }

    //设置文本
    public void setText(int viewId, CharSequence text) {
        mController.setText(viewId,text);
    }

    //通用fv获取控件
    public  <T extends View> T getView(int viewId) {
        return mController.getView(viewId);
    }

    //设置点击事件
    public void setOnclickListener(int viewId, View.OnClickListener listener) {
        mController.setOnclickListener(viewId,listener);
    }

    //创建内部类构建器
    public static class Builder {
        private final CommonController.CommonParams P;

        public Builder(Context context) {
            this(context, R.style.dialog);
        }

        public Builder(Context context, int themeId) {
            P = new CommonController.CommonParams(context, themeId);
        }

        //设置布局View
        public Builder setContentView(View view) {
            P.mView = view;
            P.mViewLayoutResId = 0;
            return this;
        }

        //设置布局内容LayoutId
        public Builder setContentView(int layoutId) {
            P.mView = null;
            P.mViewLayoutResId = layoutId;
            return this;
        }

        //设置文本
        public Builder setText(int viewId, CharSequence text) {
            P.mTextArray.put(viewId, text);
            return this;
        }

        //设置点击事件
        public Builder setOnClickListener(int viewId, View.OnClickListener listener) {
            P.mClickArray.put(viewId, listener);
            return this;
        }

        //设置是否可以取消
        public Builder setCancelable(boolean cancelable) {
            P.mCancelable = cancelable;
            return this;
        }

        //设置Cancel监听
        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
            P.mOnCancelListener = onCancelListener;
            return this;
        }

        //设置Dismiss监听
        public Builder setOnDismissListener(OnDismissListener onDismissListener) {
            P.mOnDismissListener = onDismissListener;
            return this;
        }

        //设置key监听
        public Builder setOnKeyListener(OnKeyListener onKeyListener) {
            P.mOnKeyListener = onKeyListener;
            return this;
        }

        //配置一些通用参数
        public Builder fullWidth(){
            P.mWidth = ViewGroup.LayoutParams.MATCH_PARENT;
            return this;
        }

        //从底部弹出，是否有动画
        public Builder fromBottom(boolean isAnimation){
            if (isAnimation){
                P.mAnimations = R.style.dialog_from_bottom_anim;
            }
            P.mGravity = Gravity.BOTTOM;
            return this;
        }

        //设置宽高
        public Builder setWidthAndHeight(int width,int height){
            P.mWidth = width;
            P.mHeight = height;
            return this;
        }

        //添加默认动画
        public Builder addDefaultAnimation(){
            P.mAnimations = R.style.dialog_scale_anim;
            return this;
        }

        //自行设置动画
        public Builder setAnimations(int styleAnimation){
            P.mAnimations = styleAnimation;
            return this;
        }

        public CommonDialog create() {
            // Context has already been wrapped with the appropriate theme.
            final CommonDialog dialog = new CommonDialog(P.mContext, P.mThemeResId);
            P.apply(dialog.mController);
            dialog.setCancelable(P.mCancelable);
            if (P.mCancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }
            dialog.setOnCancelListener(P.mOnCancelListener);
            dialog.setOnDismissListener(P.mOnDismissListener);
            if (P.mOnKeyListener != null) {
                dialog.setOnKeyListener(P.mOnKeyListener);
            }
            return dialog;
        }

        public CommonDialog show() {
            final CommonDialog dialog = create();
            dialog.show();
            return dialog;
        }
    }
}

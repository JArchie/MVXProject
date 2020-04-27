package com.jarchie.lib_common.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * 作者: 乔布奇
 * 日期: 2020-04-26 22:43
 * 邮箱: jarchie520@gmail.com
 * 描述: 通用型Dialog构建器
 */
class CommonController {
    private CommonDialog mDialog;
    private Window mWindow;
    private DialogViewHelper mViewHelper;

    public CommonController(CommonDialog dialog, Window window) {
        this.mDialog = dialog;
        this.mWindow = window;
    }

    public void setViewHelper(DialogViewHelper viewHelper) {
        this.mViewHelper = viewHelper;
    }

    //获取Dialog
    public CommonDialog getDialog() {
        return mDialog;
    }

    //设置文本
    public void setText(int viewId, CharSequence text) {
        mViewHelper.setText(viewId,text);
    }

    //通用fv获取控件
    public  <T extends View> T getView(int viewId) {
        return mViewHelper.getView(viewId);
    }

    //设置点击事件
    public void setOnclickListener(int viewId, View.OnClickListener listener) {
        mViewHelper.setOnclickListener(viewId,listener);
    }

    //获取Dialog的Window对象
    public Window getWindow() {
        return mWindow;
    }

    public static class CommonParams {
        public Context mContext;
        public int mThemeResId;
        //点击透明阴影是否能够取消
        public boolean mCancelable = true;
        //dialog Cancel监听
        public DialogInterface.OnCancelListener mOnCancelListener;
        //dialog Dismiss监听
        public DialogInterface.OnDismissListener mOnDismissListener;
        //dialog Key监听
        public DialogInterface.OnKeyListener mOnKeyListener;
        //布局View
        public View mView;
        //布局Layout ID
        public int mViewLayoutResId;
        //存放文本的修改，文本可能有多个，需要使用Map存储，这里选择SparseArray因为它更加高效
        public SparseArray<CharSequence> mTextArray = new SparseArray<>();
        //存放点击事件
        public SparseArray<View.OnClickListener> mClickArray = new SparseArray<>();
        //宽度
        public int mWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
        //动画
        public int mAnimations = 0;
        //位置
        public int mGravity = Gravity.CENTER;
        //高度
        public int mHeight = ViewGroup.LayoutParams.WRAP_CONTENT;


        public CommonParams(Context context, int themeResId) {
            this.mContext = context;
            this.mThemeResId = themeResId;
        }

        /**
         * 绑定和设置参数
         *
         * @param mController
         */
        public void apply(CommonController mController) {
            DialogViewHelper viewHelper = null;
            //设置Dialog的布局
            if (mViewLayoutResId != 0) {
                viewHelper = new DialogViewHelper(mContext, mViewLayoutResId);
            }

            if (mView != null) {
                viewHelper = new DialogViewHelper();
                viewHelper.setContentView(mView);
            }

            if (viewHelper == null) {
                throw new IllegalArgumentException("请设置布局setContentView()");
            }
            //给Dialog设置布局
            mController.getDialog().setContentView(viewHelper.getContentView());

            //设置辅助类
            mController.setViewHelper(viewHelper);

            //设置文本
            int textArraySize = mTextArray.size();
            for (int i = 0; i < textArraySize; i++) {
                mController.setText(mTextArray.keyAt(i), mTextArray.valueAt(i));
            }

            //设置点击事件
            int clickArraySize = mClickArray.size();
            for (int i = 0; i < clickArraySize; i++) {
                mController.setOnclickListener(mClickArray.keyAt(i), mClickArray.valueAt(i));
            }

            //配置自定义效果:全屏，从底部弹出，动画等
            Window window = mController.getWindow();
            //设置位置
            window.setGravity(mGravity);
            //设置动画
            if (mAnimations != 0) {
                window.setWindowAnimations(mAnimations);
            }
            //设置宽高
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = mWidth;
            params.height = mHeight;
            window.setAttributes(params);
        }
    }
}

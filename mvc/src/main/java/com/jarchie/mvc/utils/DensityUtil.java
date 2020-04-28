package com.jarchie.mvc.utils;

import android.content.res.Resources;

/**
 * 作者: 乔布奇
 * 日期: 2020-04-28 22:17
 * 邮箱: jarchie520@gmail.com
 * 描述:
 */
public class DensityUtil {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(float dpValue) {
        return (int) (0.5f + dpValue * Resources.getSystem().getDisplayMetrics().density);
    }
}

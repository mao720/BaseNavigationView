/**
 * Copyright 2017 JessYan
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mjf.navigationview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;

import com.zhy.autolayout.utils.AutoUtils;

/**
 * ================================================
 * 一些框架常用的工具
 * ================================================
 */
public class Utils {

    private Utils() {
        throw new IllegalStateException("you can't instantiate me!");
    }

    /**
     * 获得资源
     */
    public static Resources getResources(Context context) {
        return context.getResources();
    }

    /**
     * 获得字符数组
     */
    public static String[] getStringArray(Context context, int id) {
        return getResources(context).getStringArray(id);
    }

    /**
     * 获得资源id数组
     */
    public static int[] getResIdsArray(Context context, int id) {
        TypedArray ar = getResources(context).obtainTypedArray(id);
        int len = ar.length();
        int[] resIds = new int[len];
        for (int i = 0; i < len; i++)
            resIds[i] = ar.getResourceId(i, 0);
        ar.recycle();
        return resIds;
    }

    public static int getAutoWidth(boolean autoLayoutEnabled, int width) {
        return autoLayoutEnabled ? AutoUtils.getPercentWidthSize(width) : width;
    }

    public static int getAutoHeight(boolean autoLayoutEnabled, int height) {
        return autoLayoutEnabled ? AutoUtils.getPercentHeightSize(height) : height;
    }
}

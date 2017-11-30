/**
  * Copyright 2017 JessYan
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  *      http://www.apache.org/licenses/LICENSE-2.0
  *
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

/**
 * ================================================
 * 一些框架常用的工具
 * <p>
 * Created by JessYan on 2015/11/23.
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public class ArmsUtils {

    private ArmsUtils() {
        throw new IllegalStateException("you can't instantiate me!");
    }

    /**
     * 获得资源
     */
    public static Resources getResources(Context context) {
        return context.getResources();
    }

    /**
     * 得到字符数组
     */
    public static String[] getStringArray(Context context, int id) {
        return getResources(context).getStringArray(id);
    }

    /**
     * 得到资源id数组
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

}

/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.tony.imageloaderlibrary.cache;

import com.tony.imageloaderlibrary.utils.CommonUtils;

import android.graphics.Bitmap;

/**
 * Created by sanyinchen on 16/1/18.
 */
public class MyLruCache implements CacheBitmapAction {
    private android.support.v4.util.LruCache<String, Bitmap> bitmapLruCache;

    public MyLruCache() {
        CommonUtils.log("MyLruCache init");
        int maxmemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        bitmapLruCache = new android.support.v4.util.LruCache<String, Bitmap>(maxmemory / 4) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount() / 1024;

            }
        };
    }

    public Bitmap cacheBitmap(String key, Bitmap bitmap) {
        CommonUtils.log("MyLruCache cacheBitmap");
        return bitmapLruCache.put(CommonUtils.getMD5(key), bitmap);
    }

    public Bitmap getCachrBitmap(String key) {
        return bitmapLruCache.get(CommonUtils.getMD5(key));
    }
}

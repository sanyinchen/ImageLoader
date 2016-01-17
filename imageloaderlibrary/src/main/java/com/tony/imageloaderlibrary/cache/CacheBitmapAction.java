/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.tony.imageloaderlibrary.cache;

import android.graphics.Bitmap;

/**
 * Created by sanyinchen on 16/1/18.
 */
public interface CacheBitmapAction {
    Bitmap cacheBitmap(String key, Bitmap bitmap);

    Bitmap getCachrBitmap(String key);
}

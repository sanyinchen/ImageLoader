/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.tony.imageloaderlibrary;

import com.tony.imageloaderlibrary.http.DownloadConfig;
import com.tony.imageloaderlibrary.http.ImageDownLoad;

import android.graphics.Bitmap;

/**
 * Created by sanyinchen on 16/1/14.
 */
public class ImageLoader {
    private ImageDownLoad imageDownLoad = null;
    private DownloadConfig downloadConfig = null;

    public ImageLoader(DownloadConfig downloadConfig) {
        this.downloadConfig = downloadConfig;
        imageDownLoad = new ImageDownLoad(downloadConfig);
    }

}

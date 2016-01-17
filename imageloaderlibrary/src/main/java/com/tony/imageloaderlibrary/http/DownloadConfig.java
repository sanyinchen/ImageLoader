/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.tony.imageloaderlibrary.http;

import com.tony.imageloaderlibrary.cache.CacheBitmapAction;
import com.tony.imageloaderlibrary.cache.CacheManager;

/**
 * Created by sanyinchen on 16/1/14.
 */
public class DownloadConfig {
    private String url;
    private CacheBitmapAction cacheBitmapAction;

    public CacheBitmapAction getCacheBitmapAction() {
        if (cacheBitmapAction == null) {
            cacheBitmapAction = CacheManager.instance().getLruCache();
        }
        return cacheBitmapAction;
    }

    public String getUrl() {
        return url;
    }

    public DownloadConfig(String url) {

        this.url = url;
    }

    public DownloadConfig buildConfig(CacheBitmapAction cacheBitmapAction) {
        this.cacheBitmapAction = cacheBitmapAction;
        return this;
    }
}

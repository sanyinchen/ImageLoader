/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.tony.imageloaderlibrary.cache;

import java.util.HashMap;

/**
 * Created by sanyinchen on 16/1/18.
 */
public class CacheManager {
    public static final String LURCAHE = "luracahe";
    private HashMap<String, CacheBitmapAction> cacheManager;

    public static CacheManager instance() {
        return CacheManagerInterfance.cacheManager;
    }

    private CacheManager() {
        cacheManager = new HashMap<>();
        cacheManager.put(LURCAHE, new MyLruCache());
    }

    public CacheBitmapAction getService(String service) {
        if (cacheManager == null) {
            return null;
        }
        return cacheManager.get(service);
    }

    public CacheBitmapAction getLruCache() {
        return getService(LURCAHE);
    }

    private static class CacheManagerInterfance {
        private static final CacheManager cacheManager = new CacheManager();
    }
}

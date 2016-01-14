/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.tony.imageloaderlibrary.http;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.tony.imageloaderlibrary.utils.StreamUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by sanyinchen on 16/1/14.
 */
public class ImageDownLoad {
    private DownloadConfig downloadConfig;
    private ImageDownloadListener imageDownloadListener;
    private ExecutorService excutorService =
            Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());

    public ImageDownLoad(DownloadConfig downloadConfig) {
        this.downloadConfig = downloadConfig;
    }

    public void setOnImageDownloadListener(ImageDownloadListener imageDownloadListener) {
        if (imageDownloadListener != null) {
            this.imageDownloadListener = imageDownloadListener;
        }
    }

    public Bitmap downloadImage() {
        final Bitmap[] bitmap = {null};
        excutorService.submit(new Runnable() {
            @Override
            public void run() {
                URLConnection downloadConnection = null;
                InputStream inputStream = null;
                try {
                    URL url = new URL(downloadConfig.getUrl());
                    downloadConnection = url.openConnection();
                    inputStream = downloadConnection.getInputStream();
                    bitmap[0] = BitmapFactory.decodeStream(inputStream);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    StreamUtils.closeFile(inputStream);
                }
            }
        });

        return bitmap[0];
    }

    interface ImageDownloadListener {
        void onSuccess(Bitmap bitmap);

        void onFail(Bitmap bitmap);
    }
}

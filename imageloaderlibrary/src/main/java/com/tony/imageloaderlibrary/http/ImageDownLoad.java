/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.tony.imageloaderlibrary.http;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.tony.imageloaderlibrary.utils.CommonUtils;
import com.tony.imageloaderlibrary.utils.StreamUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

/**
 * Created by sanyinchen on 16/1/14.
 */
public class ImageDownLoad {
    private DownloadConfig downloadConfig;
    private ImageDownloadListener imageDownloadListener;
    //    private ExecutorService excutorService =
    //            Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());

    public ImageDownLoad(DownloadConfig downloadConfig) {
        this.downloadConfig = downloadConfig;
    }

    public void setOnImageDownloadListener(ImageDownloadListener imageDownloadListener) {
        if (imageDownloadListener != null) {
            this.imageDownloadListener = imageDownloadListener;
        }
    }

    public void downloadImage() {
        DownloadThread downloadThread = new DownloadThread(this);
        downloadThread.execute();

    }

    public class DownloadThread extends AsyncTask<String, Long, Bitmap> {
        private WeakReference<ImageDownLoad> owner;

        public DownloadThread(ImageDownLoad owner) {
            this.owner = new WeakReference<ImageDownLoad>(owner);
        }

        @Override
        protected Bitmap doInBackground(String[] params) {
            if (getOwner() == null) {
                return null;
            }
            Bitmap bitmap = null;
            URLConnection downloadConnection = null;
            InputStream inputStream = null;
            try {
                URL url = new URL(getOwner().downloadConfig.getUrl());
                downloadConnection = url.openConnection();
                inputStream = downloadConnection.getInputStream();

                int length = downloadConnection.getContentLength();
                byte[] data = new byte[length];
                byte[] temp = new byte[2 * 1024];
                int now = 0;
                int readLength;
                while ((readLength = inputStream.read(temp)) > 0) {
                    System.arraycopy(temp, 0, data, now, readLength);
                    now += readLength;
                    publishProgress(Long.valueOf(now));
                }
                bitmap = BitmapFactory.decodeByteArray(data, 0, length);
                // BitmapFactory.decodeByteArray()
                // publishProgress(Long.valueOf(0));
                // OutputStreamWriter outputStreamWriter=new OutputStreamWriter(new O);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    StreamUtils.closeFile(inputStream);
                }
            }
            return bitmap;
        }

        @Override
        protected void onProgressUpdate(Long... values) {
            super.onProgressUpdate(values);
            CommonUtils.log(values.length + "onProgressUpdate--" + values[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (getOwner() != null && getOwner().imageDownloadListener != null) {
                if (bitmap == null) {
                    getOwner().imageDownloadListener.onFail();
                }
                if (bitmap != null) {
                    getOwner().imageDownloadListener.onSuccess(bitmap);
                }
            }
        }

        private ImageDownLoad getOwner() {
            if (owner.get() != null) {
                return owner.get();
            }
            return null;
        }

    }

    public interface ImageDownloadListener {
        void onSuccess(Bitmap bitmap);

        void onProcess(int process);

        void onFail();
    }
}

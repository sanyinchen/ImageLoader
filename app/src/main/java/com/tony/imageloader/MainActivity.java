package com.tony.imageloader;

import com.tony.imageloaderlibrary.ImageLoader;
import com.tony.imageloaderlibrary.http.DownloadConfig;
import com.tony.imageloaderlibrary.http.ImageDownLoad;
import com.tony.imageloaderlibrary.utils.CommonUtils;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    String testurl = "http://img4.duitang.com/uploads/item/201404/25/20140425100445_MGZRx.jpeg";
    String testurl1 = "http://i2.tietuku.com/cfe7af7521f13a3d.jpg";
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CommonUtils.log("-----------start---------");
        imageView = (ImageView) findViewById(R.id.test);
        DownloadConfig downloadConfig = new DownloadConfig(testurl1);
        ImageLoader imageLoader = new ImageLoader(downloadConfig);
        imageLoader.build(new DefaultImageDownloadListener() {
            @Override
            public void onSuccess(Bitmap bitmap) {
                 imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onFail() {
                imageView.setBackgroundColor(Color.argb(0xff, 0xff, 0xff, 0x33));
            }
        });
        imageLoader.startDownload();
    }

    public class DefaultImageDownloadListener implements ImageDownLoad.ImageDownloadListener {

        @Override
        public void onSuccess(Bitmap bitmap) {

        }

        @Override
        public void onProcess(int process) {

        }

        @Override
        public void onFail() {

        }
    }
}

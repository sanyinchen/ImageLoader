package com.tony.imageloader;

import com.tony.imageloaderlibrary.ImageLoader;
import com.tony.imageloaderlibrary.http.DownloadConfig;
import com.tony.imageloaderlibrary.http.ImageDownLoad;
import com.tony.imageloaderlibrary.utils.CommonUtils;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String testurl = "http://img4.duitang.com/uploads/item/201404/25/20140425100445_MGZRx.jpeg";
    String testurl1 = "http://i2.tietuku.com/cfe7af7521f13a3d.jpg";
    ImageView imageView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.test);
        DownloadConfig downloadConfig = new DownloadConfig(testurl);
        final ImageLoader imageLoader = new ImageLoader(downloadConfig);
        imageLoader.build(new DefaultImageDownloadListener() {
            @Override
            public void onSuccess(Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onProcess(long process, long total, long perCent) {
                CommonUtils.log("process:" + process + " total:" + total + " perCent:" + perCent + "%");
            }

            @Override
            public void onFail() {
                imageView.setBackgroundResource(R.mipmap.ic_launcher);
            }
        });
        imageLoader.startDownload();

        button = (Button) findViewById(R.id.button_test);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "reset!", Toast.LENGTH_SHORT).show();
                imageLoader.startDownload();
            }
        });
    }

    public class DefaultImageDownloadListener implements ImageDownLoad.ImageDownloadListener {

        @Override
        public void onSuccess(Bitmap bitmap) {

        }

        @Override
        public void onProcess(long process, long total, long perCent) {

        }

        @Override
        public void onFail() {

        }
    }
}

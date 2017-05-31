package com.skyaccessteam.homeapp4;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button bv_start;
    private ProgressBar progressBar;
    private int progressStatus =0;
    private Handler handler = new Handler();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar)findViewById(R.id.myProgressBar);
        bv_start = (Button)findViewById(R.id.bv_start);

        progressStatus =0;

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus <100){
                    progressStatus += 1;
                    handler.post(new Runnable(){
                        public void run(){
                            progressBar.setProgress(progressStatus);

                        }
                    });
                    try {
                        Thread.sleep(25);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        }).start();









    }
}

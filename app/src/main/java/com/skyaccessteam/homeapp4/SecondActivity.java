package com.skyaccessteam.homeapp4;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private WebView webView;
    private WebView webView2;
    TextView tv_SSID_Value;
    TextView passwordValue;
    Button bv_getContent;
    Button bv_getDeviceData;
    Button bv_startApp;
    TextView modelValue;
    TextView firmwareValue;
    TextView dslDriverValue;
    TextView manufacturerValue;
    TextView ssid;
    TextView downSpeedValue;
    TextView upSpeedValue;
    TextView t1;
    TextView t2;
    ImageView hub;
    ProgressBar progressBar;
    Button bv_getGraphs;

    private static final String TAG= "homeAppDebug";

    private int progressStatus =0;
    private Handler handler = new Handler();

    final String baseUrl = "http://192.168.0.1";
    final String baseUrl2 = "http://admin:sky@192.168.0.1/sky_router_status.html";
    final String baseUrl3 = "http://admin:sky@192.168.0.1/sky_wireless_settings.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //tv_SSID_Value = (TextView)findViewById(R.id.tv_SSID_Value);
        manufacturerValue = (TextView)findViewById(R.id.manufacturer_Value);
       passwordValue = (TextView)findViewById(R.id.wifiPass_value);
        modelValue = (TextView)findViewById(R.id.model_Value);
        firmwareValue = (TextView)findViewById(R.id.firmware_Value);
        dslDriverValue = (TextView)findViewById(R.id.dslDriver_Value);
        upSpeedValue = (TextView)findViewById(R.id.US_Value);
        downSpeedValue = (TextView)findViewById(R.id.DS_Value);
        ssid = (TextView)findViewById(R.id.tv_ssid_val);
        bv_getContent = (Button)findViewById(R.id.bv_getContent);
        bv_getDeviceData = (Button)findViewById(R.id.bv_getDeviceData);
        hub = (ImageView) findViewById(R.id.iv_Hub3);
        progressBar = (ProgressBar)findViewById(R.id.myProgressBar2);
        bv_getGraphs = (Button) findViewById(R.id.bv_getGraphs);

        t1 = (TextView)findViewById(R.id.manufacturer_text);
        t1 = (TextView)findViewById(R.id.tv_SSID);
        t1 = (TextView)findViewById(R.id.tv_w_pwd);
        t1 = (TextView)findViewById(R.id.model_text);
        t1 = (TextView)findViewById(R.id.firmware_text);
        t1 = (TextView)findViewById(R.id.dslDriver_text);
        t1 = (TextView)findViewById(R.id.US_text);
        t2 = (TextView)findViewById(R.id.subtittle);

//        Typeface font1 = Typeface.createFromAsset(getAssets(),"exo-light.otf");
//        Typeface font2 = Typeface.createFromAsset(getAssets(),"exo-regular.otf");
////        Typeface font3 = Typeface.createFromAsset(getAssets(),"ComingSoon.ttf");
//        manufacturerValue.setTypeface(font1);
//        modelValue.setTypeface(font1);
//        firmwareValue.setTypeface(font1);
//        dslDriverValue.setTypeface(font1);
//        upSpeedValue.setTypeface(font1);
//        downSpeedValue.setTypeface(font1);
//        ssid.setTypeface(font1);
//
//        t1.setTypeface(font1);
//        t2.setTypeface(font1);

        webView = (WebView)findViewById(R.id.wv_dummy);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(baseUrl2);

        webView2 = (WebView)findViewById(R.id.wv_dummy2);
        webView2.getSettings().setJavaScriptEnabled(true);
        webView2.getSettings().setDomStorageEnabled(true);
        webView2.setWebViewClient(new WebViewClient());
        webView2.loadUrl(baseUrl3);

        delay();


        bv_getContent.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //progressBar.setVisibility(View.VISIBLE);
               // secondProgressBar();

                getTextPag1();
               getTextPage2();
            }
        });

        bv_getDeviceData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this,ThirdActivity.class));
            }
        });



        bv_getGraphs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this,FourthActivity.class));
            }
        });

       // getWifiInfo();
        secondProgressBar();
    }

    private void secondProgressBar(){

        //progressBar.setVisibility(View.VISIBLE);

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
                //startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        }).start();


        //delay();
    }


    private void getWifiInfo() {
        WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        Log.d("wifiInfo", wifiInfo.toString());
        Log.d("SSID",wifiInfo.getSSID());
        //tv_SSID_Value.setText(wifiInfo.getSSID());
    }

    private void login() {
//        String loginJs = "javascript:" +
//                "document.getElementById('user_login').value = 'admin';"  +
//                "document.getElementById('user_password').value = 'password';"  +
//                "document.getElementById('btnLogin').click();";
//
//        webView.loadUrl(loginJs);

    }

    private void getTextPag1() {
        String elementId = "content-body";
        webView.evaluateJavascript(
                "(function() { return document.getElementById('" + elementId + "').textContent; })();",
                new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String html) {
                        String Manufacturer;
                        String Model;
                        String Firmware;
                        String DSL_driver;
                        String SSID;
                        String Upstream;
                        String Downstream;
                        Log.d(TAG, "saludos desde getTextPag1");

                        // --- SSID ---
                        SSID = html.substring(html.indexOf("sky_WirelessAllSSIDs" ) + 50 , html.indexOf("sky_WirelessAllSSIDs") + 61);
                        ssid.setText(SSID);
                        // --- end Manufacturer ---

                        // --- Manufacturer ---
                        Manufacturer = html.substring(html.indexOf("Manufacturer") + 17 , html.indexOf("Manufacturer") + 30);
                        manufacturerValue.setText(Manufacturer);
                        // --- end Manufacturer ---

                        // --- Model ---
                        Model = html.substring(html.indexOf("Model") + 10 , html.indexOf("Model") + 25);
                        modelValue.setText(Model);
                        // --- end Model ---

                        // --- Firmware ---
                        Firmware = html.substring(html.indexOf("Firmware Version") + 29 , html.indexOf("Firmware Version") + 42);
                        firmwareValue.setText(Firmware);
                        // --- end Manufacturer ---

                        // --- DSL Driver ---
                        DSL_driver = html.substring(html.indexOf("DSL" ) + 170 , html.indexOf("DSL") + 183);
                        dslDriverValue.setText(DSL_driver);
                        // --- end Manufacturer ---

                        // --- Upstream Speed ---
                        Upstream = html.substring(html.indexOf("Upstream" ) + 73 , html.indexOf("Upstream") + 77);
                        upSpeedValue.setText(Upstream + " Kbps");
                        // --- end Manufacturer ---

                        // --- Downstream Speed ---
                        Downstream = html.substring(html.indexOf("Downstream" ) + 75 , html.indexOf("Downstream") + 80);
                        downSpeedValue.setText(Downstream + " Kbps");
                        // --- end Manufacturer ---

                        hub.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void getTextPage2() {

        //String elementId = "eapSecInfo";
        String elementId = "wlWpaPsk";
        webView2.evaluateJavascript(
                "(function() { return document.getElementById('" + elementId + "').value; })();",
                new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String html2) {

                        String WIFIPass;
                       //delay();
                        Log.d(TAG, html2);

                        // --- WIFI Pass ---
                        WIFIPass = html2.substring(1, html2.length()-1);
                        passwordValue.setText(WIFIPass);
                        // --- end Manufacturer ---

                    }
                });
    }

    public void delay (){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                //bv_getContent.setVisibility(View.VISIBLE);
                getTextPag1();
                getTextPage2();
                progressBar.setVisibility(View.INVISIBLE);
                bv_getGraphs.setVisibility(View.VISIBLE);

                //buttons[inew][jnew].setBackgroundColor(Color.BLACK);
            }
        }, 5000);


    }



}




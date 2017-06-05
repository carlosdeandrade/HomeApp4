package com.skyaccessteam.homeapp4;

import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class FourthActivity extends AppCompatActivity {

    TextView hola;
    WebView webView;
    TextView nOfNetworks;
    Button button55;
    private static final String TAG= "homeAppDebug";
    private static final int TAG2= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        hola = (TextView)findViewById(R.id.textView55);
        hola.setMovementMethod(new ScrollingMovementMethod());
        button55 = (Button)findViewById(R.id.button55);
        nOfNetworks = (TextView)findViewById(R.id.nOfNetworks);

        final String baseUrl4 = "http://192.168.0.1/sky_wlvis_site_survey.asp";
        final String baseUrl2 = "http://admin:sky@192.168.0.1/sky_router_status.html";
        final String baseUrl5 = "http://192.168.0.1/wlvis/main.js";


        webView = (WebView)findViewById(R.id.wv_dummy3);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(baseUrl4);



        button55.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                getTextPag1();
                //getTextPage2();
            }
        });



    }


    private void getTextPage2() {


        String elementId = "header";
       // webView.
        webView.evaluateJavascript(
                "(function() { return document.getElementById('" + elementId + "').textContent; })();",
                new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String html2) {

                        String WIFIPass;

                        // --- WIFI Pass ---
                       // WIFIPass = html2.substring(1, html2.length()-1);
                        hola.setText(html2);
                        // --- end Manufacturer ---



                    }
                });

    }


    private void getTextPag1() {
        String elementId = "aplistplaceholder";
        webView.evaluateJavascript(
                "(function() { return document.getElementById('" + elementId + "').innerHTML; })();",
                new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String html) {
                        String networkName;
                        String networkAddress;
                        String signal;
                        String SNR;
                        String bandwidth;
                        String centerChannel;
                        String controlChannel;
                        String maxRate;
                        String protocol;
                        String security;
                        String auxHTML = html;
                        String auxHTML2;
                        //Log.d(TAG, html);
                        //Log.d(TAG, auxHTML);

//                        int count =0;
//                        int index;
//                        String [] lineStarts = new String[5];
////
                        System.out.println(" lalalala ");

                        String pattern="class=\\\"network\\\"";
                        String sTemp = html;
                        int counter = 0;

                        while (sTemp.length() > 0) {
                            int index = sTemp.indexOf(pattern);
                            System.out.println("index: " + index);
                            if (index == -1)
                                break;

                            sTemp = sTemp.substring(index + pattern.length(), sTemp.length());
                            System.out.println("sTemp: " + sTemp);
                            counter++;
                        }

                        System.out.println("counter: " + counter);

                        // To print the value of the integer:
                        nOfNetworks.setText(String.valueOf(counter));

                       










                        // --- networkName ---
//                        networkName = html.substring(html.indexOf("u003Ctd align=\\\"left\\\">") + 23 , html.indexOf("\\u003C/td>\\u003Ctd class=\\\"network\\\" "));
//                        hola.setText(networkName);
//                        Log.d(TAG, networkName);
                        // --- end Manufacturer ---

                    }
                });

    }




}

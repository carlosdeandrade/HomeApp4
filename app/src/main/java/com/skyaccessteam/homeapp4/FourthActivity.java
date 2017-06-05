package com.skyaccessteam.homeapp4;

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
                        Log.d(TAG, html);
                        Log.d(TAG, auxHTML);
//                        int index = auxHTML.indexOf("class=\\\"network\\\"");
//                        int count =0;
//                        // tengo que contar la cantidad de redes que tengo
//
//
//
//
//                        while (index != -1) {
//                            count++;
//                            auxHTML = auxHTML.substring(index + 1);
//                            index = auxHTML.indexOf("class=\\\"network\\\"");
//                            //Log.d(TAG2, count);
//                            hola.setText(count);
//
//                        }

                        // --- networkName ---
//                        networkName = html.substring(html.indexOf("u003Ctd align=\\\"left\\\">") + 23 , html.indexOf("\\u003C/td>\\u003Ctd class=\\\"network\\\" "));
//                        hola.setText(networkName);
//                        Log.d(TAG, networkName);
                        // --- end Manufacturer ---

                    }
                });

    }



}

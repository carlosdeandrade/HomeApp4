package com.skyaccessteam.homeapp4;

import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


public class ThirdActivity extends AppCompatActivity {

    TextView value3;
    TextView deviceChannel_Value;
    TextView deviceSSID_Value;
    TextView deviceBSSID_Value;
    TextView deviceMAC_Value;
    TextView deviceIP_Value;
    TextView deviceFreq_Value;
    TextView deviceLinkSpeed_Value;
    TextView deviceNetId_Value;
    TextView deviceRSSI_Value;
    Button bv_getContent;
    Button bv_getDeviceData;
    Button bv_getGraphs;


    String display;
    String freq;
    String linkSpeed;
    String bssid;
    String ssid;
    String netId;
    String ip;
    String mac;
    String rssi;

    ProgressBar progressBar;
    private int progressStatus =0;
    private Handler handler = new Handler();




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        thirdProgressBar();

        //value3 = (TextView)findViewById(R.id.value3);
        deviceSSID_Value = (TextView)findViewById(R.id.deviceSSID_Value);
        deviceBSSID_Value = (TextView)findViewById(R.id.deviceBSSID_Value);
        deviceMAC_Value = (TextView)findViewById(R.id.deviceMAC_Value);
        deviceIP_Value = (TextView)findViewById(R.id.deviceIP_Value);
        deviceFreq_Value = (TextView)findViewById(R.id.deviceFreq_Value);
        deviceLinkSpeed_Value = (TextView)findViewById(R.id.deviceLinkSpeed_Value);
        deviceNetId_Value = (TextView)findViewById(R.id.deviceNetId_Value);
        deviceChannel_Value = (TextView)findViewById(R.id.deviceChannel_Value);
        deviceRSSI_Value = (TextView)findViewById(R.id.deviceRSSI_Value);
        bv_getContent = (Button)findViewById(R.id.bv_getContent);
        bv_getDeviceData = (Button)findViewById(R.id.bv_getDeviceData);
        bv_getGraphs = (Button)findViewById(R.id.bv_getGraphs);
        progressBar = (ProgressBar)findViewById(R.id.myProgressBar3);

        delay();







//        display += "SSID : " + wifiInfo.getSSID() + "RSSI: " + wifiInfo.getRssi() + "Hiden SSID" + wifiInfo.getHiddenSSID() + "\n" +
//        "BSSID: " + wifiInfo.getBSSID() + "MAC Add :" + wifiInfo.getMacAddress() + "\n" +
//        "Frequency: " + wifiInfo.getFrequency() + "IP Add: " + wifiInfo.getIpAddress() + "\n" +
//        "Link Speed: " + wifiInfo.getLinkSpeed() + "Network Id: " + wifiInfo.getNetworkId() + "\n" +
//        "suplicant state" + wifiInfo.getSupplicantState();
//        value3.setText(display);

        //getWifiInfo();


        bv_getDeviceData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThirdActivity.this,ThirdActivity.class));
            }
        });

        bv_getContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThirdActivity.this,SecondActivity.class));
            }
        });

//        bv_getGraphs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(ThirdActivity.this,FourthActivity.class));
//            }
//        });

    }


    private void thirdProgressBar(){


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



    }

    public void delay (){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                //bv_getContent.setVisibility(View.VISIBLE);
                //getTextPag1();
                //getTextPage2();
                progressBar.setVisibility(View.INVISIBLE);
                //deviceIP_Value.setText("hola");
                getWifiInfo();

                //buttons[inew][jnew].setBackgroundColor(Color.BLACK);
            }
        }, 5000);


    }


    private void getWifiInfo() {


        WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();




        ssid +=  wifiInfo.getSSID();
        ssid = ssid.substring(ssid.indexOf("null") + 5 , ssid.length()-1);
        deviceSSID_Value.setText(ssid);

        bssid +=  wifiInfo.getBSSID();
        bssid = bssid.substring(bssid.indexOf("null") + 4 , bssid.length());
        deviceBSSID_Value.setText(bssid.toUpperCase());

        rssi +=  wifiInfo.getRssi();
        rssi = rssi.substring(rssi.indexOf("null") + 4 , rssi.length());
        deviceRSSI_Value.setText(rssi);


        linkSpeed +=  wifiInfo.getLinkSpeed() + " Mbps";
        linkSpeed = linkSpeed.substring(linkSpeed.indexOf("null") + 4 , linkSpeed.length());
        deviceLinkSpeed_Value.setText(linkSpeed);

        freq +=  wifiInfo.getFrequency() + " MHz";
        freq = freq.substring(freq.indexOf("null") + 4 , freq.length());
        deviceFreq_Value.setText(freq);

        //value3.setText("channel 1");

        if (freq.contains("2412")) {
            deviceChannel_Value.setText("1");
        } else { if (freq.contains("2417")) {
            deviceChannel_Value.setText("2");
        } else { if (freq.contains("2422")) {
            deviceChannel_Value.setText("3");
        } else { if (freq.contains("2427")) {
            deviceChannel_Value.setText("4");
        } else { if (freq.contains("2432")) {
            deviceChannel_Value.setText("5");
        } else { if (freq.contains("2437")) {
            deviceChannel_Value.setText("6");
        } else { if (freq.contains("2442")) {
            deviceChannel_Value.setText("7");
        } else { if (freq.contains("2447")) {
            deviceChannel_Value.setText("8");
        } else { if (freq.contains("2452")) {
            deviceChannel_Value.setText("9");
        } else { if (freq.contains("2457")) {
            deviceChannel_Value.setText("10");
        } else { if (freq.contains("2462")) {
            deviceChannel_Value.setText("11");
        } else { if (freq.contains("2467")) {
            deviceChannel_Value.setText("12");
        } else { if (freq.contains("2472")) {
            deviceChannel_Value.setText("13");
        } else { if (freq.contains("2484")) {
            deviceChannel_Value.setText("14");
        } else {  } } } } } } } } } } } }} }






        netId +=  wifiInfo.getNetworkId();
        netId = netId.substring(netId.indexOf("null") + 4 , netId.length());
        deviceNetId_Value.setText(netId);

        ip +=  wifiInfo.getIpAddress();
        ip = ip.substring(ip.indexOf("null") + 4 , ip.length());
        deviceIP_Value.setText(ip);

        mac +=  wifiInfo.getMacAddress();
        mac = mac.substring(mac.indexOf("null") + 4 , mac.length());
        deviceMAC_Value.setText(mac);











    }




}
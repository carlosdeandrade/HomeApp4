package com.skyaccessteam.homeapp4;

import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.FloatProperty;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.numetriclabz.numandroidcharts.ChartData;
import com.numetriclabz.numandroidcharts.MultiLineChart;

import java.util.ArrayList;
import java.util.List;

public class FourthActivity extends AppCompatActivity {

    TextView hola;
    WebView webView;
    TextView nOfNetworks;
    Button button55;
    LineChart lineChart;
    BarChart barChart;
    String[] myStringArray = new String[3];
    MultiLineChart multiLineChart;

    HorizontalBarChart HorChar;

    private static final String TAG= "homeAppDebug";
    private static final int TAG2= 0;


    private float first_X = 50;
    private float first_Y = 230;
    private float end_X = 100;
    private float end_Y = 230;
    private float Max = 50;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        hola = (TextView)findViewById(R.id.textView55);
        hola.setMovementMethod(new ScrollingMovementMethod());
        button55 = (Button)findViewById(R.id.button55);
        nOfNetworks = (TextView)findViewById(R.id.nOfNetworks);
       // lineChart = (LineChart)findViewById(R.id.graph2);
        //barChart = (BarChart)findViewById(R.id.graph);
        multiLineChart = (MultiLineChart) findViewById(R.id.graph);

        final String baseUrl4 = "http://192.168.0.1/sky_wlvis_site_survey.asp";
        final String baseUrl2 = "http://admin:sky@192.168.0.1/sky_router_status.html";
        final String baseUrl5 = "http://192.168.0.1/wlvis/main.js";


        webView = (WebView)findViewById(R.id.wv_dummy3);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(baseUrl4);


//        //---- Starts the Bars Graph
//        ArrayList<BarEntry> entries = new ArrayList<>();
//        entries.add(new BarEntry(104f, 0));
//
//        entries.add(new BarEntry(80f, 1));
//        entries.add(new BarEntry(60f, 2));
//        entries.add(new BarEntry(100f, 3));
//        entries.add(new BarEntry(50f, 4));
//        entries.add(new BarEntry(30f, 5));
//        entries.add(new BarEntry(80f, 6));
//        entries.add(new BarEntry(60f, 7));
//        entries.add(new BarEntry(100f, 8));
//        entries.add(new BarEntry(50f, 9));
//
//        entries.add(new BarEntry(100f, 11));
//        entries.add(new BarEntry(50f, 12));
//        entries.add(new BarEntry(30f, 13));
//
//        BarDataSet dataset = new BarDataSet(entries, "Channels");
//
//        ArrayList<String> labels = new ArrayList<String>();
//        labels.add("1");
//        labels.add("2");
//        labels.add("3");
//        labels.add("4");
//        labels.add("5");
//        labels.add("6");
//        labels.add("7");
//        labels.add("8");
//        labels.add("9");
//        labels.add("10");
//        labels.add("11");
//        labels.add("12");
//        labels.add("13");
//
////        BarChart chart = new BarChart(this);
////        setContentView(chart);
//
//        BarData data = new BarData(labels, dataset);
//        barChart.setData(data);
//
//        barChart.setDescription("2.4GHz Frequency Spectrum");
//
//        barChart.setTouchEnabled(true);
//        barChart.setDragEnabled(true);
//        barChart.setScaleEnabled(true);
//        dataset.setColors(ColorTemplate.JOYFUL_COLORS);
//
//        //---- Ends the Bars Graph








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
                        String auxHTML;
                        String auxHTML2 = html;
                        int counter;
                        int x = 0;
                        int bucky[] = new int[10];
                        String array[] = new String[10];


                        counter = getNOfNetworks(html);

                        // while loop for the number of available networks found

                        float percentage = 0;
                        Float a = null;
                        Float b = null;
                        Float c = null;
                        Float dBm = null;
                        Float bandwidthToChannel = null;

                        // la condicion era while x < counter.. voy a usar x < 3 por ahora
                        while (x < counter) {

                            //System.out.println(" aqui empieza el x igual a " + x);
                            // --- networkName ---
                            //System.out.println(" ------------------------- html1: " + html);
                            networkName = html.substring(html.indexOf("u003Ctd align=\\\"left\\\">") + 23, html.indexOf("\\u003C/td>\\u003Ctd class=\\\"network\\\" "));
                            System.out.println("--------------------------");
                            System.out.println("--------------------------");
                            System.out.println(" network Name: " + networkName);
                            System.out.println("--------------------------");
                            hola.setText(networkName);
                            Log.d(TAG, networkName);
                            // --- end networkName ---

                            // --- signal ---
                            //System.out.println(" ------------------------- html2: " + html);

                            auxHTML = html.substring(html.indexOf("wtht1 ") + 70, html.length());
                            //System.out.println(" ------------------------- auxHATML: " + auxHTML);
                            signal = auxHTML.substring(0, auxHTML.indexOf("\\u003C/td>\\u003Ctd align=\\\"right\\\" style=\\\"padding-right:2%"));
                            // System.out.println(":auxhtml ahora es : "+ auxHTML);
                            System.out.println(":signal es : " + signal);

                            // --- end signal ---


                            // --- bandwidth ---
                            auxHTML = auxHTML.substring(auxHTML.indexOf("u003C/td>\\u003Ctd align=\\\"right\\\" style=\\\"padding-right:2%;\\\">") + 66, auxHTML.length());
                            //System.out.println(" auxHTML desde el bandwidth1 es "+ auxHTML);
                            auxHTML = auxHTML.substring(auxHTML.indexOf("003C/td>\\u003Ctd align=\\\"right\\\" style=\\\"padding-right:2%;\\\">") + 61, auxHTML.length());
                            bandwidth = auxHTML.substring(0, auxHTML.indexOf("\\u003C/td>\\u003Ctd align=\\\"right\\\" style=\\\"padding-right:2%"));
                            //System.out.println(" auxHTML desde el bandwidth2 es "+ auxHTML);
                            System.out.println("bandwidth: " + bandwidth);
                            // --- end bandwidth ---

                            // --- center channel ---
                            auxHTML = auxHTML.substring(auxHTML.indexOf("u003Ctd align=\\\"right\\\" style=\\\"padding-right:2%;\\\">") + 52, auxHTML.length());
                            centerChannel = auxHTML.substring(0, auxHTML.indexOf("\\u003C/td>\\u003Ctd align=\\\"right\\\" style=\\\"padding-right:2%"));
                            //System.out.println(" auxHTML desde center channel es "+ auxHTML);
                            System.out.println("center channel: " + centerChannel);
                            // --- end center channel ---
//                            System.out.println("auxHTML2 " + auxHTML2);
//                            System.out.println("html " + html);
//                            System.out.println("auxHTML " + auxHTML);



//                            System.out.println("dentro de ciclo");
//
//                            System.out.println("VALORES ANTES DE EMPEZAR ESTA SEGUNDA PARTE: -----------------------");
//                            System.out.println("bandwidthToChannel: " + bandwidthToChannel);
//                            System.out.println("bandwidth: " + bandwidth);
//                            System.out.println("a: " + a);
//                            System.out.println("b: " + b);
//                            System.out.println("c: " + c);
//                            System.out.println("dBm: " + dBm);
//                            System.out.println("percentage: " + percentage);
//                            System.out.println("---------------- FIN -----------------------");
//                            System.out.println(" parse float bandiwidth " + Float.parseFloat(bandwidth));
//                            bandwidthToChannel = null;
//                            dBm = null;
//                            System.out.println(" bandwidth to Channel null " + bandwidthToChannel);
//                            bandwidthToChannel = Float.parseFloat(bandwidth) / 5;
//                            System.out.println(" bandwidth to Channel dividido entre cinco" + bandwidthToChannel);
//                            b = Float.parseFloat(centerChannel);
//                            System.out.println(" valor de center channel" + centerChannel);
//                            System.out.println(" valor de b" + b);
//                            a = Float.parseFloat(centerChannel) - (bandwidthToChannel / 2);
//                            System.out.println(" valor de center channel" + centerChannel);
//                            System.out.println(" valor de a" + a);
//                            c = Float.parseFloat(centerChannel) + (bandwidthToChannel / 2);
//                            System.out.println(" valor de center channel" + centerChannel);
//                            System.out.println(" valor de c" + c);
//                            System.out.println(" dBm  null " + dBm);
//                            System.out.println(" signal antes del float to parse " + signal);
//                            dBm = Float.parseFloat(signal);
//                            System.out.println(" valor de signal" + signal);
//                            System.out.println(" valor de dBm" + dBm);
//                            percentage = (dBm + 100);
//                            System.out.println(" valor de percentage" + percentage);
//
//
//                            System.out.println("el valor de a es: " + a);
//                            System.out.println("el valor de b es: " + b);
//                            System.out.println("el valor de c es: " + c);
//
//
//                            System.out.println("el valor de dBm es: " + dBm);
//                            System.out.println("el valor de percentage es: " + percentage);
//                            System.out.println("----------------------------------------------");
                            html=auxHTML;
                            x = x+1;
                        }

                        //System.out.println("SALI de ciclo");


//                        // Aqui empieza el ploteo ----------------------------------------------------------------

//                        List<ChartData> value1 = new ArrayList<>();
//                        value1.add(new ChartData(2f, 1f)); //values.add(new ChartData(y,x));
//                        value1.add(new ChartData(3f, 3f));
//                        value1.add(new ChartData(5f, 5f));
//                        value1.add(new ChartData(8f, 8f));
//                        value1.add(new ChartData(9f, 9f));
//                        value1.add(new ChartData(12f, 12f));

                        // desde aqui descomentar
                           // Float y;

//                           // Float u = 3f;
//
//                            // traido de thirdActivity netId +=  wifiInfo.getNetworkId();
//
//
//                            System.out.println(" aqui termina el x igual a " + x);
//                            System.out.println(" ahora x ++ ");
//                            x = x+1;
//                            System.out.println(" nuevo valor de x: " + x);
//
//
//                        List<ChartData> value2 = new ArrayList<>();
//                        value2.add(new ChartData(0f, a)); //values.add(new ChartData(y,x));
//                        value2.add(new ChartData(percentage, b));
//                        value2.add(new ChartData(0f, c));
//
//                        List<ChartData> value3 = new ArrayList<>();
//                        // value3.add(new ChartData(value1));
//                        value3.add(new ChartData(value2));
//                        // value3.add(new ChartData(value4));
//
//                        multiLineChart.setData(value3);
//
//
//
//
//                        // Defining X-axis labels
//                        List<String> h_lables = new ArrayList<>();
////                        h_lables.add(" ");
////                        h_lables.add(" ");
//                        h_lables.add("1");
//                        h_lables.add("2");
//                        h_lables.add("3");
//                        h_lables.add("4");
//                        h_lables.add("5");
//                        h_lables.add("6");
//                        h_lables.add("7");
//                        h_lables.add("8");
//                        h_lables.add("9");
//                        h_lables.add("10");
//                        h_lables.add("11");
//                        h_lables.add("12");
//                        h_lables.add("13");
//
//                        multiLineChart.setHorizontal_label(h_lables);
//
//
//                        multiLineChart.setCircleSize(1f);
//
//                        delay();

                        // hasta aqui descomentar
                        // multiLineChart.setGesture(true);
//                        // Aqui termina el ploteo ----------------------------------------------------------------


                        // }


                    }
                });

    }

     public int getNOfNetworks(String html){
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

         System.out.println("imprimiendo DESDE el metodo counter: " + counter);

         // To print the value of the integer:
         nOfNetworks.setText(String.valueOf(counter));
         return counter;

     }

    public void delay (){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                //prueba.setText("hola");
                //buttons[inew][jnew].setBackgroundColor(Color.BLACK);
            }
        }, 5000);

    }


//    public int getPowerPercentage(int power) {
//        int i = 0;
//        int MIN_DBM = -100;
//        if (power <= MIN_DBM) {
//            i = 0;
//        } else {
//            i = 100 + power;
//        }
//
//        return i;
//    }







}

package com.skyaccessteam.homeapp4;

import android.graphics.Paint;
import android.graphics.Path;
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

import java.util.ArrayList;

public class FourthActivity extends AppCompatActivity {

    TextView hola;
    WebView webView;
    TextView nOfNetworks;
    Button button55;
    LineChart lineChart;
    BarChart barChart;
    String[] myStringArray = new String[3];

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
        lineChart = (LineChart)findViewById(R.id.graph2);
        barChart = (BarChart)findViewById(R.id.graph);

        final String baseUrl4 = "http://192.168.0.1/sky_wlvis_site_survey.asp";
        final String baseUrl2 = "http://admin:sky@192.168.0.1/sky_router_status.html";
        final String baseUrl5 = "http://192.168.0.1/wlvis/main.js";


        webView = (WebView)findViewById(R.id.wv_dummy3);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(baseUrl4);


        //---- Starts the Bars Graph
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(104f, 0));

        entries.add(new BarEntry(80f, 1));
        entries.add(new BarEntry(60f, 2));
        entries.add(new BarEntry(100f, 3));
        entries.add(new BarEntry(50f, 4));
        entries.add(new BarEntry(30f, 5));
        entries.add(new BarEntry(80f, 6));
        entries.add(new BarEntry(60f, 7));
        entries.add(new BarEntry(100f, 8));
        entries.add(new BarEntry(50f, 9));

        entries.add(new BarEntry(100f, 11));
        entries.add(new BarEntry(50f, 12));
        entries.add(new BarEntry(30f, 13));

        BarDataSet dataset = new BarDataSet(entries, "Channels");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("1");
        labels.add("2");
        labels.add("3");
        labels.add("4");
        labels.add("5");
        labels.add("6");
        labels.add("7");
        labels.add("8");
        labels.add("9");
        labels.add("10");
        labels.add("11");
        labels.add("12");
        labels.add("13");

//        BarChart chart = new BarChart(this);
//        setContentView(chart);

        BarData data = new BarData(labels, dataset);
        barChart.setData(data);

        barChart.setDescription("2.4GHz Frequency Spectrum");

        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
        dataset.setColors(ColorTemplate.JOYFUL_COLORS);

        //---- Ends the Bars Graph


//        double x=0;
//        int numDataPoints = 1000;
//
//        for (int i=0; i<numDataPoints; i++) {
//            float sinFunction = Float.parseFloat(String.valueOf(Math.sin(x)));
//            float cosFunction = Float.parseFloat(String.valueOf(Math.cos(x)));
//            x=x+0.1;
//            yAXESsin.add(new Entry(sinFunction,i));
//            yAXEScos.add(new Entry(cosFunction,i));
//
//        }






//
//
//            public SinWave(Context context, AttributeSet attrs) {
//                super(context, attrs);
//            }
//
//            @Override
//            protected void onDraw(Canvas canvas) {
//                super.onDraw(canvas);
//                Paint paint = new Paint() {
//                    {
//                        setStyle(Paint.Style.STROKE);
//                        setStrokeCap(Paint.Cap.ROUND);
//                        setStrokeWidth(0.7f);
//                        setAntiAlias(true);
//                        setColor(0xFFFF00FF);
//                    }
//                };
//                final Path path = new Path();
//                path.moveTo(first_X, first_Y);
//                path.quadTo((first_X + end_X)/2, Max, end_X, end_Y);
//                canvas.drawPath(path, paint);
//            }
//









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
                        String auxHTML2=html;
                        int counter;
                        //int x =0;

                        System.out.println(" lalalala ");
                        counter = getNOfNetworks(html);

                        //while ( x < counter) {
                            // --- networkName ---
                            networkName = html.substring(html.indexOf("u003Ctd align=\\\"left\\\">") + 23 , html.indexOf("\\u003C/td>\\u003Ctd class=\\\"network\\\" "));
                            System.out.println(" network Name: " + networkName);
                            hola.setText(networkName);
                            Log.d(TAG, networkName);
                             // --- end networkName ---

                            // --- signal ---
                            auxHTML = html.substring(html.indexOf("u003Cdiv class=\\\"wtht1 brcm\\\" style=\\\"width:80px;\\\">\\u003C/div>\\u003C/div>\\u003C/span>") +86 , html.length());
                            signal = auxHTML.substring(0, auxHTML.indexOf("\\u003C/td>\\u003Ctd align=\\\"right\\\" style=\\\"padding-right:2%"));
                       // System.out.println(":auxhtml ahora es : "+ auxHTML);
                        System.out.println(":signal es : "+ signal);

                            // --- end signal ---


                            // --- bandwidth ---
                            auxHTML = auxHTML.substring(auxHTML.indexOf("u003C/td>\\u003Ctd align=\\\"right\\\" style=\\\"padding-right:2%;\\\">")+66 , auxHTML.length());
                        //System.out.println(" auxHTML desde el bandwidth1 es "+ auxHTML);
                        auxHTML = auxHTML.substring(auxHTML.indexOf("003C/td>\\u003Ctd align=\\\"right\\\" style=\\\"padding-right:2%;\\\">") + 61, auxHTML.length());
                            bandwidth = auxHTML.substring(0, auxHTML.indexOf("\\u003C/td>\\u003Ctd align=\\\"right\\\" style=\\\"padding-right:2%"));
                            //System.out.println(" auxHTML desde el bandwidth2 es "+ auxHTML);
                            System.out.println("bandwidth: "+ bandwidth);
                            // --- end bandwidth ---

                            // --- center channel ---
                            auxHTML = auxHTML.substring(auxHTML.indexOf("u003Ctd align=\\\"right\\\" style=\\\"padding-right:2%;\\\">")+52 , auxHTML.length());
                            centerChannel = auxHTML.substring(0, auxHTML.indexOf("\\u003C/td>\\u003Ctd align=\\\"right\\\" style=\\\"padding-right:2%"));
                            //System.out.println(" auxHTML desde center channel es "+ auxHTML);
                            System.out.println("center channel: "+ centerChannel);
                            // --- end center channel ---
                        System.out.println("todo el string: "+ auxHTML2);


//                        int i;
//                        for (i = 1; i <= 3; i++) {
//                            int x = 0;
//                            x += (i * 3);
//                            array.push(x);
//
//                        }
//                        System.out.println(" eeee "+ );


                           // x++;

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







}

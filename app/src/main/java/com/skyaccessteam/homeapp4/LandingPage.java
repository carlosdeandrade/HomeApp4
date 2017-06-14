package com.skyaccessteam.homeapp4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LandingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        TextView description1 = (TextView) findViewById(R.id.description1);
        Button hubButton = (Button)findViewById(R.id.hubButton);
        Button mobileButton = (Button)findViewById(R.id.mobileButton);

        //description1.bringToFront();


        hubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LandingPage.this,SecondActivity.class));
            }
        });

        mobileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LandingPage.this,ThirdActivity.class));
            }
        });

    }
}

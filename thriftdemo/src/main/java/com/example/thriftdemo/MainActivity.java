package com.example.thriftdemo;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;

import com.example.thriftdemo.client.ThriftClient;
import com.example.thriftdemo.service.ThriftService;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        (findViewById(R.id.kehuduan)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        (new ThriftClient()).start();
                    }
                }).start();
            }
        });
        (findViewById(R.id.fuwuduan)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            (new ThriftService()).start();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        });
    }
}


package com.example.testnetty;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xj.netty.googleProtocolBuffer.server.MyServer;


public class FuWuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fu_wu);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    (new MyServer()).start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

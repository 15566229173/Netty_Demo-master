package com.example.testnetty;

import android.app.Activity;
import android.os.Bundle;

import com.xj.netty.googleProtocolBuffer.client.MyClient;


public class KeHuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ke_hu);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    (new MyClient()).start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

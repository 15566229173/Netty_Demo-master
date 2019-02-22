package com.example.testnetty;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.xj.netty.googleProtocolBuffer.PtotcolBufferTest;
import com.xj.netty.serviceHttp.TestServer;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        (findViewById(R.id.kehuduan)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,KeHuActivity.class));
//                (new PtotcolBufferTest()).start();
            }
        });
        (findViewById(R.id.fuwuduan)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,FuWuActivity.class));
            }
        });
    }
}

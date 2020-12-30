package com.baidu.flutter.flutter_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.native_page);

        Button button1 = findViewById(R.id.open_activity);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MyFlutterFragmentActivity.class));
            }
        });

        Button button2 = findViewById(R.id.open_fragment1);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FlutterFragmentPageActivity1.class));
            }
        });

        Button button3 = findViewById(R.id.open_fragment2);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FlutterFragmentPageActivity2.class));
            }
        });

    }
}

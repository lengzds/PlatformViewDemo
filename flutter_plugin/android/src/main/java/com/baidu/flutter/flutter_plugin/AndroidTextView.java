package com.baidu.flutter.flutter_plugin;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import io.flutter.plugin.platform.PlatformView;

class AndroidTextView implements PlatformView {

    private TextView textView;

    AndroidTextView(Context context) {
        textView = new TextView(context);
        textView.setText("android端TextView");
        textView.setTextColor(Color.parseColor("#FF000000"));
        textView.setGravity(Gravity.CENTER);
    }

    @Override
    public View getView() {
        return textView;
    }

    @Override
    public void dispose() {

    }
}

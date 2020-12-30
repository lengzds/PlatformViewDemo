package com.baidu.flutter.flutter_plugin;

import android.content.Context;

import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MessageCodec;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugin.platform.PlatformView;
import io.flutter.plugin.platform.PlatformViewFactory;

class SharePlatformViewFactory extends PlatformViewFactory {
    public SharePlatformViewFactory() {
        super(StandardMessageCodec.INSTANCE);
    }

    @Override
    public PlatformView create(Context context, int id, Object args) {
        return new AndroidTextView(context);
    }
}

package com.baidu.flutter.flutter_app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import io.flutter.embedding.android.FlutterEngineConfigurator;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.plugins.util.GeneratedPluginRegister;

public class FlutterFragmentActivity2 extends FragmentActivity implements FlutterEngineConfigurator {
    DemoCollectionPagerAdapter demoCollectionPagerAdapter;
    ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flutter_viewpager);

        demoCollectionPagerAdapter = new DemoCollectionPagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(demoCollectionPagerAdapter);
        viewPager.setOffscreenPageLimit(3);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        GeneratedPluginRegister.registerGeneratedPlugins(flutterEngine);
    }

    @Override
    public void cleanUpFlutterEngine(@NonNull FlutterEngine flutterEngine) {

    }
}

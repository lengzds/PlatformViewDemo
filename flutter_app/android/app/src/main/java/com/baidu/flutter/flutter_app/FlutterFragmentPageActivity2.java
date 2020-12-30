package com.baidu.flutter.flutter_app;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class FlutterFragmentPageActivity2 extends FragmentActivity {
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
}

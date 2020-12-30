package com.baidu.flutter.flutter_app;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import java.util.ArrayList;
import java.util.List;

import io.flutter.embedding.android.FlutterFragment;

// Since this is an object collection, use a FragmentStatePagerAdapter,
// and NOT a FragmentPagerAdapter.
public class DemoCollectionPagerAdapter extends FragmentPagerAdapter {
    private List<FlutterFragment> fragments = new ArrayList<>();
    int index = 4;

    public DemoCollectionPagerAdapter(FragmentManager fm) {
//        super(fm);
        // 这个参数是1.1才有的，在1.0的时候跟不设置一样，会有问题
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        for (int i = 0; i < index; i++) {
            FlutterFragment fragment = new FlutterFragment.NewEngineFragmentBuilder().build();
            fragments.add(fragment);
        }
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int index) {
        return fragments.get(index);
    }

    @Override
    public long getItemId(int position) {
        return fragments.get(position).hashCode();
    }
}
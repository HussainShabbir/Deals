package com.example.hussainshabbir.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.RecyclerView;

import com.example.hussainshabbir.aynctask.Network;
import com.example.hussainshabbir.fragments.SearchTabFragment;
import com.example.hussainshabbir.fragments.TabFragment;
import com.example.hussainshabbir.selectdeals.Product;

import java.util.List;

/**
 * Created by HussainShabbir on 5/30/17.
 */

public class FragmentPageAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    List<Product> list;

    public FragmentPageAdapter(FragmentManager fm, int mNumOfTabs) {
        super(fm);
        this.mNumOfTabs = mNumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new TabFragment();
                break;
            case 1:
                fragment = new SearchTabFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

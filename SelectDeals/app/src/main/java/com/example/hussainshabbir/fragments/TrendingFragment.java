package com.example.hussainshabbir.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.hussainshabbir.adapter.MyRecyclerViewAdapter;
import com.example.hussainshabbir.aynctask.Network;
import com.example.hussainshabbir.classInterface.ListManager;
import com.example.hussainshabbir.classInterface.RecyclerViewClickListener;
import com.example.hussainshabbir.selectdeals.Product;
import com.example.hussainshabbir.selectdeals.R;
import com.example.hussainshabbir.selectdeals.WebViewActivity;

import java.util.List;

import static android.R.id.list;

/**
 * Created by HussainShabbir on 5/30/17.
 */

public class TrendingFragment extends Fragment implements ListManager, RecyclerViewClickListener {
    private Network network;
    private RecyclerView rv;
    private List storedList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.trending_fragment,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        rv = (RecyclerView)getView().findViewById(R.id.recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        network = new Network(TrendingFragment.this,null,null);
        network.execute("http://api.walmartlabs.com/v1/trends?format=json&apiKey=2kss5eyjk2w7zkzpqmd68ts9");
    }

    public void updateListView(List list) {
        this.storedList = list;
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(getActivity(),list,TrendingFragment.this);
        rv.setAdapter(adapter);
    }

    @Override
    public void rowViewDidClick(View view) {
        if (storedList.size() == 0) {
            return;
        }
        int itemPosition = rv.getChildLayoutPosition(view);
        Product product = (Product) storedList.get(itemPosition);
        String productUrl = product.getProductUrl();
        Intent intent = new Intent(getActivity(),WebViewActivity.class);
        intent.putExtra("productUrl",productUrl);
        startActivity(intent);
    }
}

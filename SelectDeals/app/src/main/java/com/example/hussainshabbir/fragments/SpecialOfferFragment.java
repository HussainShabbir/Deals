package com.example.hussainshabbir.fragments;

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
import com.example.hussainshabbir.selectdeals.Product;
import com.example.hussainshabbir.selectdeals.R;

import java.util.List;

/**
 * Created by HussainShabbir on 6/1/17.
 */

public class SpecialOfferFragment extends Fragment implements ListManager {
    Network network;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.special_offer_fragment,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        network = new Network(null,null,SpecialOfferFragment.this);
        network.execute("http://api.walmartlabs.com/v1/paginated/items?format=json&specialOffer=specialbuy&apiKey=2kss5eyjk2w7zkzpqmd68ts9");
    }

    @Override
    public void updateListView(List<Product> list) {
        RecyclerView rv = (RecyclerView)getView().findViewById(R.id.recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(getActivity(),list);
        rv.setAdapter(adapter);
    }
}

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

/**
 * Created by HussainShabbir on 6/1/17.
 */

public class SpecialOfferFragment extends Fragment implements ListManager,RecyclerViewClickListener {
    Network network;
    private RecyclerView rv;
    private List storedList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.special_offer_fragment,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        rv = (RecyclerView)getView().findViewById(R.id.recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        network = new Network(null,null,SpecialOfferFragment.this);
        network.execute("http://api.walmartlabs.com/v1/paginated/items?format=json&specialOffer=specialbuy&apiKey=2kss5eyjk2w7zkzpqmd68ts9");
    }

    @Override
    public void updateListView(List list) {
        this.storedList = list;
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(getActivity(),list,SpecialOfferFragment.this);
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

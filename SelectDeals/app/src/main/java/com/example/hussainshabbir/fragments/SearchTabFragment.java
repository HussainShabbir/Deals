package com.example.hussainshabbir.fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hussainshabbir.adapter.MyRecyclerViewAdapter;
import com.example.hussainshabbir.aynctask.Network;
import com.example.hussainshabbir.classInterface.ListManager;
import com.example.hussainshabbir.classInterface.RecyclerViewClickListener;
import com.example.hussainshabbir.selectdeals.Product;
import com.example.hussainshabbir.selectdeals.R;
import com.example.hussainshabbir.selectdeals.WebViewActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by HussainShabbir on 5/31/17.
 */

public class SearchTabFragment extends Fragment implements ListManager, RecyclerViewClickListener {
    private Network network;
    private SearchView searchView;
    private String searchQuery;
    private boolean loading = true;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    private int prevIndex = 10, nextIndex = 20;
    LinearLayoutManager linearLayoutManager;
    RecyclerView rv;
    private List storedList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_tab_fragment,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        rv = (RecyclerView)getView().findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(linearLayoutManager);
        recyclerViewDidScrolled();
        searchView = (SearchView)getView().findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchQuery = query.toString();
                storedList = new ArrayList();
                prevIndex = 10;
                nextIndex = 20;
                network = new Network(null,SearchTabFragment.this,null);
                network.execute(String.format("http://api.walmartlabs.com/v1/search?format=json&query=%s&apiKey=2kss5eyjk2w7zkzpqmd68ts9&start=%d&numItems=%d",searchQuery,prevIndex,nextIndex));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }


    public void updateListView(List list) {
        for (int i = 0; i< list.size(); i++) {
            storedList.add(list.get(i));
        }
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(getActivity(),storedList, SearchTabFragment.this);
        rv.setAdapter(adapter);
        if (storedList.size() == 0) {
            Toast.makeText(getActivity(), "No data found", Toast.LENGTH_SHORT).show();
        }
    }

    private void recyclerViewDidScrolled() {
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();
                    visibleItemCount = linearLayoutManager.getChildCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    if (loading && ((pastVisiblesItems + visibleItemCount) >= totalItemCount)) {
                        loading = false;
                        prevIndex = prevIndex + nextIndex;
                        if (searchQuery == null) {
                            searchQuery = searchView.getQuery().toString();
                        }
                        network = new Network(null,SearchTabFragment.this,null);
                        network.execute(String.format("http://api.walmartlabs.com/v1/search?format=json&query=%s&apiKey=2kss5eyjk2w7zkzpqmd68ts9&start=%d&numItems=%d",searchQuery,prevIndex,nextIndex));
                    }
                }
            }
        });
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

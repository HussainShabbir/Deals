package com.example.hussainshabbir.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hussainshabbir.adapter.MyRecyclerViewAdapter;
import com.example.hussainshabbir.aynctask.Network;
import com.example.hussainshabbir.classInterface.ListManager;
import com.example.hussainshabbir.selectdeals.R;

import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by HussainShabbir on 5/31/17.
 */

public class SearchTabFragment extends Fragment implements ListManager {
    Network network;
    SearchView searchView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.search_tab_fragment,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        searchView = (SearchView)getView().findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String searchQuery = query.toString();
                network = new Network(null,SearchTabFragment.this);
                network.execute(String.format("http://api.walmartlabs.com/v1/search?query=%s&apiKey=2kss5eyjk2w7zkzpqmd68ts9",searchQuery));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    public void loadData() {

    }

    public void updateListView(List list) {
        RecyclerView rv = (RecyclerView)getView().findViewById(R.id.recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(getActivity(),list);
        rv.setAdapter(adapter);
    }
}

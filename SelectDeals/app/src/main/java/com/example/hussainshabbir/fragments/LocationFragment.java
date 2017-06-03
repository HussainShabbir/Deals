package com.example.hussainshabbir.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.hussainshabbir.adapter.LocationRecyclerViewAdpater;
import com.example.hussainshabbir.aynctask.LocationAsyncTask;
import com.example.hussainshabbir.classInterface.ListManager;
import com.example.hussainshabbir.selectdeals.StoreLocation;
import com.example.hussainshabbir.selectdeals.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HussainShabbir on 6/2/17.
 */

public class LocationFragment extends Fragment implements ListManager {
    //Network network;
    private SearchView searchView;
    private LocationAsyncTask network;
    private String searchQuery;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.location_fragment,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        searchView = (SearchView)getView().findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchQuery = query.toString();
                int zipcode = 0;
                try {
                    zipcode = Integer.parseInt(searchQuery);
                } catch (NumberFormatException e) {
                    Log.d("zipcode","Invalid");
                }
                network = new LocationAsyncTask(LocationFragment.this);
                if (zipcode > 0) {
                    network.execute(String.format("http://api.walmartlabs.com/v1/stores?format=json&%s=%s&apiKey=2kss5eyjk2w7zkzpqmd68ts9","zip",searchQuery));
                } else {
                    StringBuilder searchQuerySb = new StringBuilder(searchQuery.toLowerCase());
                    searchQuerySb.setCharAt(0, Character.toUpperCase(searchQuerySb.charAt(0)));
                    searchQuery = searchQuerySb.toString();
                    network.execute(String.format("http://api.walmartlabs.com/v1/stores?format=json&%s=%s&apiKey=2kss5eyjk2w7zkzpqmd68ts9","city",searchQuery));
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void updateListView(List list) {
        RecyclerView rv = (RecyclerView)getView().findViewById(R.id.recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        int itemCount = list.size();
        List locationList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            StoreLocation location = (StoreLocation)list.get(i);
            if (location.getZip().equals(searchQuery) || location.getCity().equals(searchQuery)) {
                locationList.add(location);
            }
        }
        LocationRecyclerViewAdpater adapter = new LocationRecyclerViewAdpater(getActivity(),locationList);
        rv.setAdapter(adapter);
        if (locationList.size() == 0) {
            Toast.makeText(getActivity(), "No location found", Toast.LENGTH_SHORT).show();
        }
    }
}

package com.example.hussainshabbir.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hussainshabbir.selectdeals.R;
import com.example.hussainshabbir.selectdeals.StoreLocation;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by HussainShabbir on 6/3/17.
 */

public class LocationRecyclerViewAdpater extends RecyclerView.Adapter<LocationRecyclerViewAdpater.CustomViewHolder> {
    private List locationList;
    private Context context;

    public LocationRecyclerViewAdpater(Context context, List locationList) {
        this.context = context;
        this.locationList = locationList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.location_row, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LocationRecyclerViewAdpater.CustomViewHolder customViewHolder, int i) {
        StoreLocation location = (StoreLocation) locationList.get(i);
        customViewHolder.streetAddress.setText(String.format("%s %s",location.getStreetAddress(), location.getCountry()));
        customViewHolder.location.setText(String.format("%s %s %s", location.getCity(), location.getStateProvCode(),location.getZip()));
        customViewHolder.phoneNumber.setText(String.format("%s",location.getPhoneNumber()));
        if (location.getSundayOpen()) {
            customViewHolder.sunday.setText("Sunday: Opened ");
        } else {
            customViewHolder.sunday.setText("Sunday: Closed");
        }
    }

    @Override
    public int getItemCount() {
        return (null != locationList ? locationList.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView streetAddress;
        protected TextView location;
        protected TextView phoneNumber;
        protected TextView sunday;

        public CustomViewHolder(View view) {
            super(view);
            this.streetAddress = (TextView) view.findViewById(R.id.address);
            this.location = (TextView)view.findViewById(R.id.location);
            this.phoneNumber = (TextView)view.findViewById(R.id.phone_number);
            this.sunday = (TextView)view.findViewById(R.id.sunday);
        }
    }
}

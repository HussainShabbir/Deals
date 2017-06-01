package com.example.hussainshabbir.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.hussainshabbir.selectdeals.Product;
import com.example.hussainshabbir.selectdeals.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HussainShabbir on 5/28/17.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.CustomViewHolder> {
    private List<Product> productList;
    private Context mContext;

    public MyRecyclerViewAdapter(Context context, List<Product> productList) {
        this.productList = productList;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_row, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        Product product = productList.get(i);
        customViewHolder.name.setText(product.getName());
        if (product.getPrice() != null) {
            customViewHolder.price.setText(String.format("$%s",product.getPrice()));
        } else {
            customViewHolder.price.setText(String.format("$%s",product.getSalePrice()));
        }
        /*customViewHolder.webImageView.loadData(product.getThumbnailImage(), "text/html; charset=UTF-8", null);
        customViewHolder.webImageView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                Log.v("",url);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return (null != productList ? productList.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        //protected WebView webImageView;
        protected TextView name;
        protected TextView price;

        public CustomViewHolder(View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.name);
            this.price = (TextView) view.findViewById(R.id.price);
            //this.webImageView = (WebView) view.findViewById(R.id.web_image_view);
        }
    }
}

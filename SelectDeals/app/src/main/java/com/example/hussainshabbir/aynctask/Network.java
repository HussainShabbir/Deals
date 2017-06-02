package com.example.hussainshabbir.aynctask;

import android.app.Fragment;
import android.os.AsyncTask;

import com.example.hussainshabbir.classInterface.ListManager;
import com.example.hussainshabbir.fragments.SearchTabFragment;
import com.example.hussainshabbir.fragments.SpecialOfferFragment;
import com.example.hussainshabbir.fragments.TrendingFragment;
import com.example.hussainshabbir.selectdeals.Product;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by HussainShabbir on 5/27/17.
 */

public class Network extends AsyncTask<String,Void,String> {
    TrendingFragment trendingFragment = null;
    SearchTabFragment searchTabFragment = null;
    SpecialOfferFragment specialOfferFragment = null;
    ListManager listManager;


    public Network(TrendingFragment tabFragment, SearchTabFragment searchTabFragment, SpecialOfferFragment specialOfferFragment) {
        this.trendingFragment= tabFragment;
        this.searchTabFragment = searchTabFragment;
        this.specialOfferFragment = specialOfferFragment;
    }


    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
    }
    @Override
    protected String doInBackground(String...params)
    {
        try{
            URL url=new URL(params[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            BufferedReader bfr = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String response = bfr.readLine();
            return response;
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(String response)
    {
        super.onPostExecute(response);
        try {
            JSONArray jsonArray = new JSONArray();
            JSONObject json = new JSONObject(response);
            jsonArray = json.getJSONArray("items");
            List<Product> list = new ArrayList<Product>();
            for (int i = 0 ; i< jsonArray.length(); i++) {
                Product product = new Product();
                JSONObject obj = jsonArray.getJSONObject(i);
                if (obj.has("itemId")) {
                    product.setItemId(obj.getString("itemId"));
                }
                if (obj.has("brandName")) {
                    product.setBrandName(obj.getString("brandName"));
                }
                if (obj.has("clearance")) {
                    product.setClearance(obj.getString("clearance"));
                }
                if (obj.has("color")) {
                    product.setColor(obj.getString("color"));
                }
                if (obj.has("customerRating")) {
                    product.setCustomerRating(obj.getString("customerRating"));
                }
                if (obj.has("customerRatingImage")) {
                    product.setCustomerRatingImage(obj.getString("customerRatingImage"));
                }
                if (obj.has("modelNumber")) {
                    product.setModelNumber(obj.getString("modelNumber"));
                }
                if (obj.has("name")) {
                    product.setName(obj.getString("name"));
                }
                if (obj.has("msrp")) {
                    product.setPrice(obj.getString("msrp"));
                }
                if (obj.has("salePrice")) {
                    product.setSalePrice(obj.getString("salePrice"));
                }
                if (obj.has("gender")) {
                    product.setGender(obj.getString("gender"));
                }
                if (obj.has("age")) {
                    product.setAge(obj.getString("age"));
                }
                list.add(product);
            }
            updateListManager();
            listManager.updateListView(list);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void updateListManager() {
        if (trendingFragment != null) {
            listManager = this.trendingFragment;
        } else if (searchTabFragment != null) {
            listManager = this.searchTabFragment;
        } else if (specialOfferFragment != null) {
            listManager = this.specialOfferFragment;
        }
    }
}


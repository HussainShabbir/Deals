package com.example.hussainshabbir.aynctask;

import android.location.Location;
import android.os.AsyncTask;

import com.example.hussainshabbir.classInterface.ListManager;
import com.example.hussainshabbir.fragments.LocationFragment;
import com.example.hussainshabbir.selectdeals.Product;
import com.example.hussainshabbir.selectdeals.StoreLocation;

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

import static java.lang.Math.E;

/**
 * Created by HussainShabbir on 6/3/17.
 */

public class LocationAsyncTask extends AsyncTask <String,Void,String> {
    LocationFragment locationFragment;
    private ListManager listManager;

    public LocationAsyncTask(LocationFragment locationFragment) {
        this.locationFragment = locationFragment;
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
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        try {
            JSONArray jsonArray = new JSONArray(response);
            List list = new ArrayList();
            for (int i = 0; i< jsonArray.length(); i++ ) {
                StoreLocation location = new StoreLocation();
                //Location location = new Location();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject.has("no")) {
                    location.setNumber(jsonObject.getString("no"));
                }
                if (jsonObject.has("name")) {
                    location.setName(jsonObject.getString("name"));
                }
                if (jsonObject.has("country")) {
                    location.setCountry(jsonObject.getString("country"));
                }
                if (jsonObject.has("coordinates")) {
                    location.setCoordinates(jsonObject.getJSONArray("coordinates"));
                }
                if (jsonObject.has("streetAddress")) {
                    location.setStreetAddress(jsonObject.getString("streetAddress"));
                }
                if (jsonObject.has("city")) {
                    location.setCity(jsonObject.getString("city"));
                }
                if (jsonObject.has("stateProvCode")) {
                    location.setStateProvCode(jsonObject.getString("stateProvCode"));
                }
                if (jsonObject.has("zip")) {
                    location.setZip(jsonObject.getString("zip"));
                }
                if (jsonObject.has("phoneNumber")) {
                    location.setPhoneNumber(jsonObject.getString("phoneNumber"));
                }
                if (jsonObject.has("sundayOpen")) {
                    location.setSundayOpen(jsonObject.getBoolean("sundayOpen"));
                }
                if (jsonObject.has("timezone")) {
                    location.setTimeZone(jsonObject.getString("timezone"));
                }
                list.add(location);
            }
            updateListManager(list);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void updateListManager(List list) {
        listManager = this.locationFragment;
        listManager.updateListView(list);
    }
}

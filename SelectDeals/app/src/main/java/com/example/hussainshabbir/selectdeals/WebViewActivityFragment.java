package com.example.hussainshabbir.selectdeals;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * A placeholder fragment containing a simple view.
 */
public class WebViewActivityFragment extends Fragment {

    public WebViewActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_web_view, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        WebViewActivity activity = (WebViewActivity) getActivity();
        String productUrl = activity.getIntent().getExtras().getString("productUrl");
        WebView webView = (WebView)view.findViewById(R.id.webView);
        webView.loadUrl(productUrl);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().finish();
    }
}

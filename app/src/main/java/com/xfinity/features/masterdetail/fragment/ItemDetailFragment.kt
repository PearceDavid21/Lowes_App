package com.xfinity.features.masterdetail.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ProgressBar

class ItemDetailFragment : Fragment() {
    private lateinit var webView: WebView
    private var tabletSize: Boolean = false
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.xfinity.R.layout.fragment_item_detail, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabletSize = resources.getBoolean(com.xfinity.R.bool.isTablet)

        if (tabletSize) {
            progressBar = view.findViewById(com.xfinity.R.id.progress_circular)
            if (arguments != null) progressBar.visibility = View.VISIBLE;

            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed({
                // For tablet show loading animation instead of transition animation
                loadContent(view, arguments)
            }, 400)
        } else {
            loadContent(view, arguments)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadContent(view: View, bundle: Bundle?) {
        if (bundle != null) {
            if (bundle.containsKey("URL")) {
                val url = bundle.getString("URL")
                webView = this.activity!!.findViewById(com.xfinity.R.id.webView1)
                webView.settings.javaScriptEnabled = true
                webView.loadUrl(url)
            }
        }
    }

}
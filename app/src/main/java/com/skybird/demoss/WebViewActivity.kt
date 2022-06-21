package com.skybird.demoss

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_webview.*

/**
 * Date：2022/6/6
 * Describe:
 */
class WebViewActivity : AppCompatActivity() {

    private val GOOGLE_SEARCH = "https://www.google.com/search?q=%s"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        btn_go.setOnClickListener {
            val url = String.format(GOOGLE_SEARCH, search.text)
//            val url = search.text.toString()
            web_view.loadUrl(url)
        }
        btn_pre.setOnClickListener {
            if (web_view.canGoBack()) {
                web_view.goBack()
            }
        }

        btn_next.setOnClickListener {
            if (web_view.canGoForward()) {
                web_view.goForward();
            }
        }
        btn_reload.setOnClickListener {
            web_view.reload()
        }

        btn_cancel.setOnClickListener {
            web_view.stopLoading()
        }
        initWebView()
    }

    private fun initWebView() {
        val settings = web_view!!.settings
        settings.javaScriptEnabled = true
        settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        settings.setSupportZoom(true)
        settings.domStorageEnabled = true
        settings.loadsImagesAutomatically = true
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.defaultTextEncodingName = "utf-8"
        web_view!!.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                web_view!!.loadUrl(url)
                return super.shouldOverrideUrlLoading(web_view, url)
            }
        }
        web_view!!.setDownloadListener { url, userAgent, contentDisposition, mimetype, contentLength ->
            val uri = Uri.parse(url) // url为你要链接的地址
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        web_view.loadUrl("https://www.baidu.com")
    }

    override fun onDestroy() {
        web_view.destroy()
        super.onDestroy()
    }
}
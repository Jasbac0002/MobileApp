package com.example.whatisthisapp

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.net.URL

class RssFeeds : AppCompatActivity() {

    private lateinit var btnBack: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RSSAdapter
    private lateinit var webView: WebView
    private val rssUrl = "https://rss.nytimes.com/services/xml/rss/nyt/Sports.xml"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Hide Status bars and Make it Full Screen
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.rss_feeds)

        btnBack = findViewById(R.id.btnBack)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RSSAdapter(emptyList()) { url -> showWebPage(url) }
        recyclerView.adapter = adapter

        webView = findViewById(R.id.webView)
        webView.settings.javaScriptEnabled = true
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = WebViewClient()

        fetchData()

        btnBack.setOnClickListener{
            onBackPressed()
        }

    }

    private fun fetchData() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val url = URL(rssUrl)
                val parser = XmlPullParserFactory.newInstance().newPullParser()
                parser.setInput(url.openStream(), null)

                val items = mutableListOf<RSSItem>()
                var eventType = parser.eventType
                var currentItem: RSSItem? = null

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    when (eventType) {
                        XmlPullParser.START_TAG -> {
                            if (parser.name == "item") {
                                currentItem = RSSItem("", "", "", "", "")
                            } else if (currentItem != null) {
                                when (parser.name) {
                                    "title" -> currentItem.title = parser.nextText()
                                    "link" -> currentItem.link = parser.nextText()
                                    "pubDate" -> currentItem.pubDate = parser.nextText()
                                    "description" -> currentItem.description = parser.nextText()
                                    "enclosure" -> {
                                        val imageUrl = parser.getAttributeValue(null, "url")
                                        currentItem.imageUrl = imageUrl ?: ""
                                    }
                                }
                            }
                        }
                        XmlPullParser.END_TAG -> {
                            if (parser.name == "item" && currentItem != null) {
                                items.add(currentItem)
                            }
                        }
                    }
                    eventType = parser.next()
                }

                launch(Dispatchers.Main) {
                    adapter.updateData(items)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun showWebPage(url: String) {
        recyclerView.visibility = View.GONE
        webView.visibility = View.VISIBLE
        webView.loadUrl(url)
    }

    override fun onBackPressed() {
        if (webView.visibility == View.VISIBLE && webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
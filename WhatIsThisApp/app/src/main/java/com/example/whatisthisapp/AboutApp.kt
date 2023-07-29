package com.example.whatisthisapp


import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class AboutApp : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var items: Array<String>
    private lateinit var descriptions: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Hide Status bars and Make it Full Screen
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(R.layout.about_app)

        val btnBack: Button = findViewById<Button>(R.id.btn_appinfo_back)

        btnBack.setOnClickListener{
            onBackPressed()
        }

        val webView = findViewById<WebView>(R.id.wv_website)

    // Enable JavaScript (if needed)
            webView.settings.javaScriptEnabled = true

    // Load the local HTML file
            webView.loadUrl("file:///android_asset/whatisthis.html")


        listView = findViewById(R.id.listView)
        items = arrayOf("Fruits", "Animals")
        descriptions = arrayOf(
            "Fruits in New Zealand:\n" +
                    "\n" +
                    "Kiwifruit, a popular fruit native to New Zealand, is known for its vibrant green flesh and sweet-tart flavor, making it a delightful treat for fruit lovers.\n" +
                    "Feijoas, also known as pineapple guavas, are unique fruits found in New Zealand. These small, green fruits have a distinct tropical flavor with hints of pineapple, guava, and mint.\n" +
                    "New Zealand is famous for its delicious and juicy apples. With a wide variety of apple cultivars grown in the country, including the popular Braeburn and Royal Gala, locals and visitors alike can enjoy a bounty of crisp and refreshing apples.",
            "The kiwi bird is an iconic symbol of New Zealand and is unique to the country. These flightless birds have a distinctive appearance, with their small wings, long beaks, and soft, hair-like feathers.\n" +
                    "The tuatara, a reptile species found only in New Zealand, is often referred to as a \"living fossil.\" With a lineage that dates back over 200 million years, the tuatara is one of the oldest living reptiles on Earth.\n" +
                    "The Hector's dolphin, also known as the New Zealand dolphin, is one of the smallest and rarest dolphin species in the world. These playful and friendly dolphins can be spotted along the coasts of New Zealand, delighting visitors with their acrobatic displays."
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedItem = items[position]
            val selectedDescription = descriptions[position]
            showDescriptionDialog(selectedItem, selectedDescription)
        }


    }

    private fun showDescriptionDialog(item: String, description: String) {
        val dialog = AlertDialog.Builder(this)
            .setTitle(item)
            .setMessage(description)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
        dialog.show()
    }

}
package com.example.whatisthisapp

data class RSSItem(
    var title: String,
    var link: String,
    var pubDate: String,
    var description: String,
    var imageUrl: String
)
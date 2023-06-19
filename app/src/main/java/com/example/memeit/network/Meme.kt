package com.example.memeit.network

data class ResponseWrapper(
    val success: Boolean,
    val data: MemeData
)

data class MemeData(
    val memes: List<Meme>
)

data class Meme(
    val id: String,
    val name: String,
    val url: String,
    val width: Int,
    val height: Int,
    val box_count: Int,
    val captions: Int
)

package com.example.memeit.utils

import com.example.memeit.network.Meme

interface CustomClickListener {
    fun onMemeClickListen(meme: Meme)
}
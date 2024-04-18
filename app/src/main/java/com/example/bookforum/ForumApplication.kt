package com.example.bookforum

import android.app.Application
import com.example.bookforum.data.ForumContainer
import com.example.bookforum.data.ForumDataContainer

class ForumApplication: Application() {
    lateinit var container: ForumContainer

    override fun onCreate() {
        super.onCreate()
        container = ForumDataContainer(this)
    }
}
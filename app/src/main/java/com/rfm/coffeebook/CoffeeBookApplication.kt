package com.rfm.coffeebook

import android.app.Application
import androidx.room.Room
import com.rfm.coffeebook.main.data.AppDatabase

class CoffeeBookApplication : Application() {

    lateinit var database: AppDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "coffee_book_db"
        ).build()
    }
}
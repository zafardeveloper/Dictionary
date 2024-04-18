package com.example.bottomnavigation_practise.core

import android.app.Application
//import com.alif.newsapplication.model.dataSource.db.history.DataBaseDataSource
import com.example.bottomnavigation_practise.model.Dictionary.model.dataSource.db.dictionary.DataBaseDataSource
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        DataBaseDataSource.initDataBase(this)
    }

}
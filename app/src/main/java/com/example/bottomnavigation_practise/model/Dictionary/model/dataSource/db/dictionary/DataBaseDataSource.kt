package com.example.bottomnavigation_practise.model.Dictionary.model.dataSource.db.dictionary

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.alif.newsapplication.model.dataSource.db.dictionary.DictionaryDataBase
import kotlin.properties.Delegates.notNull

object DataBaseDataSource {

    var dataBase: NewHistoryDataBase by notNull()

    var dictionaryDataBase: DictionaryDataBase by notNull()

    fun initDataBase(context: Context) {
        dataBase = Room.databaseBuilder(context, NewHistoryDataBase::class.java, "new_history")
            .addMigrations(miration_from_1_to_2)
            .build()

        dictionaryDataBase =
            Room.databaseBuilder(context, DictionaryDataBase::class.java, "dictionary")
                .createFromAsset("dictionary.db")
                .build()
    }

    private val miration_from_1_to_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                """
                CREATE TABLE "favorite" (
                    "title"	TEXT NOT NULL,
                    "description"	TEXT NOT NULL,
                    "urlToImage"	TEXT NOT NULL,
                    "id"	INTEGER NOT NULL,
                    PRIMARY KEY("id" AUTOINCREMENT)
                )
            """.trimIndent()
            )
        }
    }

}
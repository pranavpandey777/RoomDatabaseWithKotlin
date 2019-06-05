package com.example.roomdatabasewithkotlin

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context


@Database(entities = arrayOf(Data::class),version = 1)
abstract class MyDatabase : RoomDatabase() {


    abstract  fun dao():DataDao
    companion object {
       var INSTANCE: MyDatabase? =null

        val dbname:String="MyData"
        fun getUserInstance(context: Context): MyDatabase? {
            if (INSTANCE == null) {

                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MyDatabase::class.java, "chapter.db").build()
                }

            return INSTANCE
        }

    }

}

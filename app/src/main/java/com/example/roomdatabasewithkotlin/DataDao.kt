package com.example.roomdatabasewithkotlin

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query


@Dao

interface DataDao {


    @Insert
    fun insert(data:Data):Long

    @Query("SELECT * FROM Data")
    fun getdata():List<Data>

}
package com.example.roomdatabasewithkotlin

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Data (@PrimaryKey(autoGenerate = true) val id: Int=0,@ColumnInfo(name = "name") var name:String)

package com.example.ucp_2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp_2.data.dao.BarangDao
import com.example.ucp_2.data.dao.SuplierDao
import com.example.ucp_2.data.entity.Barang
import com.example.ucp_2.data.entity.Suplier

@Database (entities = [Barang::class, Suplier ::class], version = 1, exportSchema = false)
abstract class TokoDatabase2 : RoomDatabase() {

    abstract fun BarangDao() : BarangDao
    abstract fun SuplierDao() : SuplierDao


    companion object{
        @Volatile
        private var Instance: TokoDatabase2? = null

        fun getTokoDatabase (context: Context): TokoDatabase2{
            return (Instance ?: synchronized(this){
                Room.databaseBuilder(
                    context,
                    TokoDatabase2::class.java,
                    "TokoDatabase2"
                )
                    .build().also { Instance = it}
            })
        }
    }
}
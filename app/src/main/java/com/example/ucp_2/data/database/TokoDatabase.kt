package com.example.ucp_2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp_2.data.dao.BarangDao
import com.example.ucp_2.data.dao.SuplierDao
import com.example.ucp_2.data.entity.Barang
import com.example.ucp_2.data.entity.Suplier

@Database (entities = [Barang::class], [Suplier ::class], version = 1, exportSchema = false)
abstract class TokoDatabase : RoomDatabase() {

    abstract fun BarangDao() : BarangDao
    abstract fun SuplierDao() : SuplierDao


    companion object{
        @Volatile
        private var Instance: TokoDatabase? = null

        fun getTokoDatabase (context: Context): TokoDatabase{
            return (Instance ?: synchronized(this){
                Room.databaseBuilder(
                    context,
                    TokoDatabase::class.java,
                    "TokoDatabase"
                )
                    .build().also { Instance = it}
            })
        }
    }
}
package com.example.ucp_2.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucp_2.data.entity.Barang
import com.example.ucp_2.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

@Dao
interface SuplierDao {
    @Insert
    suspend fun insertSuplier(
        suplier: Suplier
    )

    @Query("SELECT * FROM suplier ORDER BY namaSpr ASC")
    fun getAllSuplier(): Flow<List<Suplier>>

    @Query("SELECT * FROM suplier WHERE idSpr = :id")
    fun getSuplier(id: Int): Flow<Suplier>

}

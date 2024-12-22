package com.example.ucp_2.repository

import com.example.ucp_2.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

interface RepositorySpr {
    suspend fun insertSpr(suplier: Suplier)

    fun getAllSpr () : Flow<List<Suplier>>

    fun getSpr (idSpr: String) : Flow<Suplier>

    suspend fun deleteSpr(suplier: Suplier)

    suspend fun updateSpr (suplier: Suplier)
}
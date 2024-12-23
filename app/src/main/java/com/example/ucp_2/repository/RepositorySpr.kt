package com.example.ucp_2.repository

import com.example.ucp_2.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

interface RepositorySpr {
    suspend fun insertSpr(suplier: Suplier)

    fun getAllSpr () : Flow<List<Suplier>>

    fun getSpr (idSpr: Int) : Flow<Suplier>


}
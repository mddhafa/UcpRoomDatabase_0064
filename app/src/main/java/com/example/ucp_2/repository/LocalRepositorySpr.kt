package com.example.ucp_2.repository

import com.example.ucp_2.data.dao.SuplierDao
import com.example.ucp_2.data.entity.Suplier
import kotlinx.coroutines.flow.Flow

class LocalRepositorySpr (
    private val suplierDao: SuplierDao

) : RepositorySpr{
    override suspend fun insertSpr(suplier: Suplier) {
        suplierDao.insertSuplier(suplier)
    }

    override fun getAllSpr () : Flow<List<Suplier>> {
        return suplierDao.getAllSuplier()
    }

    override fun getSpr (id: String) : Flow<Suplier> {
        return suplierDao.getSuplier(id)
    }

    override suspend fun deleteSpr (suplier: Suplier){
        suplierDao.deleteSuplier(suplier)
    }

    override suspend fun updateSpr (suplier: Suplier){
        suplierDao.updateSuplier(suplier)
    }
}
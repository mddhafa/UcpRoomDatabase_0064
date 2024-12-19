package com.example.ucp_2.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ucp_2.data.entity.Barang
import com.example.ucp_2.data.entity.Suplier
import com.example.ucp_2.repository.RepositoryBrg
import com.example.ucp_2.repository.RepositorySpr

class HomeTokoViewModel(
    private val repositoryBrg: RepositoryBrg,
    private val repositorySpr: RepositorySpr
) : ViewModel(){

}

data class HomeUiState(
    val listbrg: List<Barang> = listOf(),
    val listspr: List<Suplier> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)
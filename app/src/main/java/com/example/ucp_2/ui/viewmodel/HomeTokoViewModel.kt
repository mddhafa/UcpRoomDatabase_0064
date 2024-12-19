package com.example.ucp_2.ui.viewmodel

import com.example.ucp_2.data.entity.Barang
import com.example.ucp_2.data.entity.Suplier

data class HomeUiState(
    val listbrg: List<Barang> = listOf(),
    val listspr: List<Suplier> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)
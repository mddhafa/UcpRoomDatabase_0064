package com.example.ucp_2.ui.viewmodel.barang

import com.example.ucp_2.data.entity.Barang

data class HomeBrgUiState(
    val listBrg: List<Barang> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = " "
)
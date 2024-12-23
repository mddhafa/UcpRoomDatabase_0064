package com.example.ucp_2.data

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import com.example.ucp_2.ui.viewmodel.PenyediaViewModel
import androidx.compose.runtime.getValue
import com.example.ucp_2.ui.viewmodel.suplier.HomeSprViewModel

object SuplierList {

    @Composable
    fun DataSuplier (
        sprViewModel: HomeSprViewModel = viewModel(factory = PenyediaViewModel.Factory)
    ): List<String>{
        val getSprName by sprViewModel.homeSprUiState.collectAsState()
        val nama = getSprName.listSpr.map { it.namaSpr}
        return nama
    }
}
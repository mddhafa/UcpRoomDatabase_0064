package com.example.ucp_2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp_2.data.entity.Barang
import com.example.ucp_2.data.entity.Suplier
import com.example.ucp_2.repository.RepositoryBrg
import com.example.ucp_2.repository.RepositorySpr
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class HomeTokoViewModel(
    private val repositoryBrg: RepositoryBrg,
    private val repositorySpr: RepositorySpr
) : ViewModel(){
    val homeUiState: StateFlow<HomeUiState> = combine(
        repositoryBrg.getAllBrg().filterNotNull(),
        repositorySpr.getAllSpr().filterNotNull()
    ){ listBrg, listSpr ->
        HomeUiState(
            listBrg = listBrg.toList(),
            listSpr = listSpr.toList(),
            isLoading = false
        )
    }
        .onStart {
            emit(HomeUiState(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                HomeUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeUiState(
                isLoading = true,
            )
        )
}

data class HomeUiState(
    val listBrg: List<Barang> = listOf(),
    val listSpr: List<Suplier> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)
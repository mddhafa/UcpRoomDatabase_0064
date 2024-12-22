package com.example.ucp_2.ui.viewmodel.suplier

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp_2.data.entity.Suplier
import com.example.ucp_2.repository.RepositorySpr
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class HomeSprViewModel(
    private val repositorySpr: RepositorySpr
) : ViewModel() {
    open val homeSprUiState: StateFlow<HomeSprUiState> = repositorySpr.getAllSpr()
        .filterNotNull()
        .map {
            HomeSprUiState(
                listSpr = it.toList(),
                isLoading = false,
            )
        }
        .onStart {
            emit(HomeSprUiState(isLoading = true))
            delay(900)
        }
        .catch {
            emit(
                HomeSprUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = HomeSprUiState(
                isLoading = true,
            )
        )
}

data class HomeSprUiState(
    val listSpr: List<Suplier> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = " "
)
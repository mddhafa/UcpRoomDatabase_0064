package com.example.ucp_2.ui.viewmodel.barang

import com.example.ucp_2.data.entity.Barang
import com.example.ucp_2.ui.viewmodel.suplier.BrgUiState
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import com.example.ucp_2.repository.RepositoryBrg
import com.example.ucp_2.ui.navigation.AlamatNavigasi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

class UpdateBrgViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryBrg: RepositoryBrg
) : ViewModel() {

    var updateUiState by mutableStateOf(BrgUiState())
        private set

    private val _idBrg: String =
        checkNotNull(savedStateHandle[AlamatNavigasi.DestinasiUpdate.idBrg])

    init {
        viewModelScope.launch {
            updateUiState = repositoryBrg.getBrg(_idBrg)
                .filterNotNull()
                .first()
                .toUiStateBrg()
        }
    }

}

    fun Barang.toUiStateBrg() : BrgUiState = BrgUiState(
    barangEvent = this.toDetailBrgUiEvent(),
)
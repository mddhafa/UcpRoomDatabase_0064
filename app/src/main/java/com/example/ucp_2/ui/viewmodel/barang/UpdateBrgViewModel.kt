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
import com.example.ucp_2.ui.viewmodel.suplier.BarangEvent
import com.example.ucp_2.ui.viewmodel.suplier.FormErrorBrgState

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

    fun updateState(barangEvent: BarangEvent){
        updateUiState = updateUiState.copy(
            barangEvent = barangEvent,
        )
    }

    private fun validateFields(): Boolean {
        val event = updateUiState.barangEvent
        val errorState = FormErrorBrgState(
            idBrg = if (event.idBrg.isNotEmpty()) null else "Id Barang tidak boleh kosong",
            namaBrg = if (event.namaBrg.isNotEmpty()) null else "Nama Barang tidak boleh kosong",
            deskripsi = if (event.deskripsi.isNotEmpty()) null else "Deskripsi tidak boleh kosong",
            harga = if (event.harga.isNotEmpty()) null else "Harga tidak boleh kosong",
            stok = if (event.stok >= 0) null else "Stok tidak boleh kosong",
            namaSuplier = if (event.namaSuplier.isNotEmpty()) null else "Nama Suplier tidak boleh kosong"
        )

        updateUiState = updateUiState.copy(isEntryValid = errorState)
        return errorState.isBrgValid()
    }


}

    fun Barang.toUiStateBrg() : BrgUiState = BrgUiState(
    barangEvent = this.toDetailBrgUiEvent(),
)
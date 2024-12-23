package com.example.ucp_2.ui.viewmodel.barang

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp_2.data.entity.Barang
import com.example.ucp_2.repository.RepositoryBrg
import com.example.ucp_2.ui.navigation.AlamatNavigasi
import com.example.ucp_2.ui.viewmodel.suplier.BarangEvent
import com.example.ucp_2.ui.viewmodel.suplier.toBarangEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailBrgViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryBrg: RepositoryBrg,
) : ViewModel(){
    private val _idBrg: Int = checkNotNull(savedStateHandle[AlamatNavigasi.DestinasiDetailBrg.idBrg])

    val detailUiState: StateFlow<DetailBrgUiState> = repositoryBrg.getBrg(_idBrg)
        .filterNotNull()
        .map {
            DetailBrgUiState(
                detailUiEvent = it.toDetailBrgUiEvent(),
                isLoading = false
            )
        }
        .onStart {
            emit(DetailBrgUiState(isLoading = true))
            delay(600)
        }
        .catch {
            emit(
                DetailBrgUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Terjadi Kesalahan"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailBrgUiState(
                isLoading = true
            ),
        )


    fun deleteBrg(){
        detailUiState.value.detailUiEvent.toBarangEntity().let {
            viewModelScope.launch {
                repositoryBrg.deleteBrg(it)
            }
        }
    }
}

data class DetailBrgUiState(
    val detailUiEvent: BarangEvent = BarangEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
){
    val isUiEventEmpty : Boolean
        get() = detailUiEvent == BarangEvent()

    val isUiEventNotEmpty : Boolean
        get() = detailUiEvent != BarangEvent()
}

fun Barang.toDetailBrgUiEvent () : BarangEvent{
    return BarangEvent(
        idBrg = idBrg,
        namaBrg = namaBrg,
        deskripsi = deskripsi,
        harga = harga,
        stok = stok,
        namaSuplier = namaSuplier
    )
}
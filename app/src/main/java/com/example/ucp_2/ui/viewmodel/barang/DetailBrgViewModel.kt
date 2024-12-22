package com.example.ucp_2.ui.viewmodel.barang

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp_2.data.entity.Barang
import com.example.ucp_2.repository.RepositoryBrg
import com.example.ucp_2.ui.navigation.AlamatNavigasi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailBrgViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryBrg: RepositoryBrg,
) : ViewModel(){
    private val _id : String = checkNotNull(savedStateHandle[AlamatNavigasi.DestinasiDetailBrg.IDBRG])

    val detailUiState: StateFlow<DetailBrgUiState> = repositoryBrg.getBrg(_id)
        .filterNotNull()
        .map {
            DetailBrgUiState(
                detailUiEvent = it.toDetailBrgUiEvent(),
                isLoading = false,
            )
        }
        .onStart {
            emit(DetailBrgUiState(isLoading = true))
            delay(600)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailBrgUiState(
                isLoading = true,
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
    val listBrg: List<Barang> = listOf(),
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
package com.example.ucp_2.ui.viewmodel.barang

import androidx.lifecycle.ViewModel
import com.example.ucp_2.data.entity.Barang
import com.example.ucp_2.repository.RepositoryBrg
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


class BarangViewModel (
    private val repositoryBrg: RepositoryBrg
): ViewModel (){

    var uiState by mutableStateOf(BrgUiState())

    fun updateState(barangEvent: BarangEvent){
        uiState = uiState.copy(
            barangEvent = barangEvent,
        )
    }

    private fun validateFields(): Boolean{

    }

}

data class BrgUiState(
    val barangEvent: BarangEvent = BarangEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null
)
data class FormErrorState(
    val id_brg: String? = null,
    val nama_brg: String? = null,
    val deskripsi: String? = null,
    val harga: String? = null,
    val stok: String? = null,
    val namaSuplier: String? = null
){
    fun isValid(): Boolean{
        return id_brg == null && nama_brg == null && deskripsi == null &&
                harga == null && stok == null && namaSuplier == null
    }

}

fun BarangEvent.toBarangEntity(): Barang = Barang(
    id_brg = id_brg,
    nama_brg = nama_brg,
    deskripsi = deskripsi,
    harga = harga,
    stok = stok,
    namaSuplier = namaSuplier
)

data class BarangEvent(
    val id_brg: String ="",
    val nama_brg: String ="",
    val deskripsi: String="",
    val harga: String ="",
    val stok: String ="",
    val namaSuplier: String ="",
)
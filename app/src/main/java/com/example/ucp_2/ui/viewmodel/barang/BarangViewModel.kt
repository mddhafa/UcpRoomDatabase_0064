package com.example.ucp_2.ui.viewmodel.barang

import androidx.lifecycle.ViewModel
import com.example.ucp_2.data.entity.Barang
import com.example.ucp_2.repository.RepositoryBrg
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class BarangViewModel (
    private val repositoryBrg: RepositoryBrg
): ViewModel (){

    var uiState by mutableStateOf(BrgUiState())

    fun updateState(barangEvent: BarangEvent){
        uiState = uiState.copy(
            barangEvent = barangEvent
        )
    }

    private fun validateFields(): Boolean{
        val event= uiState.barangEvent
        val errorState = FormErrorState(
            idBrg = if (event.idBrg.isNotEmpty()) null else "Id Barang Tidak Boleh Kosong",
            namaBrg = if (event.namaBrg.isNotEmpty()) null else "Nama Barang Tidak Boleh Kosong",
            deskripsi = if (event.deskripsi.isNotEmpty()) null else "Deskripsi Tidak Boleh Kosong",
            harga = if (event.harga.isNotEmpty()) null else "Harga Tidak Boleh Kosong",
            stok = if (event.stok.isNotEmpty()) null else "Stok Tidak Boleh Kosong",
            namaSuplier = if (event.namaSuplier.isNotEmpty()) null else "Nama Supplier Tidak Boleh Kosong",
        )
        uiState = uiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun saveData(){
        val currentEvent = uiState.barangEvent

        if(validateFields()){
            viewModelScope.launch {
                try {
                    repositoryBrg.insertBrg(currentEvent.toBarangEntity())
                    uiState = uiState.copy(
                        snackBarMessage = "Data berhasil diisi",
                        barangEvent = BarangEvent(),
                        isEntryValid = FormErrorState()
                    )
                }catch (e: Exception){
                    uiState = uiState.copy(
                        snackBarMessage = "Data gagal disimpan"
                    )
                }
            }
        }else{
            uiState = uiState.copy(
                snackBarMessage = "Input tidak valid, Periksa ulang data kamu"
            )
        }
    }
    fun resetSnackBarMessage(){
        uiState = uiState.copy(snackBarMessage = null)
    }

}

data class BrgUiState(
    val barangEvent: BarangEvent = BarangEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null
)
data class FormErrorState(
    val idBrg: String? = null,
    val namaBrg: String? = null,
    val deskripsi: String? = null,
    val harga: String? = null,
    val stok: String? = null,
    val namaSuplier: String? = null
){
    fun isValid(): Boolean{
        return idBrg == null && namaBrg == null && deskripsi == null &&
                harga == null && stok == null && namaSuplier == null
    }

}

fun BarangEvent.toBarangEntity(): Barang = Barang(
    idBrg = idBrg,
    namaBrg = namaBrg,
    deskripsi = deskripsi,
    harga = harga,
    stok = stok,
    namaSuplier = namaSuplier
)

data class BarangEvent(
    val idBrg: String ="",
    val namaBrg: String ="",
    val deskripsi: String="",
    val harga: String ="",
    val stok: String ="",
    val namaSuplier: String ="",
)
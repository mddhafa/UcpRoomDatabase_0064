package com.example.ucp_2.ui.viewmodel.suplier

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp_2.data.entity.Barang
import com.example.ucp_2.repository.RepositoryBrg
import kotlinx.coroutines.launch

class BarangViewModel (
    private val repositoryBrg: RepositoryBrg
) : ViewModel (){

    var uiBrgState by mutableStateOf(BrgUiState())

    fun updateBrgState(barangEvent: BarangEvent){
        uiBrgState = uiBrgState.copy(
            barangEvent = barangEvent,
        )
    }

    private fun validateFields(): Boolean{
        val event = uiBrgState.barangEvent
        val errorState = FormErrorBrgState(
            idBrg = if (event.idBrg >= 0) null else "Id Barang tidak boleh kosong",
            namaBrg = if (event.namaBrg.isNotEmpty()) null else "Id Barang tidak boleh kosong",
            deskripsi = if (event.deskripsi.isNotEmpty()) null else "Id Barang tidak boleh kosong",
            harga = if (event.harga.isNotEmpty()) null else "Id Barang tidak boleh kosong",
            stok = if (event.stok >= 0 ) null else "Id Barang tidak boleh kosong",
            namaSuplier = if (event.namaSuplier.isNotEmpty()) null else "Id Barang tidak boleh kosong",
        )

        uiBrgState = uiBrgState.copy(isEntryValid = errorState)
        return errorState.isBrgValid()
    }
    fun saveData() {
        val currentEvent = uiBrgState.barangEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryBrg.insertBrg(currentEvent.toBarangEntity())
                    uiBrgState = uiBrgState.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        barangEvent = BarangEvent(), //Reset input form
                        isEntryValid = FormErrorBrgState() // Reset error state
                    )
                } catch (e: Exception) {
                    uiBrgState = uiBrgState.copy(
                        snackBarMessage = "Data gagal disimpan"
                    )
                }
            }
        } else {
            uiBrgState = uiBrgState.copy(
                snackBarMessage = "Input tidak valid. Periksa kembali data anda."
            )
        }
    }
    //Reset pesan Snackbar setelah ditampilkan
    fun resetSnackBarMessage(){
        uiBrgState = uiBrgState.copy(snackBarMessage = null)
    }
}

data class BrgUiState(
    val barangEvent: BarangEvent = BarangEvent(),
    val isEntryValid: FormErrorBrgState = FormErrorBrgState(),
    val snackBarMessage: String? = null
)

data class FormErrorBrgState(
    val idBrg: String? = null,
    val namaBrg: String? = null,
    val deskripsi: String? = null,
    val harga: String? = null,
    val stok: String? = null,
    val namaSuplier: String? = null
){
    fun isBrgValid(): Boolean{
        return idBrg == null && namaBrg == null && deskripsi == null &&
                harga == null && stok == null && namaSuplier == null
    }
}

data class BarangEvent(
    val idBrg: Int= 0,
    val namaBrg : String= "",
    val deskripsi : String= "",
    val harga : String= "",
    val stok : Int= 0,
    val namaSuplier : String= ""
)

fun BarangEvent.toBarangEntity(): Barang = Barang(
    idBrg = idBrg,
    namaBrg = namaBrg,
    deskripsi = deskripsi,
    harga = harga,
    stok = stok,
    namaSuplier = namaSuplier
)
package com.example.ucp_2.ui.viewmodel.suplier

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp_2.data.entity.Suplier
import com.example.ucp_2.repository.RepositorySpr
import kotlinx.coroutines.launch

class SuplierViewModel (
    private val repositorySpr: RepositorySpr
) : ViewModel (){

    var uiSprState by mutableStateOf(SprUiState())

    fun updateSprState(suplierEvent: SuplierEvent){
        uiSprState = uiSprState.copy(
            suplierEvent = suplierEvent,
        )
    }

    private fun validateFields(): Boolean{
        val event = uiSprState.suplierEvent
        val errorState = FormErrorSprState(
            idSpr = if (event.idSpr >= 0) null else "Id Suplier tidak boleh kosong",
            namaSpr = if (event.namaSpr.isNotEmpty()) null else "Nama Suplier tidak boleh kosong",
            kontak = if (event.kontak.isNotEmpty()) null else "Kontak tidak boleh kosong",
            alamat = if (event.alamat.isNotEmpty()) null else "Alamat tidak boleh kosong",
        )
        uiSprState = uiSprState.copy(isEntryValid = errorState)
        return errorState.isSprValid()
    }
    fun saveData() {
        val currentEvent = uiSprState.suplierEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositorySpr.insertSpr(currentEvent.toSuplierEntity())
                    uiSprState = uiSprState.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        suplierEvent = SuplierEvent(),
                        isEntryValid = FormErrorSprState()
                    )
                } catch (e: Exception) {
                    uiSprState = uiSprState.copy(
                        snackBarMessage = "Data gagal disimpan"
                    )
                }
            }
        } else {
            uiSprState = uiSprState.copy(
                snackBarMessage = "Input tidak valid. Periksa kembali data anda."
            )
        }
    }
    //Reset pesan Snackbar setelah ditampilkan
    fun resetSnackBarMessage(){
        uiSprState = uiSprState.copy(snackBarMessage = null)
    }
}

data class SprUiState(
    val suplierEvent: SuplierEvent = SuplierEvent(),
    val isEntryValid: FormErrorSprState = FormErrorSprState(),
    val snackBarMessage: String? = null
)

data class FormErrorSprState(
    val idSpr: String? = null,
    val namaSpr: String? = null,
    val kontak: String? = null,
    val alamat: String? = null
){
    fun isSprValid(): Boolean {
        return idSpr == null && namaSpr == null &&
                kontak == null && alamat == null
    }
}

fun SuplierEvent.toSuplierEntity() : Suplier = Suplier(
    idSpr = idSpr,
    namaSpr = namaSpr,
    kontak = kontak,
    alamat = alamat
)

data class SuplierEvent(
    val idSpr : Int =0,
    val namaSpr : String = "",
    val kontak : String = "",
    val alamat : String = ""
)
package com.example.ucp_2.ui.viewmodel.suplier

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp_2.data.entity.Suplier
import com.example.ucp_2.repository.RepositorySpr
import kotlinx.coroutines.launch


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
    val idSpr : String = " ",
    val namaSpr : String = " ",
    val kontak : String = " ",
    val alamat : String = " "
)
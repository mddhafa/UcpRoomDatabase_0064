package com.example.ucp_2.ui.viewmodel.suplier

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp_2.data.entity.Suplier
import com.example.ucp_2.repository.RepositorySpr
import kotlinx.coroutines.launch


data class SuplierEvent(
    val idSpr : String = " ",
    val namaSpr : String = " ",
    val kontak : String = " ",
    val alamat : String = " "
)
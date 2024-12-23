package com.example.ucp_2.ui.view.suplier

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp_2.ui.customwidget.InputDataTopAppBar
import com.example.ucp_2.ui.customwidget.Navbar
import com.example.ucp_2.ui.viewmodel.PenyediaViewModel
import com.example.ucp_2.ui.viewmodel.suplier.FormErrorSprState
import com.example.ucp_2.ui.viewmodel.suplier.SprUiState
import com.example.ucp_2.ui.viewmodel.suplier.SuplierEvent
import com.example.ucp_2.ui.viewmodel.suplier.SuplierViewModel
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun FormInputSpr(
    suplierEvent: SuplierEvent = SuplierEvent(),
    onValueChange: (SuplierEvent) -> Unit = { },
    errorState: FormErrorSprState = FormErrorSprState(),
    modifier: Modifier = Modifier

){
    Column (
        modifier = modifier.fillMaxWidth().padding(10.dp)
    ){

        Text(
            text = errorState.idSpr ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suplierEvent.namaSpr,
            onValueChange = { onValueChange(suplierEvent.copy(namaSpr = it)) },
            label = { Text("Nama Suplier") },
            isError = errorState.namaSpr != null,
            placeholder = { Text("Masukan Nama Suplier") },)
        Text(
            text = errorState.namaSpr ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suplierEvent.kontak,
            onValueChange = { onValueChange(suplierEvent.copy(kontak = it)) },
            label = { Text("Kontak") },
            isError = errorState.kontak != null,
            placeholder = { Text("Masukan Kontak") },)
        Text(
            text = errorState.kontak ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = suplierEvent.alamat,
            onValueChange = { onValueChange(suplierEvent.copy(alamat = it)) },
            label = { Text("Alamat") },
            isError = errorState.alamat != null,
            placeholder = { Text("Masukan Alamat") },
            )
        Text(
            text = errorState.alamat ?: "",
            color = Color.Red
        )
    }
}
package com.example.ucp_2.ui.view.barang

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
import com.example.ucp_2.data.SuplierList
import com.example.ucp_2.ui.customwidget.DynamicSelectedField
import com.example.ucp_2.ui.customwidget.InputDataTopAppBar
import com.example.ucp_2.ui.customwidget.Navbar
import com.example.ucp_2.ui.viewmodel.PenyediaViewModel
import com.example.ucp_2.ui.viewmodel.suplier.BarangEvent
import com.example.ucp_2.ui.viewmodel.suplier.BarangViewModel
import com.example.ucp_2.ui.viewmodel.suplier.BrgUiState
import com.example.ucp_2.ui.viewmodel.suplier.FormErrorBrgState
import kotlinx.coroutines.launch


@Composable
fun InsertBodyBrg(
    modifier: Modifier = Modifier,
    onValueChange: (BarangEvent) -> Unit = { },
    uiBrgUiState: BrgUiState,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormInputBrg(
            barangEvent = uiBrgUiState.barangEvent,
            onValueChange = onValueChange,
            errorState = uiBrgUiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Simpan")
        }
    }
}

@Composable
fun FormInputBrg(
    barangEvent: BarangEvent = BarangEvent(),
    onValueChange: (BarangEvent) -> Unit = { },
    errorState: FormErrorBrgState = FormErrorBrgState(),
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth().padding(10.dp)
    ) {

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.namaBrg,
            onValueChange = { onValueChange(barangEvent.copy(namaBrg = it)) },
            label = { Text("Nama Barang") },
            isError = errorState.namaBrg != null,
            placeholder = { Text("Masukan Nama Barang") }
        )
        Text(text = errorState.deskripsi ?: "", color = Color.Red)
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.deskripsi,
            onValueChange = { onValueChange(barangEvent.copy(deskripsi = it)) },
            label = { Text("Deskripsi") },
            isError = errorState.deskripsi != null,
            placeholder = { Text("Masukan Deskripsi") }
        )
        Text(text = errorState.deskripsi ?: "", color = Color.Red)

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.harga,
            onValueChange = { onValueChange(barangEvent.copy(harga = it)) },
            label = { Text("Harga Barang") },
            isError = errorState.harga != null,
            placeholder = { Text("Masukan Harga Barang") }
        )
        Text(text = errorState.harga ?: "", color = Color.Red)

        OutlinedTextField(
            value = barangEvent.stok.toString(),
            onValueChange = { it.toIntOrNull()?.let { stok -> onValueChange(barangEvent.copy(stok = stok)) } },
            label = { Text("Stok Barang") },
            isError = errorState.stok != null,
            placeholder = { Text("Masukan Stok Barang") },
            modifier = Modifier.fillMaxWidth()
        )
        Text(text = errorState.stok ?: "", color = Color.Red)

        DynamicSelectedField(
            selectedValue = barangEvent.namaSuplier,
            options = SuplierList.DataSuplier(),
            label = "Nama Suplier",
            onValueChangedEvent = { selectedSpr ->
                onValueChange(barangEvent.copy(namaSuplier = selectedSpr))
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

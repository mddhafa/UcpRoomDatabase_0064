package com.example.ucp_2.ui.view.barang

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp_2.ui.customwidget.Navbar
import com.example.ucp_2.ui.viewmodel.PenyediaViewModel
import com.example.ucp_2.ui.viewmodel.barang.UpdateBrgViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun UpdateBrgView (
    viewModel: UpdateBrgViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onBack : () -> Unit,
    onNavigate : () -> Unit,
    modifier: Modifier = Modifier,
){
    val uiBrgState = viewModel.updateUiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    //observe perubahan snackbarMessage
    LaunchedEffect(uiBrgState.snackBarMessage) {
        println("LaunchedEffect triggered")
        uiBrgState.snackBarMessage?.let{ message ->
            println("Launching coroutine for snackbar")
            snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Long,
            )
            viewModel.resetSnackBarMessage()
        }
    }

    Scaffold (
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState)}, //tempatkan snackbar di scaffold
        topBar = {
            Navbar(
                namaUser = "",
                onBack = onBack,
                showBackButton = true
            )
        }
    ) { padding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            //isi body
            InsertBodyBrg(
                uiBrgUiState = uiBrgState,
                onValueChange = { updatedEvent ->
                    viewModel.updateState(updatedEvent)
                },
                onClick = {
                    coroutineScope.launch {
                        if (viewModel.validateFields()) {
                            viewModel.updateData()
                            delay(600)
                            withContext(Dispatchers.Main) {
                                onNavigate()
                            }
                        }
                    }
                }
            )
        }
    }
}
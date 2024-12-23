package com.example.ucp_2.ui.view.suplier

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp_2.ui.viewmodel.PenyediaViewModel
import androidx.compose.runtime.Composable
import com.example.ucp_2.data.entity.Barang
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ucp_2.ui.customwidget.Navbar
import androidx.compose.ui.tooling.preview.Preview
import com.example.ucp_2.data.entity.Suplier
import com.example.ucp_2.ui.view.barang.ListBarang
import com.example.ucp_2.ui.viewmodel.barang.HomeBrgUiState
import com.example.ucp_2.ui.viewmodel.barang.HomeBrgViewModel
import com.example.ucp_2.ui.viewmodel.suplier.HomeSprUiState
import com.example.ucp_2.ui.viewmodel.suplier.HomeSprViewModel
import kotlinx.coroutines.launch


@Composable
fun BodyListSprView(
    homeSprUiState: HomeSprUiState,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    when {
        homeSprUiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        homeSprUiState.isError -> {
            LaunchedEffect(homeSprUiState.errorMessage) {
                homeSprUiState.errorMessage?.let { message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                }
            }
        }
        homeSprUiState.listSpr.isEmpty() -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tidak ada data Suplier.",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        else -> {
            ListSupier(
                listSpr = homeSprUiState.listSpr,
                modifier = modifier
            )
        }
    }
}

@Composable
fun ListSupier(
    listSpr: List<Suplier>,
    modifier: Modifier = Modifier,
){
    LazyColumn (
        modifier = modifier
    ){
        items(
            items = listSpr,
            itemContent = { spr->
                CardSpr (
                    spr= spr,
                )
            }
        )
    }
}

@Composable
fun CardSpr(
    spr: Suplier,
    modifier : Modifier = Modifier,
){
    Card (
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column (
            modifier = Modifier.padding(8.dp)
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.PlayArrow, contentDescription = "")
                Spacer(modifier = modifier.padding(4.dp))
                Text(
                        text = "ID: ${spr.idSpr}",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface
                )
            }
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.Person, contentDescription = "")
                Spacer(modifier = modifier.padding(4.dp))
                Text(
                    text = spr.namaSpr,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.Call, contentDescription = "")
                Spacer(modifier = modifier.padding(4.dp))
                Text(
                    text = spr.kontak,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Filled.LocationOn, contentDescription = "")
                Spacer(modifier = modifier.padding(4.dp))
                Text(
                    text = spr.alamat,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}
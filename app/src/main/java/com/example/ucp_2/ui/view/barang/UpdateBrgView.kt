package com.example.ucp_2.ui.view.barang
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp_2.ui.viewmodel.PenyediaViewModel
import com.example.ucp_2.ui.viewmodel.barang.UpdateBrgViewModel

@Composable
fun UpdateBrgView (
    viewModel: UpdateBrgViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onBack : () -> Unit,
    onNavigate : () -> Unit,
    modifier: Modifier = Modifier,
){

}
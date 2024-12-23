package com.example.ucp_2.ui.viewmodel

import android.text.Editable.Factory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucp_2.TokoApp
import com.example.ucp_2.ui.viewmodel.barang.DetailBrgViewModel
import com.example.ucp_2.ui.viewmodel.barang.HomeBrgViewModel
import com.example.ucp_2.ui.viewmodel.barang.UpdateBrgViewModel
import com.example.ucp_2.ui.viewmodel.suplier.BarangViewModel
import com.example.ucp_2.ui.viewmodel.suplier.HomeSprViewModel
import com.example.ucp_2.ui.viewmodel.suplier.SuplierViewModel

object PenyediaViewModel{

    val Factory = viewModelFactory {
        initializer {
            BarangViewModel(
                TokoApp().containerApp.repositoryBrg
            )
        }
        initializer {
            SuplierViewModel(
                TokoApp().containerApp.repositorySpr
            )
        }
        initializer {
            DetailBrgViewModel(
                createSavedStateHandle(),
                TokoApp().containerApp.repositoryBrg
            )
        }
        initializer {
            HomeBrgViewModel(
                TokoApp().containerApp.repositoryBrg
            )
        }
        initializer {
            HomeSprViewModel(
                TokoApp().containerApp.repositorySpr
            )
        }
        initializer {
            UpdateBrgViewModel(
                createSavedStateHandle(),
                TokoApp().containerApp.repositoryBrg
            )
        }
    }
}

fun CreationExtras.TokoApp(): TokoApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TokoApp)
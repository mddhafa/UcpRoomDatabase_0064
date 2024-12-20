package com.example.ucp_2.ui.viewmodel.barang

import com.example.ucp_2.data.entity.Barang


fun Barang.toDetailUiEvent () : BarangEvent{
    return BarangEvent(
        id_brg = id_brg,
        nama_brg = nama_brg,
        deskripsi = deskripsi,
        harga = harga,
        stok = stok,
        namaSuplier = namaSuplier
    )
}
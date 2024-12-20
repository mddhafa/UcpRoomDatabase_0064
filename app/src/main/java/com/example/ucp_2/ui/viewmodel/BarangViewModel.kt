package com.example.ucp_2.ui.viewmodel

import com.example.ucp_2.data.entity.Barang


fun BarangEvent.toBarangEntity(): Barang = Barang(
    id_brg = id_brg,
    nama_brg = nama_brg,
    deskripsi = deskripsi,
    harga = harga,
    stok = stok,
    namaSuplier = namaSuplier
)

data class BarangEvent(
    val id_brg: String,
    val nama_brg: String,
    val deskripsi: String,
    val harga: String,
    val stok: String,
    val namaSuplier: String,
    )
package com.example.ucp_2.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Barang(
    @PrimaryKey
    val idBrg: String,
    val namaBrg: String,
    val deskripsi: String,
    val harga: String,
    val stok: String,
    val namaSuplier: String,
)

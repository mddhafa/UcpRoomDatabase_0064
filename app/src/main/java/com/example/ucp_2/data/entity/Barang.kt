package com.example.ucp_2.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Barang(
    @PrimaryKey(autoGenerate = true)
    val idBrg: Int,
    val namaBrg: String,
    val deskripsi: String,
    val harga: String,
    val stok: Int,
    val namaSuplier: String,
)

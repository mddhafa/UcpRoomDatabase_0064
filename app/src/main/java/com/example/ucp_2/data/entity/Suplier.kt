package com.example.ucp_2.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Suplier(
    @PrimaryKey
    val id_spr: String,
    val nama_spr: String,
    val kontak: String,
    val alamat: String,
)

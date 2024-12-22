package com.example.ucp_2.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Suplier(
    @PrimaryKey
    val idSpr: String,
    val namaSpr: String,
    val kontak: String,
    val alamat: String,
)

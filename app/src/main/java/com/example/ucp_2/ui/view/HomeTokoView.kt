package com.example.ucp_2.ui.view


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.height
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.Scaffold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucp_2.R
import com.example.ucp_2.ui.customwidget.Navbar

@Composable
fun HomeTokoView(
    onAddBrg: () -> Unit = {},
    onAddSpr: () -> Unit = {},
    onListBrg: (String) -> Unit = {},
    onListSpr: (String) -> Unit = {}
) {
    Scaffold(
        topBar = {
            Navbar(
                namaUser = "Muh Dhafa",
                onBack = {
                    println("Kembali ditekan")
                },
                showBackButton = false
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Selamat Datang di Toko Kami",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1976D2),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Kelola Barang dan Supplier Anda dengan mudah.",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(40.dp))
            }

            // Grid menu
            GridMenu(
                onAddBrg = onAddBrg,
                onAddSpr = onAddSpr,
                onListBrg = onListBrg,
                onListSpr = onListSpr
            )
        }
    }
}
@Composable
fun GridMenu(
    onAddBrg: () -> Unit = {},
    onAddSpr: () -> Unit = {},
    onListBrg: (String) -> Unit = {},
    onListSpr: (String) -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            MenuCard(
                title = "Barang List",
                iconRes = R.drawable.listt,
                onClick = { onListBrg("Barang List") },
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            MenuCard(
                title = "Add Barang",
                iconRes = R.drawable.add,
                onClick = onAddBrg,
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            MenuCard(
                title = "Supplier List",
                iconRes = R.drawable.listt,
                onClick = { onListSpr("Supplier List") },
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            MenuCard(
                title = "Add Supplier",
                iconRes = R.drawable.add,
                onClick = onAddSpr,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun MenuCard(
    title: String,
    iconRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF64B5F6)
        ),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
        modifier = modifier
            .height(120.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                modifier = Modifier.size(32.dp),
                tint = Color.White
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }
    }
}

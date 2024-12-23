package com.example.ucp_2.ui.customwidget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun InputDataTopAppBar(
    title: String = "Input Data",
    namaUser: String,
    onBack: () -> Unit,
    showBackButton: Boolean = true
) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(color = Color(red = 0, green = 191, blue = 255)),
        contentAlignment = Alignment.Center
    ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                if (showBackButton) {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier
                            .padding(end = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Kembali",
                            tint = Color.White
                        )
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = title,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Text(
                        text = "Di Bawah ini",
                        color = Color.White,
                        fontStyle = FontStyle.Italic,
                        fontSize = 12.sp
                    )
                }
            }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCenteredInputDataTopAppBar() {
    InputDataTopAppBar(
        title = "Input Data",
        namaUser = "John Doe",
        onBack = { },
        showBackButton = true
    )
}
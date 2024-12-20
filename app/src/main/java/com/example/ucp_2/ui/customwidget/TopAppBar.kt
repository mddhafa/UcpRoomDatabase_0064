package com.example.ucp_2.ui.customwidget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucp_2.R

@Preview(showBackground = true)
@Composable
fun NavbarPreview() {
    Navbar(namaUser = "John Doe", onBack = {})

}
@Composable
fun Navbar(
    namaUser:String,
    onBack: () -> Unit

){
    Box(Modifier.fillMaxWidth().size(150.dp)
        .clip(RoundedCornerShape(bottomEnd = 80.dp))
        .background(color = Color(red = 0, green = 191, blue = 255))){
        Row(modifier = Modifier
            .padding(top = 10.dp)
        ){

            Column(modifier = Modifier.padding(5.dp)) {
                Icon(
                    Icons.Filled.Menu, contentDescription = "",
                    tint = Color.White
                )
                Text("Hallo,",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp)
                Text(namaUser,
                    color = Color.White,
                    fontStyle = FontStyle.Italic,
                    fontSize = 15.sp)
                IconButton(
                    onClick = onBack,
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Kembali",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.weight(2f))

            }
            Spacer(modifier = Modifier.weight(1f))
            Box(modifier = Modifier.size(100.dp).padding(end = 10.dp)){
                Image(
                    painter = painterResource(id = R.drawable.umy),
                    contentDescription = "",
                    Modifier.size(100.dp).clip(shape = CircleShape)
                )
            }
        }
    }
}

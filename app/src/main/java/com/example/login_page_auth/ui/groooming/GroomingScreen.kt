package com.example.login_page_auth.ui.groooming

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.login_page_auth.PetTopAppBar
import com.example.login_page_auth.R
import com.example.login_page_auth.ui.detail.DetailDestination

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroomingScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    // Dummy data untuk contoh
    val groomingItems = listOf(
        GroomingItem("Grooming Paket Murah", "Grooming, Potong Kuku", R.drawable.g1),
        GroomingItem("Grooming Extra", "Grooming, Potong Kuku, dan Potong Rmabut", R.drawable.g1),
        // Tambahkan data GroomingItem lainnya sesuai kebutuhan
    )
    Scaffold(
        topBar = {
            PetTopAppBar(
                title = "Grooming Kit",
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        },
        modifier = modifier
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp)
        ) {
            items(groomingItems) { groomingItem ->
                GroomingCard(groomingItem = groomingItem)
            }
        }
    }
}

@Composable
fun GroomingCard(groomingItem: GroomingItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(145, 107, 120, 255)
        )

    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            // Gambar Grooming
            Image(
                painter = painterResource(id = groomingItem.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(shape = RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Informasi Grooming
            Text(text = groomingItem.title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = groomingItem.description, fontSize = 14.sp)
        }
    }
}

data class GroomingItem(val title: String, val description: String, @DrawableRes val imageRes: Int)

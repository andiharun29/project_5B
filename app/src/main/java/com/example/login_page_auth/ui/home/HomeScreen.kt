package com.example.login_page_auth.ui.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.login_page_auth.R


@Composable
fun HomeScreen(
   navController: NavController,
   ){

   // Set background color for the entire screen
   Box(
      modifier = Modifier
         .background(Color(255, 206, 206, 255))
         .fillMaxSize()
   ) {
      Image(
         painter = painterResource(id = R.drawable.homecat),
         contentDescription = null,
         contentScale = ContentScale.FillBounds,
         modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopCenter)
            .padding(50.dp)
            .size(150.dp) // Atur zIndex sesuai kebutuhan
      )
      Text(text = "Halaman Home Pet Care",
         color = Color(244, 67, 54, 255),
         fontWeight = FontWeight.Bold,
         fontSize = 25.sp ,
         modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopCenter)
            .padding(20.dp)

      )

      Column(
         verticalArrangement = Arrangement.Center,
         modifier = Modifier

            .fillMaxSize()
            .padding(16.dp)

      ) {
         SmallCard1(navController = navController, title = "Pet Hotel")

         Spacer(modifier = Modifier.height(16.dp))
         // Large card at the top

         // Two small cards below, arranged horizontally
         Row(
            modifier = Modifier
               .fillMaxWidth()
               .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
         ) {

            SmallCard2(navController = navController, title = "Data Penitipan")


            // Spacer between large card and small cards
            Spacer(modifier = Modifier.height(16.dp))

            SmallCard3(navController = navController, title = "List Grooming")

            // Spacer between small cards and button


         }
         Spacer(modifier = Modifier
            .height(16.dp)
         )

         // Button below the small cards
         Button(
            colors = ButtonDefaults.buttonColors(
               Color(241, 1, 1, 255)
            ),
            onClick =
            { navController.navigate("MainScreen")



            },
            modifier = Modifier
               .fillMaxWidth()
               .width(25.dp)
         ) {
            Text(text = "LogOut")
         }
      }
   }
}

@Composable
fun SmallCard1(navController: NavController, title: String) {
   Card(
      modifier = Modifier
         .padding(end = 8.dp), // Adjusted padding here
      colors = CardDefaults.cardColors(
         containerColor = Color(255, 122, 122, 255)
      )
   ) {
      Column(
         modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
         horizontalAlignment = Alignment.CenterHorizontally,
         verticalArrangement = Arrangement.Center
      ) {
         Text(text = title, fontWeight = FontWeight.Bold)
         Spacer(modifier = Modifier

            .height(16.dp))
         Button(
            colors = ButtonDefaults.buttonColors(
               Color(241, 1, 1, 255)
            ),
            onClick = { navController.navigate("PetHotelScreen") }
         ) {
            Text(text = "Titip Disini")
         }
      }
   }
}
@Composable
fun SmallCard2(navController: NavController, title: String) {
   Card(
      modifier = Modifier
         .fillMaxWidth(0.5f)
         .padding(end = 8.dp),
      colors = CardDefaults.cardColors(
         containerColor = Color(255, 122, 122, 255)
      )
      // Adjusted padding here
   ) {
      Column(
         modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
         horizontalAlignment = Alignment.CenterHorizontally,
         verticalArrangement = Arrangement.Center
      ) {
         Text(text = title, fontWeight = FontWeight.Bold)
         Spacer(modifier = Modifier.height(16.dp))
         Button(
            colors = ButtonDefaults.buttonColors(
               Color(241, 1, 1, 255)
            ),
            onClick = { navController.navigate("AllDataPet") }
         ) {
            Text(text = "Lihat")
         }
      }
   }
}

@Composable
fun SmallCard3(navController: NavController, title: String) {
   Card(
      modifier = Modifier
         .fillMaxWidth()
         .padding(end = 8.dp),
         colors = CardDefaults.cardColors(
         containerColor = Color(255, 122, 122, 255)
      )
      // Adjusted padding here
   ) {
      Column(
         modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
         horizontalAlignment = Alignment.CenterHorizontally,
         verticalArrangement = Arrangement.Center
      ) {
         Text(text = title, fontWeight = FontWeight.Bold)
         Spacer(modifier = Modifier.height(16.dp))
         Button(
            colors = ButtonDefaults.buttonColors(
               Color(241, 1, 1, 255)
            ),
            onClick = { navController.navigate("Product") }
         ) {
            Text(text = "Cek Disini")
         }
      }
   }
}






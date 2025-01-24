package com.example.vfc.Pages

import android.accounts.Account
import android.content.SharedPreferences
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.vfc.CartPage
import com.example.vfc.IconButtonVFC
import com.example.vfc.ItemCard
import com.example.vfc.LogoButtonVFC
import com.example.vfc.ProfilePage
import com.example.vfc.TextVFC
import com.example.vfc.ViewModel.UserViewModel
import com.example.vfc.ui.theme.Colors
import com.example.vfc.ui.theme.Fonts
import com.example.vfc.ui.theme.VFCTheme

@Composable
fun RestaurantPage(
    navController: NavHostController,
    sharedPreferences: SharedPreferences,
    firebaseModel: UserViewModel
) {
    VFCTheme {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButtonVFC(imageVector = Icons.Filled.ArrowBack, onClick = {navController.popBackStack()})
                Spacer(modifier = Modifier.width(20.dp))
                LogoButtonVFC(size = 120)
                Spacer(modifier = Modifier.width(20.dp))
                IconButtonVFC(onClick = {navController.navigate(CartPage.route)})
            }
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Colors.Primary,
                    contentColor = Colors.O4
                ), modifier = Modifier.fillMaxWidth(), shape = RectangleShape
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                TextVFC(
                    text = "KC Foods",
                    size = 40,
                    fontFamily = Fonts.headlines,
                    color = Colors.Secondary
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .width(500.dp)
                    ) {
                        TextVFC(
                            text = "All India Cuisine, \n" +
                                    "Non-veg & veg, \n" +
                                    "Between PRP and SJT\n" +
                                    "Wait time approx 20mins",
                            size = 15
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(40.dp).padding(start = 10.dp)) {
                    TextField(value ="", onValueChange ={}, label = { TextVFC(text = "Find dishes 🔎", size = 12, color = Colors.O3) }, shape = RoundedCornerShape(20.dp))
                }
                Spacer(modifier = Modifier.height(25.dp))
            }
            Column(modifier = Modifier
                .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                ItemCard(price = "50")
            }
        }
    }

}
//@Preview(showBackground = true)
//@Composable
//fun PreviewRestaurant(){
//    RestaurantPage(navController, sharedPreferences, firebaseModel)
//}
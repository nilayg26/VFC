package com.example.vfc.Pages

import android.content.SharedPreferences
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vfc.IconButtonVFC
import com.example.vfc.ItemCard
import com.example.vfc.LogoButtonVFC
import com.example.vfc.ViewModel.UserViewModel
import com.example.vfc.ui.theme.Colors
import com.example.vfc.ui.theme.Fonts
import com.example.vfc.ui.theme.VFCTheme

@Composable
fun CartPage(
    navController: NavHostController,
    sharedPreferences: SharedPreferences,
    firebaseModel: UserViewModel
) {
    VFCTheme{
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButtonVFC(imageVector = Icons.Filled.ArrowBack, onClick = {navController.popBackStack()})
                Spacer(modifier = Modifier.width(20.dp))
                LogoButtonVFC(size = 120)
                Spacer(modifier = Modifier.width(90.dp))
            }
            Column(
                modifier = Modifier
                    .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ItemCard(price = "50")
                Spacer(modifier = Modifier.height(40.dp))
                Button(modifier = Modifier.padding(10.dp).fillMaxWidth(),onClick = {  }, colors = ButtonDefaults.buttonColors(containerColor = Colors.Secondary, contentColor = Colors.Primary)) {
                    Text(text = "Place Order", fontFamily = Fonts.headlines, fontSize = 20.sp)
                }
            }
        }
    }
}
//@Preview(showBackground = true)
//@Composable
//fun PreviewCart(){
//    CartPage(navController, sharedPreferences, firebaseModel)
//}
package com.example.vfc.Pages

import android.content.SharedPreferences
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vfc.HomePage
import com.example.vfc.IconButtonVFC
import com.example.vfc.LogInPage
import com.example.vfc.LogoButtonVFC
import com.example.vfc.TextCardVFC
import com.example.vfc.TextFieldVFC
import com.example.vfc.ViewModel.UserViewModel
import com.example.vfc.ui.theme.Colors
import com.example.vfc.ui.theme.Fonts
import com.example.vfc.ui.theme.VFCTheme

@Composable
fun ProfilePage(
    navController: NavHostController,
    sharedPreferences: SharedPreferences,
    firebaseModel: UserViewModel
) {
    val info = remember { mutableStateListOf("", "") }
    val context= LocalContext.current
    val name by remember {
        mutableStateOf(sharedPreferences.getString("name",""))
    }
    val email by remember {
        mutableStateOf(sharedPreferences.getString("email",""))
    }
    val reg by remember {
        mutableStateOf(sharedPreferences.getString("reg",""))
    }

    VFCTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
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
            TextCardVFC(text = "All about you!")
            Spacer(modifier = Modifier.height(20.dp))
            TextFieldVFC(text = name.toString(), label = "Name", password = false) {
                it
            }
            TextFieldVFC(text = email.toString(), label = "Email", password = false) {
                it
            }
            TextFieldVFC(text = reg.toString(), label = "Registration Number", password = false) {
                it
            }
            Spacer(modifier = Modifier.height(100.dp))
            Button(onClick = {
               firebaseModel.signOut(context,sharedPreferences)
                navController.navigate(LogInPage.route){
                    popUpTo(HomePage.route) {
                        inclusive = true
                    }
                }
            }, colors = ButtonDefaults.buttonColors(containerColor = Colors.O4, contentColor = Colors.Primary)) {
                Text(text = "Log Out", fontFamily = Fonts.paragraph, fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(90.dp))
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewProfile(){
//    ProfilePage(navController, sharedPreferences, firebaseModel)
//}
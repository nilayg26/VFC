package com.example.vfc

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vfc.Pages.CartPage
import com.example.vfc.Pages.ForgotPassword
import com.example.vfc.Pages.HomePage
import com.example.vfc.Pages.LogIn
import com.example.vfc.Pages.ProfilePage
import com.example.vfc.Pages.RestaurantPage
import com.example.vfc.Pages.SignUp
import com.example.vfc.ViewModel.UserViewModel
import com.example.vfc.ui.theme.VFCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences=this.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
        val firebaseModel:UserViewModel by viewModels()
        setContent {
            VFCTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   Navigation(firebaseModel = firebaseModel, sharedPreferences =sharedPreferences )
                }
            }
        }
    }
}

@Composable
fun Navigation(
    firebaseModel: UserViewModel,
    sharedPreferences: SharedPreferences,
){
    val navController= rememberNavController()
    val status= sharedPreferences.getBoolean("LoginStatus",false)
    NavHost(navController = navController, startDestination = when(status){
        true->HomePage.route
        else->LogInPage.route
    }){
        composable(LogInPage.route, enterTransition = { scaleIn() }, exitTransition = { scaleOut() }) {
            LogIn(navController,sharedPreferences,firebaseModel)
        }
        composable(SignInPage.route, enterTransition = { scaleIn() }, exitTransition = { scaleOut() }){
            SignUp(navController,firebaseModel,sharedPreferences)
        }
        composable(HomePage.route, enterTransition = { scaleIn() }, exitTransition = { scaleOut() }) {
            HomePage(navController,sharedPreferences,firebaseModel)
        }
        composable(ProfilePage.route, enterTransition = { scaleIn() }, exitTransition = { scaleOut() }) {
            ProfilePage(navController,sharedPreferences,firebaseModel)
        }
        composable(CartPage.route) {
            CartPage(navController,sharedPreferences,firebaseModel)
        }
        composable(RestaurantPage.route) {
            RestaurantPage(navController,sharedPreferences,firebaseModel)
        }
        composable(ForgotPassword.route, enterTransition = { scaleIn() }, exitTransition = { scaleOut() }) {
           ForgotPassword(firebaseModel,navController)
        }
    }
}
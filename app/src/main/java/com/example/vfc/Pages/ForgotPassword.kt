package com.example.vfc.Pages
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.vfc.ForgotPassword
import com.example.vfc.GoButton
import com.example.vfc.LogInPage
import com.example.vfc.LogoButtonVFC
import com.example.vfc.TextCardVFC
import com.example.vfc.TextFieldVFC
import com.example.vfc.ViewModel.EmailNotVerified
import com.example.vfc.ViewModel.Loading
import com.example.vfc.ViewModel.UserViewModel
import com.example.vfc.check
import com.example.vfc.ui.theme.VFCTheme

@Composable
fun ForgotPassword(firebaseModel:UserViewModel,navController: NavController){
    var email by remember {
        mutableStateOf("")
    }
    val context= LocalContext.current
    val authState=firebaseModel.authState.observeAsState()
    var isLoading by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(authState.value) {
        when(authState.value){
            Loading->{isLoading=true}
            EmailNotVerified->{navController.navigate(LogInPage.route){
                popUpTo(ForgotPassword.route){
                    inclusive=true
                }
            }}
            else->{
                isLoading=false
            }
        }
    }
    VFCTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoButtonVFC()
            Spacer(modifier = Modifier.height(10.dp))
            TextCardVFC(text = "Memory issue? \n\n  No Problem!", size = 28)
            Spacer(modifier = Modifier.height(20.dp))
            TextFieldVFC(text = email, label = "Enter VIT Email", password = false) {
                email = it
                email
            }
            Spacer(modifier = Modifier.height(100.dp))
            GoButton(text = "Send Link",isLoading=isLoading) {
                if(check(listOf(email),context)){firebaseModel.forgotPassword(email = email, context = context)}
            }
            Spacer(modifier = Modifier.height(90.dp))
        }
    }
}
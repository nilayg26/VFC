package com.example.vfc.Pages
import android.content.SharedPreferences
import androidx.compose.foundation.clickable
import com.example.vfc.check
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vfc.HomePage
import com.example.vfc.LoadingScreenLL
import com.example.vfc.LogInPage
import com.example.vfc.LogoButtonVFC
import com.example.vfc.SignInPage
import com.example.vfc.TextCardVFC
import com.example.vfc.TextFieldVFC
import com.example.vfc.ViewModel.Authenticated
import com.example.vfc.ViewModel.EmailNotVerified
import com.example.vfc.ViewModel.Loading
import com.example.vfc.ViewModel.Unauthenticated
import com.example.vfc.ViewModel.UserViewModel
import com.example.vfc.createToastMessage
import com.example.vfc.ui.theme.Colors
import com.example.vfc.ui.theme.Fonts
import com.example.vfc.ui.theme.VFCTheme
@Composable
fun LogIn(
    navController: NavHostController,
    sharedPreferences: SharedPreferences,
    firebaseModel: UserViewModel
) {
    val info = remember { mutableStateListOf("", "") }
    val context= LocalContext.current
    val authState=firebaseModel.authState.observeAsState()
    var isLoading by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(authState.value){
        when(authState.value){
            Loading -> {isLoading=true}
            Authenticated->{navController.navigate(HomePage.route){
                popUpTo(LogInPage.route) {
                    inclusive=true
                }
            };isLoading=false}
            EmailNotVerified->{isLoading=false}
            Unauthenticated ->{isLoading=false}
            else->{isLoading=false}
        }
    }
    VFCTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoButtonVFC()
            Spacer(modifier = Modifier.height(10.dp))
            TextCardVFC(text = "Lets Get You In !")
            Spacer(modifier = Modifier.height(20.dp))
            TextFieldVFC(text = info[0], label = "Enter VIT Email", password = false) {
                info[0] = it
                info[0]
            }
            TextFieldVFC(text = info[1], label = "Enter Password") {
                info[1] = it
                info[1]
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Forgot Password?", modifier = Modifier
                .clickable {}, color = Colors.Tertiary, fontFamily = Fonts.paragraph
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                if (check(info.toList(),context)) {
                    sharedPreferences.edit().putString("email",info[0]).apply()
                    firebaseModel.logIn(context,sharedPreferences, pass = info[1])
                }
            }, colors = ButtonDefaults.buttonColors(containerColor = Colors.Secondary, contentColor = Colors.Primary)) {
                if (isLoading){
                    LoadingScreenLL()
                }
                else{
                    Text(text = "Lets Go! ", fontFamily = Fonts.paragraph, fontSize = 18.sp)
                }
            }
            Spacer(modifier = Modifier.height(90.dp))
            Text(text = "Newbie? Sign In", modifier = Modifier
                .clickable {navController.navigate(SignInPage.route)}, color = Colors.Tertiary, fontFamily = Fonts.paragraph
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewLogIn(){
//    LogIn(navController, sharedPreferences, firebaseModel)
//}
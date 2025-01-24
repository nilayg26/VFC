package com.example.vfc.Pages
import android.content.SharedPreferences
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vfc.LoadingScreenLL
import com.example.vfc.LogoButtonVFC
import com.example.vfc.TextCardVFC
import com.example.vfc.TextFieldVFC
import com.example.vfc.ViewModel.Authenticated
import com.example.vfc.ViewModel.EmailNotVerified
import com.example.vfc.ViewModel.Loading
import com.example.vfc.ViewModel.Unauthenticated
import com.example.vfc.ViewModel.UserViewModel
import com.example.vfc.check
import com.example.vfc.createToastMessage
import com.example.vfc.ui.theme.Colors
import com.example.vfc.ui.theme.Fonts
import com.example.vfc.ui.theme.VFCTheme

@Composable
fun SignUp(
    navController: NavHostController,
    firebaseModel: UserViewModel,
    sharedPreferences: SharedPreferences
) {
    val info = remember { mutableStateListOf("","", "", "", "") }
    val verticaScroll= rememberScrollState()
    val authState=firebaseModel.authState.observeAsState()
    var isLoading by remember {
        mutableStateOf(false)
    }
    val context= LocalContext.current
    LaunchedEffect(authState.value){
       when(authState.value){
           Loading-> {isLoading=true}
          EmailNotVerified ->{navController.popBackStack(); isLoading=false}
          Authenticated->{navController.popBackStack(); isLoading=false}
           else->{isLoading=false}
       }
    }
    VFCTheme {
        val context= LocalContext.current
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(verticaScroll),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoButtonVFC()
            Spacer(modifier = Modifier.height(10.dp))
            TextCardVFC(text = "Lets Connect !")
            Spacer(modifier = Modifier.height(20.dp))
            TextFieldVFC(text = info[0], label = "Enter name", password = false) {
                info[0] = it
                info[0]
            }
            TextFieldVFC(text = info[1], label = "Enter Registration No.", password = false) {
                info[1] = it
                info[1]
            }
            TextFieldVFC(text = info[2], label = "Enter VIT Email", password = false) {
                info[2] = it
                info[2]
            }
            TextFieldVFC(text = info[3], label = "Set Password") {
                info[3] = it
                info[3]
            }
            TextFieldVFC(text = info[4], label = "Confirm Password") {
                info[4] = it
                info[4]
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                if (check(info.toList(), context = context)) {
                    sharedPreferences.edit().putString("name", info[0]).
                        putString("reg", info[1]).putString("email",info[2]).apply()
                        firebaseModel.signUp(sharedPreferences = sharedPreferences, context = context, pass = info[3])
                }
            }, colors = ButtonDefaults.buttonColors(containerColor = Colors.Secondary, contentColor = Colors.Primary)) {
                if (isLoading){
                    LoadingScreenLL()
                }
                else{
                    Text(text = "Lets Go! ", fontFamily = Fonts.paragraph, fontSize = 18.sp)
                }

            }
        }
    }
}
//@Preview(showBackground = true)
//@Composable
//fun PreviewSignUp(){
//    SignUp(navController, firebaseModel, sharedPreferences)
//}


package com.example.vfc.Pages
import android.content.SharedPreferences
import androidx.compose.foundation.clickable
import com.example.vfc.check
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vfc.LogoButtonVFC
import com.example.vfc.TextCardVFC
import com.example.vfc.TextFieldVFC
import com.example.vfc.ui.theme.Colors
import com.example.vfc.ui.theme.Fonts
import com.example.vfc.ui.theme.VFCTheme
@Composable
fun LogIn() {
    val info = remember { mutableStateListOf("", "") }
    val context= LocalContext.current
    VFCTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            LogoButtonVFC()
            Spacer(modifier = Modifier.height(20.dp))
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

                }
                else{

                }
            }, colors = ButtonDefaults.buttonColors(containerColor = Colors.O4, contentColor = Colors.Primary)) {
                Text(text = "Lets Go! ", fontFamily = Fonts.paragraph, fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(90.dp))
            Text(text = "Newbie? Sign In", modifier = Modifier
                .clickable {}, color = Colors.Tertiary, fontFamily = Fonts.paragraph
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLogIn(){
    LogIn()
}
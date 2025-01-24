package com.example.vfc.Pages
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.vfc.LogoButtonVFC
import com.example.vfc.TextCardVFC
import com.example.vfc.TextFieldVFC
import com.example.vfc.check
import com.example.vfc.ui.theme.Colors
import com.example.vfc.ui.theme.Fonts
import com.example.vfc.ui.theme.VFCTheme

@Composable
fun SignUp() {
    val info = remember { mutableStateListOf("","", "", "", "") }
    VFCTheme {
        val context= LocalContext.current
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            LogoButtonVFC()
            Spacer(modifier = Modifier.height(20.dp))
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
            TextFieldVFC(text = info[2], label = "Enter Email", password = false) {
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

                }
                else{

                }
            }, colors = ButtonDefaults.buttonColors(containerColor = Colors.O4, contentColor = Colors.Primary)) {
                Text(text = "Lets Go! ", fontFamily = Fonts.paragraph, fontSize = 18.sp)
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewSignUp(){
    SignUp()
}


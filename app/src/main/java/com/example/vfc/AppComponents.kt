package com.example.vfc

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vfc.ui.theme.Colors
import com.example.vfc.ui.theme.Fonts

@Composable
fun TextFieldVFC(text: String, password:Boolean=true, label: String="", lamda: (String) -> String){
    var passwordVisible by remember {
        mutableStateOf(true)
    }
    OutlinedTextField(value = text, onValueChange ={newVal->lamda(newVal)}, label = { Text(
        text = label
    )
    },
        shape = RoundedCornerShape(20.dp), modifier = Modifier.padding(top = 10.dp),
        colors = OutlinedTextFieldDefaults.colors(Color.Black, Color.Black),
        visualTransformation =
        if (passwordVisible && password) PasswordVisualTransformation() else VisualTransformation.None ,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if(!password){
                Icons.Filled.AccountBox}else if (passwordVisible)
                Icons.Filled.Favorite
            else Icons.Filled.FavoriteBorder
            val description = if (passwordVisible) "Hide password" else "Show password"
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, contentDescription = description)
            }
        }
    )
}
@Composable
fun TextCardVFC(text:String){
    Card(colors = CardDefaults.cardColors(containerColor = Colors.Primary, contentColor = Colors.O4), modifier = Modifier.fillMaxWidth(), shape = RectangleShape) {
        Text(
            text = text,
            fontFamily = Fonts.paragraph,
            fontSize = 28.sp,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(40.dp)
        )
    }
}

@Composable
fun LogoButtonVFC(){
        Image(painter = painterResource(id = R.drawable.app_logo), contentDescription = "logo",
            Modifier
                .size(100.dp))
       // Text(text = "Little Lemon", fontFamily = Fonts.headlines, fontSize = 28.sp)
}
fun check(list: List<String>,context: Context):Boolean{
    if (list.contains("")){
        context.createToastMessage("Fields cannot be empty")
        return false
    }
    if(list.size==5){
        if(!list[2].contains("@vitstudent.ac.in")){
            context.createToastMessage("Enter valid email address")
            return  false
        }
        if (list[3]!=list[4]){
            context.createToastMessage("Password does not match")
            return false
        }
    }
    if(list.size==2){
        if(!list[1].contains("@vitstudent.ac.in")){
            context.createToastMessage("Enter valid email address")
            return  false
        }
    }
    return true
}

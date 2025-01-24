package com.example.vfc

import android.content.Context
import android.content.SharedPreferences
import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
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
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun IconButtonVFC( imageVector: ImageVector=Icons.Filled.ShoppingCart,onClick:()->(Unit)={}, size: Int=40) {
    IconButton(modifier = Modifier.size(size.dp),onClick = onClick, colors = IconButtonDefaults.iconButtonColors(containerColor = Colors.Secondary)) {
        val picUrl=""
        Icon(imageVector = imageVector, contentDescription ="shopping cart")
    }
}

@Composable
fun LogoButtonVFC(size: Int=100){
        Image(painter = painterResource(id = R.drawable.app_logo), contentDescription = "logo",
            Modifier
                .size(size.dp))
       // Text(text = "Little Lemon", fontFamily = Fonts.headlines, fontSize = 28.sp)
}
@Composable
fun TextVFC(text: String="Little Lemon", size:Int=30, fontFamily: FontFamily =Fonts.paragraph, color: Color =Colors.O4){
    Text(
        text = text,
        fontFamily = fontFamily,
        fontSize = size.sp,
        color = color,
        modifier = Modifier
            .padding(start = 10.dp)
    )
}
@Composable
fun ButtonVFC(text:String="",color: Color=Colors.Secondary,fontSize:Int=18,onClick:()->(Unit)={}){
    Button(onClick = onClick, colors = ButtonDefaults.buttonColors(containerColor = color, contentColor = Colors.Primary)) {
        Text(text = text, fontFamily = Fonts.paragraph, fontSize = fontSize.sp)
    }
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
@Composable
fun LoadingScreenLL(size: Int=25) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            strokeWidth = 5.dp,
            modifier = Modifier.size(size.dp)
        )
    }

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RestaurantCard(
    name: String = "KC foods",
    des: String = "All India Cuisine, \nNon-veg & veg, \nBetween PRP and SJT",
    picUrl: String = "",
    price: String
){
    Spacer(modifier = Modifier.height(10.dp))
    Card(modifier = Modifier
        .padding(start = 10.dp, end = 10.dp)
        .fillMaxWidth(), colors = CardDefaults.cardColors(Color.White), elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Spacer(modifier = Modifier.height(5.dp))
        Text(text=name, fontFamily = Fonts.paragraph, fontWeight = FontWeight.Bold,
            color = Colors.O3, fontSize = 25.sp, modifier = Modifier.padding(start = 10.dp)
        )
        Row(modifier = Modifier.fillMaxWidth(), Arrangement.SpaceBetween,Alignment.CenterVertically) {
            Box(modifier = Modifier.width(200.dp)){
                TextVFC(text = des, color = Colors.Primary, size = 12)
            }
            Box(modifier = Modifier
                .padding(10.dp)
                .size(80.dp)
                .clip(RoundedCornerShape(10.dp))){
                GlideImage(
                    model = picUrl,
                    contentDescription = "Food pic",
                    modifier = Modifier.matchParentSize(),
                    contentScale = ContentScale.Crop,
                    loading = placeholder(R.drawable.lemon_dessert)
                )
            }
        }
        TextVFC(text = "Rs.$price", size = 12, color = Colors.O3)
        Spacer(modifier = Modifier.height(10.dp))
    }
}

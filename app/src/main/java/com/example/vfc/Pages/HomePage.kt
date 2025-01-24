package com.example.vfc.Pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vfc.IconButtonVFC
import com.example.vfc.LogoButtonVFC
import com.example.vfc.R
import com.example.vfc.RestaurantCard
import com.example.vfc.TextVFC
import com.example.vfc.ui.theme.Colors
import com.example.vfc.ui.theme.Fonts
import com.example.vfc.ui.theme.VFCTheme

@Composable
fun HomePage(){
    VFCTheme {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButtonVFC(imageVector = Icons.Filled.AccountBox)
                Spacer(modifier = Modifier.width(20.dp))
                LogoButtonVFC(size = 120)
                Spacer(modifier = Modifier.width(20.dp))
                IconButtonVFC()
            }
            Spacer(modifier = Modifier.height(10.dp))
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Colors.Primary,
                    contentColor = Colors.O4
                ), modifier = Modifier.fillMaxWidth(), shape = RectangleShape
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                TextVFC(
                    text = "Welcome to VFC!",
                    size = 40,
                    fontFamily = Fonts.headlines,
                    color = Colors.Secondary
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .size(180.dp)
                    ) {
                        TextVFC(
                                text = "Connecting Food, Connecting VIT\nBrowse menus, Take-away, and pick up all in one app",
                            size = 15
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(bottom = 25.dp, end = 10.dp)
                            .size(150.dp)
                            .clip(RoundedCornerShape(16.dp))

                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.lemon_dessert),
                            contentDescription = "Hero Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.matchParentSize()
                        )
                    }
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(40.dp),horizontalArrangement = Arrangement.Center) {
                    TextField(value ="", onValueChange ={}, label = { TextVFC(text = "Find food spots 🔎", size = 12, color = Colors.O3)}, shape = RoundedCornerShape(20.dp))
                }
                Spacer(modifier = Modifier.height(25.dp))
            }
            Column(modifier = Modifier
                .fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                RestaurantCard(price = "100/person appx.")
            }
        }
    }

}
@Preview(showBackground = true)
@Composable
fun PreviewHome(){
    HomePage()
}

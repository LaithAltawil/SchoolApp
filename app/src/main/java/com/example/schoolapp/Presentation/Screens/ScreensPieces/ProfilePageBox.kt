package com.example.schoolapp.Presentation.Screens.ScreensPieces

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

//todo @LT Under Going changes to make every box have different data
@Composable
fun ProfilePageBox(
    Title:String,
    listOfLists: List<List<String>>
){
    Box(modifier = Modifier

        .clip(RoundedCornerShape(26.dp))
        .border(3.dp, Color.White,
            shape = RoundedCornerShape(26.dp)
        )
        .size(337.dp)

        .background(MaterialTheme.colorScheme.primary)){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            Text(text = Title,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White)
            Spacer(modifier = Modifier.height(20.dp))
            Row(horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween
                , modifier = Modifier
            ) {

                    Text(
                        text = " " ,
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.White
                    )
                    Text(
                        text = " ",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(10.dp))




            }
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Text(
                    text = "Age : ",
                    style = MaterialTheme.typography.headlineSmall
                    ,
                    color = Color.White
                )
                Text(
                    text = "20",
                    style = MaterialTheme.typography.headlineSmall
                    ,
                    color = Color.White
                )

            }
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Text(
                    text = "Gender : ",
                    style = MaterialTheme.typography.headlineSmall
                    ,
                    color = Color.White
                )
                Text(
                    text = "Male",
                    style = MaterialTheme.typography.headlineSmall
                    ,
                    color = Color.White
                )

            }
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Text(
                    text = "Email : ",
                    style = MaterialTheme.typography.headlineSmall
                    ,
                    color = Color.White
                )
                Text(
                    text = "",
                    style = MaterialTheme.typography.headlineSmall
                    ,
                    color = Color.White
                )
            }



        }
    }


}
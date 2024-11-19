package com.example.schoolapp.Presentation.Screens.ScreensPieces

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.schoolapp.Presentation.VM.MainViewModel

//=======================================================
//Designs of top app bar                                =
//=======================================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeworksTopAppBar(viewModel: MainViewModel, modifier: Modifier, title: String) {
    //=======================================================
    //variables: Local & states                             =
    //=======================================================
    val state by viewModel.state.collectAsState()

    //TAB main UI & Logic
    LargeTopAppBar(
        title = {
            //TAB Main UI: Row
            Row(modifier = Modifier.fillMaxWidth()) {
                //TAB title
                Text(
                    title, fontSize = 60.sp,
                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                    modifier = Modifier.padding(start = 40.dp)
                )
            }
        },
        modifier = modifier.clip(
            RoundedCornerShape(
                bottomEnd = 25.dp,
                bottomStart = 25.dp
            )
        ),
        colors =
        TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        navigationIcon = {
            IconButton(onClick = {
                /*TODO*/
            }) {
                Icon(
                    modifier = Modifier.size(50.dp),
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "Localized description",
                    tint = MaterialTheme.colorScheme.background
                )

            }

        }
    )
}

@Composable
@Preview
fun HomeworksTopAppBarPreview() {
    HomeworksTopAppBar(viewModel = MainViewModel(), modifier = Modifier, title = "Title")
}
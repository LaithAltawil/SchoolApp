package com.example.schoolapp.Presentation.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.compose.AppTheme
import com.example.schoolapp.Data.MockData.Mock.Titles
import com.example.schoolapp.Presentation.Screens.ScreensPieces.ProfilePageBox
import com.example.schoolapp.Presentation.VM.MainViewModel
import com.example.schoolapp.R

//=======================================================
//profile page                                          =
//=======================================================
@Composable
fun Profile_page(viewModel: MainViewModel, navController: NavController) {
    //=======================================================
    //variables: local & states                             =
    //=======================================================
    //states                                                =
    //=======================================================
    val studentState = viewModel.student.collectAsState()
    val parentState = viewModel.parent.collectAsState()

    //=======================================================
    //local variables                                       =
    //=======================================================
    val studentFirstName = studentState.value?.studentFirstName
    val studentSecondName = studentState.value?.studentSecondName
    val studentDetails: MutableList<MutableList<String>> = mutableListOf(
        mutableListOf("الصف", studentState.value?.studentClass.toString()),
        mutableListOf("الجنس", studentState.value?.studentGender.toString()),
        mutableListOf("الجنسية", studentState.value?.studentNationality.toString()),
        mutableListOf(
            "يوم الميلاد",
            studentState.value?.studentDateOfBirth.toString()
        ),
        mutableListOf("", "")
    )
    val parentsDetails: MutableList<MutableList<String>> = mutableListOf(
        mutableListOf("الاسم", studentSecondName.toString()),
        mutableListOf("رقم الهاتف", parentState.value?.parentPhoneNumber.toString()),
        mutableListOf("الوظيفة", parentState.value?.parentJob.toString()),
        mutableListOf("الجنسية", parentState.value?.parentNationality.toString()),
        mutableListOf("عنوان ", parentState.value?.parentAddress.toString())

    )
    val otherDetails: MutableList<MutableList<String>> = mutableListOf(
        mutableListOf("الرقم الوطني", studentState.value?.studentNationalId.toString()),
        mutableListOf("مكان الميلاد", studentState.value?.studentPlaceOfBirth.toString()),
        mutableListOf("عنوان السكن", studentState.value?.studentCity.toString()),
        mutableListOf("الحي", studentState.value?.studentResidence.toString()),
        mutableListOf("", "")
    )
    val allDetails: MutableList<MutableList<MutableList<String>>> = mutableListOf(
        studentDetails,
        parentsDetails,
        otherDetails
    )

    //=======================================================
    // page functions: UI & logic                           =
    //=======================================================
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Scaffold(containerColor = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    Column(
                        modifier = Modifier,
                        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier.clip(RoundedCornerShape(16.dp))
                                .width(450.dp)
                                .height(200.dp)
                                .background(MaterialTheme.colorScheme.primary),
                            contentAlignment = androidx.compose.ui.Alignment.TopStart,
                        ) {
                            Button(
                                onClick = { navController.popBackStack() },
                                modifier = Modifier.padding(top = 40.dp),
                                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    contentColor = MaterialTheme.colorScheme.onSecondary
                                )
                            ) {
                                Icon(
                                    Icons.Filled.ArrowBack,
                                    contentDescription = "Localized description",
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                        }
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Button(
                                onClick = {

                                    /*TODO*/

                                },
                                modifier = Modifier
                                    .size(200.dp)
                                    .offset(y = (-100).dp), // Keep only vertical offset if needed
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.onPrimary,
                                    contentColor = MaterialTheme.colorScheme.onSecondary
                                )
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.contact_us),
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxSize() // Makes image fill the button
                                )
                            }
                        }
                    }
                }
            ) {
                Column(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxWidth()
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(), // Make Row take full width
                            horizontalArrangement = Arrangement.Center // Center the content horizontally
                        ) {
                            Text(
                                text = studentFirstName ?: "",
                                style = MaterialTheme.typography.headlineLarge,
                                modifier = Modifier
                                    .offset(y = -80.dp) // Keep only vertical offset if needed
                                    .align(Alignment.CenterVertically), // Center text vertically within the Row
                                textAlign = TextAlign.Center // Center the text content itself
                            )
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxSize(),
                            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
                        ) {
                            LazyColumn(modifier = Modifier) {
                                itemsIndexed(allDetails) { index, item ->
                                    Box(
                                        modifier = Modifier
                                            .padding(bottom = 10.dp)
                                            .size(350.dp)
                                            .clip(RoundedCornerShape(26.dp))
                                            .background(MaterialTheme.colorScheme.primary),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        ProfilePageBox(Titles[index], allDetails, index)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
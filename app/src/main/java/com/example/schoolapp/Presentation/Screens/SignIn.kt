package com.example.schoolapp.Presentation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.SavedStateHandle
import com.example.compose.AppTheme
import com.example.schoolapp.Presentation.VM.AppViewModel
import com.example.schoolapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignIn(ViewModel: AppViewModel = AppViewModel()) {
    val state = ViewModel.signInState.collectAsState()

    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize().imePadding()
                .background(color = MaterialTheme.colorScheme.primaryContainer),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .width(400.dp)
                    .height(500.dp)
                    .clip(
                        RoundedCornerShape(20.dp)
                    )
                    .background(color = MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center

            ) {
                Column(
                    Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Welcome Back Dear Student",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.displayMedium,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.size(30.dp))
                    //TextFields = 2 and a button
                    TextField(
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        value = state.value.UserName,
                        modifier = Modifier.clip(RoundedCornerShape(20.dp)),
                        onValueChange = {
                            ViewModel.onUserNameChange(it)
                        })
                    Spacer(modifier = Modifier.size(15.dp))
                    TextField(
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        value = state.value.password,
                        modifier = Modifier.clip(RoundedCornerShape(20.dp)),
                        onValueChange = {
                            ViewModel.onPasswordChange(it)
                        },
                        visualTransformation = if (state.value.isPasswordVisible)
                            VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            val image = if (state.value.isPasswordVisible) {
                                painterResource(id = R.drawable.visibility)
                            } else {
                                painterResource(id = R.drawable.visibility_off)
                            }
                            val description =
                                if (state.value.isPasswordVisible) "Hide password" else "Show password"
                            IconButton(onClick = {
                                ViewModel.onPasswordVisibilityChange()
                            }) {
                                Icon(painter = image, contentDescription = description)


                            }


                        }
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    Button(onClick = { /*TODO*/ }) {
                        Text(
                            text = "Sign In",
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )

                    }


                }

            }


        }


    }


}

@Composable
@Preview
fun SignInPreview() {
    SignIn()
}


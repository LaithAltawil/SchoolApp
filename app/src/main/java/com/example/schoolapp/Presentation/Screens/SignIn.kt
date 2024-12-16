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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.compose.AppTheme
import com.example.schoolapp.Presentation.Util.SignInCallBack
import com.example.schoolapp.Presentation.VM.AppViewModel
import com.example.schoolapp.R

//=======================================================
//Sign in page: Logic & UI                              =
//=======================================================
@Composable
fun SignIn(viewModel: AppViewModel, onClick: () -> Unit = {}) {
    //=======================================================
    //variables: local & states                             =
    //=======================================================
    val state = viewModel.signInState.collectAsState()
    var isLoading by remember { mutableStateOf(false) }
    val context = LocalContext.current
    //=======================================================
    //Logic & UI                                            =
    //=======================================================
    AppTheme {
        //Main page UI: Column
        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .background(color = MaterialTheme.colorScheme.primaryContainer),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            //main UI: Box
            Box(
                modifier = Modifier
                    .width(360.dp)
                    .height(430.dp)
                    .clip(
                        RoundedCornerShape(16.dp)
                    )
                    .background(color = MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                //Main Box UI:Column
                Column(
                    Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Welcome Back",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.displayMedium,
                        overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.size(30.dp))
                    //TextFields = 2 and a button
                    TextField(
                        label = { Text(text = "Username") },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        //save value to the ViewModel
                        value = state.value.userName,
                        modifier = Modifier.clip(RoundedCornerShape(20.dp)),
                        onValueChange = {
                            viewModel.onUserNameChange(it)
                        })
                    Spacer(modifier = Modifier.size(15.dp))
                    TextField(
                        label = { Text(text = "Password") },
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        //store value to the viewModel
                        value = state.value.password,
                        modifier = Modifier.clip(RoundedCornerShape(20.dp)),
                        onValueChange = {
                            viewModel.onPasswordChange(it)
                        },
                        visualTransformation = if (state.value.isPasswordVisible)
                            VisualTransformation.None else PasswordVisualTransformation(),
                        //password eye logic
                        trailingIcon = {
                            val image = if (state.value.isPasswordVisible) {
                                painterResource(id = R.drawable.visibility)
                            } else {
                                painterResource(id = R.drawable.visibility_off)
                            }
                            val description =
                                if (state.value.isPasswordVisible) "Hide password" else "Show password"
                            IconButton(onClick = {
                                viewModel.onPasswordVisibilityChange()
                            }) {
                                Icon(painter = image, contentDescription = description)
                            }
                        }
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    Button(modifier = Modifier.clip(RoundedCornerShape(1.dp)),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.primary
                        ), onClick = {
                            // Validation logic
                            if (state.value.userName.isEmpty() || state.value.password.isEmpty()) {
                                viewModel.notifyMessage(context, "Please enter both username and password")
                                return@Button
                            }

                            isLoading = true

                            viewModel.signInFromApi(object : SignInCallBack {
                                override fun onSuccess(message: String) {
                                    isLoading = false
                                    // Handle success
                                    if (viewModel.checkStudent(context)) {
                                        viewModel.insertApiStudentToLocalDatabase()
                                        onClick()
                                    }
                                }

                                override fun onFailure(error: String) {
                                    isLoading = false
                                    // Handle failure
                                    viewModel.notifyMessage(context, error)
                                }
                            })

                        }) {
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
fun SignInPage() {
    //SignIn()
}

@Composable
fun LoadingDialog() {
    Dialog(onDismissRequest = { /* Handle dismiss if needed */ }) {
        CircularProgressIndicator()
    }
}
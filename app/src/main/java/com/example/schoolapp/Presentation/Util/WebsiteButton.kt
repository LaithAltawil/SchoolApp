package com.example.schoolapp.Presentation.Util

import android.widget.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebsiteButton(url: String) {
    val context = LocalContext.current
    AndroidView(
        factory = {
            Button(it).apply {
                text = "Open Website"
                setOnClickListener {
                    openWebsite(context, url)
                }
            }
        }
    )
}
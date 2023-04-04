package com.leebeebeom.composeinlinelambdastatetest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.leebeebeom.composeinlinelambdastatetest.ui.theme.ComposeInlineLambdaStateTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeInlineLambdaStateTestTheme {
                var num by remember { mutableStateOf(0) }

                ScreenLevel1(text = { num.toString() }) { num++ }.also {
                    Log.d(TAG, "ScreenLevel1 Column: 리컴포즈")
                }
            }
        }
    }
}

private const val TAG = "태그"

@Composable
fun ScreenLevel1(
    text: () -> String,
    onClick: () -> Unit
) {
    Log.d(TAG, "ScreenLevel1: 리컴포즈")
    Column {
        ScreenLevel2(text = text, onClick)
    }
}

@Composable
fun ScreenLevel2(text: () -> String, onClick: () -> Unit) {
    Log.d(TAG, "ScreenLevel2: 리컴포즈")
    Column {
        TextWrapper(text = text).also {
                Log.d(TAG, "TextWrapper: 리컴포즈")
            }
        Button(onClick = onClick) {}.also {
            Log.d(TAG, "Button: 리컴포즈")
        }
    }
}

@Composable
inline fun TextWrapper(text: () -> String) {
    Text(text = text())
}

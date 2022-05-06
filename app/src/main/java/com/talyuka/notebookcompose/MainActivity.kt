package com.talyuka.notebookcompose

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.talyuka.notebookcompose.navigation.NotesNavHost
import com.talyuka.notebookcompose.ui.theme.NoteBookComposeTheme
import com.talyuka.notebookcompose.utils.Constants.Keys.NAME_APP

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteBookComposeTheme {
                val context = LocalContext.current
                val mViewModel: MainViewModel =
                    viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = NAME_APP,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    fontStyle = FontStyle.Italic,
                                    color = MaterialTheme.colors.secondaryVariant
                                )
                            },
                            backgroundColor = MaterialTheme.colors.primaryVariant,
                            contentColor = Color.Blue,
                            elevation = 10.dp,
                        )
                    },
                    content = {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colors.background
                        ) {
                            NotesNavHost(mViewModel)
                        }
                    }
                )
            }
        }
    }
}
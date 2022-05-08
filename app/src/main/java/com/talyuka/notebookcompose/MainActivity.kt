package com.talyuka.notebookcompose

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.talyuka.notebookcompose.navigation.NavRoute
import com.talyuka.notebookcompose.navigation.NotesNavHost
import com.talyuka.notebookcompose.ui.theme.NoteBookComposeTheme
import com.talyuka.notebookcompose.utils.Constants.Keys.NAME_APP
import com.talyuka.notebookcompose.utils.DB_TYPE

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteBookComposeTheme {
                val context = LocalContext.current
                val mViewModel: MainViewModel =
                    viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
                val navController = rememberNavController()
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = NAME_APP,
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontStyle = FontStyle.Italic,
                                        color = MaterialTheme.colors.secondaryVariant
                                    )
                                    if (DB_TYPE.value.isNotEmpty()) {
                                        Icon(
                                            imageVector = Icons.Default.ExitToApp,
                                            contentDescription = "SingOut",
                                            tint = Color.White,
                                            modifier = Modifier
                                                .padding(vertical = 6.dp)
                                                .clickable {
                                                    mViewModel.signOut {
                                                        navController.navigate(NavRoute.Start.route) {
                                                            popUpTo(NavRoute.Start.route) {
                                                                inclusive = true
                                                            }
                                                        }
                                                    }
                                                }
                                        )
                                    }
                                }
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
                            NotesNavHost(mViewModel, navController)
                        }
                    }
                )
            }
        }
    }
}
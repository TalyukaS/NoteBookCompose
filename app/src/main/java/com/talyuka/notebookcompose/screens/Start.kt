package com.talyuka.notebookcompose.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.talyuka.notebookcompose.MainViewModel
import com.talyuka.notebookcompose.navigation.NavRoute
import com.talyuka.notebookcompose.utils.*
import com.talyuka.notebookcompose.utils.Constants.Keys.FIREBASE_DATABASE
import com.talyuka.notebookcompose.utils.Constants.Keys.LOGIN_TEXT
import com.talyuka.notebookcompose.utils.Constants.Keys.LOG_IN
import com.talyuka.notebookcompose.utils.Constants.Keys.NAME_DATABASE
import com.talyuka.notebookcompose.utils.Constants.Keys.PASS_TEXT
import com.talyuka.notebookcompose.utils.Constants.Keys.ROOM_DATABASE
import com.talyuka.notebookcompose.utils.Constants.Keys.SIGN_IN
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun StartScreen(navController: NavHostController, viewModel: MainViewModel) {
    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    var login by remember { mutableStateOf(Constants.Keys.EMPTY) }
    var password by remember { mutableStateOf(Constants.Keys.EMPTY) }
    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetElevation = 6.dp,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetContent = {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 32.dp)
                ) {
                    Text(
                        text = LOG_IN,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    OutlinedTextField(
                        value = login,
                        onValueChange = { login = it },
                        label = { Text(text = LOGIN_TEXT) },
                        isError = login.isEmpty()
                    )
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text(text = PASS_TEXT) },
                        isError = password.isEmpty()
                    )
                    Button(
                        modifier = Modifier.padding(top = 16.dp),
                        onClick = {
                            LOGIN = login
                            PASSWORD = password
                            viewModel.initDatabase(TYPE_FIREBASE) {
                                DB_TYPE.value = TYPE_FIREBASE
                                navController.navigate((NavRoute.Main.route))
                            }
                        },
                        enabled = login.isNotEmpty() && password.isNotEmpty()
                    ) {
                        Text(text = SIGN_IN)
                    }
                }
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = NAME_DATABASE, modifier = Modifier.padding(bottom = 16.dp))
                Button(
                    onClick = {
                        viewModel.initDatabase(TYPE_ROOM) {
                            DB_TYPE.value = TYPE_ROOM
                            navController.navigate(route = NavRoute.Main.route)
                        }
                    },
                    modifier = Modifier
                        .width(200.dp)
                        .padding(vertical = 8.dp)
                ) {
                    Text(text = ROOM_DATABASE)
                }
                Button(
                    onClick = {
                        coroutineScope.launch {
                            bottomSheetState.show()
                        }
                    },
                    modifier = Modifier
                        .width(200.dp)
                        .padding(vertical = 8.dp)
                ) {
                    Text(text = FIREBASE_DATABASE)
                }
            }
        }
    }
}
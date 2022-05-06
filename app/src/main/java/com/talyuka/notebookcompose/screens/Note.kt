package com.talyuka.notebookcompose.screens

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.talyuka.notebookcompose.MainViewModel
import com.talyuka.notebookcompose.MainViewModelFactory
import com.talyuka.notebookcompose.model.Note
import com.talyuka.notebookcompose.navigation.NavRoute
import com.talyuka.notebookcompose.ui.theme.NoteBookComposeTheme
import com.talyuka.notebookcompose.utils.Constants.Keys.DELETE
import com.talyuka.notebookcompose.utils.Constants.Keys.EDIT_NOTE
import com.talyuka.notebookcompose.utils.Constants.Keys.EMPTY
import com.talyuka.notebookcompose.utils.Constants.Keys.NAV_BACK
import com.talyuka.notebookcompose.utils.Constants.Keys.NONE
import com.talyuka.notebookcompose.utils.Constants.Keys.SUBTITLE
import com.talyuka.notebookcompose.utils.Constants.Keys.TITLE
import com.talyuka.notebookcompose.utils.Constants.Keys.UPDATE
import com.talyuka.notebookcompose.utils.Constants.Keys.UPDATE_NOTE
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NoteScreen(navController: NavHostController, viewModel: MainViewModel, noteId: String?) {
    val notes = viewModel.readAllNotes().observeAsState(listOf()).value
    val note = notes.firstOrNull{it.id ==noteId?.toInt()}?: Note(title = NONE, subtitle = NONE)
    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    var title by remember { mutableStateOf(EMPTY) }
    var subtitle by remember { mutableStateOf(EMPTY) }
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
                        text = EDIT_NOTE,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    OutlinedTextField(
                        value = title,
                        onValueChange = {title = it},
                        label = {Text(text = TITLE)},
                        isError = title.isEmpty()
                    )
                    OutlinedTextField(
                        value = subtitle,
                        onValueChange = {subtitle = it},
                        label = {Text(text = SUBTITLE)},
                        isError = subtitle.isEmpty()
                    )
                    Button(
                        modifier = Modifier.padding(top = 16.dp),
                        onClick = {
                            viewModel.updateNote(note = Note(id = note.id, title = title, subtitle = subtitle)){
                                navController.navigate((NavRoute.Main.route))
                            }
                        }
                    ) {
                        Text(text = UPDATE_NOTE)
                    }
                }
            }
        }
    ) {
        Scaffold(
            backgroundColor = MaterialTheme.colors.secondaryVariant
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp, horizontal = 12.dp),
                    elevation = 6.dp,
                    shape = RoundedCornerShape(10.dp),
                    backgroundColor = Color.White
                ) {
                    Column(
                        modifier = Modifier.padding(vertical = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = note.title,
                            modifier = Modifier.padding(top = 4.dp),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            style = TextStyle(shadow = Shadow(Color.LightGray , Offset(5.0f, 8.0f), 1.0f))
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                text = note.subtitle,
                                modifier = Modifier.padding(top = 12.dp, start = 16.dp, end = 16.dp),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.BottomCenter
                ){
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Button(
                            onClick = {
                                viewModel.deleteNote(note = note){
                                    navController.navigate(NavRoute.Main.route)
                                }
                            }
                        ) {Text(text = DELETE)}
                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    title = note.title
                                    subtitle = note.subtitle
                                    bottomSheetState.show()
                                }
                            }
                        ) {Text(text = UPDATE)}
                        Button(
                            onClick = {navController.navigate(NavRoute.Main.route)}
                        ) {Text(text = NAV_BACK)}
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrevNoteScreen() {
    NoteBookComposeTheme {
        val context = LocalContext.current
        val mViewModel: MainViewModel =
            viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
        NoteScreen(
            navController = rememberNavController(),
            viewModel = mViewModel,
            noteId = "1"
        )
    }
}
package com.talyuka.notebookcompose.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.talyuka.notebookcompose.MainViewModel
import com.talyuka.notebookcompose.model.Note
import com.talyuka.notebookcompose.navigation.NavRoute
import com.talyuka.notebookcompose.utils.Constants.Keys.ADD_NEW_NOTES
import com.talyuka.notebookcompose.utils.Constants.Keys.ADD_NOTES
import com.talyuka.notebookcompose.utils.Constants.Keys.SUBTITLE_NEW_NOTES
import com.talyuka.notebookcompose.utils.Constants.Keys.TITLE_NEW_NOTES

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddScreen(navController: NavHostController, viewModel: MainViewModel) {
    var title by remember { mutableStateOf("") }
    var subtitle by remember { mutableStateOf("") }
    var isButtonEnabled by remember { mutableStateOf(false) }
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = ADD_NEW_NOTES,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(top = 16.dp)
            )
            OutlinedTextField(
                value = title,
                onValueChange = {
                    title = it
                    isButtonEnabled = title.isNotEmpty() && subtitle.isNotEmpty()
                },
                label = { Text(text = TITLE_NEW_NOTES) },
                isError = title.isEmpty()
            )
            OutlinedTextField(
                value = subtitle,
                onValueChange = {
                    subtitle = it
                    isButtonEnabled = title.isNotEmpty() && subtitle.isNotEmpty()
                },
                label = { Text(text = SUBTITLE_NEW_NOTES) },
                isError = subtitle.isEmpty()
            )
            Button(
                modifier = Modifier.padding(16.dp),
                enabled = isButtonEnabled,
                onClick = {
                    viewModel.addNote(note = Note(title = title, subtitle = subtitle)) {
                        navController.navigate((NavRoute.Main.route))
                    }
                }
            ) {
                Text(text = ADD_NOTES)
            }
        }
    }
}
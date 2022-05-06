package com.talyuka.notebookcompose.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.talyuka.notebookcompose.MainViewModel
import com.talyuka.notebookcompose.model.Note
import com.talyuka.notebookcompose.navigation.NavRoute

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController, viewModel: MainViewModel) {
    val notes = viewModel.readAllNotes().observeAsState(listOf()).value
    Scaffold(
        modifier = Modifier.padding(top = 8.dp),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(route = NavRoute.Add.route)
                }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add Icons",
                    tint = Color.White
                )
            }
        }
    ) {
        LazyColumn {
            items(notes) { note ->
                NoteItem(note = note, navController = navController)
            }
        }
    }
}

@Composable
fun NoteItem(note: Note, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 12.dp)
            .clickable {
                navController.navigate(NavRoute.Note.route + "/${note.id}")
            },
        elevation = 6.dp,
        shape = RoundedCornerShape(15.dp),
        backgroundColor = MaterialTheme.colors.secondaryVariant
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                text = note.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.secondary,
                style = TextStyle(shadow = Shadow(Color.LightGray, Offset(5.0f, 8.0f), 1.0f))
            )
            Text(
                text = note.subtitle,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                maxLines = 3
            )
        }
    }
}
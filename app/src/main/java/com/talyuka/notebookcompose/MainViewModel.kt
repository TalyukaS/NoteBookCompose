package com.talyuka.notebookcompose

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.talyuka.notebookcompose.database.room.AppRoomDatabase
import com.talyuka.notebookcompose.database.room.repository.RoomRepository
import com.talyuka.notebookcompose.utils.REPOSITORY
import com.talyuka.notebookcompose.utils.TYPE_ROOM

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application
    fun initDatabase(type: String, onSuccess: () -> Unit) {
        when (type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(context = context).getRoomDao()
                REPOSITORY = RoomRepository(dao)
                onSuccess()
            }
        }
    }
}

class MainViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application = application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
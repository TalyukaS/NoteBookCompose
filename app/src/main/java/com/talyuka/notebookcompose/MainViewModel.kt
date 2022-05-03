package com.talyuka.notebookcompose

import android.app.Application
import java.lang.IllegalArgumentException
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.talyuka.notebookcompose.model.Note
import com.talyuka.notebookcompose.utils.TYPE_FIREBASE
import com.talyuka.notebookcompose.utils.TYPE_ROOM

class MainViewModel(application: Application): AndroidViewModel(application) {
    val realTest: MutableLiveData<List<Note>> by lazy {MutableLiveData<List<Note>>()}
    val dbType: MutableLiveData<String> by lazy { MutableLiveData<String>(TYPE_ROOM) }
    init {
        realTest.value =
            when(dbType.value){
                TYPE_ROOM -> {
                    listOf<Note>(
                        Note(title = "Первая запись", subtitle = "Перечень чего либо"),
                        Note(title = "Вторая запись", subtitle = "чего либо Перечень"),
                        Note(title = "Третья запись", subtitle = "либо чего Перечень")
                    )
                }
                TYPE_FIREBASE -> listOf()
                else -> listOf()
            }
    }
    fun initDatabase(type: String){
        dbType.value = type
    }
}

class MainViewModelFactory(private val application: Application): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(application = application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
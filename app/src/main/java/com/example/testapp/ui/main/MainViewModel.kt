package com.example.testapp.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.testapp.database.DBManager
import com.example.testapp.database.entity.BootTime
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val dataBaseManager: DBManager
    val updateTimesLiveData: MutableLiveData<List<BootTime>> = MutableLiveData(mutableListOf())

    init {
        dataBaseManager = DBManager(application)
    }

    fun loadLastBootTimes() {
        viewModelScope.launch(IO) {
            val list = dataBaseManager.getLastBootTime()
            updateTimesLiveData.postValue(list)
        }
    }

    override fun onCleared() {
        super.onCleared()
        dataBaseManager.closeDB()
    }
}
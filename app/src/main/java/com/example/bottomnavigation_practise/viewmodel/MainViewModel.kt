package com.example.bottomnavigation_practise.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core1.vm.BaseViewModel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel:BaseViewModel() {
    private val mutableAccountList = mutableListOf<String>()

}
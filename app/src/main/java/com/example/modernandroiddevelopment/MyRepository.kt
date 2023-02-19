package com.example.modernandroiddevelopment

import androidx.lifecycle.LiveData

interface MyRepository {
    fun getCounter(): LiveData<Int>
    fun increaseCounter()
}
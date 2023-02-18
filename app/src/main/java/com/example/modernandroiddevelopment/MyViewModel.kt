package com.example.modernandroiddevelopment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class MyViewModel(
    _counter: Int,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    var liveCounter: MutableLiveData<Int> = MutableLiveData(_counter)
    val modifiedCounter : LiveData<String> = Transformations.map(liveCounter) { counter ->
        "$counter 입니다"
    }
    val hasChecked: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)

    var counter: Int = savedStateHandle.get<Int>(SAVE_STATE_KEY) ?: _counter

    fun saveState() {
        savedStateHandle.set(SAVE_STATE_KEY, counter)
    }

    companion object {
        private const val SAVE_STATE_KEY = "counter"
    }
}
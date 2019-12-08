package com.miemdynamics.fossbot.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * A ViewModel for [HomeFragment].
 */
class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    /**
     * @suppress
     */
    val text: LiveData<String> = _text
}
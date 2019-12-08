package com.miemdynamics.fossbot.ui.program

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * A ViewModel for [ProgramFragment].
 */
class ProgramViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is program Fragment"
    }

    /**
     * @suppress
     */
    val text: LiveData<String> = _text
}
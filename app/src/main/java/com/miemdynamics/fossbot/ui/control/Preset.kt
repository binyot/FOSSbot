package com.miemdynamics.fossbot.ui.control

import com.miemdynamics.fossbot.data.entity.Program

data class ButtonPreset(
    var onButtonDown: Program? = null,
    var onButtonUp: Program? = null
)

class Preset(
    val name: String
) {
    val definitions = ArrayList<ButtonPreset>(8)
    init {
        for (i in 1..8) {
            definitions.add(ButtonPreset())
        }
    }
}
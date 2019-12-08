package com.miemdynamics.fossbot.ui.program

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.miemdynamics.fossbot.R

/**
 * A Fragment for interaction with predefined or user-made programs
 */
class ProgramFragment : Fragment() {

    private lateinit var programViewModel: ProgramViewModel

    /**
     * @suppress
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        programViewModel =
            ViewModelProviders.of(this).get(ProgramViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_program, container, false)
        val textView: TextView = root.findViewById(R.id.text_program)
        programViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}
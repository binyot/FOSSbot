package com.miemdynamics.fossbot.ui.control

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.miemdynamics.fossbot.R
import com.miemdynamics.fossbot.internal.viewModel
import com.miemdynamics.fossbot.network.service.RobotService
import kotlinx.android.synthetic.main.dialog_control_edit.view.*
import kotlinx.android.synthetic.main.fragment_control.*
import kotlinx.android.synthetic.main.preset_item.view.*
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein

class ControlFragment: Fragment(), KodeinAware {
    override val kodein: Kodein by closestKodein()
    private val viewModel: ControlViewModel by viewModel()

    private val KEY_EDITMODE_STATE = "KEY_EDITMODE_STATE"
    private var editModeEnabled = false
    private var actionMode: ActionMode? = null
    private var paused = false

    private fun startEditMode() {
        editModeEnabled = true
        actionMode = activity?.startActionMode(editModeCallbacks)
        actionMode?.title = "Edit"
    }

    private fun stopEditMode() {
        editModeEnabled = false
        destroyActionMode()
    }

    /**
     * Call this to prevent lingering ActionMode
     */
    private fun destroyActionMode() {
        actionMode?.finish()
        actionMode = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()
        if (editModeEnabled) {
            startEditMode()
        }
    }

    override fun onPause() {
        super.onPause()
        paused = true
        destroyActionMode()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_EDITMODE_STATE, editModeEnabled)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_control, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        savedInstanceState?.let {
            editModeEnabled = savedInstanceState.getBoolean(KEY_EDITMODE_STATE, false)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.control_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setupUI() {
        setupPresetPicker()
        setupButtons()
    }

    private fun setupButtons() {
        viewModel.selectedPreset.definitions
        viewModel.selectedPreset.definitions.forEach {
            val view = layoutInflater.inflate(R.layout.preset_item, layoutPresets, false)
            setButtonCallbacks(view.button, it)
            layoutPresets.addView(view)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setButtonCallbacks(button: Button, btnPreset: ButtonPreset) {
        button.setOnTouchListener { v, event ->
            if (!editModeEnabled && viewModel.connectionStateLive().value is RobotService.State.Connected) {
                when(event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        btnPreset.onButtonDown?.let { viewModel.runProgram(it) }
                        Log.d("PROGRAM", btnPreset.onButtonDown?.name ?: "null down")
                        true
                    }
                    MotionEvent.ACTION_UP -> {
                        btnPreset.onButtonUp?.let { viewModel.runProgram(it) }
                        Log.d("PROGRAM", btnPreset.onButtonUp?.name ?: "null up")
                        true
                    }
                    else -> false
                }
            } else false
        }
        button.setOnClickListener { v ->
            if (editModeEnabled) {
                showEditDialog(btnPreset, button)
            }
        }
    }

    private fun setupPresetPicker() {
        val layout = R.layout.support_simple_spinner_dropdown_item
        val adapter = ArrayAdapter<String>(context!!, layout)
        adapter.setDropDownViewResource(layout)
        adapter.addAll(viewModel.presets.map { preset -> preset.name })
        spinnerPreset.adapter = adapter
        spinnerPreset.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.selectedPreset = viewModel.presets[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        spinnerPreset.setSelection(0)
    }

    /**
     * TODO: Move presets out
     */
    private fun showEditDialog(buttonPreset: ButtonPreset, button: Button) {
        val view = layoutInflater.inflate(R.layout.dialog_control_edit, null)
        val layout = R.layout.support_simple_spinner_dropdown_item
        val adapter = ArrayAdapter<String>(context!!, layout)
        viewLifecycleOwner.lifecycleScope.launch {
            val programs = viewModel.getPrograms().await()
            adapter.setDropDownViewResource(layout)
            adapter.addAll(programs.map { it.name })
            view.spinnerOnPressed.adapter = adapter
            view.spinnerOnReleased.adapter = adapter
            AlertDialog.Builder(context)
                .setView(view)
                .setPositiveButton("Apply") { dialog, which ->
                    val onPressed = view.spinnerOnPressed.selectedItem
                    val onReleased = view.spinnerOnReleased.selectedItem
                    val name = "${onPressed?.toString() ?: "null"}/${onReleased?.toString()
                        ?: "null"}"
                    button.text = name
                    buttonPreset.onButtonDown = programs[view.spinnerOnPressed.selectedItemPosition]
                    buttonPreset.onButtonUp = programs[view.spinnerOnReleased.selectedItemPosition]
                }
                .setNegativeButton("Cancel") { dialog, which -> Unit }
                .create()
                .show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.actionEditMode -> {
                startEditMode()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private val editModeCallbacks = object: ActionMode.Callback {
        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            return true
        }

        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            mode?.menuInflater?.inflate(R.menu.control_edit_menu, menu)
            return true
        }

        override fun onDestroyActionMode(mode: ActionMode?) {
            if (!paused) {
                editModeEnabled = false
            }
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            return false
        }
    }
}
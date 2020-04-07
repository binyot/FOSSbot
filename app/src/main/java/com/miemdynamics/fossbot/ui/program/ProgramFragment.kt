package com.miemdynamics.fossbot.ui.program

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import com.miemdynamics.fossbot.R
import com.miemdynamics.fossbot.data.entity.Program
import com.miemdynamics.fossbot.internal.toastNotImplemented
import com.miemdynamics.fossbot.internal.viewModel
import com.miemdynamics.fossbot.network.service.RobotService
import com.miemdynamics.fossbot.ui.decorator.MarginItemDecorator
import kotlinx.android.synthetic.main.fragment_program.*
import kotlinx.android.synthetic.main.program_add_dialog.view.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein

/**
 * A [Fragment] for interaction with predefined or user-made programs
 */
class ProgramFragment : Fragment(), KodeinAware {
    override val kodein: Kodein by closestKodein()
    private val viewModel: ProgramViewModel by viewModel()

    private val programListAdapter = ProgramAdapter()
    private var actionMode: ActionMode? = null

    private val KEY_RECYCLERVIEW_STATE = "RECYCLERVIEW_STATE"
    private val KEY_SELECTMODE_STATE = "SELECTMODE_STATE"
    private val KEY_SELECTMODE_PREV_STATE = "SELECTMOVE_PREV_STATE"
    private var selectModeEnabled = false
    private var previousSelectionState = false

    private var paused = false

    private fun startSelectMode() {
        selectModeEnabled = true
        actionMode = activity?.startActionMode(selectModeCallbacks)
        actionMode?.title = "Select"
    }

    /**
     * Call this to prevent lingering ActionMode
     */
    private fun destroyActionMode() {
        actionMode?.finish()
        actionMode = null
    }

    private fun stopSelectMode() {
        selectModeEnabled = false
        destroyActionMode()
    }

    private fun editProgramDialog(program: Program? = null) {
        val view = layoutInflater.inflate(R.layout.program_add_dialog, null)
        program ?.let {
            view.editTextName.text.append(it.name)
            view.editTextBody.text.append(it.body)
            }
        val dialog = AlertDialog.Builder(context)
            .setView(view)
            .setPositiveButton("Apply") { dialog, which ->
                viewModel.addProgram(
                    Program(
                        view.editTextName.text.toString(),
                        view.editTextBody.text.toString()
                    )
                )
            }
            .setNegativeButton("Cancel") { dialog, which ->

            }
            .create()
        val editables = listOf(view.editTextName, view.editTextBody)
        editables.forEach {
            it.doAfterTextChanged {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled =
                    editables.any { !it.text.isNullOrBlank() }
            }
        }
        dialog.show()
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = false
    }

    private fun runProgramDialog(program: Program) {
        // TODO: Fix LiveData hacks
        when(viewModel.connectionStateLive().value) {
            is RobotService.State.Connected -> {
                if (viewModel.runProgramConfirmEnabled) {
                    AlertDialog.Builder(context)
                        .setMessage("Run program ${program.name}?")
                        .setPositiveButton("Upload")
                        { dialog, which -> viewModel.createProgram(program) }
                        .setNegativeButton("Run")
                        { dialog, which -> viewModel.runProgram(program) }
                        .setNeutralButton("Edit")
                        { dialog, which -> editProgramDialog(program) }
                        .show()
                } else {
                    viewModel.runProgram(program)
                }
            }
            else -> Toast.makeText(context, "No connection", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Should be called when selection mode is enabled
     * TODO: make this a callback interface
     */
    private fun onItemsSelected() {
        startSelectMode()
    }

    private fun onItemsDeselected() {
        stopSelectMode()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onPause() {
        super.onPause()
        paused = true
        destroyActionMode()
    }

    override fun onResume() {
        super.onResume()
        if (selectModeEnabled) {
            startSelectMode()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        programListAdapter.tracker?.onSaveInstanceState(outState)
        val listState = recyclerView.layoutManager?.onSaveInstanceState()
        outState.putParcelable(KEY_RECYCLERVIEW_STATE, listState)
        outState.putBoolean(KEY_SELECTMODE_STATE, selectModeEnabled)
        outState.putBoolean(KEY_SELECTMODE_PREV_STATE, previousSelectionState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_program, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()

        savedInstanceState?.let {
            previousSelectionState = it.getBoolean(KEY_SELECTMODE_PREV_STATE, false)
            selectModeEnabled = it.getBoolean(KEY_SELECTMODE_STATE, false)
            recyclerView
                .layoutManager
                ?.onRestoreInstanceState(
                    it.getParcelable(KEY_RECYCLERVIEW_STATE))
            programListAdapter.tracker?.onRestoreInstanceState(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.program_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.actionDownload -> {
                viewModel.downloadPrograms()
                true
            }
            R.id.actionUpload -> {
                if (viewModel.connectionStateLive().value !is RobotService.State.Connected) {
                    Toast.makeText(context, "No connection", Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.uploadAllPrograms()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupUI() {
        setupRecyclerView()
        buttonAddProgram.setOnClickListener {
            editProgramDialog()
        }
        viewModel.getProgramsLive().observe(viewLifecycleOwner, Observer { programList ->
            programListAdapter.programList = programList
            programListAdapter.notifyDataSetChanged()
        })
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(MarginItemDecorator(
            resources.getDimension(R.dimen.default_padding).toInt()))
        recyclerView.adapter = programListAdapter

        programListAdapter.onItemClick = { program ->
            if (!selectModeEnabled) {
                runProgramDialog(program)
            }
        }

        val tracker = SelectionTracker.Builder<Long>(
            "selection-program",
            recyclerView,
            ProgramAdapter.ItemIdKeyProvider(recyclerView),
            ProgramAdapter.ItemLookup(recyclerView),
            StorageStrategy.createLongStorage()
        ).withSelectionPredicate(SelectionPredicates.createSelectAnything())
            .build()

        tracker.addObserver(object: SelectionTracker.SelectionObserver<Long>() {
            override fun onSelectionChanged() {
                if (!previousSelectionState && tracker.hasSelection()) {
                    onItemsSelected()
                } else if (!tracker.hasSelection()) {
                    onItemsDeselected()
                }
                previousSelectionState = tracker.hasSelection()
                super.onSelectionChanged()
            }
        })

        programListAdapter.tracker = tracker
    }

    private val selectModeCallbacks = object: ActionMode.Callback {
        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            mode?.menuInflater?.inflate(R.menu.program_select_menu, menu)
            return true
        }

        override fun onDestroyActionMode(mode: ActionMode?) {
            programListAdapter.tracker?.let {
                if (!paused && it.hasSelection()) {
                    deselectAllItems()
                }
            }
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            return false
        }

        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            val tracker = programListAdapter.tracker
            return when(item?.itemId) {
                R.id.actionDeleteSelection -> {
                    AlertDialog.Builder(context)
                        .setMessage("Are you sure you want to delete ${tracker?.selection?.size()} items?")
                        .setPositiveButton("Yes"
                        ) { dialog, which -> deleteSelection() }
                        .setNegativeButton("No"
                        ) { dialog, which -> Unit }
                        .show()
                    true
                }
                R.id.actionSelectAll -> {
                    (0 until programListAdapter.itemCount)
                        .map { it.toLong() }
                        .forEach {
                            programListAdapter.tracker?.select(it)
                        }
                    programListAdapter.notifyDataSetChanged()
                    true
                }
                R.id.actionDeselectAll -> {
                    deselectAllItems()
                    true
                }
                R.id.actionUpload -> {
                    if (viewModel.connectionStateLive().value !is RobotService.State.Connected) {
                        Toast.makeText(context, "No connection", Toast.LENGTH_SHORT).show()
                    } else {
                        for (i in tracker!!.selection) {
                            val program = programListAdapter.programList[i.toInt()]
                            viewModel.uploadProgram(program)
                        }
                    }
                    true
                }
                else -> {
                    toastNotImplemented(activity!!)
                    false
                }
            }
        }

        private fun deselectAllItems() {
            programListAdapter.tracker?.clearSelection()
            programListAdapter.notifyDataSetChanged()
        }

        private fun deleteSelection() {
            val programs = programListAdapter.tracker?.selection
                ?.map {programListAdapter.programList[it.toInt()]}
                ?.let {viewModel.deletePrograms(it)}
                .also { programListAdapter.tracker?.clearSelection() }
        }
    }
}
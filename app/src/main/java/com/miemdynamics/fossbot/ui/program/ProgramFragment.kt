package com.miemdynamics.fossbot.ui.program

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
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

    private fun startSelectMode() {
        actionMode = activity?.startActionMode(selectModeCallbacks)
        actionMode?.title = "Select"
        viewModel.selectModeEnabled = true
    }

    /**
     * Call this to prevent lingering ActionMode
     */
    private fun destroyActionMode() {
        actionMode?.finish()
        actionMode = null
    }

    private fun stopSelectMode() {
        destroyActionMode()
        viewModel?.selectModeEnabled = false
    }

    private fun runProgram(program: Program) {
        when(viewModel.connectionState.value) {
            is RobotService.State.Connected -> {
                if (viewModel.runProgramConfirmEnabled) {
                    AlertDialog.Builder(context)
                        .setMessage("Run program ${program.name}?")
                        .setPositiveButton("Yes")
                        { dialog, which -> viewModel.runProgram(program) }
                        .setNegativeButton("No")
                        { dialog, which -> Unit }
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
        actionMode = activity?.startActionMode(selectModeCallbacks)
    }

    private fun onItemsDeselected() {
        actionMode?.finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.selectModeEnabled) {
            startSelectMode()
        }
    }

    override fun onStop() {
        super.onStop()
        destroyActionMode()
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
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.program_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.actionDownload -> {
                toastNotImplemented(activity!!)
                true
            }
            R.id.actionUpload -> {
                toastNotImplemented(activity!!)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupUI() {
        setupRecyclerView()
        buttonAddProgram.setOnClickListener {
            // TODO: replace with actual program creation
            viewModel.insert(
                Program("inserted program", "is a program that has been inserted")
            )
        }
        viewModel.getPrograms().observe(this, Observer { programList ->
            programListAdapter.programList = programList
        })
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(MarginItemDecorator(
            resources.getDimension(R.dimen.default_padding).toInt()))
        recyclerView.adapter = programListAdapter

        programListAdapter.onItemClick = { program ->
            runProgram(program)
        }

        // TODO: move selection tracking inside the RecyclerViewAdapter
        // TODO: make selection survive fragment destruction
        val tracker = SelectionTracker.Builder<Long>(
            "selection-program",
            recyclerView,
            ProgramAdapter.ItemIdKeyProvider(recyclerView),
            ProgramAdapter.ItemLookup(recyclerView),
            StorageStrategy.createLongStorage()
        ).withSelectionPredicate(SelectionPredicates.createSelectAnything())
            .build()

        tracker.addObserver(object: SelectionTracker.SelectionObserver<Long>() {
            private var previousSelectionState: Boolean = false
            override fun onSelectionChanged() {
                super.onSelectionChanged()
                if (!previousSelectionState && tracker.hasSelection()) {
                    onItemsSelected()
                } else if (previousSelectionState && !tracker.hasSelection()) {
                    onItemsDeselected()
                }
                previousSelectionState = tracker.hasSelection()
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
            val tracker = programListAdapter.tracker
            tracker?.let {
                if (it.hasSelection()) {
                    deselectAllItems()
                }
            }
            actionMode = null
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
                    mode?.finish()
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
            toastNotImplemented(activity!!)
            actionMode?.finish()
        }
    }
}
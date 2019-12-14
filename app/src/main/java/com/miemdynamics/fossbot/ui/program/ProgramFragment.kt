package com.miemdynamics.fossbot.ui.program

import android.os.Bundle
import android.view.*
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

    /**
     * Should be called when selection mode is enabled
     * TODO: make this a callback interface
     */
    private fun onItemsSelected() {
        actionMode = activity?.startActionMode(actionModeCallbacks)
    }

    private fun onItemsDeselected() {
        actionMode?.finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_program, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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

        // TODO: move selection tracking inside the RecyclerViewAdapter
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

    private val actionModeCallbacks = object: ActionMode.Callback {
        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            mode?.menuInflater?.inflate(R.menu.program_select_menu, menu)
            return true
        }

        override fun onDestroyActionMode(mode: ActionMode?) {
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            return false
        }

        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            return when(item?.itemId) {
                R.id.actionDeleteSelection -> {
                    toastNotImplemented(activity!!)
                    mode?.finish()
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
                    programListAdapter.tracker?.clearSelection()
                    programListAdapter.notifyDataSetChanged()
                    mode?.finish()
                    true
                }
                else -> {
                    toastNotImplemented(activity!!)
                    false
                }
            }
        }
    }
}
package com.miemdynamics.fossbot.ui.program

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.miemdynamics.fossbot.R
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
            R.id.actionSync -> {
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
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(MarginItemDecorator(
            resources.getDimension(R.dimen.default_padding).toInt()))
        val adapter = ProgramAdapter()
        recyclerView.adapter = adapter

        buttonAddProgram.setOnClickListener {
            toastNotImplemented(context!!)
        }

        viewModel.getPrograms().observe(this, Observer { programList ->
            adapter.programList = programList
        })
    }
}
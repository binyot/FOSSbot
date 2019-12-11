package com.miemdynamics.fossbot.ui.program

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.miemdynamics.fossbot.R
import com.miemdynamics.fossbot.data.entity.Program
import kotlinx.android.synthetic.main.program_item.view.*

class ProgramAdapter: RecyclerView.Adapter<ProgramAdapter.ProgramViewHolder>() {

    // TODO: Use DiffUtil
    var programList: List<Program> = ArrayList()
        set(programList) {
            field = programList
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.program_item, parent, false)
        return ProgramViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProgramViewHolder, position: Int) {
        val program = programList.get(position)
        holder.textViewName.setText(program.name)
        holder.textViewBody.setText(program.body)
    }

    override fun getItemCount(): Int {
        return programList.size
    }

    class ProgramViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textViewName = view.textViewName
        val textViewBody = view.textViewSource
    }
}
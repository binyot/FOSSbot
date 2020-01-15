package com.miemdynamics.fossbot.ui.program

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.ItemKeyProvider
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.miemdynamics.fossbot.R
import com.miemdynamics.fossbot.data.entity.Program
import kotlinx.android.synthetic.main.program_item.view.*

/**
 *  A [RecyclerView.Adapter] for [Program]
 *  Supports selection if [tracker] is set
 */
class ProgramAdapter: RecyclerView.Adapter<ProgramAdapter.ViewHolder>() {
    var tracker: SelectionTracker<Long>? = null

    var onItemClick: ((Program) -> Unit)? = null

    init {
        setHasStableIds(true)
    }

    var programList: List<Program> = ArrayList()
        set(programList) {
            val diff = DiffUtil.calculateDiff(ProgramDiffCallback(field, programList))
            field = programList
            diff.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.program_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val program = programList.get(position)
        val isSelected = tracker?.isSelected(position.toLong()) ?: false
        holder.bind(program, isSelected)
    }

    override fun getItemId(position: Int) = position.toLong()

    override fun getItemCount(): Int {
        return programList.count()
    }

    inner class ViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener {
                onItemClick?.invoke(programList[adapterPosition])
            }
        }

        fun bind(program: Program, isSelected: Boolean = false) {
            view.textViewName.text = program.name
            view.textViewBody.text = program.body
            view.isSelected = isSelected
        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
            object: ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int = adapterPosition
                override fun getSelectionKey(): Long? = itemId
            }
    }

    class ProgramDiffCallback(
        private val old: List<Program>,
        private val new: List<Program>
    ): DiffUtil.Callback() {
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            old.get(oldItemPosition) == new.get(newItemPosition)

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            old.get(oldItemPosition).name == new.get(newItemPosition).name

        override fun getNewListSize() =
            new.size

        override fun getOldListSize() =
            old.size
    }

    class ItemIdKeyProvider(
        private val recyclerView: RecyclerView
    ): ItemKeyProvider<Long>(SCOPE_MAPPED) {
        override fun getKey(position: Int): Long? {
            return recyclerView.adapter?.getItemId(position)
                ?: throw IllegalStateException("RecyclerView adapter is not set")
        }

        override fun getPosition(key: Long): Int {
            val holder = recyclerView.findViewHolderForItemId(key)
            return holder?.layoutPosition ?: RecyclerView.NO_POSITION
        }
    }

    class ItemLookup(
        private val recyclerView: RecyclerView
    ): ItemDetailsLookup<Long>() {
        override fun getItemDetails(e: MotionEvent): ItemDetails<Long>? {
            val view = recyclerView.findChildViewUnder(e.x, e.y)
            return view?.let {
                (recyclerView.getChildViewHolder(it) as ViewHolder)
                    .getItemDetails()
            }
        }
    }
}
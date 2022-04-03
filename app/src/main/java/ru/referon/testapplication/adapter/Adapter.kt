package ru.referon.testapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.referon.testapplication.databinding.CardLayoutBinding
import ru.referon.testapplication.model.Result

class Adapter(

) : androidx.recyclerview.widget.ListAdapter<Result, ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}

class ViewHolder(
    private val binding: CardLayoutBinding,

    ) : RecyclerView.ViewHolder(binding.root) {

    fun bind(lesson: Result) {

        binding.apply {

            startTime.text = lesson.startTime
            endTime.text = lesson.endTime
            name.text = lesson.name
            title.text = lesson.title
            location.text = lesson.location
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }

}
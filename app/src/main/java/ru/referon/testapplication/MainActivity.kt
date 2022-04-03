package ru.referon.testapplication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import ru.referon.testapplication.databinding.ActivityMainBinding
import ru.referon.testapplication.viewmodel.ViewModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel: ViewModel by viewModels()

        viewModel.loadLessons()

        val adapter1 = Adapter()
        val adapter2 = Adapter()
        val adapter3 = Adapter()
        val adapter4 = Adapter()
        val adapter5 = Adapter()
        val adapter6 = Adapter()
        val adapter7 = Adapter()


        binding.list1.adapter = adapter1
        binding.list2.adapter = adapter2
        binding.list3.adapter = adapter3
        binding.list4.adapter = adapter4
        binding.list5.adapter = adapter5
        binding.list6.adapter = adapter6
        binding.list7.adapter = adapter7

        viewModel.data.observe(this){state->

            binding.progressBar.isVisible = state.loading

            binding.day1.text = state.date?.get(0)
            binding.day2.text = state.date?.get(1)
            binding.day3.text = state.date?.get(2)
            binding.day4.text = state.date?.get(3)
            binding.day5.text = state.date?.get(4)
            binding.day6.text = state.date?.get(5)
            binding.day7.text = state.date?.get(6)

            adapter1.submitList(state.result?.get(0))
            adapter2.submitList(state.result?.get(1))
            adapter3.submitList(state.result?.get(2))
            adapter4.submitList(state.result?.get(3))
            adapter5.submitList(state.result?.get(4))
            adapter6.submitList(state.result?.get(5))
            adapter7.submitList(state.result?.get(6))
        }
    }

}
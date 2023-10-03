package br.com.fiap.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.ViewCompat
import androidx.core.view.children
import androidx.lifecycle.lifecycleScope
import br.com.fiap.todoapp.database.AppDatabase
import br.com.fiap.todoapp.databinding.ActivityMainBinding
import br.com.fiap.todoapp.databinding.ViewFilterItemBinding
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val taskAdapter by lazy {
        TaskAdapter()
    }

    private var selectedFilter: TaskStatus? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupFilters()
        setupRecycler()
        getFilteredList()
    }

    private fun setupRecycler() {
        binding.recyclerViewTasks.setHasFixedSize(true)
        binding.recyclerViewTasks.adapter = taskAdapter
    }

    private fun setupFilters() {
        TaskStatus.values().forEach {
            val filterOption = ViewFilterItemBinding.inflate(
                layoutInflater,
                binding.taskFilters,
                false
            ).root as? Chip
            filterOption?.id = ViewCompat.generateViewId()
            filterOption?.text = it.title
            binding.taskFilters.addView(filterOption)
        }

        binding.taskFilters.setOnCheckedChangeListener { group, checkedId ->
            val checkedChip = group.children.find {
                it.id == checkedId
            } as? Chip

            if (checkedChip == null) {
                selectedFilter = null
            } else {
                val taskStatusCheckedChip = TaskStatus.getByTiyle(
                    checkedChip?.text.toString()
                )
                selectedFilter = taskStatusCheckedChip
            }

            getFilteredList()
        }
    }

    private suspend fun getTaskList() {
        taskAdapter.setData(AppDatabase.getDatabase(this).taskDAO().selectAll())
    }

    private suspend fun getTaskFromStatus(status: TaskStatus) {
        taskAdapter.setData(AppDatabase.getDatabase(this).taskDAO().selectByStatus(status))
    }

    private fun getFilteredList() {
        lifecycleScope.launch {
            if (selectedFilter == null) {
                getTaskList()
            } else {
                getTaskFromStatus(selectedFilter!!)
            }
        }
    }
}
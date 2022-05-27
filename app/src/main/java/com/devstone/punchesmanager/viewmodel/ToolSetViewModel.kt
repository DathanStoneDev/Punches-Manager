package com.devstone.punchesmanager.viewmodel

import androidx.lifecycle.*
import com.devstone.punchesmanager.data.entities.tool.ToolSet
import com.devstone.punchesmanager.data.repository.ToolSetRepository
import kotlinx.coroutines.launch

class ToolSetViewModel (private val repository: ToolSetRepository) : ViewModel() {

    val allToolSets: LiveData<List<ToolSet>> = repository.allToolSets.asLiveData()

    fun insert(toolSet: ToolSet) = viewModelScope.launch {
        repository.insert(toolSet)
    }

    fun update(toolSet: ToolSet) = viewModelScope.launch {
        repository.update(toolSet)
    }

    fun delete(toolSet: ToolSet) = viewModelScope.launch {
        repository.delete(toolSet)
    }

    //Need Factory Method
}

class ToolSetViewModelFactory(private val repository: ToolSetRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ToolSetViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ToolSetViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
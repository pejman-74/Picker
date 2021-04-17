package com.picker.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picker.data.model.PickItem
import com.picker.data.repository.MainRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepositoryInterface,
) : ViewModel() {

    fun savePickItem(pickItem: PickItem) =
        viewModelScope.launch { repository.savePickItem(pickItem) }

    val allPickItems = repository.allPickItems()
}
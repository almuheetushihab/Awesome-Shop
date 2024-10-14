package com.example.awesomeshop.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.awesomeshop.reposatories.CategoriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(private val categoriesRepository: CategoriesRepository) : ViewModel() {
    private val _items: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>()
    }
    val items: MutableLiveData<List<String>> = _items
    fun getCategories() = viewModelScope.launch {
        try {
            _items.value = categoriesRepository.getCategories()
        } catch (e: Exception) {
            _items.value = null
        }
    }
}
package com.example.shopapp.views.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.db.Item
import com.example.shopapp.db.ItemDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CartViewModel(private val itemDao: ItemDao) : ViewModel() {

    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>> = _items.asStateFlow()

    init {
        viewModelScope.launch {
            itemDao.getItemsInCart().collect {listOfItems ->
                _items.value = listOfItems
            }
        }
    }

}


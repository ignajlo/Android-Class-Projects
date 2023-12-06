package com.example.shopapp.views.items

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.shopapp.db.ItemDao
import com.example.shopapp.db.Item

class ItemDetailsViewModel(private val itemDao: ItemDao) : ViewModel() {

    private val _item = MutableStateFlow<Item?>(null)
    val item: StateFlow<Item?> = _item.asStateFlow()


    fun fetchItemDetails(itemId: Long) {
        _item.value = itemDao.getItem(itemId)
    }

    fun addToCart(item: Item, quantity: Int) {
        kotlin.runCatching {
            itemDao.increaseItemQuantity(item.id, quantity)
        }
    }


    fun setItemQuantity(item: Item, quantity: Int) {

            itemDao.setItemQuantity(item.id, quantity)

    }


}

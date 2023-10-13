package com.program.jetpack.sample.lifecycle

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.program.example.persistence.viewmodel.ProductViewModel

class ViewModelTest(override val viewModelStore: ViewModelStore) : ViewModelStoreOwner{
    fun test(){
        /*val model: ProductViewModel = ViewModelProvider(this, viewModelFactory {
            initializer {
                ProductViewModel(mApplication, mRepository, mProductId)
            }
        }).get<ProductViewModel>(ProductViewModel::class.java)*/
    }
}
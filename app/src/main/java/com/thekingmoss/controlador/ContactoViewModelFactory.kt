package com.thekingmoss.controlador

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thekingmoss.repository.ContactoRepository

class ContactoViewModelFactory(private val repository: ContactoRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ContactoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
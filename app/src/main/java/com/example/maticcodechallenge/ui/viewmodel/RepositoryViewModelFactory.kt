package com.example.maticcodechallenge.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import data.source.RepositoryDataFactory

class RepositoryViewModelFactory(private val repositoryDataFactory: RepositoryDataFactory) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RepositoryViewModel(repositoryDataFactory) as T
    }
}

package com.example.maticcodechallenge.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import data.model.NetworkState
import data.model.Repository
import data.source.RepositoryDataFactory

class RepositoryViewModel(repositoryDataFactory: RepositoryDataFactory) : ViewModel() {

    private var networkState: LiveData<NetworkState>? = null
    private var repositoryLiveData: LiveData<PagedList<Repository>>? = null
    private var initialLoading: LiveData<NetworkState>? = null

    init {

        networkState = Transformations.switchMap(
            repositoryDataFactory.mutableLiveData
        ) { dataSource -> dataSource.networkState }

        initialLoading =
            Transformations.switchMap(repositoryDataFactory.mutableLiveData) { dataSource ->
                dataSource.initialLoading
            }

        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(10)
            .setPageSize(20).build()

        repositoryLiveData = LivePagedListBuilder(repositoryDataFactory, pagedListConfig)
            .build()
    }


    fun getNetworkState(): LiveData<NetworkState>? {
        return networkState
    }

    fun getRepositoryLiveData(): LiveData<PagedList<Repository>>? {
        return repositoryLiveData
    }

    fun getInitialLoading(): LiveData<NetworkState>? {
        return initialLoading
    }
}

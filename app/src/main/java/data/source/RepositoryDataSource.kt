package data.source

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.maticcodechallenge.R
import data.model.NetworkState
import data.model.Repository
import network.api.RepositoryAPI

class RepositoryDataSource(private val repositoryAPI: RepositoryAPI) :
    PageKeyedDataSource<Long, Repository>() {

    val networkState: MutableLiveData<NetworkState> = MutableLiveData()
    val initialLoading: MutableLiveData<NetworkState> = MutableLiveData()

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, Repository>
    ) {

        initialLoading.postValue(NetworkState.LOADING)
        networkState.postValue(NetworkState.LOADING)

        repositoryAPI.getAll(1) {
            it?.let {
                callback.onResult(it, null, 2)
                initialLoading.postValue(NetworkState.LOADED)
                networkState.postValue(NetworkState.LOADED)
            } ?: run {
                initialLoading.postValue(
                    NetworkState(
                        NetworkState.Status.FAILED,
                        R.string.no_internet
                    )
                )
                networkState.postValue(
                    NetworkState(
                        NetworkState.Status.FAILED,
                        R.string.no_internet
                    )
                )
            }

        }
    }

    override fun loadBefore(
        params: LoadParams<Long>, callback: LoadCallback<Long, Repository>
    ) {
    }

    override fun loadAfter(
        params: LoadParams<Long>,
        callback: LoadCallback<Long, Repository>
    ) {

        networkState.postValue(NetworkState.LOADING)

        repositoryAPI.getAll(params.key.toInt()) {
            it?.let {
                val nextKey = params.key + 1
                callback.onResult(it, nextKey)
                networkState.postValue(NetworkState.LOADED)
            } ?: run {
                networkState.postValue(
                    NetworkState(
                        NetworkState.Status.FAILED,
                        R.string.no_internet
                    )
                )

            }

        }
    }

}

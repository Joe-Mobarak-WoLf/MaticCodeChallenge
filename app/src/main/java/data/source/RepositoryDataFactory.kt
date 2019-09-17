package data.source

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import data.model.Repository

class RepositoryDataFactory(private val repositoryDataSource: RepositoryDataSource) :
    DataSource.Factory<Long, Repository>() {

    val mutableLiveData: MutableLiveData<RepositoryDataSource> = MutableLiveData()

    override fun create(): DataSource<Long, Repository> {
        mutableLiveData.postValue(repositoryDataSource)
        return repositoryDataSource
    }
}

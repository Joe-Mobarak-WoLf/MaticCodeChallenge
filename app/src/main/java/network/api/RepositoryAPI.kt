package network.api

import data.model.RepositoriesResult
import data.model.Repository
import network.builder.RetrofitBuilder
import network.service.RepositoryAPIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryAPI {

    fun getAll(
        page: Int,
        closure: (List<Repository>?) -> Unit
    ) {
        val retrofitBuilder = RetrofitBuilder()
        val service =
            retrofitBuilder.createRetrofitService()!!.create(RepositoryAPIInterface::class.java)
        val stringCall: Call<RepositoriesResult>
        stringCall = service.getAll("2019-01-13", page)

        stringCall.enqueue(object : Callback<RepositoriesResult> {
            override fun onResponse(
                call: Call<RepositoriesResult>,
                response: Response<RepositoriesResult>
            ) {
                if (response.isSuccessful) {
                    closure(response.body()?.items)
                }
            }

            override fun onFailure(call: Call<RepositoriesResult>, t: Throwable) {
                closure(null)
            }
        })
    }
}

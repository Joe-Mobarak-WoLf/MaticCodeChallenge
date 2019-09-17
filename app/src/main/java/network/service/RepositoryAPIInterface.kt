package network.service

import data.model.RepositoriesResult
import network.builder.UrlBuilder
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RepositoryAPIInterface {

    @GET(UrlBuilder.GET_ALL)
    fun getAll(@Query("q") date: String, @Query("page") page: Int): Call<RepositoriesResult>

}

package com.wahidhidayat.themoviedb.ui.search

import com.wahidhidayat.themoviedb.BuildConfig
import com.wahidhidayat.themoviedb.model.Movies
import com.wahidhidayat.themoviedb.network.APIEndpoints
import com.wahidhidayat.themoviedb.network.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchPresenter(v: SearchContract.SearchView) {

    private var view: SearchContract.SearchView? = v

    fun fetchResult(language: String, query: String) {
        val service = APIService.buildService(APIEndpoints::class.java)
        val call = service.getSearch(BuildConfig.API_KEY, language, query)
        call.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.isSuccessful) {
                    view?.hideLoading()
                    view?.attachToResult(response.body()?.result)
                } else {
                    view?.hideLoading()
                    view?.showToast("Something went wrong..")
                }
            }

            override fun onFailure(call: Call<Movies>, t: Throwable?) {
                view?.hideLoading()
                view?.showToast(t?.localizedMessage.toString())
            }
        })
    }
}
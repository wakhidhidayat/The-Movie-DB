package com.wahidhidayat.themoviedb.ui.movie


import com.wahidhidayat.themoviedb.model.Movies
import com.wahidhidayat.themoviedb.network.APIEndpoints
import com.wahidhidayat.themoviedb.network.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviePresenter(v: MovieContract.MovieView) {
    private var view: MovieContract.MovieView? = v

    fun showNowPlaying(apiKey: String, language: String) {
        val service = APIService.buildService(APIEndpoints::class.java)
        val call = service.getNowPlaying(apiKey, language)
        call.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.isSuccessful) {
                    view?.hideLoading()
                    val body = response.body()
                    view?.attachToNowPlaying(body?.result)
                } else {
                    view?.hideLoading()
                    view?.showToast("Something went wrong, try again later")
                }
            }

            override fun onFailure(call: Call<Movies>, t: Throwable?) {
                view?.hideLoading()
                view?.showToast(t?.localizedMessage.toString())
            }
        })
    }

    fun showPopular(apiKey: String, language: String) {
        val service = APIService.buildService(APIEndpoints::class.java)
        val call = service.getPopular(apiKey, language)
        call.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.isSuccessful) {
                    view?.hideLoading()
                    val body = response.body()
                    view?.attachToPopular(body?.result)
                } else {
                    view?.hideLoading()
                    view?.showToast("Something went wrong, try again later")
                }
            }

            override fun onFailure(call: Call<Movies>, t: Throwable?) {
                view?.hideLoading()
                view?.showToast(t?.localizedMessage.toString())
            }
        })
    }

    fun showUpcoming(apiKey: String, language: String) {
        val service = APIService.buildService(APIEndpoints::class.java)
        val call = service.getUpcoming(apiKey, language)
        call.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.isSuccessful) {
                    view?.hideLoading()
                    val body = response.body()
                    view?.attachToUpcoming(body?.result)
                } else {
                    view?.hideLoading()
                    view?.showToast("Something went wrong, try again later")
                }
            }

            override fun onFailure(call: Call<Movies>, t: Throwable?) {
                view?.hideLoading()
                view?.showToast(t?.localizedMessage.toString())
            }
        })
    }
}
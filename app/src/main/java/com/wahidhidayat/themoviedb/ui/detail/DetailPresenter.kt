package com.wahidhidayat.themoviedb.ui.detail

import com.wahidhidayat.themoviedb.BuildConfig
import com.wahidhidayat.themoviedb.model.Movie
import com.wahidhidayat.themoviedb.network.APIEndpoints
import com.wahidhidayat.themoviedb.network.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenter(v: DetailContract.DetailView) {
    private var view: DetailContract.DetailView? = v

    fun showDetail(movieId: Int, language: String) {
        val service = APIService.buildService(APIEndpoints::class.java)
        val call = service.getMovie(movieId, BuildConfig.API_KEY, language)
        call.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    view?.hideLoading()
                    view?.setDetailTitle(response.body()?.title.toString())
                    view?.setMovieBackrdop(response.body()?.backdrop_path.toString())
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable?) {
                view?.hideLoading()
                view?.showToast(t?.localizedMessage.toString())
            }
        })
    }
}
package com.wahidhidayat.themoviedb.ui.info

import com.wahidhidayat.themoviedb.BuildConfig
import com.wahidhidayat.themoviedb.model.Credits
import com.wahidhidayat.themoviedb.model.Movie
import com.wahidhidayat.themoviedb.network.APIEndpoints
import com.wahidhidayat.themoviedb.network.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class InfoPresenter(v: InfoContract.InfoView) {
    private var view: InfoContract.InfoView? = v

    fun fetchCasts(movieId: Int) {
        val service = APIService.buildService(APIEndpoints::class.java)
        val call = service.getCredits(movieId, BuildConfig.API_KEY)
        call.enqueue(object : Callback<Credits> {
            override fun onResponse(call: Call<Credits>, response: Response<Credits>) {
                if (response.isSuccessful) {
                    view?.hideLoading()
                    view?.attachToCast(response.body()?.cast)
                }
            }

            override fun onFailure(call: Call<Credits>, t: Throwable?) {
                view?.hideLoading()
                view?.showToast(t?.localizedMessage.toString())
            }
        })
    }

    fun fetchInfo(movieId: Int, language: String) {
        val service = APIService.buildService(APIEndpoints::class.java)
        val call = service.getMovie(movieId, BuildConfig.API_KEY, language)
        call.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    view?.setOverview(response.body()?.overview.toString())
                    view?.setRelease(dateFormat(response.body()?.release_date.toString()))
                    view?.setRatting(response.body()?.vote_average.toString())
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable?) {
                view?.hideLoading()
                view?.showToast(t?.localizedMessage.toString())
            }
        })
    }

    private fun dateFormat(releaseDate: String): String {
        val formatYear = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH)
        var year: String? = null
        try {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            val date: Date = simpleDateFormat.parse(releaseDate)
            year = formatYear.format(date).toString()

        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return year.toString()
    }
}
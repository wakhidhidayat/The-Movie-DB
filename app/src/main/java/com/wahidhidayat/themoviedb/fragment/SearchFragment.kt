package com.wahidhidayat.themoviedb.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wahidhidayat.themoviedb.BuildConfig
import com.wahidhidayat.themoviedb.R
import com.wahidhidayat.themoviedb.adapter.SearchAdapter
import com.wahidhidayat.themoviedb.model.MovieResult
import com.wahidhidayat.themoviedb.model.Movies
import com.wahidhidayat.themoviedb.network.APIEndpoints
import com.wahidhidayat.themoviedb.network.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {

    private lateinit var searchView: android.widget.SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        searchView = view.findViewById(R.id.search_view)
        progressBar = view.findViewById(R.id.pb_search)
        recyclerView = view.findViewById(R.id.rv_search)

        progressBar.visibility = View.INVISIBLE
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                progressBar.visibility = View.VISIBLE
                if (query != null) {
                    if (query.length > 2) {
                        fetchResult("en-US", query.toString())
                    }
                } else {
                    progressBar.visibility = View.GONE
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                progressBar.visibility = View.VISIBLE
                if (newText == "") {
                    progressBar.visibility = View.GONE
                } else {
                    fetchResult("en-US", newText.toString())
                }
                return true
            }

        })

        return view
    }

    private fun fetchResult(language: String, query: String) {
        val service = APIService.buildService(APIEndpoints::class.java)
        val call = service.getSearch(BuildConfig.API_KEY, language, query)
        call.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.isSuccessful) {
                    progressBar.visibility = View.GONE
                    recyclerView.adapter = activity?.let {
                        SearchAdapter(
                            response.body()!!.result as ArrayList<MovieResult>,
                            it
                        )
                    }
                }
            }

            override fun onFailure(call: Call<Movies>, t: Throwable?) {
                progressBar.visibility = View.GONE
                Toast.makeText(context, t?.localizedMessage.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }
}
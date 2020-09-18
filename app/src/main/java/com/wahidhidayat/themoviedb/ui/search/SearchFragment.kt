package com.wahidhidayat.themoviedb.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.wahidhidayat.themoviedb.R
import com.wahidhidayat.themoviedb.adapter.SearchAdapter
import com.wahidhidayat.themoviedb.model.MovieResult
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class SearchFragment : Fragment(), SearchContract.SearchView {

    private val presenter: SearchPresenter by inject {
        parametersOf(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_search.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }

        search_view.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                showLoading()
                if (query != null) {
                    fetchResult("en-US", query.toString())
                } else {
                    hideLoading()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                showLoading()
                if (newText == "") {
                    hideLoading()
                } else {
                    fetchResult("en-US", newText.toString())
                }
                return true
            }

        })
    }

    private fun fetchResult(language: String, query: String) {
        presenter.fetchResult(language, query)
    }

    override fun showLoading() {
        if (pb_search != null) {
            pb_search.visibility = View.VISIBLE
        }
    }

    override fun hideLoading() {
        if (pb_search != null) {
            pb_search.visibility = View.GONE
        }
    }

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun attachToResult(movie: List<MovieResult>?) {
        if (movie != null) {
            rv_search.adapter = SearchAdapter(movie, context)
        }
    }
}
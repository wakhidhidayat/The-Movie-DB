package com.wahidhidayat.themoviedb.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.wahidhidayat.themoviedb.R
import com.wahidhidayat.themoviedb.adapter.CastAdapter
import com.wahidhidayat.themoviedb.model.Cast
import com.wahidhidayat.themoviedb.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.fragment_info.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class InfoFragment : Fragment(), InfoContract.InfoView {

    private val presenter: InfoPresenter by inject {
        parametersOf(this)
    }

    private var listCast: MutableList<Cast> = mutableListOf()
    private val castAdapter = CastAdapter(listCast, context)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detailActivity: DetailActivity = activity as DetailActivity
        val movie = detailActivity.getMovie()
        val movieId = movie.id

        rv_credits.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = castAdapter
        }

        if (movieId != null) {
            fetchInfo(movieId, "en-US")
            fetchCredits(movieId)
        }
    }

    private fun fetchInfo(movieId: Int, language: String) {
        presenter.fetchInfo(movieId, language)
    }

    private fun fetchCredits(movieId: Int) {
        presenter.fetchCasts(movieId)
    }

    override fun showLoading() {
        if (pb_detail != null) {
            pb_detail.visibility = View.VISIBLE
        }
    }

    override fun hideLoading() {
        if (pb_detail != null) {
            pb_detail.visibility = View.GONE
        }
    }

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun attachToCast(casts: List<Cast>?) {
        if (casts != null) {
            listCast.addAll(casts)
            castAdapter.notifyDataSetChanged()
        }
    }

    override fun setOverview(overview: String) {
        tv_overview.text = overview
    }

    override fun setRelease(release: String) {
        tv_release.text = release
    }

    override fun setRatting(ratting: String) {
        tv_ratting.text = ratting
    }
}
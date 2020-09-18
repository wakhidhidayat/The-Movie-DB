package com.wahidhidayat.themoviedb.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.wahidhidayat.themoviedb.BuildConfig
import com.wahidhidayat.themoviedb.R
import com.wahidhidayat.themoviedb.adapter.ViewPagerAdapter
import com.wahidhidayat.themoviedb.model.MovieResult
import com.wahidhidayat.themoviedb.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

class DetailActivity : AppCompatActivity(), DetailContract.DetailView, KoinComponent {
    private val presenter: DetailPresenter by inject {
        parametersOf(this)
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setSupportActionBar(toolbar_detail)
        supportActionBar?.title = "Movie"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar_detail.setNavigationOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        val viewPagerAdapter = ViewPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = viewPagerAdapter
        tab_layout.setupWithViewPager(view_pager)

        val movie = intent.getParcelableExtra(EXTRA_MOVIE) as MovieResult
        val movieId = movie.id

        if (movieId != null) {
            fetchDetail(movieId, "en-US")
        }
    }

    fun getMovie(): MovieResult {
        return intent.getParcelableExtra(EXTRA_MOVIE) as MovieResult
    }

    private fun fetchDetail(movieId: Int, language: String) {
        presenter.showDetail(movieId, language)
    }

    override fun showLoading() {
        pb_detail.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_detail.visibility = View.GONE
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun setDetailTitle(title: String) {
        tv_title_detail.text = title
    }

    override fun setMovieBackrdop(imageUrl: String) {
        Glide.with(this@DetailActivity)
            .load(BuildConfig.BASE_IMAGE_URL + "w500" + imageUrl)
            .into(iv_detail)
    }
}
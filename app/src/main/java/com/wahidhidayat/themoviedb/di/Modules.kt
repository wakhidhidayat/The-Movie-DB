package com.wahidhidayat.themoviedb.di

import com.wahidhidayat.themoviedb.ui.detail.DetailContract
import com.wahidhidayat.themoviedb.ui.detail.DetailPresenter
import com.wahidhidayat.themoviedb.ui.info.InfoContract
import com.wahidhidayat.themoviedb.ui.info.InfoPresenter
import com.wahidhidayat.themoviedb.ui.movie.MovieContract
import com.wahidhidayat.themoviedb.ui.movie.MoviePresenter
import com.wahidhidayat.themoviedb.ui.search.SearchContract
import com.wahidhidayat.themoviedb.ui.search.SearchPresenter
import com.wahidhidayat.themoviedb.ui.video.VideoContract
import com.wahidhidayat.themoviedb.ui.video.VideoPresenter
import org.koin.dsl.module

val presenterModules = module {
    factory { (view: MovieContract.MovieView) ->
        MoviePresenter(view)
    }

    factory { (view: DetailContract.DetailView) ->
        DetailPresenter(view)
    }

    factory { (view: InfoContract.InfoView) ->
        InfoPresenter(view)
    }

    factory { (view: SearchContract.SearchView) ->
        SearchPresenter(view)
    }

    factory { (view: VideoContract.VideoView) ->
        VideoPresenter(view)
    }
}
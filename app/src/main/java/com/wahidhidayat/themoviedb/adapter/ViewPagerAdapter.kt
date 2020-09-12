package com.wahidhidayat.themoviedb.adapter

import android.content.Context
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.wahidhidayat.themoviedb.ui.info.InfoFragment
import com.wahidhidayat.themoviedb.ui.video.VideoFragment

class ViewPagerAdapter(context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = InfoFragment()
            1 -> fragment = VideoFragment()
        }
        return fragment as Fragment
    }

    override fun getCount(): Int {
        return 2
    }

    @Nullable
    override fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null
        when (position) {
            0 -> title = "Info"
            1 -> title = "Videos"
        }
        return title
    }
}
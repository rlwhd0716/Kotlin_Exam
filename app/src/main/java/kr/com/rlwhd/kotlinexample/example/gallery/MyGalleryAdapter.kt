package kr.com.rlwhd.kotlinexample.example.gallery

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class MyGalleryAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm!!) {
    private val items = ArrayList<Fragment>()

    override fun getItem(position: Int): Fragment {
        return items[position]
    }

    override fun getCount(): Int {
        return items.size
    }

    fun updateFragments(items : List<Fragment>) {
        this.items.addAll(items)
    }
}
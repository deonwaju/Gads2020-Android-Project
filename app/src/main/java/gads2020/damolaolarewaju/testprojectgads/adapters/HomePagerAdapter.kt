package gads2020.damolaolarewaju.testprojectgads.adapters

import android.os.Build
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import gads2020.damolaolarewaju.testprojectgads.ui.views.fragments.IQSkillFragment
import gads2020.damolaolarewaju.testprojectgads.ui.views.fragments.LearningLeaderFragment

class HomePagerAdapter(fragmentManager: FragmentManager):
FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    private val fragments = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        listOf(
            LearningLeaderFragment(),
            IQSkillFragment()
        )
    } else {
        TODO("VERSION.SDK_INT < M")
    }
    private val titles = listOf("Learning Leaders", "Skill IQ Leaders")
    override fun getCount(): Int = fragments.size

    override fun getItem(position: Int): Fragment = fragments[position]
    override fun getPageTitle(position: Int): CharSequence? = titles[position]
}
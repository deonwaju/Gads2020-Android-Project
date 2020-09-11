package gads2020.damolaolarewaju.testprojectgads.ui.views.activity.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*
import gads2020.damolaolarewaju.testprojectgads.R
import gads2020.damolaolarewaju.testprojectgads.adapters.HomePagerAdapter
import gads2020.damolaolarewaju.testprojectgads.ui.views.activity.submit.SubmitActivity

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private val homePagerAdapter: HomePagerAdapter by lazy {
        HomePagerAdapter(supportFragmentManager)
    }
    companion object {
        fun getIntent(context: Context): Intent {
            val intent = Intent(context, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initUi()
        toolbar.title = getString(R.string.toolbar_title)
        setSupportActionBar(toolbar)
        initListeners()
    }
    private fun initListeners(){
        initSubmitButton.setOnClickListener { initSubmitActivity() }
    }
    private fun initUi(){
        tabs.setupWithViewPager(fragmentPager)
        fragmentPager.adapter = homePagerAdapter
    }
    private fun initSubmitActivity(){
        startActivity(Intent(this, SubmitActivity::class.java))
    }
}
package gads2020.damolaolarewaju.testprojectgads.ui.views.activity.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import gads2020.damolaolarewaju.testprojectgads.ui.views.activity.home.HomeActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, 2000)
    }
}
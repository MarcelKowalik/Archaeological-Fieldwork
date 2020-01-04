package org.wit.archaeologicalfieldwork.views.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import org.wit.archaeologicalfieldwork.R
import androidx.appcompat.app.AppCompatActivity
import org.wit.archaeologicalfieldwork.views.hillfortlist.HillfortListView

class SplashScreenView : AppCompatActivity() {
    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 2000

    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {

            //Points to the list of Hillforts.
            val intent = Intent(applicationContext, HillfortListView::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mDelayHandler = Handler()
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)

    }

    public override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }

}
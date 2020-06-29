package test.taylor.com.taylorcode.aysnc.priority

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.suspendlist.R
import kotlinx.coroutines.delay

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SuspendList.of("start-up") {
            Item {
                suspendAction = { fetchUpdateInfo() }
                resumeAction = { resumeUpdateInfo() }
                priority = 2
            }
        }

        delaySplash()
    }

    private fun delaySplash() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(
                {
                    startActivity(Intent(this@SplashActivity, PriorityActivity::class.java))
                    finish()
                },
                5000
        )
    }

    private fun resumeUpdateInfo() {
        Log.v("ttaylor", "tag=suspend list, SplashActivity.resumeUpdateInfo()  thread id =${Thread.currentThread().id}")
    }

    suspend fun fetchUpdateInfo() {
        delay(2000)
    }
}
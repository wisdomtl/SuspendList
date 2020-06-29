package test.taylor.com.taylorcode.aysnc.priority

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.suspendlist.R
import kotlinx.coroutines.delay

class PriorityActivity : AppCompatActivity() {

    private val TAG = "PriorityActivity2"

    private val onObserve = {
        SuspendList.of("start-up") {
            Item {
                Log.d(TAG, ": item user")
                suspendAction = { fetchUser() }
                resumeAction = { user: Any? -> onUserResume(user) }
                priority = 3
            }
            Item {
                Log.d(TAG, ": item activity")
                suspendAction = { fetchActivity() }
                timeout = 3000
                resumeAction = { activity: Any? -> onActivityResume(activity) }
                priority = 1
            }
        }.observe()

        Unit
    }


    private fun onActivityResume(activty: Any?) {
        Log.v("ttaylor", "tag=suspend list, fetch activity(${activty.toString()}) resume  thread id=${Thread.currentThread().id}")
    }

    private fun onUserResume(user: Any?) {
        Log.v("ttaylor", "tag=suspend list, fetch user(${user.toString()}) resume  thread id=${Thread.currentThread().id}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onObserve()
    }

    suspend fun fetchUser(): String {
        Log.v("ttaylor", " PriorityActivity.fetchUser()  thread id=${Thread.currentThread().id}")
        delay(4000)
        Log.w("ttaylor", " PriorityActivity.fetchUser()  thread id=${Thread.currentThread().id}")
        return "User Taylor"
    }

    suspend fun fetchActivity(): String {
        Log.v("ttaylor", " PriorityActivity.fetchActivity()  thread id=${Thread.currentThread().id}")
        delay(5000)
        Log.w("ttaylor", " PriorityActivity.fetchActivity()  thread id=${Thread.currentThread().id}")
        return "Activity Bonus"
    }

}
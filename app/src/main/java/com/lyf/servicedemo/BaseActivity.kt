package com.lyf.servicedemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    lateinit var actionBar: ActionBar
    val TAG = "Service"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar = supportActionBar!!

//        dumpTaskAffinity(this)
//        printTaskInfo(this, "onCreate")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        dumpTaskAffinity(this)
//        printTaskInfo(this, "onNewIntent")
    }

    override fun onRestart() {
        super.onRestart()

//        printTaskInfo(this, "onRestart")
    }

    override fun onStart() {
        super.onStart()

//        printTaskInfo(this, "onStart")
    }

    override fun onResume() {
        super.onResume()

//        printTaskInfo(this, "onResume")
    }

    override fun onPause() {
        super.onPause()

//        printTaskInfo(this, "onPause")
    }

    override fun onStop() {
        super.onStop()

//        printTaskInfo(this, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()

//        printTaskInfo(this, "onDestroy")
    }
}

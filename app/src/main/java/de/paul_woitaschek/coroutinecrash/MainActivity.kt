package de.paul_woitaschek.coroutinecrash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.runBlocking
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    runOnThread {
      runBlocking { suspending() }
    }
  }

  inline fun runOnThread(crossinline action: () -> Unit) {
    thread { action() }
  }

  suspend fun suspending() = kotlinx.coroutines.experimental.run(CommonPool) { }
}

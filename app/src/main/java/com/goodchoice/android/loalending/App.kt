package com.goodchoice.android.loalending

import android.app.Application
import android.content.res.Resources
import android.widget.Toast
import com.goodchoice.android.loalending.base.viewModeModule
import com.goodchoice.android.loalending.network.networkModule
import com.goodchoice.android.loalending.util.constant.BaseUrl
import com.goodchoice.android.loalending.util.constant.Code
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {
    companion object {
        lateinit var resources: Resources
        var cookie = HashSet<String>()
    }

    override fun onCreate() {
        super.onCreate()
        //Timber 초기화
        Timber.plant(Timber.DebugTree())

        Companion.resources = resources

        //koinStart
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(networkModule(BaseUrl.LOALENDING))
            modules(viewModeModule)
        }

        //FireBase
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Timber.e("Fetching FCM registration token failed ${task.exception}")
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            Timber.e("fcm token : $token")
        })

    }
}
package az.altacademy.androidgroup2

import android.app.Application
import android.content.Context
import az.altacademy.androidgroup2.lessons.lesson25.Database
import az.altacademy.androidgroup2.utils.LanguageHelper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
    }

//    override fun attachBaseContext(base: Context) {
//        super.attachBaseContext(
//            LanguageHelper.setLocale(
//                base,
//                LanguageHelper.getCurrentLanguage(base)
//            )
//        )
//    }
}
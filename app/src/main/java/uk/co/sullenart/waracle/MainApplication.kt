package uk.co.sullenart.waracle

import android.app.Application
import android.content.Context
import android.util.Log
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import timber.log.Timber

class MainApplication : Application() {
    lateinit var component: MainComponent

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        component = DaggerMainComponent.builder()
            .mainModule(MainModule(this))
            .build()
    }
}

@GlideModule
class CustomAppGlideModule: AppGlideModule() {
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.setLogLevel(Log.DEBUG)
    }
}
package com.xfinity

import android.app.Application
import android.content.Context
import com.xfinity.injection.component.AppComponent
import com.xfinity.injection.component.DaggerAppComponent
import com.xfinity.injection.module.ApiModule
import com.xfinity.injection.module.AppModule
import com.xfinity.injection.module.NetworkModule

class NewsApplication : Application() {

    var component: AppComponent? = null
        get() {
            val apiUrl = getString(R.string.api_url)


            if (field == null) {
                component = DaggerAppComponent.builder()
                        .apiModule(ApiModule())
                        .networkModule(NetworkModule(applicationContext, apiUrl))
                        .appModule(AppModule(this))
                        .build()
            }
            return field
        }

    companion object {

        operator fun get(context: Context): NewsApplication {
            return context.applicationContext as NewsApplication
        }
    }

}
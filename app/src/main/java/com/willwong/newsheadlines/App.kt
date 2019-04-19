package com.willwong.newsheadlines

import android.app.Application
import com.willwong.newsheadlines.ui.di.module.ApplicationModule
import com.willwong.newsheadlines.ui.di.component.ApplicationComponent
import com.willwong.newsheadlines.ui.di.component.DaggerApplicationComponent
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Inject

/**
 * Initialize realm database here.
 */
class App : Application() {

    lateinit var component: ApplicationComponent
    override fun onCreate() {
        super.onCreate()

        instance = this

        injectDependency()

        Realm.init(this)
        val config : RealmConfiguration = RealmConfiguration.Builder().name("newsrealm.realm")
                .schemaVersion(0)
                .build()
        Realm.setDefaultConfiguration(config)


    }

    fun injectDependency() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this)).build()
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

    companion object {
        lateinit var instance: App private set
    }
}
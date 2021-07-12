package io.ionic.starter

import android.app.Application
import android.util.Log
import io.realm.Realm
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration

var app: App? = null

class IonicApp: Application() {

    override fun onCreate() {
        super.onCreate()

        Log.d("IONIC_QUICKSTART", "Init Realm")

        Realm.init(this)

        val appID : String ="ionictest-uicsb";
        app = App(
            AppConfiguration.Builder(appID)
            .build())
    }
}
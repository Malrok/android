package com.moventes.moventest.android

import android.app.Application
import com.google.firebase.FirebaseApp

class MoventestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this);
    }

}
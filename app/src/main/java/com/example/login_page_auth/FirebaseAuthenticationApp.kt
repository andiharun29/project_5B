package com.example.login_page_auth

import android.app.Application
import com.example.login_page_auth.repositori.AppContainer
import com.example.login_page_auth.repositori.PetContainer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FirebaseAuthenticationApp: Application() {
    lateinit var container: AppContainer

    override fun onCreate(){
        super.onCreate()

        container = PetContainer()
    }
}


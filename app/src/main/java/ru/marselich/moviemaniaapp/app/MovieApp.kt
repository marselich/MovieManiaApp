package ru.marselich.moviemaniaapp.app

import android.app.Application
import ru.marselich.moviemaniaapp.di.DaggerAppComponent

class MovieApp: Application() {

    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }

}
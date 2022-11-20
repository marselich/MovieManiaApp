package ru.marselich.moviemaniaapp.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.marselich.moviemaniaapp.presentation.fragment.MovieListFragment

@Component(modules = [DataModule::class, DomainModule::class])
interface AppComponent {

    fun inject(activity: MovieListFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance context: Context
        ): AppComponent

    }

}
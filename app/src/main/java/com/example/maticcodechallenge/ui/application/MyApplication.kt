package com.example.maticcodechallenge.ui.application

import android.app.Application
import data.source.RepositoryDataSource
import network.api.RepositoryAPI
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class MyApplication : Application(), KodeinAware {

    //Setup Dependency Injection
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MyApplication))
        bind() from provider { RepositoryDataSource(instance()) }
        bind() from provider { RepositoryAPI() }
    }

}

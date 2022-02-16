package com.example.moviecatalogue.injection

import com.example.moviecatalogue.data.source.remote.MovieCatalogueRepository
import com.example.moviecatalogue.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(): MovieCatalogueRepository {

        val remoteDataSource = RemoteDataSource.getInstance()

        return MovieCatalogueRepository.getInstance(remoteDataSource)
    }
}
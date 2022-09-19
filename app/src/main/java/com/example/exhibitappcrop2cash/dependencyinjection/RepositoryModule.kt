package com.example.exhibitappcrop2cash.dependencyinjection

import com.example.exhibitappcrop2cash.network.ExhibitModuleApiInterface
import com.example.exhibitappcrop2cash.repository.ExhibitRepository
import com.example.exhibitappcrop2cash.repository.ExhibitRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideExhibitApiRepository(exhibitModuleApiInterface: ExhibitModuleApiInterface): ExhibitRepositoryInterface {
        return ExhibitRepository(exhibitModuleApiInterface)
    }

}
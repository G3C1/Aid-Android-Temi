package com.g3c1.oasis_android_temi.di.modules

import com.g3c1.oasis_android_temi.data.datasource.datasourceimpl.PurchaseDataSourceImpl
import com.g3c1.oasis_android_temi.data.repositoryimpl.PurchaseRepositoryImpl
import com.g3c1.oasis_android_temi.domain.repository.PurchaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePurchaseRepository(dataSource: PurchaseDataSourceImpl): PurchaseRepository =
        PurchaseRepositoryImpl(dataSource = dataSource)
}
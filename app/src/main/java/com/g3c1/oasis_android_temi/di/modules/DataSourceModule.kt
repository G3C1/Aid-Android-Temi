package com.g3c1.oasis_android_temi.di.modules

import com.g3c1.oasis_android_temi.data.datasource.datasourceimpl.PurchaseDataSourceImpl
import com.g3c1.oasis_android_temi.data.remote.PurchaseAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun providePurchaseDataSource(api: PurchaseAPI) = PurchaseDataSourceImpl(api = api)

}
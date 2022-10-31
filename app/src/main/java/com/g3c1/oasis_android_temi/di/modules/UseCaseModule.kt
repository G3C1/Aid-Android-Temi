package com.g3c1.oasis_android_temi.di.modules

import com.g3c1.oasis_android_temi.domain.repository.PurchaseRepository
import com.g3c1.oasis_android_temi.domain.usecase.purchase.GetOrderListUseCase
import com.g3c1.oasis_android_temi.domain.usecase.purchase.MoveTemiUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetOrderListUseCase(repository: PurchaseRepository): GetOrderListUseCase =
        GetOrderListUseCase(repository = repository)

    @Provides
    @Singleton
    fun provideMoveTemiUseCase(repository: PurchaseRepository): MoveTemiUseCase =
        MoveTemiUseCase(repository = repository)
}
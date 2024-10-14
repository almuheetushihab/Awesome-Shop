package com.example.awesomeshop.cartsscreen

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object CartModule {

    @Provides
    fun provideTotalPriceUpdater(): CartsAdapter.TotalPriceUpdater {
        return object : CartsAdapter.TotalPriceUpdater {
            override fun updateTotalPrice(totalPrice: Double) {
            }
        }
    }
}

package uk.co.sullenart.waracle

import android.content.Context
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class MainModule(val context: Context) {
    @Singleton
    @Provides
    fun provideCakesApi() =
        Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CakeApi::class.java)

    @Singleton
    @Provides
    fun provideDataRepository(cakeApi: CakeApi) = DataRepository(cakeApi)
}
package edu.ucne.apiplanets.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.ucne.apiplanets.data.remote.DragonBallApi
import edu.ucne.apiplanets.data.repository.PlanetRepositoryImpl
import edu.ucne.apiplanets.domain.repository.PlanetRepository
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideApi(moshi: Moshi): DragonBallApi =
        Retrofit.Builder()
            .baseUrl("https://dragonball-api.com/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(DragonBallApi::class.java)

    @Provides
    @Singleton
    fun provideRepository(api: DragonBallApi): PlanetRepositoryImpl =
        PlanetRepositoryImpl(api)

    @Provides
    @Singleton
    fun providePlanetRepository(impl: PlanetRepositoryImpl): PlanetRepository = impl
}
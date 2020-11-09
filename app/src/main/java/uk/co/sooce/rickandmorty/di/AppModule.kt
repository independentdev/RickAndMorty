package uk.co.sooce.rickandmorty.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uk.co.sooce.rickandmorty.data.local.AppDatabase
import uk.co.sooce.rickandmorty.data.local.dao.CharacterDao
import uk.co.sooce.rickandmorty.data.remote.RAMApi
import uk.co.sooce.rickandmorty.data.remote.RemoteDataSource
import uk.co.sooce.rickandmorty.data.repository.RAMRepository
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(ramApi: RAMApi) = RemoteDataSource(ramApi)

    @Singleton
    @Provides
    fun provideRamApi(gson: Gson) : RAMApi = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build().create(RAMApi::class.java)

    @Singleton
    @Provides
    fun provideGson() : Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getInstance(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDatabase) = db.characterDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RemoteDataSource,
                          localDataSource: CharacterDao) =
        RAMRepository(localDataSource, remoteDataSource)
}
package com.example.hw6_2.di

import com.example.hw6_2.BuildConfig
import com.example.hw6_2.data.CharactersPagingSource
import com.example.hw6_2.data.network.CharacterApi
import com.example.hw6_2.data.repository.CharacterRepository
import com.example.hw6_2.data.repository.CharactersRepository
import com.example.hw6_2.ui.character.CharacterViewModel
import com.example.hw6_2.ui.character.EpisodesRecyclerViewAdapter
import com.example.hw6_2.ui.characters.CharactersPagingAdapter
import com.example.hw6_2.ui.characters.CharactersViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module {
    single { provideLoggingInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single { provideApi(get()) }
}

val repositoryModule = module {
    single { provideCharacterRepository(get()) }
    single { provideCharactersRepository(get()) }
    single { provideCharacterPagingSource(get()) }
}

val viewModelModule = module {
    viewModel<CharacterViewModel> { provideCharacterViewModel(get()) }
    viewModel<CharactersViewModel> { provideCharactersViewModel(get()) }
}

val recyclerViewAdapterModule = module {
    factory { provideEpisodesAdapter() }
    factory { provideCharactersPagingAdapter(get()) }
}

val modules =
    listOf(networkModule, repositoryModule, viewModelModule, recyclerViewAdapterModule)

fun provideCharacterPagingSource(api: CharacterApi) =
    CharactersPagingSource(api)

fun provideCharacterViewModel(repository: CharacterRepository) =
    CharacterViewModel(repository)

fun provideCharactersViewModel(repository: CharactersRepository) =
    CharactersViewModel(repository)

fun provideCharacterRepository(api: CharacterApi) =
    CharacterRepository(api)

fun provideCharactersRepository(charactersPagingSource: CharactersPagingSource) =
    CharactersRepository(charactersPagingSource)

fun provideEpisodesAdapter() =
    EpisodesRecyclerViewAdapter()


fun provideCharactersPagingAdapter(api: CharacterApi) =
    CharactersPagingAdapter(api)

fun provideApi(retrofit: Retrofit): CharacterApi =
    retrofit.create(CharacterApi::class.java)

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(BuildConfig.BASEURL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .connectTimeout(5L, TimeUnit.SECONDS)
        .readTimeout(5L, TimeUnit.SECONDS)
        .writeTimeout(5L, TimeUnit.SECONDS)
        .build()

fun provideLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }
package com.midtronics.di

import com.midtronics.data.api.CountryService
import com.midtronics.data.assets.ProfileReader
import com.midtronics.domain.CountriesUseCase
import com.midtronics.domain.CountryUseCase
import com.midtronics.domain.ProfileUseCase
import com.midtronics.ui.countries.CountriesViewModel
import com.midtronics.ui.country.CountryViewModel
import com.midtronics.ui.profile.ProfileViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single<ProfileReader> {
        ProfileReader(
            context = get(),
            fileName = "profile.json"
        )
    }

    single<CountryService> {

        val baseUrl = "https://restcountries.com/v3.1/"

        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(CountryService::class.java)
    }

}

val domainModule = module {
    single<ProfileUseCase> {
        ProfileUseCase(reader = get())
    }

    single<CountriesUseCase> {
        CountriesUseCase(context = get())
    }

    single<CountryUseCase> {
        CountryUseCase(service = get())
    }
}

val viewModelModule = module {
    viewModel<ProfileViewModel> { ProfileViewModel(get()) }
    viewModel<CountriesViewModel> { CountriesViewModel(get()) }
    viewModel<CountryViewModel> { CountryViewModel(get()) }
}
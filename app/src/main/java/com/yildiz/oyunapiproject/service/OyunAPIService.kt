package com.yildiz.oyunapiproject.service

import com.yildiz.oyunapiproject.model.OyunResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class OyunAPIService {
    private val BASE_URL = "https://oyunpuanla.com/machine/public/index.php/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(OyunAPI::class.java)

    fun getOyun() : Single<OyunResponse> {
        return api.getData()
    }
}
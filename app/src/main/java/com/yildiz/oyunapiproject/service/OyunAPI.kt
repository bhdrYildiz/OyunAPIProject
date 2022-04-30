package com.yildiz.oyunapiproject.service

import com.yildiz.oyunapiproject.model.OyunResponse
import io.reactivex.Single
import retrofit2.http.GET

interface OyunAPI {
    companion object {
        const val OYUN = "machineTypeList/machine/public/index.php/"
    }
    @GET(OYUN)
    fun getData(): Single<OyunResponse>
}
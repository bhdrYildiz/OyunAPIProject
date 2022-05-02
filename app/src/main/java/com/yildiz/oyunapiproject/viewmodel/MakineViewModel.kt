package com.yildiz.oyunapiproject.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yildiz.oyunapiproject.model.OyunResponse
import com.yildiz.oyunapiproject.service.OyunAPIService
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers

class MakineViewModel(application: Application) : BaseViewModel(application) {

    private val makineApı = OyunAPIService()
    private val disposable = CompositeDisposable()

    private val makineliveData = MutableLiveData<OyunResponse>()
    var makinelistLiveData : LiveData<OyunResponse> = makineliveData

    fun makineList(){
        disposable.add(
            makineApı.getOyun()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<OyunResponse>() {
                    override fun onSuccess(response: OyunResponse) {
                        makineliveData.value = response
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
        )
    }

}
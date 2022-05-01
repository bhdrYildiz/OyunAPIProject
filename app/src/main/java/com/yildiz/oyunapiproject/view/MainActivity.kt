package com.yildiz.oyunapiproject.view

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yildiz.oyunapiproject.R
import com.yildiz.oyunapiproject.adapter.RecyclerViewAdapter
import com.yildiz.oyunapiproject.databinding.ActivityMainBinding
import com.yildiz.oyunapiproject.model.OyunResponse
import com.yildiz.oyunapiproject.service.OyunAPI
import com.yildiz.oyunapiproject.service.OyunAPIService
import io.reactivex.Scheduler
import io.reactivex.SingleSource
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private var oyunApıService = OyunAPIService()
    private val recyclerviewAdapter = RecyclerViewAdapter()
    private lateinit var binding: ActivityMainBinding
    private var oyunNameList: ArrayList<String?> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        oyunApıService.getOyun()
            .subscribeOn(Schedulers.newThread())
            .subscribeWith(object : DisposableSingleObserver<OyunResponse>() {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onSuccess(s: OyunResponse) {
                    runOnUiThread {
                        recyclerviewAdapter.setOyunData(s)
                        s.forEach {
                                oyunNameList.add(it.machineType)
                        }
                    }
                }
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }
            })

        recyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = recyclerviewAdapter

    }

}


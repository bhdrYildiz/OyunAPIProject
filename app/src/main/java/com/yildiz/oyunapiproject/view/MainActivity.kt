package com.yildiz.oyunapiproject.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yildiz.oyunapiproject.adapter.RecyclerViewAdapter
import com.yildiz.oyunapiproject.databinding.ActivityMainBinding
import com.yildiz.oyunapiproject.model.OyunResponse
import com.yildiz.oyunapiproject.service.OyunAPIService
import com.yildiz.oyunapiproject.viewmodel.MakineViewModel
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var oyunApıService = OyunAPIService()
    private val recyclerviewAdapter = RecyclerViewAdapter()
    private var makineList: ArrayList<OyunResponse.OyunResponseItem> = arrayListOf()
    private lateinit var binding: ActivityMainBinding
    private var oyunNameList: ArrayList<String?> = arrayListOf()
    private lateinit var makineViewModel: MakineViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        makineViewModel = ViewModelProvider(this).get(MakineViewModel::class.java)

        makineViewModel.makineList()
        observableLiveData()

        recyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = recyclerviewAdapter
    }

    private fun observableLiveData() {
        makineViewModel.makinelistLiveData.observe(this) {
            it.forEach { res ->
                makineList.add(res)
            }
            recyclerviewAdapter.setOyunData(makineList)
        }



       /* oyunApıService.getOyun()
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
        recyclerView.adapter = recyclerviewAdapter*/

    }

}


package com.yildiz.oyunapiproject.model


import com.google.gson.annotations.SerializedName

class OyunResponse : ArrayList<OyunResponse.OyunResponseItem>(){
    data class OyunResponseItem(
        @SerializedName("machineType")
        val machineType: String
    )
}
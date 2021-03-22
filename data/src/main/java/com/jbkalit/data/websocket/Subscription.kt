package com.jbkalit.data.websocket

import com.google.gson.annotations.SerializedName

data class Subscription(
    @SerializedName("action")
    var action: String,
    @SerializedName("subs")
    var subs: List<String>
)

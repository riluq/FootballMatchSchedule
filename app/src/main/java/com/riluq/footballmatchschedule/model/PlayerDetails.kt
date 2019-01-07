package com.riluq.footballmatchschedule.model

import com.google.gson.annotations.SerializedName

data class PlayerDetails(
        @SerializedName("strPlayer")
        var playerName: String? = null,

        @SerializedName("strFanart1")
        var playerPhoto: String? = null,

        @SerializedName("strWeight")
        var playerweight: String? = null,

        @SerializedName("strHeight")
        var playerHeight: String? = null,

        @SerializedName("strPosition")
        var playerPosition: String? = null,

        @SerializedName("strDescriptionEN")
        var playerDescription: String? = null


)
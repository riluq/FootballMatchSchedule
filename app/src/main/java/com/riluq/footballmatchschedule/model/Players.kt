package com.riluq.footballmatchschedule.model

import com.google.gson.annotations.SerializedName

data class Players (
        @SerializedName("idPlayer")
        var playerId: String? = null,

        @SerializedName("strPlayer")
        var playerName: String? = null,

        @SerializedName("strCutout")
        var playerImage: String? = null,

        @SerializedName("strPosition")
        var playerPosition: String? = null
)
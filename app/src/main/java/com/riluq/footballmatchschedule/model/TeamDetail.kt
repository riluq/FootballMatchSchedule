package com.riluq.footballmatchschedule.model

import com.google.gson.annotations.SerializedName

data class TeamDetail (
        @SerializedName("strTeamBadge")
        var logoTeam: String? = null
)
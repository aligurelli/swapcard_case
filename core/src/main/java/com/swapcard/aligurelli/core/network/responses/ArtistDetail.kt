package com.swapcard.aligurelli.core.network.responses

data class ArtistDetail(
     val hasError : Boolean = false,
     val id : String ="",
     val name: String? = null,
     val disambiguation : String? = null,
     val rating : Double?= null,

)

package com.storyblok.android.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

object URLConstants {
  const val HOME_URL = "home"
}

interface StoryblokService {
    @GET("${URLConstants.HOME_URL}?version=published")
    fun getHome(@Query("token") token: String): Call<HomeResult>
}
package com.storyblok.android.model

import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeGetter {
  private val service: StoryblokService

  companion object {
    const val BASE_URL = "https://api.storyblok.com/v2/cdn/stories/"
  }

  init {
    val retrofit = Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .build()

    service = retrofit.create(StoryblokService::class.java)
  }

  fun getHomeData(token: String, callback: Callback<HomeResult>) {
    val call = service.getHome(token)
    call.enqueue(callback)
  }
}
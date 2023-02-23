package com.storyblok.android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.storyblok.android.model.HomeGetter
import com.storyblok.android.model.HomeResult
import com.storyblok.android.model.Story
import com.storyblok.android.ui.theme.AndroidApplicationTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : ComponentActivity() {

  private val homeGetter = HomeGetter()

  private var story : Story? = null

  private val callback = object : Callback<HomeResult> {
    override fun onFailure(call: Call<HomeResult>, t: Throwable) {
      Log.e("MainActivity", t.message!!)
    }

    override fun onResponse(call: Call<HomeResult>, response: Response<HomeResult>) {
      response.isSuccessful.let {
        story = response.body()?.story
        setHomeContent()
      }
    }
  }

  fun setHomeContent() {
    setContent {
      AndroidApplicationTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
          val headline = story!!.content.body[0].headline
          val subheadline = story!!.content.body[0].subheadline
          val imageurl = story!!.content.body[0].background_image.filename
          HomePage(headline, subheadline, imageurl)
        }
      }
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    homeGetter.getHomeData(getString(R.string.token), callback)
  }
}

@Composable
fun HomePage(headline: String, subheadline: String, imageurl: String) {
  GlideImage(
    imageModel = { imageurl },
    imageOptions = ImageOptions(
      alignment = Alignment.Center,
      requestSize = IntSize(800,1200)
    )
  )
  Column(modifier = Modifier.padding(16.dp)) {
    Text(text = headline, color= Color.White, fontSize = 30.sp)
    Text(text = subheadline,  color= Color.White)
  }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  AndroidApplicationTheme {
    HomePage("Android",
      "Jetpack Compose",
      "https://a.storyblok.com/f/172000/2560x1946/7ddce44d28/earth.jpg"
    )
  }
}
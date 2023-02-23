package com.storyblok.android.model

data class HomeResult(
  val story: Story
)

data class Story(
  val name: String,
  val content: Content,
)

data class Content(
  val body: List<Element>
)

data class Element(
  val headline: String,
  val subheadline: String,
  val background_image: BackgroundImage
)

data class BackgroundImage(
  val filename: String
)
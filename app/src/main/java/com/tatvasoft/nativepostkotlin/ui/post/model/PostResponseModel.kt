package com.tatvasoft.nativepostkotlin.ui.post.model

import com.google.gson.annotations.SerializedName
import kotlin.reflect.KProperty

data class PostResponseModel(


    @field:SerializedName("hits")
    val hits: List<HitsItem?>? = null,

    @field:SerializedName("hitsPerPage")
    val hitsPerPage: Int? = null,

    @field:SerializedName("processingTimeMS")
    val processingTimeMS: Int? = null,

    @field:SerializedName("query")
    val query: String? = null,

    @field:SerializedName("nbHits")
    val nbHits: Int? = null,

    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("params")
    val params: String? = null,

    @field:SerializedName("nbPages")
    val nbPages: Int? = null,

    @field:SerializedName("exhaustiveNbHits")
    val exhaustiveNbHits: Boolean? = null
)

data class HighlightResult(

    @field:SerializedName("author")
    val author: Author? = null,

    @field:SerializedName("title")
    val title: Title? = null,

    @field:SerializedName("url")
    val url: Url? = null,

    @field:SerializedName("story_text")
    val storyText: StoryText? = null
)

data class HitsItem(

	var isCheck: Boolean=false,



    @field:SerializedName("comment_text")
    val commentText: Any? = null,

    @field:SerializedName("story_text")
    val storyText: Any? = null,

    @field:SerializedName("author")
    val author: String? = null,

    @field:SerializedName("story_id")
    val storyId: Any? = null,

    @field:SerializedName("_tags")
    val tags: List<String?>? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("created_at_i")
    val createdAtI: Int? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("points")
    val points: Int? = null,

    @field:SerializedName("_highlightResult")
    val highlightResult: HighlightResult? = null,

    @field:SerializedName("num_comments")
    val numComments: Int? = null,

    @field:SerializedName("story_title")
    val storyTitle: Any? = null,

    @field:SerializedName("parent_id")
    val parentId: Any? = null,

    @field:SerializedName("story_url")
    val storyUrl: Any? = null,

    @field:SerializedName("objectID")
    val objectID: String? = null

)
data class Author(

    @field:SerializedName("matchLevel")
    val matchLevel: String? = null,

    @field:SerializedName("value")
    val value: String? = null,

    @field:SerializedName("matchedWords")
    val matchedWords: List<Any?>? = null
)

data class StoryText(

    @field:SerializedName("matchLevel")
    val matchLevel: String? = null,

    @field:SerializedName("value")
    val value: String? = null,

    @field:SerializedName("matchedWords")
    val matchedWords: List<Any?>? = null
)

data class Url(

    @field:SerializedName("matchLevel")
    val matchLevel: String? = null,

    @field:SerializedName("value")
    val value: String? = null,

    @field:SerializedName("matchedWords")
    val matchedWords: List<Any?>? = null
)

data class Title(

    @field:SerializedName("matchLevel")
    val matchLevel: String? = null,

    @field:SerializedName("value")
    val value: String? = null,

    @field:SerializedName("matchedWords")
    val matchedWords: List<Any?>? = null
)

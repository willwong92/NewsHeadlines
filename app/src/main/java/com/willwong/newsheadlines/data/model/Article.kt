package com.willwong.newsheadlines.data.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.RealmClass


/**
 * Created by WillWong on 3/25/19.
 */
@RealmClass
open class Article : RealmObject() {
        @Expose
        @SerializedName("source")
        var source: Source? = null
        @Expose
        @SerializedName("author")
        var authorName: String? = null
        @Expose
        @SerializedName("title")
        var titleName: String? = null
        @Expose
        @SerializedName("description")
        var description: String? = null
        @Expose
        @SerializedName("url")
        var url : String? = null
        @Expose
        @SerializedName("urlToImage") var urlImage: String? = null
        @Expose
        @SerializedName("publishedAt")
        var publishedAt: String? = null
        @Expose
        var content : String? = null
}
package com.willwong.newsheadlines.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Source (
        var id : String? = null,
        @Expose
        @SerializedName("name")
        @PrimaryKey
        var name : String? = null
) : RealmObject()
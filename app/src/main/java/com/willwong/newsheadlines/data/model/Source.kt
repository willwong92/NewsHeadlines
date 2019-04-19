package com.willwong.newsheadlines.data.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Source (
        var id : String? = null,
        @PrimaryKey
        var name : String? = null
) : RealmObject()
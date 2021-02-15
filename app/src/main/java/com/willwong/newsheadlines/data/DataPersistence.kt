package com.willwong.newsheadlines.data

import android.support.annotation.NonNull
import com.willwong.newsheadlines.data.model.Article

/**
 * Interface for data persistence
 */
interface DataPersistence {
    fun save(@NonNull articlesList: List<Article>)
}
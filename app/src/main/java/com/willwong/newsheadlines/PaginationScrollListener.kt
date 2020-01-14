package com.willwong.newsheadlines

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

abstract class PaginationScrollListener : RecyclerView.OnScrollListener{

    var layoutManager : LinearLayoutManager

    private val PAGE_SIZE = 10

    constructor(layoutManager: LinearLayoutManager) {
        this.layoutManager = layoutManager
    }

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        var visibleItemCount = layoutManager.childCount
        var totalItemCount = layoutManager.itemCount
        var firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (!isLoading() && !isLastPage()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0
                    && totalItemCount >= PAGE_SIZE) {
                loadMoreItems()
            }
        }
    }

    protected abstract fun loadMoreItems()

    abstract fun isLastPage() : Boolean

    abstract fun isLoading() : Boolean



}
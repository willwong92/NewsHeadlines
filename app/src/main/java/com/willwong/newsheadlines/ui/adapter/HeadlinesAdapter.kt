package com.willwong.newsheadlines.ui.adapter


import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.willwong.newsheadlines.R
import com.willwong.newsheadlines.databinding.ArticleRowBinding
import com.willwong.newsheadlines.data.model.Article


/**
 * Created by WillWong on 3/25/19.
 */
class HeadlinesAdapter(listener : onItemClickListener) : RecyclerView.Adapter<HeadlinesAdapter.ArticleHolder>() {
    private var list = mutableListOf<Article>()

    interface onItemClickListener {
        fun onItemClicked(article : Article)
    }

    private val listener : onItemClickListener

    init {
        list = ArrayList()
        this.listener = listener
    }


    inner class ArticleHolder(binding: ArticleRowBinding) : RecyclerView.ViewHolder(binding.root) {
        private val binding : ArticleRowBinding
        init {
            this.binding = binding
        }


        fun bindArticle(article: Article, listener: onItemClickListener) {
            binding.title.text = article.titleName
            binding.author.text = article.authorName
            Glide.with(binding.img.context).load(article.urlImage).into(binding.img)
            binding.desc.text = article.description

            binding.getRoot().setOnClickListener(object:View.OnClickListener {
                override fun onClick(view:View) {
                        listener.onItemClicked(article)
                }
            })

            binding.executePendingBindings()
        }
    }
    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        val itemArticle = list!![position]
        holder.bindArticle(itemArticle, listener)
    }

    fun clear() {
        list.clear()
    }

    override fun getItemCount(): Int = list!!.size

    fun setArticles(newsList: List<Article>) {
        list.addAll(newsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
        val dataBinding : ArticleRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.article_row_item,parent,false)
        dataBinding.prograssLoadPhoto.visibility = View.GONE
        return ArticleHolder(dataBinding)    }

}
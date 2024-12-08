package az.altacademy.androidgroup2.news.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import az.altacademy.androidgroup2.databinding.ItemNewsBinding
import az.altacademy.androidgroup2.news.data.models.NewsModel
import coil.load

class NewsAdapter: PagingDataAdapter<NewsModel, NewsAdapter.NewsViewHolder>(diffUtil) {

    inner class NewsViewHolder(private val binding: ItemNewsBinding): ViewHolder(binding.root){
        fun bind(data: NewsModel?){
            binding.newsTitle.text = data?.title
            binding.newsDesc.text = data?.description
            binding.newsImage.load(data?.urlToImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsBinding.inflate(inflater, parent, false)
        return NewsViewHolder(binding)
    }


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object{
        val diffUtil = object:  DiffUtil.ItemCallback<NewsModel>(){
            override fun areItemsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
                return oldItem.title == newItem.title
                        && oldItem.urlToImage == newItem.urlToImage
                        && oldItem.content == newItem.content
                        && oldItem.author == newItem.author
                        && oldItem.description == newItem.description
            }

        }
    }
}
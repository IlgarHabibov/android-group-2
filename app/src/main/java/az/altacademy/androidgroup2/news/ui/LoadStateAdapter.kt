package az.altacademy.androidgroup2.news.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import az.altacademy.androidgroup2.databinding.ItemLoadStateBinding

class LoadStateAdapter: LoadStateAdapter<az.altacademy.androidgroup2.news.ui.LoadStateAdapter.StateViewHolder> (){

    class StateViewHolder(private val binding: ItemLoadStateBinding): ViewHolder(binding.root){
        fun bind(loadState: LoadState){
            binding.loadingState.isVisible = loadState is LoadState.Loading
        }
    }

    override fun onBindViewHolder(holder: StateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): StateViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLoadStateBinding.inflate(inflater, parent, false)
        return StateViewHolder(binding)
    }
}
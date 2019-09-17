package com.example.maticcodechallenge.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.maticcodechallenge.R
import com.example.maticcodechallenge.databinding.NetworkItemBinding
import com.example.maticcodechallenge.databinding.RepositoryRowBinding
import com.squareup.picasso.Picasso
import data.model.NetworkState
import data.model.Repository


class RepositoriesAdapter :
    PagedListAdapter<Repository, RecyclerView.ViewHolder>(
        RepositoryDiffCallback
    ) {
    private var networkState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_ITEM) {
            RepositoryItemViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.row_repository,
                    parent,
                    false
                )
            )
        } else {
            return NetworkStateItemViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.row_network,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RepositoryItemViewHolder) {
            getItem(position)?.let { repository ->
                Picasso.get().load(repository.owner.avatarUrl)
                    .placeholder(R.drawable.ic_placeholder).error(R.drawable.ic_placeholder)
                    .into(holder.binding.ownerImage)
                holder.binding.model = repository
                holder.binding.rating.text = repository.score.toInt().toString()
            }

        } else if (holder is NetworkStateItemViewHolder) {
            networkState?.let {
                holder.binding.progressBar.visibility =
                    if (it.status == NetworkState.Status.RUNNING) View.VISIBLE else View.GONE
                if (it.status === NetworkState.Status.FAILED) {
                    holder.binding.errorMsg.visibility = View.VISIBLE
                    holder.binding.errorMsg.text = holder.binding.root.context.getString(it.msg)
                } else
                    holder.binding.errorMsg.visibility = View.GONE
            }
        }
    }


    private fun hasExtraRow(): Boolean {
        return networkState != null && networkState !== NetworkState.LOADED
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            TYPE_PROGRESS
        } else {
            TYPE_ITEM
        }
    }

    fun setNetworkState(newNetworkState: NetworkState) {
        val previousState = this.networkState
        val previousExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val newExtraRow = hasExtraRow()
        if (previousExtraRow != newExtraRow) {
            if (previousExtraRow) {
                notifyItemRemoved(itemCount)
            } else {
                notifyItemInserted(itemCount)
            }
        } else if (newExtraRow && previousState !== newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }


    class RepositoryItemViewHolder(val binding: RepositoryRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    class NetworkStateItemViewHolder(val binding: NetworkItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {

        private const val TYPE_PROGRESS = 0
        private const val TYPE_ITEM = 1

        val RepositoryDiffCallback = object : DiffUtil.ItemCallback<Repository>() {
            override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
                return oldItem == newItem
            }

        }
    }
}

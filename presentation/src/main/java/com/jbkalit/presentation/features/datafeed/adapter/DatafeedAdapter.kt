package com.jbkalit.presentation.features.datafeed.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jbkalit.domain.model.WebSocket
import com.jbkalit.presentation.databinding.ViewDatafeedItemBinding

class DatafeedAdapter(private var webSocketList : MutableList<WebSocket>)
    : ListAdapter<WebSocket, RecyclerView.ViewHolder>(WebSocketItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatafeedAdapter.ViewHolder {
        val binding =
            ViewDatafeedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ViewDatafeedItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(webSocket: WebSocket) {
            with(binding) {
                firstCodeTextView.text = webSocket.symbol
                firstPriceTextView.text = webSocket.topTierFullVolume.toString()
            }
        }
    }

    class WebSocketItemDiffCallback : DiffUtil.ItemCallback<WebSocket>() {
        override fun areItemsTheSame(oldItem: WebSocket, newItem: WebSocket): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: WebSocket, newItem: WebSocket): Boolean {
            return oldItem == newItem
        }
    }

    fun addCryptoWebSocketToList(webSocketList: MutableList<WebSocket>) {
        this.webSocketList.clear()
        this.webSocketList.addAll(webSocketList)
        notifyDataSetChanged()
    }

}

package com.jbkalit.presentation.features.watchlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jbkalit.domain.model.Crypto
import com.jbkalit.presentation.R
import com.jbkalit.presentation.databinding.ViewWatchlistItemBinding
import com.jbkalit.presentation.extension.addPrefix
import com.jbkalit.presentation.extension.changeTextColor
import com.jbkalit.presentation.util.NumberUtils

class WatchlistAdapter(private var cryptoList : MutableList<Crypto>,
                       private val reachEndItem: () -> Unit) :
    ListAdapter<Crypto, RecyclerView.ViewHolder>(CryptoItemDiffCallback()) {

    override fun getItemCount() = cryptoList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchlistAdapter.ViewHolder {
        val binding =
            ViewWatchlistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(cryptoList[position])
        when (position) {
            itemCount - 1 -> reachEndItem
        }
    }

    inner class ViewHolder(private val binding: ViewWatchlistItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cryptoModel: Crypto) {
            with(binding) {
                codeTextView.text = cryptoModel.name
                companyTextView.text = cryptoModel.fullName
                percentageTextView.changeTextColor(cryptoModel.changePrice, itemView.context)
                priceTextView.text = NumberUtils.formatPrice(cryptoModel.currentPrice)
                val changePercentage =
                    NumberUtils.formatPriceChanges(cryptoModel.changePricePercent).addPrefix()
                val changePrice =
                    NumberUtils.formatPriceChanges(cryptoModel.changePrice).addPrefix()
                percentageTextView.text =
                    itemView.context.getString(
                        R.string.changes_info,
                        changePrice,
                        changePercentage
                    )
            }
        }
    }

    class CryptoItemDiffCallback : DiffUtil.ItemCallback<Crypto>() {
        override fun areItemsTheSame(oldItem: Crypto, newItem: Crypto): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Crypto, newItem: Crypto): Boolean {
            return oldItem == newItem
        }
    }

    fun addCryptoToList(movieList: MutableList<Crypto>) {
        this.cryptoList.clear()
        this.cryptoList.addAll(movieList)
        notifyDataSetChanged()
    }

}

package com.example.injob.ui.search.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.injob.R
import com.example.injob.data.db.AdEntity
import com.example.injob.databinding.ItemAdsBinding

class SearchAdapter() : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    var listOfAds = ArrayList<AdEntity>()

    fun setListData(data: ArrayList<AdEntity>) {
        this.listOfAds = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            ItemAdsBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.binding.reactBtn.setOnClickListener {
            holder.binding.reactBtn.visibility = View.GONE
            holder.binding.respondedBtn.visibility = View.VISIBLE
        }
        holder.bind(listOfAds[position])
    }

    override fun getItemCount(): Int {
        return listOfAds.size
    }

    class SearchViewHolder(val binding: ItemAdsBinding) : RecyclerView.ViewHolder(binding.root) {

        private val tvTitle = binding.itemTitle
        private val tvDescription = binding.itemDescription
        private val tvPayment = binding.itemPayment
        private val tvLocation = binding.itemLocation
        private val tvImage = binding.itemImage

        fun bind(data: AdEntity) {
            tvTitle.text = data.title
            tvDescription.text = data.description
            tvPayment.text = data.payment
            tvLocation.text = data.location
            tvImage.setImageURI(Uri.parse(data.image))
        }
    }
}
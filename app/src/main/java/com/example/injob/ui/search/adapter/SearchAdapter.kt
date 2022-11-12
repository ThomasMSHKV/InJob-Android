package com.example.injob.ui.search.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.injob.R
import com.example.injob.data.db.AdEntity
import com.example.injob.databinding.ItemAdsBinding

class SearchAdapter(
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    var listOfAds = listOf<AdEntity>()
    var launchBottomSheet: () -> Unit = {}
    var launchAdsLikeWasClicked: (Int) -> Unit = {}
    var launchAdWasResponded: (Int) -> Unit = {}

    fun setListData(data: List<AdEntity>) {
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

            if (listOfAds[position].isResponded) {
                holder.binding.reactBtn.visibility = View.VISIBLE
                holder.binding.respondedBtn.visibility = View.GONE
                launchAdWasResponded.invoke(position)
                notifyItemChanged(position)
            } else {
                holder.binding.reactBtn.visibility = View.GONE
                holder.binding.respondedBtn.visibility = View.VISIBLE
                launchAdWasResponded.invoke(position)
                notifyItemChanged(position)
            }
        }
        holder.binding.contactsBtn.setOnClickListener {
            launchBottomSheet.invoke()
        }
        holder.binding.itemFavoriteBtn.setOnClickListener { imageView ->
            if (listOfAds[position].isLiked) {
                imageView.background = ActivityCompat.getDrawable(
                    holder.binding.root.context,
                    R.drawable.ic_favorite_inactive
                )
                launchAdsLikeWasClicked.invoke(position)
                notifyItemChanged(position)
            } else {
                imageView.background = ActivityCompat.getDrawable(
                    holder.binding.root.context,
                    R.drawable.ic_favorite_active
                )
                launchAdsLikeWasClicked.invoke(position)
                notifyItemChanged(position)
            }
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
        private val ivImage = binding.itemImage
        private val btnItemFavorite = binding.itemFavoriteBtn
        private val btnReact = binding.reactBtn
        private val btnResponded = binding.respondedBtn

        fun bind(ad: AdEntity) {
            tvTitle.text = ad.title
            tvDescription.text = ad.description
            tvPayment.text = ad.payment
            tvLocation.text = ad.location
            if (ad.isLiked) {
                btnItemFavorite.background =
                    ActivityCompat.getDrawable(
                        binding.root.context,
                        R.drawable.ic_favorite_active
                    )
            } else {
                btnItemFavorite.background =
                    ActivityCompat.getDrawable(
                        binding.root.context,
                        R.drawable.ic_favorite_inactive_black
                    )
            }

            if (ad.isResponded) {
                btnReact.visibility = View.GONE
                btnResponded.visibility = View.VISIBLE
            } else {
                btnReact.visibility = View.VISIBLE
                btnResponded.visibility = View.GONE
            }

            if (ad.imageIsChosen) {
                ivImage.setImageURI(Uri.parse(ad.image))
            } else {
                ivImage.setImageResource(R.drawable.picture_placeholder)
            }
        }
    }
}
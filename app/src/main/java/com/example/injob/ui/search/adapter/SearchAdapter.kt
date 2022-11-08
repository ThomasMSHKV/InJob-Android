package com.example.injob.ui.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.injob.R
import com.example.injob.data.db.AdEntity
import kotlinx.android.synthetic.main.item_ads.view.*

class SearchAdapter() : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    var listOfAds = ArrayList<AdEntity>()

    fun setListData(data: ArrayList<AdEntity>) {
        this.listOfAds = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_ads, parent, false)
        return SearchViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {

        holder.reactBtn.setOnClickListener {
            holder.reactBtn.visibility = View.GONE
            holder.respondedBtn.visibility = View.VISIBLE
        }
        holder.bind(listOfAds[position])
    }

    override fun getItemCount(): Int {
        return listOfAds.size
    }

    class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvTitle = view.item_title
        val tvDescription = view.item_description
        val tvPayment = view.item_payment
        val tvLocation = view.item_location
        val tvImage = view.item_image
        val reactBtn = view.react_btn
        val contactsBtn = view.contacts_btn
        val respondedBtn = view.responded_btn

        fun bind(data: AdEntity) {
            tvTitle.text = data.title
            tvDescription.text = data.description
            tvPayment.text = data.payment
            tvLocation.text = data.location


        }
    }
}

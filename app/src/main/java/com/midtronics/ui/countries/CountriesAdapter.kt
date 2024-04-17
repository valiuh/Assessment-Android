package com.midtronics.ui.countries

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.midtronics.R

class CountriesAdapter(
    private val clickListener: (country: String) -> Unit
):
    RecyclerView.Adapter<CountriesAdapter.CountryViewHolder>() {

    private var countries: List<String> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_list_country, parent, false)

        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val lesson = countries[position]
        holder.bind(lesson)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(countries: List<String>) {
        this.countries  = countries
        notifyDataSetChanged()
    }

    inner class CountryViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView),  View.OnClickListener {
        private val countryName: TextView = itemView.findViewById(R.id.country_name)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            clickListener(
                countries[adapterPosition]
            )
        }

        fun bind(country: String) {
            countryName.text = country
        }
    }
}
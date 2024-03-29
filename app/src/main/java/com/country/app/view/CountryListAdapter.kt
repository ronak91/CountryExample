package com.country.app.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.country.app.R
import com.country.app.Utils.getProgressDrawable
import com.country.app.Utils.loadImage
import com.country.app.model.Countries
import kotlinx.android.synthetic.main.item_country.view.*

class CountryListAdapter(var country: ArrayList<Countries>): RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    fun updateCountries(newCountries: ArrayList<Countries>) {
            country.clear()
            country.addAll(newCountries)
            notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CountryViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.item_country,parent,false)
    )

    override fun getItemCount() = country.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(country[position])
    }

    class CountryViewHolder(view:View): RecyclerView.ViewHolder(view){

         private val countryFlag = view.imageFlag
         private val countryName = view.name
         private val countryCapital = view.capital
         private val progressDrawable = getProgressDrawable(view.context)

         fun bind(countries:Countries){
                countryName.text = countries.countryName
                countryCapital.text = countries.capital
                countryFlag.loadImage(countries.flag, progressDrawable)
          }
    }
}
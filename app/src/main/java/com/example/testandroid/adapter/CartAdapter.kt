package com.example.testandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testandroid.R
import com.example.testandroid.model.MealDB
import kotlinx.android.synthetic.main.item_recyclerview_cart.view.*

class CartAdapter(private val meals: ArrayList<MealDB>) : RecyclerView.Adapter<CartAdapter.DataViewHolder>() {

    lateinit var adapterItemClickListener: OnAdapterDeleteClickListener

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(mealDB: MealDB) {
            Glide.with(itemView.context)
                .load(mealDB.image)
                .into(itemView.image_cart)


            itemView.tv_name_cart.text = mealDB.name

            itemView.btn_plus_count.setOnClickListener {
                var currentCount = Integer.parseInt(itemView.tv_count_cart.text.toString())
                currentCount++

                itemView.tv_count_cart.text = "$currentCount"
            }

            itemView.btn_min_count.setOnClickListener {
                var currentCount = Integer.parseInt(itemView.tv_count_cart.text.toString())

                if(currentCount > 1){
                    currentCount--

                    itemView.tv_count_cart.text = "$currentCount"
                }
            }

            itemView.button_remove_cart.setOnClickListener {
                adapterItemClickListener.onAdapterItemClickListener(meals[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recyclerview_cart, parent, false)
        )

    override fun getItemCount(): Int = meals.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(meals[position])

    fun addMeals(meals: List<MealDB>){
        if(this.meals.size > 0) this.meals.clear()
        this.meals.addAll(meals)
    }


    interface OnAdapterDeleteClickListener {
        fun onAdapterItemClickListener(meal: MealDB)
    }


}
package com.example.testandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testandroid.R
import com.example.testandroid.model.Meal
import kotlinx.android.synthetic.main.item_recyclerview.view.*


class MainAdapter(private val meals: ArrayList<Meal>, private val idSaved: ArrayList<String>?) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    lateinit var adapterItemClickListener: OnAdapterItemClickListener


     inner class DataViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(meal: Meal){

            Glide.with(itemView.context)
                .load(meal.image)
                .into(itemView.imageview_ava)
            itemView.tv_name.text = meal.name


            if(idSaved != null && idSaved.size > 0){
                if(idSaved.contains(meal.id)){
                    itemView.button_add.visibility = View.INVISIBLE
                }
            }

            itemView.button_add.setOnClickListener {
                adapterItemClickListener.onAdapterItemClickListener(meal)
                it.visibility = View.INVISIBLE
            }
        }
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder = DataViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview, parent, false)
    )

    override fun getItemCount(): Int = meals.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) = holder.bind(meals[position])

    fun addMeals(meals: List<Meal>){
        if(this.meals.size > 0) this.meals.clear()
        this.meals.addAll(meals)
    }

    interface OnAdapterItemClickListener {
        fun onAdapterItemClickListener(meal: Meal)
    }
}
package com.example.testandroid.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testandroid.R
import com.example.testandroid.adapter.CartAdapter
import com.example.testandroid.model.MealDB
import com.example.testandroid.view_model.CartViewModel
import kotlinx.android.synthetic.main.activity_cart.*
import org.koin.android.viewmodel.ext.android.viewModel

class CartActivity : AppCompatActivity(), CartAdapter.OnAdapterDeleteClickListener {

    private val cartViewModel : CartViewModel by viewModel()
    private var cartAdapter = CartAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        cartAdapter.adapterItemClickListener = this@CartActivity
        rv_cart.layoutManager = LinearLayoutManager(this)
        rv_cart.adapter = cartAdapter

        cartViewModel.mealsFromDb.observe(this, Observer {
            if(it.isNotEmpty()){
                empty_ilustration.visibility = View.GONE
                cartAdapter.addMeals(it)
                cartAdapter.notifyDataSetChanged()
            }else{
                rv_cart.visibility = View.INVISIBLE
                empty_ilustration.visibility = View.VISIBLE
            }
        })
    }

    override fun onAdapterItemClickListener(meal: MealDB) {
        cartViewModel.deleteMealDb(meal)
        Toast.makeText(this, "${meal.name} deleted from cart", Toast.LENGTH_SHORT).show()
    }
}
package com.example.testandroid.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testandroid.R
import com.example.testandroid.adapter.MainAdapter
import com.example.testandroid.model.Meal
import com.example.testandroid.model.MealDB
import com.example.testandroid.utils.SpacesItemDecoration
import com.example.testandroid.utils.Status
import com.example.testandroid.view_model.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(), MainAdapter.OnAdapterItemClickListener {

    private lateinit var mainAdapter: MainAdapter
    private val mainViewModel: MainViewModel by viewModel()
    private val listIdSaved: ArrayList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        icon_cart.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

        setupUI()
        setupObserver()
        updateCart()
    }

    private fun updateCart() {
        mainViewModel.mealsDB.observe(this, Observer { meals ->
            if (meals.isNotEmpty()) {
                layout_cart.visibility = View.VISIBLE
                count_cart.text = "${meals.size}"

                meals.forEach { meal ->
                    listIdSaved.add(meal!!.id)
                }
            } else {
                layout_cart.visibility = View.INVISIBLE
            }
        })
    }

    private fun setupObserver() {
        mainViewModel.meals.observe(this, Observer { res ->
            when (res.status) {
                Status.LOADING -> {
                    loading.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    loading.visibility = View.INVISIBLE
                    res.data?.let { data ->
                        mainAdapter.addMeals(data.meals)
                        mainAdapter.notifyDataSetChanged()
                    }
                    mainAdapter.notifyDataSetChanged()
                }
                Status.ERROR -> {
                    loading.visibility = View.INVISIBLE
                    Toast.makeText(this, res.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    private fun setupUI() {
        val spanCount = 2 // 3 columns
        val spacing = 50 // 50px
        val includeEdge = false
        recyclerview.layoutManager = GridLayoutManager(this, 2)
        recyclerview.addItemDecoration(
            SpacesItemDecoration(
                spanCount,
                spacing,
                includeEdge
            )
        )

        mainAdapter = MainAdapter(arrayListOf(), listIdSaved)
        mainAdapter.adapterItemClickListener = this@MainActivity
        recyclerview.adapter = mainAdapter
    }

    override fun onAdapterItemClickListener(meal: Meal) {
        mainViewModel.insertMealDb(MealDB(id = meal.id, name = meal.name, image = meal.image))
    }


}
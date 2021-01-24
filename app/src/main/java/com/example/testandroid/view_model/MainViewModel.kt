package com.example.testandroid.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testandroid.model.MealDB
import com.example.testandroid.utils.MyResponse
import com.example.testandroid.model.Meals
import com.example.testandroid.repository.MainRepository
import kotlinx.coroutines.launch


class  MainViewModel(
    private val mainRepository: MainRepository
): ViewModel() {

    private val _meals = MutableLiveData<MyResponse<Meals>>()
    val meals: LiveData<MyResponse<Meals>> = _meals
    val mealsDB: LiveData<List<MealDB>> = mainRepository.getMealsDb()

    init {
        fetchMeals()
    }

    private fun fetchMeals() {
        viewModelScope.launch {
            _meals.postValue(MyResponse.loading(null))

            mainRepository.getMeals().let {res ->
                if(res.isSuccessful){
                    _meals.postValue(MyResponse.success(res.body()))
                }else{
                    _meals.postValue(MyResponse.error(res.errorBody().toString(), null))
                }
            }
        }
    }

    fun insertMealDb(mealDB: MealDB) = viewModelScope.launch {
        mainRepository.insertMeal(mealDB)
    }

}
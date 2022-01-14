package com.rodrigobn.frameworkdigitale_commerce.data.preferences

import android.R.attr.data
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.rodrigobn.frameworkdigitale_commerce.data.models.Product

class Prefs(context: Context) {

    private val APP_PREF = "app_pref"
    private val APP_PREF_NAME_PROFILE = "app_pref_name_profile"
    private val APP_PREF_CART = "app_pref_name_profile"

    private val preferences: SharedPreferences = context.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE)

    var nameProfile: String?
        get() = preferences.getString(APP_PREF_NAME_PROFILE, "")
        set(value) = preferences.edit().putString(APP_PREF_NAME_PROFILE, value).apply()

    fun getCartList(): Array<Product>? {
        val gson = Gson()
        val jsonProductsCart: String? = preferences.getString(APP_PREF_CART, null)
        return gson.fromJson(jsonProductsCart, Array<Product>::class.java)
    }

    fun saveListProductCart(products: Array<Product>){
        val gson = Gson()
        val jsonProductsCart = gson.toJson(products)
        preferences.edit().putString(APP_PREF_CART, jsonProductsCart).apply()
    }

    fun addProductCartList(newProduct: Product){
        val gson = Gson()
        val oldListProductsCart = getCartList()
        val newListProductsCart = arrayListOf<Product>()
        if (oldListProductsCart != null) {
            for (i in oldListProductsCart.indices){
                if (oldListProductsCart[i].name == newProduct.name){
                    oldListProductsCart[i].quantity += 1
                    saveListProductCart(oldListProductsCart)
                    return
                }
            }
            for (i in oldListProductsCart.indices){
                newListProductsCart.add(oldListProductsCart[i])
            }
            newListProductsCart.add(newProduct)
        }
        val jsonProductsCart = gson.toJson(newListProductsCart)
        preferences.edit().putString(APP_PREF_CART, jsonProductsCart).apply()
    }
}
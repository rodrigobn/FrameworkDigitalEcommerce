package com.rodrigobn.frameworkdigitale_commerce.view.main

import android.content.Intent
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rodrigobn.frameworkdigitale_commerce.R
import com.rodrigobn.frameworkdigitale_commerce.data.models.Product
import com.rodrigobn.frameworkdigitale_commerce.prefs
import com.rodrigobn.frameworkdigitale_commerce.view.adapters.DialogProductQuantity
import com.rodrigobn.frameworkdigitale_commerce.view.adapters.ShopFruitsAdapter
import com.rodrigobn.frameworkdigitale_commerce.view.auth.LoginActivity
import com.rodrigobn.frameworkdigitale_commerce.view.base.BaseActivity
import com.rodrigobn.frameworkdigitale_commerce.view.shopCart.CartActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class MainActivity : BaseActivity(), ShopFruitsAdapter.ButtonAddProductClickListener, DialogProductQuantity.CallbackDialog {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var fruitsRecyclerview: RecyclerView
    private lateinit var tempListFruits : ArrayList<Product>

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun init() {
        initView()
        initOnclick()
        prepareLists()
    }

    private fun initView() {
        hello.text = getString(R.string.hello, prefs.nameProfile)
    }

    private fun initOnclick() {
        fab.setOnClickListener {
            goToShoppingCart()
        }
    }

    private fun prepareLists() {
        viewModel.deleteAll()
        tempListFruits = arrayListOf()
        tempListFruits.addAll(viewModel.productListMock)
        fruitsRecyclerview = recyclerViewListFruits
        fruitsRecyclerview.layoutManager = GridLayoutManager(this@MainActivity, 2)
        fruitsRecyclerview.setHasFixedSize(true)
        fruitsRecyclerview.adapter = ShopFruitsAdapter(tempListFruits, this@MainActivity)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        val itemSearch = menu?.findItem(R.id.action_search)
        val itemLogout = menu?.findItem(R.id.action_logout)
        val searchView = itemSearch?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(text: String?): Boolean {
                //TODO("Not yet implemented")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                tempListFruits.clear()
                val searchText = newText!!.lowercase(Locale.getDefault())
                if (searchText.isNotEmpty()){
                    viewModel.productListMock.forEach { product ->
                        if (product.name.lowercase(Locale.getDefault()).contains(searchText)){
                            tempListFruits.add(product)
                        }
                    }
                    fruitsRecyclerview.adapter!!.notifyDataSetChanged()
                } else {
                    tempListFruits.clear()
                    tempListFruits.addAll(viewModel.productListMock)
                    fruitsRecyclerview.adapter!!.notifyDataSetChanged()
                }
                return false
            }
        })

        itemLogout?.setOnMenuItemClickListener {
            logout()
        }
        return super.onCreateOptionsMenu(menu)
    }

    private fun logout(): Boolean {
        viewModel.logout()
        goToLogin()
        return false
    }

    private fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun goToShoppingCart() {
        val intent = Intent(this, CartActivity::class.java)
        startActivity(intent)
    }

    override fun onClickDialogConfirm(product: Product) {
        viewModel.save(product)
        Log.d("xxx", "init: $product")
    }

    override fun onButtonAddProductClickListener(product: Product) {
        openDialog(product)
    }

    private fun openDialog(product: Product) {
        val dialogQuantity = DialogProductQuantity(product, this, this)
        dialogQuantity.show()
    }
}
package com.rodrigobn.frameworkdigitale_commerce.view.main

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rodrigobn.frameworkdigitale_commerce.R
import com.rodrigobn.frameworkdigitale_commerce.data.models.Product
import com.rodrigobn.frameworkdigitale_commerce.prefs
import com.rodrigobn.frameworkdigitale_commerce.view.adapters.DialogProductQuantity
import com.rodrigobn.frameworkdigitale_commerce.view.adapters.FruitsAdapter
import com.rodrigobn.frameworkdigitale_commerce.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(), FruitsAdapter.ButtonAddProductClickListener {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var fruitsRecyclerview: RecyclerView


    override fun init() {
        initView()
        prepareLists()
        initObserver()
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    private fun initView() {
        hello.text = getString(R.string.hello, prefs.nameProfile)
    }

    private fun prepareLists() {
        fruitsRecyclerview = listFruits
        fruitsRecyclerview.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
        fruitsRecyclerview.setHasFixedSize(true)
        fruitsRecyclerview.adapter = FruitsAdapter(viewModel.productListMock, this@MainActivity)
    }

    private fun initObserver(){
        viewModel.productList.observe(this, Observer {
            Log.d("xxx", "init: ${it.size}")
        })
    }

    override fun onButtonClickListener(product: Product) {
        openDialog(product)
        Toast.makeText(this, product.toString(), Toast.LENGTH_LONG).show()
        viewModel.save(product)

    }

    private fun openDialog(product: Product) {
        val dialogQuantity = DialogProductQuantity(product, this)
        dialogQuantity.show()
    }
}
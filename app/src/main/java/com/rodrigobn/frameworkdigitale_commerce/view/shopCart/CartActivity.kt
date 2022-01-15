package com.rodrigobn.frameworkdigitale_commerce.view.shopCart

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rodrigobn.frameworkdigitale_commerce.R
import com.rodrigobn.frameworkdigitale_commerce.data.models.Product
import com.rodrigobn.frameworkdigitale_commerce.view.adapters.CartFruitsAdapter
import com.rodrigobn.frameworkdigitale_commerce.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartActivity : BaseActivity(), CartFruitsAdapter.ButtonRemoveProductClickListener {

    private val viewModel: CartViewModel by viewModel()
    private lateinit var fruitsRecyclerview: RecyclerView

    override fun getLayout(): Int {
        return R.layout.activity_cart
    }

    override fun init() {
        initViews()
        prepareLists()
        initObserver()
    }

    private fun initViews() {
        supportActionBar!!.setTitle("Carrinho")
    }

    private fun prepareLists() {
        fruitsRecyclerview = recyclerViewCart
        fruitsRecyclerview.layoutManager = LinearLayoutManager(this@CartActivity, RecyclerView.VERTICAL, false)
        fruitsRecyclerview.setHasFixedSize(true)
    }

    private fun initObserver(){
        viewModel.productList.observe(this, Observer {
            fruitsRecyclerview.adapter = CartFruitsAdapter(it as MutableList<Product>, this@CartActivity)
            fruitsRecyclerview.adapter!!.notifyDataSetChanged()

            var total = 0f
            viewModel.productList.value?.forEach {
                total += it.price
            }
            buttonFinish.text = "Total R$ %.2f".format(total)
        })
    }

    override fun onButtonRemoveProductClickListener(product: Product) {

    }

}
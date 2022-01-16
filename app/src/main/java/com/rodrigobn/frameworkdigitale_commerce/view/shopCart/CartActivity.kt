package com.rodrigobn.frameworkdigitale_commerce.view.shopCart

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.ActivityNotFoundException
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.util.Log.d
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itextpdf.text.Document
import com.itextpdf.text.DocumentException
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfWriter
import com.rodrigobn.frameworkdigitale_commerce.R
import com.rodrigobn.frameworkdigitale_commerce.data.models.Product
import com.rodrigobn.frameworkdigitale_commerce.view.adapters.CartFruitsAdapter
import com.rodrigobn.frameworkdigitale_commerce.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class CartActivity : BaseActivity(), CartFruitsAdapter.ButtonRemoveProductClickListener {

    private val viewModel: CartViewModel by viewModel()
    private lateinit var fruitsRecyclerview: RecyclerView
    private lateinit var listForReceipt: List<Product>
    private lateinit var cw: ContextWrapper
    private lateinit var fileDirectory: File
    private lateinit var fileName: String
    private var receiptTotal: String = ""

    override fun getLayout(): Int {
        return R.layout.activity_cart
    }

    override fun init() {
        requestPermissions()
        initProperties()
        initViews()
        prepareLists()
        initObserver()
        initOnclick()

    }

    private fun initProperties() {
        cw = ContextWrapper(applicationContext)
        fileDirectory = cw.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)!!
        fileName = "recibo_" + SimpleDateFormat("ddMMyyyy_HHmmss", Locale.getDefault())
            .format(System.currentTimeMillis())
    }

    private fun initViews() {
        supportActionBar!!.title = getString(R.string.cart)
    }


    private fun prepareLists() {
        fruitsRecyclerview = recyclerViewCart
        fruitsRecyclerview.layoutManager =
            LinearLayoutManager(this@CartActivity, RecyclerView.VERTICAL, false)
        fruitsRecyclerview.setHasFixedSize(true)
    }

    private fun initObserver() {
        viewModel.productList.observe(this, Observer {
            fruitsRecyclerview.adapter =
                CartFruitsAdapter(it as MutableList<Product>, this@CartActivity)
            fruitsRecyclerview.adapter!!.notifyDataSetChanged()

            var total = 0f
            viewModel.productList.value?.forEach {product ->
                total += product.price * product.quantity
            }
            buttonFinish.text = "Total R$ %.2f".format(total)
            receiptTotal += "Total R$ %.2f".format(total)

            listForReceipt = viewModel.productList.value!!
        })
    }

    private fun initOnclick() {
        buttonFinish.setOnClickListener {
            generatePDF()
        }
    }

    private fun generatePDF() {
        val document = Document()
        val path = File(fileDirectory, "$fileName.pdf")

        try {
            PdfWriter.getInstance(document, FileOutputStream(path))
            document.open()
            document.add(Paragraph("Framework Digital E-commerce"))
            document.add(Paragraph("Comprovante de compra"))
            document.add(Paragraph(" "))
            listForReceipt.forEach { product ->
                document.add(Paragraph(product.name + "_________________" + (product.price * product.price)))
            }
            document.add(Paragraph(" "))
            document.add(Paragraph(receiptTotal))
            document.close()
            viewPdf("$fileName.pdf")

        } catch (de: DocumentException) {
            Log.e("PDFCreator", "DocumentException:$de")
        } catch (e: IOException) {
            Log.e("PDFCreator", "ioException:$e")
        }
    }

    private fun viewPdf(file: String) {
        val pdfFile = File("$fileDirectory/$file")
        val path: Uri = FileProvider.getUriForFile(
            this,
            this.applicationContext.packageName + ".provider",
            pdfFile
        )

        val pdfIntent = Intent(Intent.ACTION_VIEW)
        pdfIntent.setDataAndType(path, "application/pdf")
        pdfIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        try {
            startActivity(pdfIntent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                this@CartActivity,
                "Houve uma falha na geração do recibo",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onButtonRemoveProductClickListener(product: Product) {
        viewModel.delete(product)
    }

    private fun hasWriteExternalStoragePermission() =
        ActivityCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) == PERMISSION_GRANTED

    private fun hasReadExternalStoragePermission() =
        ActivityCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) == PERMISSION_GRANTED

    private fun requestPermissions() {
        val permissionsToRequest = mutableListOf<String>()
        if (!hasWriteExternalStoragePermission()) {
            permissionsToRequest.add(WRITE_EXTERNAL_STORAGE)
        }
        if (!hasReadExternalStoragePermission()) {
            permissionsToRequest.add(READ_EXTERNAL_STORAGE)
        }

        if (permissionsToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissionsToRequest.toTypedArray(), 0)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0 && grantResults.isNotEmpty()) {
            for (i in grantResults.indices) {
                if (grantResults[i] == PERMISSION_GRANTED) {
                    d("PermissionRequest", "${permissions[i]} granted.")
                }
            }
        }
    }
}
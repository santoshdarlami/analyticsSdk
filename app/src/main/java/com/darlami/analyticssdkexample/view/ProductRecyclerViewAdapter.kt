package com.darlami.analyticssdkexample.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.darlami.analyticssdkexample.R
import com.darlami.analyticssdkexample.model.Product
import com.darlami.analyticssdkexample.view.ProductRecyclerViewAdapter.ViewHolder

class ProductRecyclerViewAdapter(
    private val productList: List<Product>,
) : RecyclerView.Adapter<ViewHolder>() {
    private val selectedItems: MutableList<Product> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    fun getSelectedItems(): List<Product> {
        return selectedItems
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imageView: ImageView = itemView.findViewById(R.id.productIv)
        private val titleTv: TextView = itemView.findViewById(R.id.titleTv)
        private val descriptionTv: TextView = itemView.findViewById(R.id.descriptionTv)
        private val priceTv: TextView = itemView.findViewById(R.id.priceTv)
        private val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)

        fun bind(product: Product) {
            Glide.with(itemView.context).load(product.image).into(imageView)
            titleTv.text = product.name
            descriptionTv.text = product.description
            priceTv.text = product.price.toString()

            checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) selectedItems.add(product) else selectedItems.remove(
                    product
                )
            }
        }

    }
}
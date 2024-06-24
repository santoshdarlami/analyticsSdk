package com.darlami.analyticssdkexample.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.darlami.analyticsdk.AndroidAnalyticsSDK
import com.darlami.analyticsdk.model.EventType
import com.darlami.analyticssdkexample.databinding.FragmentShopBinding
import com.darlami.analyticssdkexample.view_model.ProductsViewModel

class ShopFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentShopBinding
    private val viewModel: ProductsViewModel by activityViewModels()
    private lateinit var analyticsSDK: AndroidAnalyticsSDK
    private lateinit var adapter: ProductRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentShopBinding.inflate(inflater, container, false)
        analyticsSDK = AndroidAnalyticsSDK.getInstance(requireActivity().application)
        adapter = ProductRecyclerViewAdapter(viewModel.productList)
        binding.productsRv.adapter = adapter
        binding.productsRv.layoutManager = LinearLayoutManager(requireContext())
        binding.buyNowFab.setOnClickListener(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analyticsSDK.startSession()
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.buyNowFab -> {
                val selectedItems = adapter.getSelectedItems()
                if (selectedItems.isEmpty()) {
                    Toast.makeText(requireContext(), "Select at least 1 product", Toast.LENGTH_SHORT).show()
                    return
                }
                selectedItems.forEach {
                    analyticsSDK.addEvent(
                        EventType.PURCHASE, "product_id" to it.id.toString(), "product_name" to it.name, "price" to it.price.toString()
                    )
                }
                Toast.makeText(requireContext(), "Bought", Toast.LENGTH_SHORT).show()
                analyticsSDK.stopSession()
            }
        }
    }
}
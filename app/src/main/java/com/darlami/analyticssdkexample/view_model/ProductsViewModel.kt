package com.darlami.analyticssdkexample.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.darlami.analyticsdk.AndroidAnalyticsSDK
import com.darlami.analyticsdk.model.EventType
import com.darlami.analyticssdkexample.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProductsViewModel(application: Application) : AndroidViewModel(application) {


    private val product1 = Product(
        1,
        "Colgate",
        "Colgate Total Teeth Whitening Toothpaste, 10 Benefits Including Sensitivity Relief ,Whitening Mint",
        10.0f,
        "https://images.unsplash.com/photo-1610216690558-4aee861f4ab3?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8dG9vdGhwYXN0ZXxlbnwwfHwwfHx8MA%3D%3D"
    )
    private val product2 = Product(
        2,
        "Sensodyne",
        "Sensodyne Repair and Protect Whitening Toothpaste, Toothpaste for Sensitive Teeth and Cavity Prevention",
        10.0f,
        "https://images.unsplash.com/photo-1612705166160-97d3b2e8e212?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fHRvb3RocGFzdGV8ZW58MHx8MHx8fDA%3D"
    )
    private val product3 = Product(
        3,
        "Crest Pro",
        "Crest Pro-Health Clean Mint Toothpaste (4.3oz) Triple Pack",
        10.0f,
        "https://images.unsplash.com/photo-1602193815349-525071f27564?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTZ8fHRvb3RocGFzdGV8ZW58MHx8MHx8fDA%3D"
    )
    private val product4 = Product(
        4,
        "Sensodyne Pronamel",
        "Sensodyne Pronamel Intensive Enamel Repair Toothpaste for Sensitive Teeth, to Reharden and Strengthen Enamel",
        10.0f,
        "https://images.unsplash.com/photo-1612705166160-97d3b2e8e212?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTB8fHRvb3RocGFzdGV8ZW58MHx8MHx8fDA%3D"
    )
    private val product5 = Product(
        5,
        "Colgate 2 in 1",
        "Colgate 2 in 1 Toothpaste Mouthwash Whitening 4.6 Tubes, Icy Blast, 27.6 Oz, Pack of 6",
        10.0f,
        "https://images.unsplash.com/photo-1610216690558-4aee861f4ab3?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Nnx8dG9vdGhwYXN0ZXxlbnwwfHwwfHx8MA%3D%3D"
    )
    private val product6 = Product(
        6,
        "Crest 3D White",
        "Crest 3D White Stain Eraser Teeth Whitening Toothpaste, Polishing Mint, 3.1 oz (Pack of 4)",
        10.0f,
        "https://images.unsplash.com/photo-1608248597279-f99d160bfcbc?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTh8fHRvb3RocGFzdGV8ZW58MHx8MHx8fDA%3D"
    )
    private val product7 = Product(
        7,
        "Opalescence",
        "Opalescence Whitening Toothpaste Original Formula (Pack of 3) - Oral Care, Mint Flavor, Gluten Free - 4.7 Ounce - TP-5166-3",
        10.0f,
        "https://images.unsplash.com/photo-1637872497552-b1494849ca49?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTV8fHRvb3RocGFzdGV8ZW58MHx8MHx8fDA%3D"
    )
    val productList: List<Product> = listOf(product1, product2, product3, product4, product5, product6, product7)


}
package com.devstone.punchesmanager.data.repository

import com.devstone.punchesmanager.data.entities.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getAllProducts(): Flow<List<Product>>

    suspend fun getProductById(id: String): Product?

    suspend fun insertProduct(product: Product)

    suspend fun updateProduct(product: Product)

    suspend fun deleteProduct(product: Product)
}
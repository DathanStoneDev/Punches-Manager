package com.devstone.punchesmanager.data.repository

import com.devstone.punchesmanager.data.dao.ProductDao
import com.devstone.punchesmanager.data.entities.product.Product
import kotlinx.coroutines.flow.Flow

class ProductRepositoryImpl(
    private val dao: ProductDao
): ProductRepository {
    override fun getAllProducts(): Flow<List<Product>> {
        return dao.getAllProducts()
    }

    override suspend fun getProductById(id: String): Product? {
        return dao.getProductById(id)
    }

    override suspend fun insertProduct(product: Product) {
        return dao.insertProduct(product)
    }

    override suspend fun updateProduct(product: Product) {
        return dao.updateProduct(product)
    }

    override suspend fun deleteProduct(product: Product) {
        return dao.deleteProduct(product)
    }
}
package com.saintroche.products.service;

import com.saintroche.products.client.CategoryClient;
import com.saintroche.products.client.CategoryDTO;
import com.saintroche.products.models.entities.Product;
import com.saintroche.products.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryClient categoryClient;

    public ProductServiceImp(ProductRepository productRepository, CategoryClient categoryClient) {
        this.productRepository = productRepository;
        this.categoryClient = categoryClient;
    }

    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product createProduct(Product product) {
        if(product.getCategoryId() != null) {
            CategoryDTO category = categoryClient.getCategory(product.getCategoryId());
            if(category != null) {
                product.setCategoryId(category.getId());
            } else {
                throw new IllegalArgumentException("Category with id " + product.getCategoryId() + " does not exist.");
            }
        }

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        if(!productRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("Product with id " + id + " does not exist");
        }

        product.setId(id);
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        if(!productRepository.findById(id).isPresent()) {
            throw new IllegalArgumentException("Product with id " + id + " does not exist");
        }

        productRepository.deleteById(id);
    }
}

package com.example.demo.service;

import com.example.demo.repository.ProductRepository;
import com.example.demo.form.ProductUpdateForm;
import com.example.demo.model.Product;
import com.example.demo.form.ProductForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    public Product findProductById(Integer productId) {
        return productRepository.findProductById(productId);
    }

    public Product createProduct(ProductForm productForm) {
        Product product = new Product();
        product.setName(productForm.getName());
        product.setPrice(productForm.getPrice());
        return productRepository.save(product);
    }

    public Product updateProduct(Integer productId, ProductUpdateForm productUpdateForm) {
        Product productFound = this.findProductById(productId);

        if (!productUpdateForm.getName().isEmpty()) {
            productFound.setName(productUpdateForm.getName());
        }
        if (productUpdateForm.getPrice() != null) {
            productFound.setPrice(productUpdateForm.getPrice());
        }
        return productRepository.save(productFound);
    }

    public void deleteProductById(Integer productId) {
        productRepository.delete(this.findProductById(productId));
    }
}

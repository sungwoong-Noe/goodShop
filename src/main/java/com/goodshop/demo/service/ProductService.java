package com.goodshop.demo.service;

import com.goodshop.demo.domain.product.Product;
import com.goodshop.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    //저장
    @Transactional
    public void saveProduct(Product pdct){
        productRepository.save(pdct);
    }

    //조회
    public List<Product> findProducts(){
        return productRepository.findAll();
    }

    public Product findOne(Long code){
        return productRepository.findOne(code);
    }
}

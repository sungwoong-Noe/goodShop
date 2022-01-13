package com.goodshop.demo.service;

import com.goodshop.demo.domain.product.Product;
import com.goodshop.demo.repository.ProdictRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProdictRepository prodictRepository;

    //저장
    @Transactional
    public void saveProduct(Product pdct){
        prodictRepository.save(pdct);
    }

    //조회
    public List<Product> findProducts(){
        return prodictRepository.findAll();
    }

    public Product findOne(Long code){
        return prodictRepository.findOne(code);
    }
}

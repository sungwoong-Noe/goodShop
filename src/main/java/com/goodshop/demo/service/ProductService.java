package com.goodshop.demo.service;

import com.goodshop.demo.domain.order.OrderItem;
import com.goodshop.demo.domain.product.Product;
import com.goodshop.demo.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    public Product findOne(Long pdct_code){
        return productRepository.findOne(pdct_code);
    }

    @Transactional
    public List<Product> orderProduct(List<OrderItem> orderItems){

        List<Product> orderProducts = new ArrayList<>();
        for (OrderItem orderProduct : orderItems){
            orderProducts.add(productRepository.findOne(orderProduct.getProduct().getPdct_code()));
        }
        return orderProducts;
    }

    //
}

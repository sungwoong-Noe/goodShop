package com.goodshop.demo.repository;

import com.goodshop.demo.domain.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    @PersistenceContext
    private final EntityManager em;

    //준영속 Entity 영속으로 변경 -> merge 방식
    public void save(Product pdct){
        if(pdct.getPdct_code() == null){
            pdct.setPdct_date(LocalDateTime.now());
            em.persist(pdct);
        }else{
            em.merge(pdct);
        }
    }

    //하나 조회
    public Product findOne(Long pdct_code){
        return em.find(Product.class, pdct_code);
    }

    //전체조회
    public List<Product> findAll(){
        return em.createQuery("select p from Product p", Product.class)
                .getResultList();
    }


}

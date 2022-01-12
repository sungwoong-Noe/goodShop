package com.goodshop.demo.domain;

import com.goodshop.demo.domain.product.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {
    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String category_name;

    @ManyToMany
    @JoinTable(name="category_item",
        joinColumns = @JoinColumn(name="category_id"),
        inverseJoinColumns = @JoinColumn(name="pdct_code"))
    private List<Product> products = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();


}

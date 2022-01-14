package com.goodshop.demo.domain.product.childEntity;

import com.goodshop.demo.domain.product.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("T")
@Getter
@Setter
public class TechEntity extends Product {
}

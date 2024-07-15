package com.demo.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.ProductItem;

@Repository
public interface ProductItemRepository extends R2dbcRepository<ProductItem, Integer>{

}

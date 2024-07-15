package com.demo.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.BoughtItem;


@Repository
public interface BoughtItemRepository extends R2dbcRepository<BoughtItem, Integer>{

	
}

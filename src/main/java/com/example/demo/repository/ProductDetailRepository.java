package com.example.demo.repository;

import com.example.demo.entity.ProductDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductDetailRepository extends PagingAndSortingRepository<ProductDetailEntity, Integer> {

}

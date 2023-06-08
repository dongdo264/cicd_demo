package com.example.demo.repository;

import com.example.demo.entity.ProductDetailEntity;
import com.example.demo.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
//    @Query(value = "SELECT productCode, productName, productLine, warrantyPeriod, buyPrice, status, createAt FROM products", nativeQuery = true)
//    List<Object> findAllProducts();
    public void deleteById(int id);

}

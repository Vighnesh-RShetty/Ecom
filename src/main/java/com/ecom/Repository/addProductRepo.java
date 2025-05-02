package com.ecom.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.model.addProducts;

@Repository
public interface addProductRepo extends JpaRepository<addProducts, Integer> {

}

package com.ecom.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.model.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer> {

	public Boolean existsByName(String name);
}

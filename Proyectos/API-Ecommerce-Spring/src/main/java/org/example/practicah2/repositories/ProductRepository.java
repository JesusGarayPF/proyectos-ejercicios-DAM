package org.example.practicah2.repositories;

import org.example.practicah2.dto.ProductDto;
import org.example.practicah2.entities.Category;
import org.example.practicah2.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface  /* */ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT new org.example.practicah2.dto.ProductDto(p.product_id, p.price, p.stock, p.sku, p.name, p.description) " +
            "FROM Product p " +
            "WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "OR LOWER(p.description) LIKE LOWER(CONCAT('%', :description, '%')) " +
            "ORDER BY p.name ASC")
    List<ProductDto> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrderByNameAsc(
            @Param("name") String name,
            @Param("description") String description,
            Pageable pager);
}

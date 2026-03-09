package org.example.practicah2.services;

import org.example.practicah2.dto.ProductDto;
import org.example.practicah2.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    List<Product> findAllPaged(Integer page, Integer pageSize);
    List<ProductDto> findByNameOrDescriptionPagedOrdAsc(Integer page, Integer pageSize, String busqueda);
}

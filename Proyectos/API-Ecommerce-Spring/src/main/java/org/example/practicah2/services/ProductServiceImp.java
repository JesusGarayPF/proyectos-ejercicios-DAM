package org.example.practicah2.services;

import org.example.practicah2.dto.ProductDto;
import org.example.practicah2.entities.Product;
import org.example.practicah2.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllPaged(Integer page, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by("name"));
        Page<Product> productsPage = productRepository.findAll(pageRequest);
        return productsPage.toList();
    }

    @Override
    public List<ProductDto> findByNameOrDescriptionPagedOrdAsc(Integer page, Integer pageSize, String busqueda) {
        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by("name"));
        return productRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrderByNameAsc(busqueda, busqueda, pageRequest);
    }

}
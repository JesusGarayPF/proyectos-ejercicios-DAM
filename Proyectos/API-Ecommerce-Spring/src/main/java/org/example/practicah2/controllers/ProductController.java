package org.example.practicah2.controllers;

import org.example.practicah2.dto.ProductDto;
import org.example.practicah2.entities.Product;
import org.example.practicah2.repositories.ProductRepository;
import org.example.practicah2.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }
    @GetMapping("/{page}/{pageSize}")
    public ResponseEntity<List<Product>> findAllPaged(@PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize ) {
        return ResponseEntity.ok(productService.findAllPaged(page, pageSize));
    }
    @GetMapping("/search/{query}/{page}/{pageSize}")
    public ResponseEntity<List<ProductDto>> findByNameOrDescriptionOrdAsc(
            @PathVariable("query") String busqueda,
            @PathVariable("page") Integer page,
            @PathVariable("pageSize") Integer pageSize) {
        return ResponseEntity.ok(productService.findByNameOrDescriptionPagedOrdAsc(page, pageSize, busqueda));
    }
}

package org.example.practicah2.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer product_id;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private Integer stock;
    @Column(nullable = false, length = 15)
    private String sku;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(length = 1000)
    private String description;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_categories",
            joinColumns = @JoinColumn(name="product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")

    )
    @JsonManagedReference
    private List<Category> listaCategorias;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "wishlist_products",
            joinColumns = @JoinColumn(name="product_id"),
            inverseJoinColumns = @JoinColumn(name ="wishlist_id")

    )
    @JsonBackReference
    private List<Wishlist> listaWishlist;

    public Integer getProduct_id() {
        return product_id;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Category> getListaCategorias() {
        return listaCategorias;
    }

    public List<Wishlist> getListaWishlist() {
        return listaWishlist;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setListaCategorias(List<Category> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public void setListaWishlist(List<Wishlist> listaWishlist) {
        this.listaWishlist = listaWishlist;
    }
}

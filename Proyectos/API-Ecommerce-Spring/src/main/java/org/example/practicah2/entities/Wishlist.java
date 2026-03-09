package org.example.practicah2.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "wishlist")
@AllArgsConstructor
@NoArgsConstructor
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wishlist_id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    private Customer customer;
    @Column(nullable = false)
    private Boolean shared;
    @Column(length = 100, nullable = false)
    private String name;
    @ManyToMany(mappedBy = "listaWishlist")
    @JsonBackReference
    private List<Product> listaProductos;

    public Boolean getShared() {return shared; }

    public void setShared(Boolean shared) {this.shared = shared; }

    public Integer getWishlist_id() {
        return wishlist_id;
    }

    public void setWishlist_id(Integer wishlist_id) {
        this.wishlist_id = wishlist_id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Product> listaProductos) {
        this.listaProductos = listaProductos;
    }
}

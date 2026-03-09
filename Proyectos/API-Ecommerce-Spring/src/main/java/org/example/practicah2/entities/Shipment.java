package org.example.practicah2.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "shipments")
@NoArgsConstructor
@Setter
@Getter
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shipment_id;
    @OneToOne
    @JoinColumn(name ="order_id", unique = true)
    private Order order;
    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp shipment_date;
    @Column(length = 10, nullable = false)
    private String zip_code;
    @Column(length = 50, nullable = false)
    private String city;
    @Column(length = 50, nullable = false)
    private String country;
    @Column(length = 50, nullable = false)
    private String state;
    @Column(length = 250, nullable = false)
    private String address;

    public Shipment(Integer shipment_id, Order order, String zip_code, String city, String country, String state, String address) {
        this.shipment_id = shipment_id;
        this.order = order;
        this.zip_code = zip_code;
        this.city = city;
        this.country = country;
        this.state = state;
        this.address = address;
    }

    public Integer getShipment_id() {
        return shipment_id;
    }

    public void setShipment_id(Integer shipment_id) {
        this.shipment_id = shipment_id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Timestamp getShipment_date() {
        return shipment_date;
    }

    public void setShipment_date(Timestamp shipment_date) {
        this.shipment_date = shipment_date;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

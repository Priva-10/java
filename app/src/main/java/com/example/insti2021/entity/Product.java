package com.example.insti2021.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class Product implements Serializable {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @PrimaryKey(autoGenerate = true)
    public Integer id;
    @ColumnInfo( name = "name")
    public String name;
    @ColumnInfo( name = "description")
    public String description;
    @ColumnInfo(name="price")
    public float price;
    @ColumnInfo(name="stockQuantity")
    public int stockQuantity;
    @ColumnInfo(name="stockAlert")
    public int stockAlert;


    public Product() {

    }

    public Product(String name, String description, float price, int stockQuantity, int stockAlert) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.stockAlert = stockAlert;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public int getStockAlert() {
        return stockAlert;
    }

    public void setStockAlert(int stockAlert) {
        this.stockAlert = stockAlert;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                ", stockAlert=" + stockAlert +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

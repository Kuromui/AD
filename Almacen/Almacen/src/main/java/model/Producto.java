package model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Producto implements Serializable {

    private String id, title, description;
    private int stock;
    private double price;

    public Producto(String title, String description, int stock, double price) {
        this.title = title;
        this.description = description;
        this.stock = stock;
        this.price = price;
    }
}

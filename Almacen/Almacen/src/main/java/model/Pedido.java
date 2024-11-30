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

public class Pedido implements Serializable {

    private int id;
    private int id_producto;
    private String descripcion;
    private double precio_total;

    public Pedido(String descripcion, double precio_total, int id_producto) {
        this.descripcion = descripcion;
        this.precio_total = precio_total;
        this.id_producto = id_producto;
    }

}

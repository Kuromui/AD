package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "jugadores")

public class Jugador implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nombre;
    @Column
    private String posicion;
    @Column
    private String valor_mercado;
    @Column
    private String goles;
    @Column
    private String nacionalidad;

    public Jugador(String nombre, String posicion, String valor_mercado, String goles, String nacionalidad) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.valor_mercado = valor_mercado;
        this.goles = goles;
        this.nacionalidad = nacionalidad;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "nombre='" + nombre + '\'' +
                ", posicion='" + posicion + '\'' +
                '}'+ '\n';
    }

    //Muchos jugadores pertenecen a un equipo
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_equipo")
    private Equipo equipo;
}

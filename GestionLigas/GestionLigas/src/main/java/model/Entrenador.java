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
@Table (name = "entrenadores")

public class Entrenador implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nombre;
    @Column (name = "calificación")
    private String calificacion;
    @Column
    private String titulos;


    public Entrenador(String nombre, String calificacion, String titulos) {
        this.nombre = nombre;
        this.calificacion = calificacion;
        this.titulos = titulos;
    }

    @Override
    public String toString() {
        return "Entrenador{" +
                "nombre='" + nombre + '\'' +
                ", calificacion='" + calificacion + '\'' +
                ", titulos='" + titulos + '\'' +
                '}'+'\n';
    }
    //Un equipo tiene un entrenador

    @OneToOne
    @JoinColumn(name = "id_equipo")
    private Equipo equipo;
}

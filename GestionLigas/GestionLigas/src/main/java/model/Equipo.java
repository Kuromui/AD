package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name="Equipo.findAll", query ="FROM Equipo")
@Entity
@Table (name = "equipos")

public class Equipo implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nombre_equipo;
    @Column
    private String ciudad;

    public Equipo(String nombre_equipo, String ciudad) {
        this.nombre_equipo = nombre_equipo;
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                 nombre_equipo + '\'' +
                "," + ciudad + '\'' +
                "," + liga +
                '}'+'\n';
    }

    //Cada equipo pertenece a UNA liga
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_liga")
    private Liga liga;

    //Un equipo tiene muchos jugadores
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Jugador> jugadorList;

    //un equipo tiene un entrenador
    @OneToOne(mappedBy = "equipo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Entrenador entrenador;
}

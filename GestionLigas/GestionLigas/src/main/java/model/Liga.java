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
@NamedQuery(name="Liga.findAll", query ="FROM Liga")
@Entity
@Table (name = "ligas")

public class Liga implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nombre_liga;
    @Column
    private String fecha_inicio;
    @Column
    private String fecha_fin;

    public Liga(String nombre_liga, String fecha_inicio, String fecha_fin) {
        this.nombre_liga = nombre_liga;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }

    @Override
    public String toString() {
        return "" +
                "id=" + id +
                ", nombre_liga='" + nombre_liga + '\'' +
                ", fecha_inicio='" + fecha_inicio + '\'' +
                ", fecha_fin='" + fecha_fin + '\'' +
                '}'+ '\n';
    }
    //Una liga contiene varios equipos
    @OneToMany(mappedBy="id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Equipo> equipoList;



}

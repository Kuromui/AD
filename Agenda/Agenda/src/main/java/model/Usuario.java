package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Usuario implements Serializable {
    private int id;
    private String nombre;
    private String correo;
    private String password;
    private int telefono;
    private int idPerfil;

    public Usuario(String nombre, String password, String correo, int telefono, int idPerfil) {
        this.nombre = nombre;
        this.password = password;
        this.correo = correo;
        this.telefono = telefono;
        this.idPerfil = idPerfil;
    }
    public void mostrarDatos(){
        System.out.println("id = " + id);
        System.out.println("nombre = " + nombre);
        System.out.println("password = " + password);
        System.out.println("correo = " + correo);
        System.out.println("telefono = " + telefono);
        System.out.println("idPerfil = " + idPerfil);
    }

}



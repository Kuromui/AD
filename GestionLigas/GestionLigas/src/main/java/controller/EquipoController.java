package controller;

import DAO.EntrenadorDAO;
import DAO.EquipoDAO;
import DAO.JugadorDAO;
import model.Entrenador;
import model.Equipo;
import model.Jugador;

import javax.swing.*;

public class EquipoController {
    private EquipoDAO equipoDAO;
    private JugadorDAO jugadorDAO;
    private EntrenadorDAO entrenadorDAO;

    public EquipoController(){
        equipoDAO = new EquipoDAO();
        jugadorDAO = new JugadorDAO();
        entrenadorDAO = new EntrenadorDAO();
    }
    public void asignarJugadorEquipo(int idJugador, int idEquipo){
        Jugador jugador = jugadorDAO.obtenerJugadorID(idJugador);
        if(jugador != null){
            System.out.println("jugador ok");
            Equipo equipo = equipoDAO.obtenerEquipoID(idEquipo);
            if (equipo != null){
                System.out.println("equipo ok");
                jugador.setEquipo(equipo);
                jugadorDAO.editarJugador(jugador);
            }
            else{
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error. Compruebe que ha introducido bien los identificadores.");
            }
        }
    }
    public void asignarEntrenadorEquipo(int idEntrenador, int idEquipo){
        Entrenador entrenador = entrenadorDAO.obtenerEntrenadorID(idEntrenador);
        if(entrenador != null){
            System.out.println("entrenador ok");
            Equipo equipo = equipoDAO.obtenerEquipoID(idEquipo);
            if (equipo != null){
                System.out.println("equipo ok");
                entrenador.setEquipo(equipo);
                entrenadorDAO.editarEntrenador(entrenador);
            }
            else{
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error. Compruebe que ha introducido bien los identificadores.");
            }
        }
    }
}

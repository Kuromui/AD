package controller;

import DAO.EquipoDAO;
import DAO.LigaDAO;
import model.Equipo;
import model.Liga;

import javax.swing.*;

public class LigaController {

    private LigaDAO ligaDAO;
    private EquipoDAO equipoDAO;

    public LigaController(){
        ligaDAO = new LigaDAO();
        equipoDAO = new EquipoDAO();
    }

    public void asignarEquipoLiga(int idEquipo, int idLiga){
        Equipo equipo = equipoDAO.obtenerEquipoID(idEquipo);
        if(equipo != null){
            System.out.println("equipo ok");
            Liga liga = ligaDAO.obtenerLigaID(idLiga);
            if (liga != null){
                System.out.println("liga ok");
                equipo.setLiga(liga);
                equipoDAO.editarEquipo(equipo);
            }
            else{
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error. Compruebe que ha introducido bien los identificadores.");
            }
        }
    }
    public void eliminarEquipoLiga(int idEquipo, int idLiga){
        Equipo equipo = equipoDAO.obtenerEquipoID(idEquipo);
        if(equipo != null){
            System.out.println("equipo ok");
            Liga liga = ligaDAO.obtenerLigaID(idLiga);
            if (liga != null){
                System.out.println("liga ok");
                equipoDAO.eliminarEquipoLiga(idEquipo, idLiga);
            }
            else{
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error. Compruebe que ha introducido bien los identificadores.");
            }
        }
    }
}

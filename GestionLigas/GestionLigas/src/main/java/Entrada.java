import DAO.EntrenadorDAO;
import DAO.EquipoDAO;
import DAO.JugadorDAO;
import DAO.LigaDAO;
import GUI.interfazInicio;
import controller.EquipoController;
import controller.LigaController;
import model.Equipo;
import model.Jugador;
import model.Liga;

import javax.swing.*;
import java.util.Scanner;

public class Entrada {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        LigaDAO ligaDAO = new LigaDAO();
        EquipoDAO equipoDAO = new EquipoDAO();
        JugadorDAO jugadorDAO = new JugadorDAO();
        EntrenadorDAO entrenadorDAO = new EntrenadorDAO();
        LigaController ligaController = new LigaController();
        EquipoController equipoController = new EquipoController();

        SwingUtilities.invokeLater(() -> {
             interfazInicio interfazInicio = new interfazInicio();
             interfazInicio.setVisible(true);
        });

    }
}

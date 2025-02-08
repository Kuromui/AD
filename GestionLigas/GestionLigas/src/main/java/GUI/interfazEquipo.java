package GUI;

import DAO.EquipoDAO;
import controller.EquipoController;
import controller.LigaController;
import model.Equipo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class interfazEquipo extends JFrame{
    private JPanel GestionEquipo;
    private JButton crearButton;
    private JButton limpiarButton;
    private JTextField tFnombre_equipo;
    private JTextField tFciudad;
    private JTextField tFID;
    private JButton eliminarButton;
    private JButton editarButton;
    private JLabel lblNomEq;
    private JLabel lblCiudad;
    private JLabel lbltitulo2;
    private JLabel lblident;
    private JLabel lbltitulo1;
    private JLabel lblIDLiga;
    private JTextField tFIDLiga;
    private JTabbedPane tabbedPane1;
    private JTextField tFAsociarJU;
    private JTextField tFAsociarEQ;
    private JButton btnAsociar;
    private JPanel AsociarPanel;
    private JLabel lblTit;
    private JLabel lblidju;
    private JLabel lblideq;
    private JTextField tFAsociarEnt;
    private JTextField tFAsociarEqENT;
    private JButton btnAsociarEnt;
    private JPanel AsociarPanel2;
    private JLabel lbltit;
    private JLabel lbljugent;
    private JLabel lblent;
    private JTextArea tAmostrar;
    private JButton mostrarButton;

    public interfazEquipo(){
        setContentPane(GestionEquipo);
        setTitle("Equipo");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        //setSize(700,600);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        //LIMPIAR PANTALLA
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            tFnombre_equipo.setText("");
            tFciudad.setText("");
            tFID.setText("");
            tFIDLiga.setText("");
            }
        });
        //EDITAR
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            EquipoDAO equipoDAO = new EquipoDAO();

            }
        });
        //CREAR EQUIPO
        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre_equipo = tFnombre_equipo.getText();
                String ciudad = tFciudad.getText();

                if(tFciudad.getText().equals("") || tFnombre_equipo.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Faltan datos para crear un equipo.");
                }else{
                    Equipo equipo = new Equipo();
                    equipo.setNombre_equipo(nombre_equipo);
                    equipo.setCiudad(ciudad);
                    EquipoDAO equipoDAO = new EquipoDAO();
                    equipoDAO.crearEquipo(equipo);
                    JOptionPane.showMessageDialog(null,"Equipo creado con éxito.");
                    JOptionPane.showMessageDialog(null,"Se le asignará una liga desde el departamento LIGA.");
                }
            }
        });
        //ELIMINAR EQUIPO
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tFID.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Valor vacío");
                }else {
                    int idE = Integer.parseInt(tFID.getText());
                    int idL = Integer.parseInt(tFIDLiga.getText());
                    LigaController ligaController = new LigaController();
                    ligaController.eliminarEquipoLiga(idE,idL);
                    JOptionPane.showMessageDialog(null,"Operación realizada.");

                }
            }
        });
        //CERRAR VENTANA Y VOLVER A INICIO
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new interfazInicio();
                dispose();
            }
        });

        //ASOCIAR JUGADOR A EQUIPO
        btnAsociar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EquipoController equipoController = new EquipoController();
                int idEquipo = Integer.parseInt(tFAsociarEQ.getText());
                int idJugador = Integer.parseInt(tFAsociarJU.getText());

                if(!tFAsociarJU.getText().equals("") && !tFAsociarEQ.getText().equals("")){
                    equipoController.asignarJugadorEquipo(idJugador,idEquipo);
                }else{
                    JOptionPane.showMessageDialog(null,"Error al introducir datos");
                }

            }
        });

        //ASOCIAR ENTRENADOR A EQUIPO
        btnAsociarEnt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EquipoController equipoController = new EquipoController();
                int idEquipo2 = Integer.parseInt(tFAsociarEqENT.getText());
                int idEntrenador = Integer.parseInt(tFAsociarEnt.getText());

                if(!tFAsociarEnt.getText().equals("") && !tFAsociarEqENT.getText().equals("")){
                    equipoController.asignarEntrenadorEquipo(idEntrenador,idEquipo2);
                }else{
                    JOptionPane.showMessageDialog(null,"Error al introducir datos");
                }
            }
        });

        //MOSTRAR EQUIPOS
        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            EquipoDAO equipoDAO = new EquipoDAO();
            tAmostrar.setText(equipoDAO.equiposDisponibles());
            }
        });
    }
}

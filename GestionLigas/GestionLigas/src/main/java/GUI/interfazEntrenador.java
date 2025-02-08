package GUI;

import DAO.EntrenadorDAO;
import DAO.EquipoDAO;
import model.Entrenador;
import model.Equipo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class interfazEntrenador extends JFrame {

    private JPanel GestionEntrenador;
    private JTabbedPane tPgestor;
    private JButton enviarButton;
    private JButton limpiarButton;
    private JTextField tfnom;
    private JTextField tfcal;
    private JTextField tftit;
    private JTextArea tAdatos;
    private JButton mostrarDatosButton;
    private JLabel lblnom;
    private JLabel lblcal;
    private JLabel lbltil;

    public interfazEntrenador(){
        setContentPane(GestionEntrenador);
        setTitle("Inicio");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);        //Evita que se cierre la APP
        //setSize(700,600);
        pack(); // componentes se acomodan automáticamente
        setLocationRelativeTo(null);
        setVisible(true);


        //CERRAR VENTANA Y VOLVER A INICIO
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new interfazInicio();
                dispose();
            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfnom.setText("");
                tfcal.setText("");
                tftit.setText("");
            }
        });
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = tfnom.getText();
                String cal = tfcal.getText();
                String tit = tftit.getText();

                if(tfnom.getText().equals("") || tftit.getText().equals("")|| tfcal.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Faltan datos para crear un equipo.");
                }else{
                    Entrenador entrenador = new Entrenador();
                    entrenador.setNombre(nom);
                    entrenador.setCalificacion(cal);
                    entrenador.setTitulos(tit);
                    EntrenadorDAO entrenadorDAO = new EntrenadorDAO();
                    entrenadorDAO.crearEntrenador(entrenador);
                    JOptionPane.showMessageDialog(null,"Entrenador creado con éxito.");
                    JOptionPane.showMessageDialog(null,"Se le asignará un equipo desde el departamento EQUIPO.");
                }

            }
        });
        mostrarDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EntrenadorDAO entrenadorDAO = new EntrenadorDAO();
                tAdatos.setText(entrenadorDAO.obtenerTodosEntrenadores());

            }
        });
    }
}

package GUI;

import DAO.JugadorDAO;
import model.Jugador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class interfazJugador extends JFrame {


    private JPanel GestionJugador;
    private JButton btnLimpiar;
    private JRadioButton rBe1;
    private JRadioButton rBe2;
    private JRadioButton rBe3;
    private JTextField tFnom;
    private JComboBox cBPosicion;
    private JTextField tFValor;
    private JTextField tFGol;
    private JTextField tFNac;
    private JLabel lblTitle;
    private JLabel lblNom;
    private JLabel lblPos;
    private JLabel lblVal;
    private JLabel lblGol;
    private JLabel lblnac;
    private JButton btnEnviar;
    private JButton mostrarJugadoresButton;
    private JTextArea tAJug;
    private ButtonGroup grupoBtn;

    public interfazJugador(){
        setContentPane(GestionJugador);
        setTitle("Jugador");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);        //Evita que se cierre la APP
        //setSize(700,600);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        //LIMPIAR
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tFGol.setText("");
                tFnom.setText("");
                tFNac.setText("");
                tFValor.setText("");
                cBPosicion.setSelectedIndex(0);
                rBe1.setSelected(false);
                rBe2.setSelected(false);
                rBe3.setSelected(false);
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

        btnEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = tFnom.getText();
                String posicion = (String) cBPosicion.getSelectedItem();
                String valor_mercado = tFValor.getText();
                String goles =tFGol.getText();
                String nacionalidad = tFNac.getText();
                Jugador jugador = new Jugador();
                JugadorDAO jugadorDAO = new JugadorDAO();

                //NOMBRE
                if(!tFnom.getText().equals("")){
                    jugador.setNombre(nombre);
                }
                //POSICION
                if(!cBPosicion.getSelectedItem().equals("")){
                    String opcion = (String) cBPosicion.getSelectedItem();

                    switch (opcion){
                        case "Top" :
                            jugador.setPosicion("TOP");
                            break;
                        case "Jungla" :
                            jugador.setPosicion("Jungla");
                            break;
                        case "Mid" :
                            jugador.setPosicion("Mid");
                            break;
                        case "Carry" :
                            jugador.setPosicion("Carry");
                            break;
                        case "Support" :
                            jugador.setPosicion("Support");
                            break;
                    }
                }
                //VALOR MERCADO
                if(!tFValor.getText().equals("")){
                    jugador.setValor_mercado(valor_mercado);
                }
                //GOLES
                if(!tFGol.getText().equals("")){
                    jugador.setGoles(goles);
                }
                //NACIONALIDAD
                if(!tFNac.getText().equals("")){
                    jugador.setNacionalidad(nacionalidad);
                }
                jugadorDAO.crearJugador(jugador);
                JOptionPane.showMessageDialog(null,"Jugador registrado con exito");
            }
        });
        mostrarJugadoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JugadorDAO jugadorDAO = new JugadorDAO();
                tAJug.setText(jugadorDAO.obtenerTodosJugadores().toString());
            }
        });
    }
}

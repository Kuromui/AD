package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class interfazInicio extends JFrame {
    private JPanel Inicio;
    private JComboBox CBOpciones;
    private JButton limpiarButton;
    private JButton aceptarButton;
    private JLabel lblTitulo;
    private JLabel lblSeleccion;
    private JLabel lblLogo;
    private interfazLiga iLiga;
    private interfazEquipo iEquipo;
    private interfazJugador iJugador;
    private interfazEntrenador iEntrenador;

    public interfazInicio(){
        setContentPane(Inicio);
        setTitle("Inicio");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(550,400);
        setLocationRelativeTo(null);
        setVisible(true);
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CBOpciones.setSelectedIndex(0);
            }
        });

        aceptarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String opcion = ((String) CBOpciones.getSelectedItem()); //cast

                switch (opcion){
                    case "Liga" :
                        iLiga = new interfazLiga();
                        break;
                    case "Equipo" :
                        iEquipo = new interfazEquipo();
                        break;
                    case "Jugador" :
                        iJugador = new interfazJugador();
                        break;
                    case "Entrenador" :
                        iEntrenador = new interfazEntrenador();
                        break;
                }
                setVisible(true);
                dispose();

                /*if(CBOpciones.getSelectedItem().equals("Liga")){
                    iLiga = new interfazLiga();
                    setVisible(true);
                   dispose();
                }
                if(CBOpciones.getSelectedItem().equals("Equipo")){
                    iEquipo = new interfazEquipo();
                    setVisible(true);
                    dispose();
                }
                if(CBOpciones.getSelectedItem().equals("Jugador")){
                    iEquipo = new interfazEquipo();
                    setVisible(true);
                    dispose();
                }
                if(CBOpciones.getSelectedItem().equals("Entrenador")){
                    iEquipo = new interfazEquipo();
                    setVisible(true);
                    dispose();
                }*/
            }
        });
    }

    private void createUIComponents() {
        lblLogo = new JLabel(new ImageIcon("src/main/java/logo.png"));
    }
}

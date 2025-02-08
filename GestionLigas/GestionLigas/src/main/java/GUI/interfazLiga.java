package GUI;

import DAO.LigaDAO;
import controller.LigaController;
import model.Equipo;
import model.Liga;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class interfazLiga extends JFrame{


    private JPanel GestiónLiga;
    private JTabbedPane PanelPrincipal;
    private DefaultTableModel modelo = new DefaultTableModel();
    private JButton crearButton;
    private JButton limpiarButton;
    private JTextField txtFNombre;
    private JTextField txtFInicio;
    private JTextField txtFFin;
    private JPanel CRUDPanel;
    private JPanel ActualizarPanel;
    private JPanel EliminarPanel;
    private JTextField txtFIDEliminar;
    private JButton eliminarBtn;
    private JButton btnLimpiarEliminar;
    private JLabel lblIDEliminar;
    private JLabel txtIntro;
    private JLabel lblNombre;
    private JLabel lblInicio;
    private JLabel lblFin;
    private JTextField txtFIDUpdate;
    private JTextArea txtAMostrarLigas;
    private JRadioButton RBNombre;
    private JTextField tFUpdateNombre;
    private JButton btnLigasDispo;
    private JLabel intro;
    private JRadioButton RBInicio;
    private JTextField tFUpdateInicio;
    private JRadioButton RBFin;
    private JTextField tFUpdateFin;
    private JButton btnUpdateAceptar;
    private JButton btnUpdateLimpiar;
    private JPanel AsociarPanel;
    private JLabel lblTitulo;
    private JTextField tFIDAsociarEquipo;
    private JTextField tFIDAsociarLiga;
    private JButton btnLimpiarAsociar;
    private JButton btnAceptarAsociar;
    private JLabel lblIDAsociar;
    private JLabel lblIDLigaAsociar;

    public interfazLiga() {
        setContentPane(GestiónLiga);
        setTitle("Inicio");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);        //Evita que se cierre la APP
        //setSize(700,600);
        pack(); // componentes se acomodan automáticamente
        setLocationRelativeTo(null);
        setVisible(true);

        //CREAR
        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre_liga = txtFNombre.getText();
                String fecha_inicio = txtFInicio.getText();
                String fecha_fin = txtFFin.getText();

                if (txtFNombre.getText().equals("") || txtFInicio.getText().equals("") || txtFFin.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Quedan valores pendientes de introducir.");
                } else {
                    Liga liga = new Liga();
                    liga.setNombre_liga(nombre_liga);
                    liga.setFecha_inicio(fecha_inicio);
                    liga.setFecha_fin(fecha_fin);

                    LigaDAO ligaDAO = new LigaDAO();
                    ligaDAO.crearLiga(liga);

                    JOptionPane.showMessageDialog(null, "Liga creada correctamente");
                }
            }
        });

        //LIMPIAR-CREAR
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtFNombre.setText("");
                txtFInicio.setText("");
                txtFFin.setText("");
            }
        });

        //ACTUALIZAR
        btnUpdateAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LigaDAO ligaDAO = new LigaDAO();


                if (txtFIDUpdate.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Por favor, introduzca el identificador.");
                }
                int id = Integer.parseInt(txtFIDUpdate.getText());
                Liga liga = ligaDAO.obtenerLigaID(id); //obtenemos la liga por id

                //SELECCIÓN RADIOBUTTON

                if (RBNombre.isSelected() && !tFUpdateNombre.getText().equals("")) {
                    liga.setNombre_liga(tFUpdateNombre.getText());
                }
                if (RBInicio.isSelected() && !tFUpdateInicio.getText().equals("")) {
                    liga.setFecha_inicio(tFUpdateInicio.getText());
                }
                if (RBNombre.isSelected() && !tFUpdateFin.getText().equals("")) {
                    liga.setFecha_fin(tFUpdateFin.getText());
                }
                ligaDAO.editarLiga(liga); //edita la liga
                JOptionPane.showMessageDialog(null, "Actualizada correctamente.");
            }
        });

        //LIMPIAR-UPDATE
        btnUpdateLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtFIDUpdate.setText("");
                tFUpdateFin.setText("");
                tFUpdateInicio.setText("");
                tFUpdateNombre.setText("");
            }
        });


        //ELIMINAR
        eliminarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtFIDEliminar.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Valor vacío");
                }else {
                    int id = Integer.parseInt(txtFIDEliminar.getText());
                    LigaDAO ligaDAO = new LigaDAO();
                    ligaDAO.eliminarLigaID(id);
                    JOptionPane.showMessageDialog(null, "Liga eliminada correctamente");
                }
            }
        });
        //LIMPIAR-ELIMINAR
        btnLimpiarEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtFIDEliminar.setText("");
            }
        });
        //COMPROBAR LIGAS DISPONIBLES
        btnLigasDispo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LigaDAO ligaDAO = new LigaDAO();
                txtAMostrarLigas.setText(ligaDAO.ligasDisponibles());
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

        //ASOCIAR-LIMPIAR
        btnLimpiarAsociar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tFIDAsociarEquipo.setText("");
                tFIDAsociarLiga.setText("");
            }
        });
        btnAceptarAsociar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                LigaController ligaController = new LigaController();
                if(!tFIDAsociarEquipo.getText().equals("") && !tFIDAsociarLiga.getText().equals("")){
                    int idEquipo = Integer.parseInt(tFIDAsociarEquipo.getText());
                    int idLiga = Integer.parseInt(tFIDAsociarLiga.getText());
                    ligaController.asignarEquipoLiga(idEquipo,idLiga);
                    JOptionPane.showMessageDialog(null,"Operación realizada.");
                }else{
                    JOptionPane.showMessageDialog(null, "Valores pendientes de ingresar");
                }

            }
        });
    }
}

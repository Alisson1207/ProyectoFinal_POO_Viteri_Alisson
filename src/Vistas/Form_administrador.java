package Vistas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form_administrador {
    public JPanel panel_principal;
    private JLabel icono;
    private JLabel lb_titulo;
    private JButton bt_personal;
    private JButton bt_productos;
    private JButton bt_ventas;
    private JButton bt_consultas;
    private JButton bt_cerrar;
    private JPanel panel_mostrar;

    private JPanel panelPersonal;
    private JPanel panelProductos;
    private JPanel panelVentas;
    private JPanel panelConsultas;
    private JTable table;
    private DefaultTableModel tableModel;

    public Form_administrador() {

        // Redimensionar y establecer el icono en el JLabel directamente en el constructor
        Icon icon = icono.getIcon();
        if (icon instanceof ImageIcon) {
            ImageIcon imageIcon = (ImageIcon) icon;
            Image image = imageIcon.getImage();
            Image resizedImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            icono.setIcon(new ImageIcon(resizedImage));
        }

    }
        public static void main (String[]args){
            JFrame frame = new JFrame("Form_administrador");
            frame.setContentPane(new Form_administrador().panel_principal);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }
    }


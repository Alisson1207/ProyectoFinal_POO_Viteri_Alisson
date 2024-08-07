package Vistas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

public class Form_cajero {
    public JPanel panel_cajero;
    private JLabel icono;
    private JTabbedPane menu_cajero;
    private JPanel panel_catalogo;
    private JLabel lb_catalogo;
    private JButton bt_bebidas;
    private JButton bt_vegetales;
    private JButton bt_snacks;
    private JButton bt_panaderia;
    private JButton bt_carnes;
    private JButton bt_lacteos;
    private JButton bt_regresar;
    private JPanel catalogo_bebidas;
    private JLabel icon_regresar;

    /**
     * Constructor de la clase Form_administrador. Configura el formulario y los eventos.
     * Inicializa los componentes y establece las acciones para los botones.
     */
    public Form_cajero() {
        // Configuración del icono
        Icon icon = icono.getIcon();
        if (icon instanceof ImageIcon) {
            ImageIcon imageIcon = (ImageIcon) icon;
            Image image = imageIcon.getImage();
            Image resizedImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            icono.setIcon(new ImageIcon(resizedImage));
        }
        bt_bebidas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cambiar a la pestaña deseada (por ejemplo, la primera pestaña)
                menu_cajero.setSelectedIndex(1);
            }
        });

        bt_vegetales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu_cajero.setSelectedIndex(4);
            }
        });

        bt_snacks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu_cajero.setSelectedIndex(2);
            }
        });

        bt_panaderia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu_cajero.setSelectedIndex(3);
            }
        });

        bt_carnes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu_cajero.setSelectedIndex(5);
            }
        });

        bt_lacteos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu_cajero.setSelectedIndex(6);
            }
        });


        bt_regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu_cajero.setSelectedIndex(0);
            }
        });



    }
}


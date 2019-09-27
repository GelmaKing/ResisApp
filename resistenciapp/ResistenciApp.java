package resistenciapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

public class ResistenciApp extends JFrame {

    private Resistencia canva;


    public ResistenciApp() {

        setSize(500, 500);
        setResizable(false);
        setTitle("ResistenciApp");
        setLayout(new GridLayout());

        canva = new Resistencia();
        add(canva);

        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;

        this.setBounds((ancho / 2) - (this.getWidth() / 2), (alto / 2) - (this.getHeight() / 2), 500, 500);
    }

    public static void main(String[] args) {
        ResistenciApp ventana = new ResistenciApp();
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

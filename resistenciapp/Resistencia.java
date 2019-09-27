package resistenciapp;

import javax.swing.*;
import java.awt.*;
import java.awt.Point.*;
import java.awt.event.MouseAdapter;
import java.awt.event.*;
import java.awt.geom.*;
import java.text.DecimalFormat;
import java.util.*;

public class Resistencia extends JPanel {

    private ArrayList<Cifras> cifras;
    private Rectangle2D current;
    private Escala multiplicador;
    private Tolerancia tole;
    private int aux;
    private JButton b4;
    private JButton b5;
    private JButton b6;
    private Coeficiente coe;
    private boolean bandera;
    private String pantalla;
    private double conjunto;

    public Resistencia() {
        DecimalFormat formato2 = new DecimalFormat("#.##");
        
        conjunto = 0;
        pantalla = "";
        bandera = false;
        cifras = new ArrayList<>();
        aux = 0;
        b4 = new JButton("4 Bandas");
        b5 = new JButton("5 Bandas");
        b6 = new JButton("6 Bandas");
        /*
             g2.setColor(Color.black);
        g2.fillRect(0, 247, 66, 6);
        g2.fillRect(425, 247, 75, 6);
        g2.setColor(new Color(249, 231, 159));
        g2.fillOval(65, 188, 125, 125);
        g2.fillOval(300, 188, 125, 125);
        g2.fillRect(150, 200, 200, 100);*/

        cifras.add(new Cifras(new Rectangle2D.Double(119, 189, 10, 124)));
        cifras.get(0).setBanda1(true);
        cifras.add(new Cifras(new Rectangle2D.Double(195, 200, 10, 99)));
        multiplicador = new Escala(new Rectangle2D.Double(285, 200, 10, 99));
        tole = new Tolerancia(new Rectangle2D.Double(362, 189, 10, 124));

        add(b4);
        add(b5);
        add(b6);

        b4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                cifras.removeAll(cifras);
                bandera = false;
                pantalla = "";
                cifras.add(new Cifras(new Rectangle2D.Double(119, 189, 10, 124)));
                cifras.get(0).setBanda1(true);
                cifras.add(new Cifras(new Rectangle2D.Double(195, 200, 10, 99)));
                multiplicador = new Escala(new Rectangle2D.Double(285, 200, 10, 99));
                tole = new Tolerancia(new Rectangle2D.Double(362, 189, 10, 124));

            }

        });

        b5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                cifras.removeAll(cifras);
                bandera = false;
                pantalla = "";
                cifras.add(new Cifras(new Rectangle2D.Double(119, 189, 10, 124)));
                cifras.get(0).setBanda1(true);
                cifras.add(new Cifras(new Rectangle2D.Double(195, 200, 10, 99)));
                cifras.add(new Cifras(new Rectangle2D.Double(235, 200, 10, 99)));
                multiplicador = new Escala(new Rectangle2D.Double(285, 200, 10, 99));
                tole = new Tolerancia(new Rectangle2D.Double(362, 189, 10, 124));

            }

        });

        b6.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                cifras.removeAll(cifras);
                pantalla = "";
                bandera = true;

                cifras.add(new Cifras(new Rectangle2D.Double(119, 189, 10, 124)));
                cifras.get(0).setBanda1(true);
                cifras.add(new Cifras(new Rectangle2D.Double(175, 200, 10, 99)));
                cifras.add(new Cifras(new Rectangle2D.Double(225, 200, 10, 99)));
                multiplicador = new Escala(new Rectangle2D.Double(260, 200, 10, 99));
                tole = new Tolerancia(new Rectangle2D.Double(310, 200, 10, 99));
                coe = new Coeficiente(new Rectangle2D.Double(362, 189, 10, 124));

            }

        });

        //cifras.add(new Cifras(new Rectangle2D.Double(362, 189, 10, 124)));
        //cifras.add(new Rectangle2D.Double(, 188, 124, 10));
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {

                current = find(e.getPoint());

                if (current != null && SwingUtilities.isLeftMouseButton(e)) {

                    System.out.println("Esta entrando al press ");
                    aux = nCifra(e.getPoint());

                }

            }

            @Override
            public void mouseClicked(MouseEvent e) {

                if (current != null && SwingUtilities.isLeftMouseButton(e)) {
                    pantalla = "";

                    if (aux != -1 && current != multiplicador.getR() && current != tole.getR()) {

                        cifras.get(aux).setCifra(cifras.get(aux).getCifra() + 1);

                        // System.out.println(pantalla);
                    }

                    if (current == multiplicador.getR()) {
                        multiplicador.setEscala(multiplicador.getEscala() + 1);
                    }

                    if (current == tole.getR()) {
                        tole.setTolerancia(tole.getTolerancia() + 1);
                    }

                    if (bandera) {
                        if (current == coe.getR()) {
                            coe.setCoeficiente(coe.getCoeficiente() + 1);
                        }
                    }

                    for (int i = 0; i < cifras.size(); i++) {

                        pantalla += Integer.toString(cifras.get(i).getCifra());

                    }

                    //System.out.println(pantalla);
                    conjunto = Double.parseDouble(pantalla);
                    conjunto = conjunto * multiplicador.Escala();
                    if (multiplicador.Escala() == 100) {
                        conjunto = conjunto / 1000;
                    }

                    if (bandera) {
                        pantalla = formato2.format(conjunto) + scale() + " " + tolerance() + " " + Coef();
                    } else {
                        pantalla = formato2.format(conjunto) + scale() + " " + tolerance();
                    }

                }

            }

        });

        addMouseMotionListener(new MouseMotionAdapter() {

            public void mouseMoved(MouseEvent e) {

                if (find(e.getPoint()) == null) {
                    setCursor(Cursor.getDefaultCursor());
                } else {

                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                }

            }

        });

    }

    public Rectangle2D find(Point2D p) {

        for (int i = 0; i < cifras.size(); i++) {
            Rectangle2D r = (Rectangle2D) cifras.get(i).getR();
            if (r.contains(p)) {
                return r;
            }
        }
        if (multiplicador.getR().contains(p)) {
            return multiplicador.getR();
        }
        if (tole.getR().contains(p)) {
            return tole.getR();
        }
        if (bandera) {
            if (coe.getR().contains(p)) {
                return coe.getR();
            }
        }

        return null;
    }

    public int nCifra(Point p) {

        for (int i = 0; i < cifras.size(); i++) {

            Rectangle2D r = cifras.get(i).getR();
            if (r.contains(p)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // g2.drawOval(0, 0, 300, 100);
        g2.setColor(Color.black);
        g2.fillRect(0, 247, 66, 6);
        g2.fillRect(425, 247, 75, 6);
        g2.setColor(new Color(245, 176, 65));
        g2.fillOval(65, 188, 125, 125);
        g2.fillOval(300, 188, 125, 125);
        g2.fillRect(150, 200, 200, 100);
        //g2.fillRect(150, 200, 200, 100);

        g2.setColor(Color.blue);
        if (!cifras.isEmpty()) {

            for (int i = 0; i < cifras.size(); i++) {
                if (cifras.get(i).getCifra() == 0) {
                    g2.setColor(Color.black);
                    g2.fill(cifras.get(i).getR());
                }
                if (cifras.get(i).getCifra() == 1) {
                    g2.setColor(new Color(110, 44, 0));
                    g2.fill(cifras.get(i).getR());
                }
                if (cifras.get(i).getCifra() == 2) {
                    g2.setColor(Color.red);
                    g2.fill(cifras.get(i).getR());
                }
                if (cifras.get(i).getCifra() == 3) {
                    g2.setColor(new Color(255, 112, 0));
                    g2.fill(cifras.get(i).getR());
                }
                if (cifras.get(i).getCifra() == 4) {
                    g2.setColor(Color.yellow);
                    g2.fill(cifras.get(i).getR());
                }
                if (cifras.get(i).getCifra() == 5) {
                    g2.setColor(Color.green);
                    g2.fill(cifras.get(i).getR());
                }
                if (cifras.get(i).getCifra() == 6) {
                    g2.setColor(Color.blue);
                    g2.fill(cifras.get(i).getR());
                }
                if (cifras.get(i).getCifra() == 7) {
                    g2.setColor(Color.magenta);
                    g2.fill(cifras.get(i).getR());
                }
                if (cifras.get(i).getCifra() == 8) {
                    g2.setColor(Color.gray);
                    g2.fill(cifras.get(i).getR());
                }

                if (cifras.get(i).getCifra() == 9) {
                    g2.setColor(Color.white);
                    g2.fill(cifras.get(i).getR());
                }
            }

            /**
             * ***************MULTIPLICADOR**************************
             */
            g2.setColor(pinturaM());
            g2.fill(multiplicador.getR());

            /**
             * *********************************************
             */
            /**
             * ***************TOLERANCIA**************************
             */
            g2.setColor(pinturaT());
            g2.fill(tole.getR());

            /**
             * *********************************************
             */
            /**
             * ***************COEFICIENTE**************************
             */
            if (bandera) {
                g2.setColor(pinturaC());
                g2.fill(coe.getR());
            }

            /**
             * *********************************************
             */
            // g2.setStroke(new BasicStroke(40));
            g2.setColor(Color.black);
            g2.setFont(new Font("Times New Roman", Font.PLAIN, 30));
            g2.drawString(pantalla, 20, 400);

            repaint();

        }

    }

    public Color pinturaT() {

        if (tole.getTolerancia() == 1) {
            return (new Color(110, 44, 0));
        }
        if (tole.getTolerancia() == 2) {
            return (Color.red);
        }
        if (tole.getTolerancia() == 3) {
            return (new Color(255, 112, 0));
        }
        if (tole.getTolerancia() == 4) {
            return (Color.yellow);
        }
        if (tole.getTolerancia() == 5) {
            return (Color.green);
        }
        if (tole.getTolerancia() == 6) {
            return (Color.blue);
        }
        if (tole.getTolerancia() == 7) {
            return (Color.magenta);
        }
        if (tole.getTolerancia() == 8) {
            return (Color.gray);
        }

        if (tole.getTolerancia() == 9) {
            return new Color(208, 183, 0);
        }

        if (tole.getTolerancia() == 10) {
            return new Color(146, 167, 190);
        }
        if (tole.getTolerancia() == 11) {
            return (new Color(245, 176, 65));
        }
        return null;
    }

    public Color pinturaM() {

        if (multiplicador.getEscala() == 0) {
            return (Color.black);
        }
        if (multiplicador.getEscala() == 1) {
            return (new Color(110, 44, 0));
        }
        if (multiplicador.getEscala() == 2) {
            return (Color.red);
        }
        if (multiplicador.getEscala() == 3) {
            return (new Color(255, 112, 0));
        }
        if (multiplicador.getEscala() == 4) {
            return (Color.yellow);
        }
        if (multiplicador.getEscala() == 5) {
            return (Color.green);
        }
        if (multiplicador.getEscala() == 6) {
            return (Color.blue);
        }
        if (multiplicador.getEscala() == 7) {
            return (Color.magenta);
        }
        if (multiplicador.getEscala() == 8) {
            return (Color.gray);
        }

        if (multiplicador.getEscala() == 9) {
            return (Color.white);
        }

        if (multiplicador.getEscala() == 10) {
            return new Color(208, 183, 0);
        }

        if (multiplicador.getEscala() == 11) {
            return new Color(146, 167, 190);
        }
        return null;
    }

    public Color pinturaC() {

        if (coe.getCoeficiente() == 0) {
            return (Color.black);
        }
        if (coe.getCoeficiente() == 1) {
            return (new Color(110, 44, 0));
        }
        if (coe.getCoeficiente() == 2) {
            return (Color.red);
        }
        if (coe.getCoeficiente() == 3) {
            return (new Color(255, 112, 0));
        }
        if (coe.getCoeficiente() == 4) {
            return (Color.yellow);
        }
        if (coe.getCoeficiente() == 5) {
            return (Color.green);
        }
        if (coe.getCoeficiente() == 6) {
            return (Color.blue);
        }
        if (coe.getCoeficiente() == 7) {
            return (Color.magenta);
        }
        if (coe.getCoeficiente() == 8) {
            return (Color.gray);
        }
        return null;
    }

    public String scale() {

        if (multiplicador.getEscala() == 0) {
            return "Ω";
        }
        if (multiplicador.getEscala() == 1) {
            return "Ω";
        }
        if (multiplicador.getEscala() == 2) {
            return ("Ω");
        }
        if (multiplicador.getEscala() == 3) {
            return ("kΩ");
        }
        if (multiplicador.getEscala() == 4) {
            return ("kΩ");
        }
        if (multiplicador.getEscala() == 5) {
            return ("kΩ");
        }
        if (multiplicador.getEscala() == 6) {
            return ("MΩ");
        }
        if (multiplicador.getEscala() == 7) {
            return ("MΩ");
        }
        if (multiplicador.getEscala() == 8) {
            return ("MΩ");
        }

        if (multiplicador.getEscala() == 9) {
            return ("GΩ");
        }

        if (multiplicador.getEscala() == 10) {
            return ("Ω");
        }

        if (multiplicador.getEscala() == 11) {
            return ("Ω");
        }

        return ("Ω");
    }

    public String tolerance() {

        if (tole.getTolerancia() == 1) {
            return "±1%";
        }
        if (tole.getTolerancia() == 2) {
            return ("±2%");
        }
        if (tole.getTolerancia() == 3) {
            return ("±0.05%");
        }
        if (tole.getTolerancia() == 4) {
            return ("±0.02%");
        }
        if (tole.getTolerancia() == 5) {
            return ("±0.5%");
        }
        if (tole.getTolerancia() == 6) {
            return ("±0.25%");
        }
        if (tole.getTolerancia() == 7) {
            return ("±0.1%");
        }
        if (tole.getTolerancia() == 8) {
            return ("±0.01%");
        }

        if (tole.getTolerancia() == 9) {
            return ("±5%");
        }

        if (tole.getTolerancia() == 10) {
            return ("±10%");
        }

        if (tole.getTolerancia() == 11) {
            return ("±20%");
        }

        return ("");
    }

    public String Coef() {

        if (coe.getCoeficiente() == 0) {
            return "250ppm/°C";
        }
        if (coe.getCoeficiente() == 1) {
            return "100ppm/°C";
        }
        if (coe.getCoeficiente() == 2) {
            return ("50ppm/°C");
        }
        if (coe.getCoeficiente() == 3) {
            return ("15ppm/°C");
        }
        if (coe.getCoeficiente() == 4) {
            return ("25ppm/°C");
        }
        if (coe.getCoeficiente() == 5) {
            return ("20ppm/°C");
        }
        if (coe.getCoeficiente() == 6) {
            return ("10ppm/°C");
        }
        if (coe.getCoeficiente() == 7) {
            return ("5ppm/°C");
        }
        if (coe.getCoeficiente() == 8) {
            return ("1ppm/°C");
        }

        return ("");
    }

}

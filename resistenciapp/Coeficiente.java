package resistenciapp;

import java.awt.geom.Rectangle2D;

public class Coeficiente {

    private int coeficiente;
    private Rectangle2D r;

    public Coeficiente(Rectangle2D r) {

        this.r = r;
        coeficiente = 0;

    }

    public int getCoeficiente() {
        return coeficiente;
    }

    public void setCoeficiente(int coeficiente) {
        if (coeficiente <= 8) {
            this.coeficiente = coeficiente;
        } else {
            this.coeficiente = 0;
        }
    }

    public Rectangle2D getR() {
        return r;
    }

}

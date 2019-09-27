package resistenciapp;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Cifras {

    private int cifra;
    private boolean banda1;
    private Rectangle2D r;

    public Cifras(Rectangle2D r) {
        banda1 = false;
        cifra = 0;
        this.r = r;
    }

    public boolean isBanda1() {
        return banda1;
    }

    public void setBanda1(boolean banda1) {
        
        this.banda1 = banda1;
        cifra = 1;
    }

    public int getCifra() {
        return cifra;
    }

    public void setCifra(int cifra) {

        if (cifra <= 9) {
            this.cifra = cifra;
        } else {
            if (isBanda1()) {
                this.cifra = 1;
            } else {
                this.cifra = 0;
            }
        }
    }

    public Rectangle2D getR() {
        return r;
    }

}

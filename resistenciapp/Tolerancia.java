package resistenciapp;

import java.awt.geom.Rectangle2D;

public class Tolerancia {

    private int tolerancia;
    private Rectangle2D r;
    
        public Tolerancia(Rectangle2D r) {
            
        this.r = r;
        tolerancia = 1;

    }


    public int getTolerancia() {
        return tolerancia;
    }

    public void setTolerancia(int tolerancia) {
        if (tolerancia <= 11) {
            this.tolerancia = tolerancia;
        } else {
            this.tolerancia = 1;
        }
    }

    public Rectangle2D getR() {
        return r;
    }

}

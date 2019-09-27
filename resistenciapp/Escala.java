package resistenciapp;

import java.awt.geom.Rectangle2D;

public class Escala{
    
    private int escala;
    private Rectangle2D r;

    public Escala(Rectangle2D r) {
        this.r = r;
        escala = 0;
    }

    public int getEscala() {
        return escala;
    }

    public void setEscala(int escala) {
        if(escala <= 11){
        this.escala = escala;
        }else{
        this.escala = 0;
        }
    }
    
    public Rectangle2D getR(){
        return r;
    }
    
    public double Escala(){
    
        if (escala == 0) {
            return 1;
        }
        if (escala == 1) {
        return 10;  
        }
        if (escala == 2) {
            return 100;
        }
        if (escala == 3) {
            return 1;
        }
        if (escala == 4) {
            return 10;
        }
        if (escala == 5) {
            return 100;
        }
        if (escala == 6) {
            return 1;
        }
        if (escala == 7) {
            return 10;
        }
        if (escala == 8) {
            return 100;
        }
        if (escala == 9) {
            return 1;
        }
        if (escala == 10) {
            return 0.1;
        }
        if (escala == 11) {
            return 0.01;
        }
    
    return 0;
    }


}

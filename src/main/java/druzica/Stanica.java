package druzica;

import java.util.ArrayList;

/**
 * Created by Juraj on 30.11.2016.
 */
public class Stanica {

    private String nazev;
    private Double surX;
    private Double surY;
    private Double surZ;
    private Double surB;
    private Double surL;
    private Double surH;


    public String getNazev() {
        return nazev;
    }

    public Double getSurX() {
        return surX;
    }

    public Double getSurY() {
        return surY;
    }

    public Double getSurZ() {
        return surZ;
    }

    public Double getSurB() {
        return surB;
    }

    public Double getSurL() {
        return surL;
    }

    public Double getSurH() {
        return surH;
    }

    public Stanica(String nazev, Double surX, Double surY, Double surZ, Double surB, Double surL, Double surH) {

        this.nazev = nazev;
        this.surX = surX;
        this.surY = surY;
        this.surZ = surZ;
        this.surB = surB;
        this.surL = surL;
        this.surH = surH;
    }

}

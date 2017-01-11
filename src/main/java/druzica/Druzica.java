package druzica;

/**
 * Created by Juraj on 30.11.2016.
 */
public class Druzica {

    private Integer ID;
    private String nazev;
    private Double surX;
    private Double surY;
    private Double surZ;
    private Double surB;
    private Double surL;
    private Double surH;
    private Double cas;

    public Druzica(Integer ID, String nazev, Double surX, Double surY, Double surZ, Double surB, Double surL, Double surH, Double cas) {
        this.ID = ID;
        this.nazev = nazev;
        this.surX = surX;
        this.surY = surY;
        this.surZ = surZ;
        this.surB = surB;
        this.surL = surL;
        this.surH = surH;
        this.cas = cas;
    }

    public Integer getID() {
        return ID;
    }

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

    public Double getCas() {
        return cas;
    }

}

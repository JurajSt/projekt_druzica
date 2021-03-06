package druzica;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Juraj on 11.01.2017.
 */
public class DruzicaTest {

    private static final Integer ID = 1;
    private static final String NAZEV = "Testovacia stanica";
    private static final Double SURX = 10.0;
    private static final Double SURY = 10.0;
    private static final Double SURZ= 10.0;
    private static final Double SURB = 10.0;
    private static final Double SURL = 10.0;
    private static final Double SURH = 10.0;
    private static final Double CAS = 10.0;
    private Druzica druzica;

    @Before
    public void setup() {
        druzica = new Druzica(ID, NAZEV, SURX, SURY, SURZ, SURB, SURL, SURH, CAS);
    }

    @Test
    public void getID(){

        Assert.assertEquals(ID, druzica.getID());
    }

}
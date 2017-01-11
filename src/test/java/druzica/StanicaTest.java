package druzica;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

import static org.junit.Assert.*;

/**
 * Created by Juraj on 11.01.2017.
 */
public class StanicaTest {

    private static final String NAZEV = "Testovacia stanica";
    private static final Double SURX = 10.0;
    private static final Double SURY = 10.0;
    private static final Double SURZ= 10.0;
    private static final Double SURB = 10.0;
    private static final Double SURL = 10.0;
    private static final Double SURH = 10.0;
    private Stanica stanica;

    @Before
    public void setup() {
        stanica = new Stanica(NAZEV, SURX, SURY, SURZ, SURB, SURL, SURH);
    }


    @Test
    public void getNazev(){

        Assert.assertEquals(NAZEV, stanica.getNazev());
    }

    /*@Test
    public void getSurB(){

        Assert.assertEquals(NAZEV, stanica.getSurB());
    }

    @Test
    public void getSurL(){

        Assert.assertEquals(NAZEV, stanica.getSurL());
    }*/

}
package druzica;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Juraj on 11.01.2017.
 */
public class DruzicaRepositoryTest {

    private static final Integer ID = 1;
    private static final String NAZEV = "Testovacia druzica";
    private static final Double SURX = 10.0;
    private static final Double SURY = 10.0;
    private static final Double SURZ= 10.0;
    private static final Double SURB = 10.0;
    private static final Double SURL = 10.0;
    private static final Double SURH = 10.0;
    private static final Double CAS = 10.0;
    private static final Druzica DRUZICA = new Druzica(ID, NAZEV, SURX, SURY, SURZ, SURB, SURL, SURH, CAS);
    private DruzicaRepository repository;

    @Before
    public void setup() {
        repository = new DruzicaRepository();
    }

    @Test
    public void deleteDruzica(){

        repository.saveDruzica(DRUZICA);
        repository.deleteDruzica(ID);
        Druzica druzica = repository.getDruzica(ID);
        Assert.assertNull(druzica);
    }

    @Test
    public void saveDruzica(){

        repository.saveDruzica(DRUZICA);
        Druzica druzica = repository.getDruzica(ID);
        Assert.assertEquals(DRUZICA, druzica);
    }

}
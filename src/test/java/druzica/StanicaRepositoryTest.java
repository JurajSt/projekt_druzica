package druzica;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;


import javax.print.attribute.standard.MediaSize;
import static org.junit.Assert.*;

/**
 * Created by Juraj on 11.01.2017.
 */
public class StanicaRepositoryTest {

    private static final String NAZEV = "Testovacia stanica";
    private static final Double SURX = 10.0;
    private static final Double SURY = 10.0;
    private static final Double SURZ= 10.0;
    private static final Double SURB = 10.0;
    private static final Double SURL = 10.0;
    private static final Double SURH = 10.0;
    private static final Stanica STANICA = new Stanica(NAZEV, SURX, SURY, SURZ, SURB, SURL, SURH);
    private StanicaRepository repository;

    @Before
    public void setup() {
        repository = new StanicaRepository();
    }

    @Test
    public void deleteStanica()  {
        repository.saveStanica(STANICA);
        repository.deleteStanica(NAZEV);
        Stanica stanica = repository.getStanica(NAZEV);
        Assert.assertNull(stanica);
    }

    @Test
    public void saveStanica()  {

        repository.saveStanica(STANICA);
        Stanica stanica = repository.getStanica(NAZEV);
        Assert.assertEquals(STANICA, stanica);
    }

}
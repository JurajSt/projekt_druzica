package druzica;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by ruz76 on 2.11.2016.
 */
@Service
public class StanicaRepository {

    private final Map<String, Stanica> stanice = Maps.newHashMap();

    public Stanica getStanica(String nazev) {
        return stanice.get(nazev);
    }

    public Double getSurB(String nazev) {
        return stanice.get(nazev).getSurB();
    }

    public void deleteStanica(String nazev) {
        stanice.remove(nazev);
    }

    public void saveStanica(Stanica stanica) {
        stanice.put(stanica.getNazev(), stanica);
    }

    public void updateStanica(Stanica stanica) {stanice.put(stanica.getNazev(), stanica);}

    public Map<String, Stanica> getAll() {
        return stanice;
    }

    public Double getSurL(String nazev) {return stanice.get(nazev).getSurL();}
}

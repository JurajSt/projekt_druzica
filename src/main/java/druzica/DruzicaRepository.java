package druzica;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by ruz76 on 2.11.2016.
 */
@Service
public class DruzicaRepository {
    private final Map<Integer, Druzica> druzice = Maps.newHashMap();

    public Druzica getDruzica(Integer ID) {
        return druzice.get(ID);
    }

    public void deleteDruzica(Integer ID) {
        druzice.remove(ID);
    }

    public void saveDruzica(Druzica druzica) {
        druzice.put(druzica.getID(), druzica);
    }

    public Map<Integer, Druzica> getAll() {
        return druzice;
    }
}
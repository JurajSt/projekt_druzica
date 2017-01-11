package druzica;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by Juraj on 14.12.2016.
 */
public class NavigSpravaRepository {
    private final Map<String, NavigSprava> navigSpravy = Maps.newHashMap();

    public void saveSprava(NavigSprava navigSprava) {
        navigSpravy.put(navigSprava.getSprava(), navigSprava);
    }
}

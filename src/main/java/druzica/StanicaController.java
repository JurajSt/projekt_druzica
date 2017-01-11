package druzica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

/**
 * Created by Juraj on 30.11.2016.
 */
@Controller
//@RequestMapping("/stanica")
public class StanicaController {

    private final StanicaRepository stanicaRepository;

    @Autowired
    public StanicaController(StanicaRepository stanicaRepository) {
        this.stanicaRepository = stanicaRepository;
        stanicaRepository.saveStanica(new Stanica("Testovaci stanica", 1000.00, 1000.00, 100.00, 10.00, 10.00, null));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/stanica")
    public String listAll(Model model) {
        Map<String, Stanica> stanice = stanicaRepository.getAll();
        model.addAttribute("stanice", stanice);
        return "/index1";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/deleteStanica/{nazev}")
    public String deleteStanica(@PathVariable("nazev") String nazev){
        stanicaRepository.deleteStanica(nazev);
        return "redirect:/stanica";    //"redirect:/";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/showMap")
    public String showMap(Model model) {
        Map<String, Stanica> stanice = stanicaRepository.getAll();
        model.addAttribute("stanice", stanice);
        return "/mapa";
    }


    /*@RequestMapping(method = RequestMethod.GET, path = "/showMap")
    public String showMap(Model model){
        Map<String, Stanica> stanice = stanicaRepository.getAll();
        Object[] key = stanice.keySet().toArray();
        Double coorB = null;
        Double coorL = null;
        ArrayList<ArrayList> coorAll = new ArrayList<>();
        for (int i = 0; i<key.length; i++){
            coorB = stanicaRepository.getSurB(key[i].toString());
            coorL = stanicaRepository.getSurL(key[i].toString());
            ArrayList coor = new ArrayList();
            coor.add(key[i].toString());
            coor.add(coorB);
            coor.add(coorL);
            coorAll.add(coor);
        }
        model.addAttribute("coorAll", coorAll);
        //System.out.print(coorB);
        //System.out.print(coorL);

        return "/mapa";
    }*/

    @RequestMapping(method = RequestMethod.POST, path = "/createStanica")
    public String createStanica(@RequestParam("nazevStanice") String nazevStanice,
                                @RequestParam("surStaniceX") Double surStaniceX,
                                @RequestParam("surStaniceY") Double surStaniceY,
                                @RequestParam("surStaniceZ") Double surStaniceZ,
                                @RequestParam("surStaniceB") Double surStaniceB,
                                @RequestParam("surStaniceL") Double surStaniceL,
                                @RequestParam("surStaniceH") Double surStaniceH) {
        Stanica stanica = null;
        if (surStaniceB == null || surStaniceL == null || surStaniceH == null) {

            ArrayList<Double> CoorXYZtoLatLonH = new PrevodSuradnic().XYZtoLatLonH(surStaniceX, surStaniceY, surStaniceZ);
            Double surStaniceB1 = CoorXYZtoLatLonH.get(0);
            Double surStaniceL1 = CoorXYZtoLatLonH.get(1);
            Double surStaniceH1 = CoorXYZtoLatLonH.get(2);
            stanica = new Stanica(nazevStanice, surStaniceX, surStaniceY, surStaniceZ, surStaniceB1, surStaniceL1, surStaniceH1);
        }

        if (surStaniceX == null || surStaniceY == null || surStaniceZ == null) {

            ArrayList<Double> CoorLatLonHtoXYZ = new PrevodSuradnic().LatLonHtoXYZ(surStaniceB, surStaniceL, surStaniceH);
            Double surStaniceX1 = CoorLatLonHtoXYZ.get(0);
            Double surStaniceY1 = CoorLatLonHtoXYZ.get(1);
            Double surStaniceZ1 = CoorLatLonHtoXYZ.get(2);
            stanica = new Stanica(nazevStanice, surStaniceX1, surStaniceY1, surStaniceZ1, surStaniceB, surStaniceL, surStaniceH);
        }
        stanicaRepository.saveStanica(stanica);
        return "redirect:/stanica";


    }
}
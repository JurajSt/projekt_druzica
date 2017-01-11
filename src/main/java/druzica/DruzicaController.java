package druzica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Juraj on 30.11.2016.
 */
@Controller
//@RequestMapping("/druzica")
public class DruzicaController {

    private final DruzicaRepository druzicaRepository;

    @Autowired
    public DruzicaController(DruzicaRepository druzicaRepository) {
        this.druzicaRepository = druzicaRepository;
        druzicaRepository.saveDruzica(new Druzica(0, "Testovaci druzica", 1000.00, 1000.00, 1000.00, 100.00, 100.00, 100.00, 100.00));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/druzica")
    public String listAll(Model model) {
        Map<Integer, Druzica> druzice = druzicaRepository.getAll();
        model.addAttribute("druzice", druzice);
        return "/index";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/showMapSatellites")
    public String showMapSatellites (Model model) {
        Map<Integer, Druzica> druzice = druzicaRepository.getAll();
        model.addAttribute("druzice", druzice);
        return "/mapaDruzice";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/createDruzica")
    public String createDruzica () throws IOException {
        File folder = new File("src/main/resources/file");
        File[] listOfFiles = folder.listFiles();
        String cesta;
        Integer ID;
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                //System.out.println("File " + listOfFiles[i].getName());
                cesta = "src\\main\\resources\\file\\" + listOfFiles[i].getName();
                ArrayList<ArrayList<String>> vypocet = new Funkcie().vypocetNavData(cesta);             // precita subor a vypocita suradnice druzice
                int sur;
                for (sur = 0; sur<vypocet.size(); sur++){
                    System.out.println(vypocet.get(sur));
                    String cisloDruzice = vypocet.get(sur).get(0);
                    Double XX1 = Double.valueOf(vypocet.get(sur).get(1));
                    Double YY1 = Double.valueOf(vypocet.get(sur).get(2));
                    Double ZZ1 = Double.valueOf(vypocet.get(sur).get(3));
                    Double Cas = Double.valueOf(vypocet.get(sur).get(4));
                    Double XX = new PrevodSuradnic().round(XX1, 1000);
                    Double YY = new PrevodSuradnic().round(XX1, 1000);
                    Double ZZ = new PrevodSuradnic().round(XX1, 1000);
                    ArrayList<Double> BLH = new PrevodSuradnic().XYZtoLatLonH(XX1, YY1, ZZ1);
                    Double BB1 = BLH.get(0);
                    Double LL1 = BLH.get(1);
                    Double HH1 = BLH.get(2);
                    /*Double BB = new PrevodSuradnic().round(BB1, 10000);
                    Double LL = new PrevodSuradnic().round(LL1, 10000);
                    Double HH = new PrevodSuradnic().round(HH1, 10000);*/
                    ID = sur + 1 + (i*100);                                 // i*100 pre viac navigačnych správ
                    //Druzica druzica = new Druzica(ID, cisloDruzice, XX, YY, ZZ, BB1, LL1, HH1, Cas);
                    druzicaRepository.saveDruzica(new Druzica(ID, cisloDruzice, XX, YY, ZZ, BB1, LL1, HH1, Cas));
                }
                //System.out.println(skuska);
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }
        return "redirect:/druzica";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/deleteDruzica/{ID}")
    public String deleteDruzica(@PathVariable("ID") Integer ID){
        druzicaRepository.deleteDruzica(ID);
        return "redirect:/druzica";                    // presmeruje do kontroleru /
    }

}
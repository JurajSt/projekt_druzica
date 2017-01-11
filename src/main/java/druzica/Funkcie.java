package druzica;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Juraj on 09.01.2017.
 */
public class Funkcie {



    public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {

        File convFile = new File( multipart.getOriginalFilename());
        multipart.transferTo(convFile);
        return convFile;
    }
    public Random random;
    public int rand() {
        return random.nextInt();
    }

    public ArrayList<ArrayList<String>> vypocetNavData(String cesta) throws IOException {

        // Open the file
        FileInputStream fstream = new FileInputStream(cesta);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        //StringBuilder sb = new StringBuilder();
        ArrayList<String> sb = new ArrayList();
        String line;
        int index = 0;
        int indexAll = 0;
        //Read File Line By Line
        while ((line = br.readLine()) != null) {
            String line1 = line.trim();             // ureze medzery na konci
            //System.out.println(line1);
            sb.add(line1+"\n");
            indexAll = indexAll +1;
            //indexAll = indexAll+1;
            //System.out.println(indexAll);
            if (line1.equals("END OF HEADER")){
                //System.out.println(line1);
                index = indexAll;
                //System.out.println(index);

                //break;
            }
            //System.out.println(line1);
        }
        int ii;
        int j;
        int k;
        int l;
        int q = 0;

        ArrayList<String> hlavicka = new ArrayList();
        ArrayList<String> telo = new ArrayList();
        ArrayList<ArrayList<String>> zoznamSprav = new ArrayList();
        ArrayList<ArrayList<String>> surDruzice= new ArrayList();
        ArrayList<String> navigSprava;
        ArrayList<String> XYZCasId = null;
        for (ii = 0; ii<index; ii++){
            hlavicka.add(sb.get(ii));
        }
        for (j = index; j<sb.size(); j++){
            telo.add(sb.get(j));
        }

        while (q<telo.size()) {
            navigSprava = new ArrayList();
            for (k = q; k < q+8; k++) {
                navigSprava.add(telo.get(k));
            }

            //System.out.println(navigSprava);
            zoznamSprav.add(navigSprava);
            //sprava.clear();
            q = q+8;
        }

        Double omega = 0.000072921151467;           //uhlova rychlost rotacie zeme
        Double mi = 398600500000000.0;              //geocentricka gravitacna konstanta

        Double X;
        Double Y;
        Double Z;

        for (l = 0; l<zoznamSprav.size(); l++){
            Double Dt;
            Double platnostSpravy = 7200.0;
            Double krok = 3600.0;
            for (Dt = 0.0; Dt < platnostSpravy; Dt= Dt+krok) {
                String parse = "[ ]+";         // rozdelenie textu podla medzery (aj ked su dve medzery po sebe)
                int s;
                ArrayList<String[]> spravaS = new ArrayList();
                for (s = 0; s < 8; s++) {
                    String riadok = zoznamSprav.get(l).get(s).replace("D-", "ee").replace("D", "e");
                    //System.out.println(riadok);
                    String riadok1 = riadok.replace("-", " -");
                    //System.out.println(riadok1);
                    String riadok2 = riadok1.replace("ee", "e-");
                    System.out.println(riadok2);
                    String[] riadok3 = riadok2.trim().split(parse);
                    //System.out.println(riadok3.length);
                    spravaS.add(riadok3);
                }
                System.out.println(spravaS.get(0));
                //Double Dt = 0.0;
                Integer cisloDruzice = Integer.valueOf(spravaS.get(0)[0]);          //.get(index navig. spravy).get(cislo riadku)[pozicia na riadku]
                Integer rok = Integer.valueOf(spravaS.get(0)[1]);
                Integer mesiac = Integer.valueOf(spravaS.get(0)[2]);
                Integer den = Integer.valueOf(spravaS.get(0)[3]);
                Integer hodina = Integer.valueOf(spravaS.get(0)[4]);
                Integer minuta = Integer.valueOf(spravaS.get(0)[5]);
                Double sekunda = Double.valueOf(spravaS.get(0)[6]);
                Double casSek = (hodina * 60 * 60) + (minuta * 60) + sekunda;
                Double aSQRT = Double.valueOf(spravaS.get(2)[3]);
                System.out.println(aSQRT + " aSQRT");
                Double a = Math.pow(aSQRT, 2);
                System.out.println(a + " a");
                Double n0 = Math.sqrt(mi / (Math.pow(a, 3)));
                System.out.println(n0 + " n0");
                Double Dn = Double.valueOf(spravaS.get(1)[2]);
                System.out.println(Dn + " Dn");
                Double n = n0 + Dn;
                System.out.println(n + " n");
                Double M0 = Double.valueOf(spravaS.get(1)[3]);
                System.out.println(M0 + " M0");
                Double M = M0 + n * Dt;
                System.out.println(M + " M");
                Double e = Double.valueOf(spravaS.get(2)[1]);
                System.out.println(e + " e");
                Double E = M;
                Double E1 = null;
                Double hodnota1 = M;
                Double hodnota2 = 1.0;
                while (!hodnota1.equals(hodnota2)) {
                    Double E0 = hodnota1 - ((hodnota1 - e * Math.sin(hodnota1) - M) / (1 - e * Math.cos(hodnota1)));
                    E1 = E0 - ((E0 - e * Math.sin(E0) - M) / (1 - e * Math.cos(E0)));
                    hodnota1 = new PrevodSuradnic().round(E0, 100000);
                    hodnota2 = new PrevodSuradnic().round(E1, 100000);
                    System.out.println(E0 + " E0");
                    System.out.println(E1 + " E1");
                }
                Double cosv = (Math.cos(E1) - e) / (1 - e * Math.cos(E1));
                System.out.println(cosv + " cosv");
                Double v = Math.acos(cosv);
                System.out.println(v + " v");
                Double w = Double.valueOf(spravaS.get(4)[2]);
                Double cuc = Double.valueOf(spravaS.get(2)[0]);
                Double cus = Double.valueOf(spravaS.get(2)[2]);
                System.out.println(w + " w");
                System.out.println(cuc + " cuc");
                System.out.println(cus + " cus");

                Double vw = v + w;
                System.out.println(vw + " vw");
                Double cos2x = Math.pow(Math.cos(vw), 2) - Math.pow(Math.sin(vw), 2);           // priprava podla vzorcov
                Double sin2x = 2 * Math.sin(vw) * Math.cos(vw);
                System.out.println(cos2x + " cos2x");
                System.out.println(sin2x + " sin2x");

                Double du = cuc * cos2x + cus * sin2x;
                System.out.println(du + " du");
                Double u = v + w + du;
                System.out.println(u + " u");

                Double crc = Double.valueOf(spravaS.get(4)[1]);
                Double crs = Double.valueOf(spravaS.get(1)[1]);
                System.out.println(crc + " crc");
                System.out.println(crs + " crs");

                Double dr = crc * cos2x + crs * sin2x;
                System.out.println(dr + " dr");
                Double r = a * (1 - e * Math.cos(E1)) + dr;
                Double r_round = new PrevodSuradnic().round(r, 100000);
                System.out.println(r + " r");
                Double x = r * Math.cos(u);
                Double y = r * Math.sin(u);
                System.out.println(x + " x");
                System.out.println(y + " y");

                Double rKontrola = Math.sqrt((x * x) + (y * y));
                System.out.println(rKontrola + " rKontrola");
                Double rKontrola_round = new PrevodSuradnic().round(rKontrola, 100000);
                if (r_round.equals(rKontrola_round)) {
                    //System.out.println("Vypocet zatial v poriadku");
                } else {
                    //System.out.println("Vo vypocte je chyba");
                }

                Double cic = Double.valueOf(spravaS.get(3)[1]);
                Double cis = Double.valueOf(spravaS.get(3)[3]);
                Double i0 = Double.valueOf(spravaS.get(4)[0]);
                Double idot = Double.valueOf(spravaS.get(5)[0]);
                System.out.println(cic + " cic");
                System.out.println(cis + " cis");
                System.out.println(i0 + " i0");
                System.out.println(idot + " idot");

                Double di = cic * cos2x + cis * sin2x;
                Double i = i0 + idot * Dt + di;
                System.out.println(di + " di");
                System.out.println(i + " i");

                Double O0 = Double.valueOf(spravaS.get(3)[2]);
                Double Odot = Double.valueOf(spravaS.get(4)[3]);
                Double T0e = Double.valueOf(spravaS.get(3)[0]);
                System.out.println(O0 + " O0");
                System.out.println(Odot + " Odot");
                System.out.println(T0e + " T0e");

                Double O = O0 + (Odot - omega) * Dt - omega * T0e;
                System.out.println(O + " O");

                X = x * Math.cos(O) - y * Math.sin(O) * Math.cos(i);
                Y = x * Math.sin(O) + y * Math.cos(O) * Math.cos(i);
                Z = y * Math.sin(i);

                Double rKontrola2 = Math.sqrt((X * X) + (Y * Y) + (Z * Z));
                System.out.println(rKontrola2 + " rKontrola2");
                Double rKontrola2_round = new PrevodSuradnic().round(rKontrola2, 100000);
                if (r_round.equals(rKontrola2_round)) {
                    //System.out.println("Vypocet zatial v poriadku");
                } else {
                    //System.out.println("Vo vypocte je chyba");
                }
                XYZCasId = new ArrayList();
                XYZCasId.add(cisloDruzice.toString());
                XYZCasId.add(X.toString());
                XYZCasId.add(Y.toString());
                XYZCasId.add(Z.toString());
                XYZCasId.add(casSek.toString());
                surDruzice.add(XYZCasId);
            }
        }
        br.close();

        return surDruzice;
    }
}

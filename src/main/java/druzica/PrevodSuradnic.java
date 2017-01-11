package druzica;

import java.util.ArrayList;

/**
 * Created by Juraj on 05.01.2017.
 */
public class PrevodSuradnic {

    public ArrayList<Double> XYZtoLatLonH (Double surX, Double surY, Double surZ) {
        // konstanty elipsoidu
        Double a = 6378137.0;
        Double b = 6356752.3142;
        int r = 10000; // konstanta zaokruhlovania

        Double e_2 = ((a * a) - (b * b)) / (a * a);
        Double e = Math.sqrt(e_2);

        Double lon = Math.atan(surY / surX);
        Double lon_degrees = Math.toDegrees(lon);

        Double p = Math.sqrt((surX * surX) + (surY * surY));

        Double lat_degrees = null;
        Double h = null;
        for (int i = 0; i <= 3; i++) {
            Double lat0 = Math.atan((surZ / p) * (1 / (1 - e_2)));
            Double cos2lat0 = (1 + Math.cos(2 * lat0)) / 2;
            Double sin2lat0 = (1 - Math.cos(2 * lat0)) / 2;
            Double N0 = (a * a) / (Math.sqrt(a * a * cos2lat0 + b * b * sin2lat0));
            h = (p / Math.cos(lat0)) - N0;
            Double lat = Math.atan((surZ / p) * (1 / (1 - (e_2) * (N0 / (N0 + h)))));
            lat_degrees = Math.toDegrees(lat);
        }

        Double lat_degrees1 = round(lat_degrees, r);
        Double lon_degrees1 = round(lon_degrees, r);
        Double h1 = round(h, r);

        ArrayList<Double> LatLonH = new ArrayList<Double>();

        if (surX < 0) {
            Double lon_degrees2 = lon_degrees1 + 180;
            LatLonH.add(lat_degrees1);
            LatLonH.add(lon_degrees2);
            LatLonH.add(h1);
            return LatLonH;
        }
        LatLonH.add(lat_degrees1);
        LatLonH.add(lon_degrees1);
        LatLonH.add(h1);
        return LatLonH;
    }

    public ArrayList<Double> LatLonHtoXYZ (Double surB, Double surL, Double surH){

        // konstanty elipsoidu
        Double a = 6378137.0;
        Double b = 6356752.3142;
        int r = 1000; // konstanta zaokruhlovania

        Double e_2 = ((a*a)-(b*b))/(a*a);
        Double B = Math.toRadians((surB));
        Double L = Math.toRadians((surL));

        Double N = a/Math.sqrt(1-e_2*Math.pow(Math.sin(B),2));

        Double X1 = ((N+surH)*Math.cos(B)*Math.cos(L));
        Double X = round(X1, r);

        Double Y1 = (N+surH)*Math.cos(B)*Math.sin(L);
        Double Y = round(Y1, r);

        Double Z1 = (N*(1-e_2)+surH)*Math.sin(B);
        Double Z = round(Z1, r);

        ArrayList<Double> XYZ = new ArrayList<Double>();
        XYZ.add(X);
        XYZ.add(Y);
        XYZ.add(Z);
        return XYZ;
    }

    public Double round (Double hodnota, int r){                        // zaokruhlovanie

        Double hodnota_round = hodnota*r;
        long hodnota_round1 = Math.round(hodnota_round);
        Double hodnota_round2 = Double.valueOf(hodnota_round1)/r;
        return hodnota_round2;
    }



}

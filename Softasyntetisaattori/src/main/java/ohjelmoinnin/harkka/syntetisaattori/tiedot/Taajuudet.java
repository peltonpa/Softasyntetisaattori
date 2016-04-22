package ohjelmoinnin.harkka.syntetisaattori.tiedot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Taajuudet {

    Map<String, Float> taajuuskartta;

    /**
     * Konstruktori.
     */
    public Taajuudet() {
        this.taajuuskartta = new HashMap();
        this.generoi();
    }

    /**
     * Generoi apumetodin avulla taajuuskarttaan nuotteja vastaavat taajuudet.
     */
    public void generoi() {
        List<String> nuotit = nuotit();
        List<Float> alkutaajuudet = alkutaajuudet();
        for (int i = 1; i < 7; i++) {
            for (int j = 0; j < 12; j++) {
                String nuotti = nuotit.get(j);
                nuotti += Integer.toString(i);
                this.taajuuskartta.put(nuotti, (float) (alkutaajuudet.get(j) * Math.pow(2, i)));
            }
        }
    }

    /**
     * Nuottien merkkijonojen generointi.
     * @return lista, jossa on nuotit merkkijonoina.
     */
    public List<String> nuotit() {
        ArrayList<String> nuotit = new ArrayList();
        nuotit.add("C");
        nuotit.add("C#");
        nuotit.add("D");
        nuotit.add("D#");
        nuotit.add("E");
        nuotit.add("F");
        nuotit.add("F#");
        nuotit.add("G");
        nuotit.add("G#");
        nuotit.add("A");
        nuotit.add("A#");
        nuotit.add("B");
        return nuotit;
    }

    /**
     * Generoi taajuudet ykkösoktaavin nuoteille.
     * @return lista, jossa on nuotteja vastaavat taajuudet ensimmäiselle oktaaville.
     */
    public List<Float> alkutaajuudet() {
        ArrayList<Float> alkutaajuudet = new ArrayList();
        alkutaajuudet.add(32.7f);
        alkutaajuudet.add(34.6f);
        alkutaajuudet.add(36.7f);
        alkutaajuudet.add(38.9f);
        alkutaajuudet.add(41.2f);
        alkutaajuudet.add(43.7f);
        alkutaajuudet.add(46.2f);
        alkutaajuudet.add(49f);
        alkutaajuudet.add(52f);
        alkutaajuudet.add(55f);
        alkutaajuudet.add(58.3f);
        alkutaajuudet.add(61.7f);
        return alkutaajuudet;
    }

    public Map<String, Float> getTaajuuskartta() {
        return taajuuskartta;
    }

}

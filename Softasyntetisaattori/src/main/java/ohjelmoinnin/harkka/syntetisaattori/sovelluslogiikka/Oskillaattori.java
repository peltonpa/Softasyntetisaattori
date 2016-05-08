package ohjelmoinnin.harkka.syntetisaattori.sovelluslogiikka;

import java.util.HashMap;
import java.util.Map;
import ohjelmoinnin.harkka.syntetisaattori.tiedot.Nuotti;
import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.data.Buffer;
import net.beadsproject.beads.ugens.Envelope;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.ugens.WavePlayer;
import ohjelmoinnin.harkka.syntetisaattori.tiedot.Taajuudet;

/**
 * Tämä luokka on itse äänilähde. Kun Syntetisaattorisovellus-olio luodaan, saa
 * se samalla 3 oskillaattoria. Oskillaattorilla on 5 eri aaltomuotoa (ks.
 * Aallonmuoto-enumi).Jokainen oskillaattori tuottaa signaalia omalle
 * gainilleen, joka lähettyy eteenpäin Syntetisaattorisovellus- olion
 * master-gainille. Master-gain taas jatkaa matkaa AudioContextiin, joka
 * tulkitsee signaalin väreilynä ja passaa eteenpäin Javan audio-outputille ja
 * lopulta esim. headphone-jackiin tai kaiuttimeen.
 *
 */
public class Oskillaattori {

    private AudioContext ac;
    private WavePlayer aalto;
    private Gain g;
    private String kuori;
    private Map<String, Float> taajuuskartta;
    private int oktaavi;

    /**
     * Konstruktori AudioContextilla, nuotilla ja aallonmuotobufferilla.
     *
     * @param ac AudioContext-olio
     * @param nuotti nuotti merkkijonona
     * @param buffer ääniaaltobufferi
     */
    public Oskillaattori(AudioContext ac, String nuotti, Buffer buffer) {
        this.ac = ac;
        this.aalto = new WavePlayer(ac, Nuotti.valueOf(nuotti).getTaajuus(), buffer);
        this.g = new Gain(ac, 1, 0.5f);
        this.kuori = kuori;
        this.g.addInput(this.aalto);
        poisPaalta();
        this.taajuuskartta = new Taajuudet().getTaajuuskartta();
        this.oktaavi = 4;
        asetaNuotti(nuotti);
    }

    /**
     * Tämä metodi palauttaa oskillaattori-olioon liittyvän gain-olion.
     *
     * @return oskillaattori-olioon liittyvä gain-olio.
     */
    public Gain getGain() {
        return this.g;
    }

    /**
     * Metodi asettaa oskillaattorin lähettämään signaalia parametrin "nuotti"-
     * käskemällä taajuudella (nuotti-merkkijono muutetaan Nuotti-enumin avulla
     * int-muotoiseksi taajuudeksi).
     *
     * @param nuotti nuotti merkkijonoesityksenä
     */
    public void asetaNuotti(String nuotti) {
        nuotti += Integer.toString(this.oktaavi);
        float taajuus = this.taajuuskartta.get(nuotti);
        this.aalto.setFrequency(taajuus);
    }

    /**
     * Asettaa aaltomuodoksi parametrina annetun bufferin.
     *
     * @param buffer ääniaaltobufferi
     */
    public void asetaAaltomuoto(Buffer buffer) {
        this.aalto.setBuffer(buffer);
    }

    /**
     * Gainin voimakkuuden asetus oskillaattorille. Tätä muuttaa vain Osc1:n
     * volume-slideri GUI:ssa.
     *
     * @param gaini haluttu äänenvoimakkuus liukulukuna
     */
    public void asetaGain(Float gaini) {
        if (gaini >= 1f) {
            gaini = 1f;
        }
        if (gaini <= 0f) {
            gaini = 0f;
        }
        this.g.setGain(gaini);
    }

    public AudioContext getAc() {
        return ac;
    }

    public WavePlayer getAalto() {
        return aalto;
    }

    public String getKuori() {
        return kuori;
    }

    /**
     * Tarkistaa, onko oskillaattori päällä.
     *
     * @return true, jos päällä, false jos pois päältä.
     */
    public boolean onPaalla() {
        return !this.g.isPaused();
    }

    /**
     * Laittaa oskillaattorin pois päältä.
     */
    public void poisPaalta() {
        this.g.pause(true);
    }

    /**
     * Laittaa oskillaattorin päälle.
     */
    public void paalle() {
        this.g.pause(false);
    }

    /**
     * Metodi ikään kuin kääntää on-off-kytkintä: jos oskillaattori ei ole
     * päällä, tämän jälkeen se on. Toisaalta jos se oli pois päältä, menee se
     * päälle.
     */
    public void onOffKytkin() {
        if (onPaalla()) {
            poisPaalta();
        } else {
            paalle();
        }
    }

    /**
     * Nostaa oktaavia yhdellä pykälällä (max. 5).
     */
    public void nostaOktaavia() {
        this.oktaavi++;
        if (this.oktaavi > 5) {
            this.oktaavi = 5;
        }
    }

    /**
     * Laskee oktaavia yhdellä pykälällä (min. 1).
     */
    public void laskeOktaavia() {
        this.oktaavi--;
        if (this.oktaavi < 1) {
            this.oktaavi = 1;
        }
    }

    /**
     * Hakee kokonaislukuna nykyisen oktaavin.
     *
     * @return oktaavi
     */
    public int getOktaavi() {
        return oktaavi;
    }
}

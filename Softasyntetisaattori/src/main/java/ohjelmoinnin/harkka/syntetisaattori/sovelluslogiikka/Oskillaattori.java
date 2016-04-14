package ohjelmoinnin.harkka.syntetisaattori.sovelluslogiikka;

import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.data.Buffer;
import net.beadsproject.beads.ugens.Envelope;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.ugens.WavePlayer;

/**
 * Tämä luokka on itse äänilähde. Kun Syntetisaattorisovellus-olio luodaan,
 * saa se samalla 3 oskillaattoria. Oskillaattorilla on 5 eri aaltomuotoa
 * (ks. Aallonmuoto-enumi).Jokainen oskillaattori tuottaa 
 * signaalia omalle gainilleen, joka lähettyy eteenpäin Syntetisaattorisovellus-
 * olion master-gainille. Master-gain taas jatkaa matkaa AudioContextiin,
 * joka tulkitsee signaalin väreilynä ja passaa eteenpäin Javan audio-outputille
 * ja lopulta esim. headphone-jackiin tai kaiuttimeen. 
 * 
 */

public class Oskillaattori {

    private AudioContext ac;
    private WavePlayer aalto;
    private Gain g;
    private String kuori;

    public Oskillaattori(AudioContext ac, String nuotti) {
        this.ac = ac;
        this.aalto = new WavePlayer(ac, Nuotti.valueOf(nuotti).getTaajuus(), Buffer.SINE);
        this.g = new Gain(ac, 1, 1);
        this.kuori = kuori;
        this.g.addInput(this.aalto);
        poisPaalta();
    }

    public Oskillaattori(AudioContext ac) {
        this.ac = ac;
        this.aalto = new WavePlayer(ac, Nuotti.valueOf("C").getTaajuus(), Buffer.SINE);
        this.g = new Gain(ac, 1, 1);
        this.kuori = kuori;
        this.g.addInput(this.aalto);
        poisPaalta();
    }

    public Oskillaattori(AudioContext ac, String nuotti, Buffer buffer) {
        this.ac = ac;
        this.aalto = new WavePlayer(ac, Nuotti.valueOf(nuotti).getTaajuus(), buffer);
        this.g = new Gain(ac, 1, 1);
        this.kuori = kuori;
        this.g.addInput(this.aalto);
        poisPaalta();
    }

    /**
     * Tämä metodi palauttaa oskillaattori-olioon liittyvän gain-olion
     * @return oskillaattori-olioon liittyvä gain-olio 
     */
    public Gain getGain() {
        return this.g;
    }

    /**
     * Metodi asettaa oskillaattorin lähettämään signaalia parametrin "nuotti"-
     * käskemällä taajuudella (nuotti-merkkijono muutetaan Nuotti-enumin avulla
     * int-muotoiseksi taajuudeksi)
     * @param nuotti 
     */
    public void asetaNuotti(String nuotti) {
        int taajuus = Nuotti.valueOf(nuotti).getTaajuus();
        this.aalto.setFrequency(taajuus);
    }

    public void asetaAaltomuoto(Buffer buffer) {
        this.aalto.setBuffer(buffer);
    }

    public void lisaaAanenvoimakkuutta() {
        this.g.setGain(this.g.getGain() + 1);
    }

    public void laskeAanenvoimakkuutta() {
        this.g.setGain(this.g.getGain() - 1);
        if (this.g.getGain() < 0) {
            this.g.setGain(0);
        }
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

    public boolean onPaalla() {
        return !this.g.isPaused();
    }

    public void poisPaalta() {
        this.g.pause(true);
    }

    public void paalle() {
        this.g.pause(false);
    }
    
    public void onOffKytkin() {
        if(onPaalla()) {
            poisPaalta();
        } else {
            paalle();
        }
    }

}

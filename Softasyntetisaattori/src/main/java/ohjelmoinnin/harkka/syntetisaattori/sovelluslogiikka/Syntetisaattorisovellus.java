package ohjelmoinnin.harkka.syntetisaattori.sovelluslogiikka;

import java.util.ArrayList;
import java.util.List;
import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.data.Buffer;
import net.beadsproject.beads.ugens.Envelope;
import net.beadsproject.beads.ugens.Gain;

/**
 * Varsinainen syntetisaattorin hallinnointiluokka. Tällä asetetaan
 * oskillaattoreille kulloinenkin nuotti ja käsketään niitä soittamaan se.
 * Oskillaattori lähettää Syntetisaattorisovelluksen master-gain-oliolle
 * signaalin, joka välittyy AudioContextille. Soitto()-metodi avaa
 * AudioContextin, joka kääntää tämän edelleen Javan output-implementaatiolle
 * joka viimein muuttaa signaalin sähkövirraksi kuulokeulostulossa tai esim.
 * ulkoisessa äänikortissa (ja tämä sähkövirta väreilee elementeissä jolloin
 * syntyy ääntä). 
 * 
 */
public class Syntetisaattorisovellus {

    private Envelope kuori;
    private AudioContext ac;
    private List<Oskillaattori> oskillaattorit;
    private Gain master;

    /**
     * Luokan konstruktori.
     * @param ac 
     */
    public Syntetisaattorisovellus(AudioContext ac) {
        this.ac = ac;
        this.kuori = new Envelope(ac, 500);
        this.oskillaattorit = new ArrayList();
        this.master = new Gain(ac, 1, 1);
        lisaaOskillaattorit();
        alustaAudioContextiinGainit();
    }

    /**
     * Lisää sovellukseen kolme oskillaattoria siniaalloilla.
     */
    public void lisaaOskillaattorit() {
        this.oskillaattorit.add(new Oskillaattori(ac, "C", Buffer.SINE));
        this.oskillaattorit.add(new Oskillaattori(ac, "C", Buffer.SINE));
        this.oskillaattorit.add(new Oskillaattori(ac, "C", Buffer.SINE));
    }

    /**
     * Asettaa nuotti-parametrin määrittämän nuotin kaikille. Nuotti on
     * merkkijono ja kääntyy int-taajuudeksi (ks. Nuotti-enum).
     * @param nuotti 
     */
    public void asetaNuottiKaikille(String nuotti) {
        for (int i = 0; i < this.oskillaattorit.size(); i++) {
            this.oskillaattorit.get(i).asetaNuotti(nuotti);
        }
    }
    
    private void alustaAudioContextiinGainit() {
        oskillaattoriInputitMasteriin();
        masterGainAudioContextiin();
    }
    
    private void oskillaattoriInputitMasteriin() {
        for (int i = 0; i < this.oskillaattorit.size(); i++) {
            Gain g = this.oskillaattorit.get(i).getGain();
            this.master.addInput(g);
        }
    }
    
    private void masterGainAudioContextiin() {
        this.ac.out.addInput(this.master);
    }

    /**
     * Hakee indeksillä 0-2 oskillaattorin sovelluksen oskillaattoreista.
     * @param numero indeksinumero
     * @return palauttaa oskillaattorin indeksillä 0-2.
     */
    public Oskillaattori haeOskillaattoriIndeksilla(int numero) {
        return this.oskillaattorit.get(numero);
    }

    /**
     * Avaa AudioContext-olion jolloin kuulokeliitännästä pitäisi
     * kuulua ääntä (olettaen, että oskillaattorit ovat päällä).
     * @throws Exception 
     */
    public void soitto() throws Exception {
        ac.start();
//        Thread.sleep(3000);
//        ac.stop();
    }
    
    /**
     * Hiljentää äänen ulostulon.
     * @throws Exception 
     */
    public void hiljenna() throws Exception {
        ac.stop();
    }

    public Envelope getKuori() {
        return kuori;
    }

    public AudioContext getAc() {
        return ac;
    }

    public List<Oskillaattori> getOskillaattorit() {
        return oskillaattorit;
    }

    public Gain getMaster() {
        return master;
    }

}

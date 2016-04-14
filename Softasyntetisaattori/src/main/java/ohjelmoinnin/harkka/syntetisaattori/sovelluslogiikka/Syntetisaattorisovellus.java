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

    public Syntetisaattorisovellus(AudioContext ac) {
        this.ac = ac;
        this.kuori = new Envelope(ac, 500);
        this.oskillaattorit = new ArrayList();
        this.master = new Gain(ac, 1, 1);
        this.lisaaOskillaattorit();
    }

    public void lisaaOskillaattorit() {
        this.oskillaattorit.add(new Oskillaattori(ac, "C", Buffer.SQUARE));
        this.oskillaattorit.add(new Oskillaattori(ac, "C", Buffer.NOISE));
        this.oskillaattorit.add(new Oskillaattori(ac, "C", Buffer.SINE));
    }

    public void asetaNuottiKaikille(String nuotti) {
        for (int i = 0; i < this.oskillaattorit.size(); i++) {
            this.oskillaattorit.get(i).asetaNuotti(nuotti);
        }
    }
    
    

    public void soita() throws Exception {
        for (int i = 0; i < this.oskillaattorit.size(); i++) {
            Gain g = this.oskillaattorit.get(i).getGain();
            this.master.addInput(g);
        }

        //      this.kuori.addSegment(1000, 1000);
        ac.out.addInput(this.master);
        soitto();
    }

    public Oskillaattori haeOskillaattoriIndeksillä(int numero) {
        return this.oskillaattorit.get(numero);
    }

    public void soitto() throws Exception {
        ac.start();
        Thread.sleep(1000);
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

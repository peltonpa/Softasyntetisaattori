package Sovelluslogiikka;

import java.util.ArrayList;
import java.util.List;
import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.data.Buffer;
import net.beadsproject.beads.ugens.Envelope;
import net.beadsproject.beads.ugens.Gain;

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
        this.oskillaattorit.add(new Oskillaattori(ac, "C", Buffer.SQUARE));
    }
    
    public void lisaaOskillaattori(Buffer buffer, String nuotti) {
        this.oskillaattorit.add(new Oskillaattori(ac, nuotti, buffer));
    }
    
    public void asetaNuottiKaikille(String nuotti) {
        for(int i = 0; i < this.oskillaattorit.size(); i++) {
            this.oskillaattorit.get(i).asetaNuotti(nuotti);
        }
    }

    public void soita() throws Exception {
        for (int i = 0; i < this.oskillaattorit.size(); i++) {
            Gain g = this.oskillaattorit.get(i).getGain();
            this.master.addInput(g);
        }

        this.kuori.addSegment(1000, 1000);
        ac.out.addInput(this.master);
        soitaKaikkiOskillaattorit();
        ac.start();
        Thread.sleep(2000);
        ac.stop();
    }

    public void soitaKaikkiOskillaattorit() {
        for (int i = 0; i < this.oskillaattorit.size(); i++) {
            this.oskillaattorit.get(i).soi();
        }
    }
}

package Sovelluslogiikka;

import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.data.Buffer;
import net.beadsproject.beads.ugens.Envelope;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.ugens.WavePlayer;

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
    }

    public Oskillaattori(AudioContext ac, String nuotti, Buffer buffer) {
        this.ac = ac;
        this.aalto = new WavePlayer(ac, Nuotti.valueOf(nuotti).getTaajuus(), buffer);
        this.g = new Gain(ac, 1, 1);
        this.kuori = kuori;
    }
    
    public Gain getGain() {
        return this.g;
    }

    public void soi() {
        this.g.addInput(aalto);
    }
    
    public void asetaNuotti(String nuotti) {
        int taajuus = Nuotti.valueOf(nuotti).getTaajuus();
        this.aalto.setFrequency(taajuus);
    }

    public void asetaAaltomuoto(Buffer buffer) {
        this.aalto.setBuffer(buffer);
    }

}

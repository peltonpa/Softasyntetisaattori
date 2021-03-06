package ohjelmoinnin.harkka.syntetisaattori.softasyntetisaattori;

import ohjelmoinnin.harkka.syntetisaattori.ohjain.Kontrolleri;
import ohjelmoinnin.harkka.syntetisaattori.gui.Gui;
import java.util.Scanner;
import javax.swing.JFrame;
import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.ugens.Noise;
import ohjelmoinnin.harkka.syntetisaattori.sovelluslogiikka.Syntetisaattorisovellus;
/**
 * Main-luokka. Luo Syntetisaattorisovellus-olion ja käynnistää graafisen
 * käyttöliittymän.
 * 
 */
public class Main {

    /**
     * Käynnistää ohjelman.
     * @param args Argumentit
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        //Tekstikayttoliittyma tl = new Tekstikayttoliittyma(new Scanner(System.in));
        //tl.kaynnista();
        Syntetisaattorisovellus syna = new Syntetisaattorisovellus(new AudioContext());
        
        JFrame jf = new JFrame();
        Gui gui = new Gui(syna, jf);
        
        jf.add(gui);
        jf.addKeyListener(new Kontrolleri(syna));
        jf.pack();
        jf.setVisible(true);
        jf.requestFocus();
    }

}

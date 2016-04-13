package ohjelmoinnin.harkka.syntetisaattori.softasyntetisaattori;

import ohjelmoinnin.harkka.syntetisaattori.kayttoliittyma.Tekstikayttoliittyma;
import ohjelmoinnin.harkka.syntetisaattori.gui.Gui;
import java.util.Scanner;
import javax.swing.JFrame;
import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.ugens.Noise;
import ohjelmoinnin.harkka.syntetisaattori.sovelluslogiikka.Syntetisaattorisovellus;

public class Main {

    public static void main(String[] args) throws Exception {
        //Tekstikayttoliittyma tl = new Tekstikayttoliittyma(new Scanner(System.in));
        //tl.kaynnista();
        Syntetisaattorisovellus syna = new Syntetisaattorisovellus(new AudioContext());
        Gui gui = new Gui(syna);
        JFrame jf = new JFrame();
        
        jf.add(gui);
        jf.pack();
        jf.setVisible(true);
    }

}

package ohjelmoinnin.harkka.syntetisaattori.softasyntetisaattori;

import Kayttoliittyma.*;
import java.util.Scanner;
import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.ugens.Noise;

public class Main {

    public static void main(String[] args) throws Exception {
        Tekstikayttoliittyma tl = new Tekstikayttoliittyma(new Scanner(System.in));
        tl.kaynnista();
    }

}

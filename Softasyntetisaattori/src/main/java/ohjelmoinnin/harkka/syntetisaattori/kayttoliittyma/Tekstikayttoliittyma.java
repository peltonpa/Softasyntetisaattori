package ohjelmoinnin.harkka.syntetisaattori.kayttoliittyma;

import ohjelmoinnin.harkka.syntetisaattori.sovelluslogiikka.Syntetisaattorisovellus;
import ohjelmoinnin.harkka.syntetisaattori.sovelluslogiikka.Nuotti;
import ohjelmoinnin.harkka.syntetisaattori.sovelluslogiikka.Aallonmuoto;
import java.util.Scanner;
import net.beadsproject.beads.core.AudioContext;
import ohjelmoinnin.harkka.syntetisaattori.ohjaus.Kontrolleri;

public class Tekstikayttoliittyma {

    private Scanner lukija;

    public Tekstikayttoliittyma(Scanner lukija) {
        this.lukija = lukija;
    }

    public void kaynnista() throws Exception {
        System.out.println("Okei elikkäs tässä sitä vaan käytellään syntikkaa venaas");
        AudioContext ac = new AudioContext();
        Syntetisaattorisovellus syna = new Syntetisaattorisovellus(ac);
        while (true) {
            System.out.println("Jos haluat lisätä oskillaattoreita, syötä 1, jos haluat soittaa syötä mitä tahansa muuta");
            int syote = Integer.parseInt(lukija.nextLine());
            if (syote == 1) {
                uusiOskillaattori(syna);
            } else {
                break;
            }
        }
        Kontrolleri kontrolleri = new Kontrolleri(syna);
        //huom, ei toimi vielä
        System.out.println("Paina näppäimistöltä C, D, E, F, G, B tai A soittaaksesi nuotin.");
   
        //System.out.println("jooh elikkäs kiitos käytöstä");
    }

    public void uusiOskillaattori(Syntetisaattorisovellus syna) {
        while (true) {
            System.out.println("Valitse aaltomuoto (waveform) oskillaattorille. Vaihtoehdot: "
                    + "KANTTI, SAHA, KOLMIO, SINI, KOHINA");
            String aalto = lukija.nextLine();
            if (oikeaAaltomuoto(aalto)) {
                syna.lisaaOskillaattori(Aallonmuoto.valueOf(aalto).getAalto(), "C");
                break;
            }
        }
    }

    public void asetaNuotti(Syntetisaattorisovellus syna) {
        while (true) {
            System.out.println("Valitse jokin seuraavista nuoteista: C, D, E, F, G, B tai A");
            String nuotti = lukija.nextLine();
            if (oikeaNuotti(nuotti)) {
                syna.asetaNuottiKaikille(nuotti);
                break;
            } else {
                System.out.println("Annoit vääränlaisen nuotin.");
            }
        }
    }

    public Boolean oikeaNuotti(String nuotti) {
        for (Nuotti n : Nuotti.values()) {
            if (n.toString().equals(nuotti)) {
                return true;
            }
        }
        return false;
    }

    public Boolean oikeaAaltomuoto(String aalto) {
        for (Aallonmuoto a : Aallonmuoto.values()) {
            if (a.toString().equals(aalto)) {
                return true;
            }
        }
        return false;
    }
}

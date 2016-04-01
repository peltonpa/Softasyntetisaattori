package Kayttoliittyma;

import Sovelluslogiikka.*;
import java.util.Scanner;
import net.beadsproject.beads.core.AudioContext;

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
            System.out.println("gaini:");
            System.out.println(syna.getOskillaattorit().get(0).getGain().getGain());
            System.out.println("Paina 3 lisätäksesi uuden äänilähteen (defaulttina on jo yksi), Paina 2 asettaaksesi nuotin, 1 soittaaksesi tämänhetkisen nuotin, 0 lopettaaksesi koko paskan");
            int komento = Integer.parseInt(lukija.nextLine());
            if (komento == 0) {
                ac.stop();
                break;
            } else if (komento == 1) {
                syna.soita();
            } else if (komento == 2) {
                asetaNuotti(syna);
            } else if (komento == 3) {
                uusiOskillaattori(syna);
            } else {
                System.out.println("Annoit väärän komennon");
            }
        }
        System.out.println("jooh elikkäs kiitos käytöstä");
    }
    
    public void uusiOskillaattori(Syntetisaattorisovellus syna) {
        while(true) {
            System.out.println("Valitse aaltomuoto (waveform) oskillaattorille. Vaihtoehdot: "
                    + "KANTTI, SAHA, KOLMIO, SINI, KOHINA");
            String aalto = lukija.nextLine();
            if(oikeaAaltomuoto(aalto)) {
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
        for(Aallonmuoto a : Aallonmuoto.values()) {
            if(a.toString().equals(aalto)) {
                return true;
            }
        }
        return false;
    }
}

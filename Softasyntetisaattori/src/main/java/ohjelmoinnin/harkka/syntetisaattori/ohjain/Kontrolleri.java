package ohjelmoinnin.harkka.syntetisaattori.ohjain;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import ohjelmoinnin.harkka.syntetisaattori.sovelluslogiikka.Syntetisaattorisovellus;

public class Kontrolleri implements KeyListener {

    private Syntetisaattorisovellus syna;

    /**
     * Luokan konstruktori.
     * @param syna 
     */
    public Kontrolleri(Syntetisaattorisovellus syna) {
        this.syna = syna;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        try {
            System.out.println("juu");
        } catch (Exception ee) {
            System.out.println("buu");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("kirjain: " + e.getKeyChar());
        try {
            String soitettava = "" + e.getKeyChar();
            soitettava.toUpperCase();
            String nuotti = soitettava(soitettava);
            if (!nuotti.equals("EI")) {
                syna.asetaNuottiKaikille(nuotti);
            }
            syna.soitto();
        } catch (Exception ee) {
            throw new UnsupportedOperationException("Kontrollerin KeyPressed ei skulaa");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        try {
            this.syna.hiljenna();
        } catch (Exception ee) {
            throw new UnsupportedOperationException("Ei voitu hiljentää");
        }
    }

    /**
     * Vaihtaa painetun näppäimen nuotti-merkkijonoksi.
     * @param nuotti painettu näppäin, joka muutetaan nuotiksi
     * @return soitettava nuotti
     */
    public String soitettava(String nuotti) {
        if (nuotti.equals("A")) {
            return "C";
        }
        if (nuotti.equals("S")) {
            return "D";
        }
        if (nuotti.equals("D")) {
            return "E";
        }
        if (nuotti.equals("F")) {
            return "F";
        }
        if (nuotti.equals("G")) {
            return "G";
        }
        if (nuotti.equals("H")) {
            return "A";
        }
        if (nuotti.equals("J")) {
            return "B";
        }
        if (nuotti.equals("W")) {
            return "C#";
        }
        if (nuotti.equals("E")) {
            return "D#";
        }
        if (nuotti.equals("T")) {
            return "F#";
        }
        if (nuotti.equals("Y")) {
            return "G#";
        }
        if (nuotti.equals("U")) {
            return "A#";
        }
        return "EI";
    }

}

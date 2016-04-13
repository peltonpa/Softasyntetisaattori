package ohjelmoinnin.harkka.syntetisaattori.ohjaus;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import ohjelmoinnin.harkka.syntetisaattori.sovelluslogiikka.Nuotti;
import ohjelmoinnin.harkka.syntetisaattori.sovelluslogiikka.Syntetisaattorisovellus;

//luokka vielä kesken, tarkoitus ohjata graafista käyttöliittymää näppäimistöllä
public class Kontrolleri implements KeyListener {

    private Syntetisaattorisovellus syna;

    public Kontrolleri(Syntetisaattorisovellus syna) {
        this.syna = syna;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        String kirjain = "" + Character.toUpperCase(e.getKeyChar());
        if (oikeaNuotti(kirjain)) {
            try {
                System.out.println("jeee");
                syna.asetaNuottiKaikille(kirjain);
                syna.soita();
            } catch (Exception ee) {
                System.out.println("Virhe kontrollerin keyPressed-metodissa");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("wululuu");
        try {
            //syna.lopeta();
        } catch (Exception ee) {
            System.out.println("Virhe Kontrolleri-luokan keyReleased-metodissa");
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

}

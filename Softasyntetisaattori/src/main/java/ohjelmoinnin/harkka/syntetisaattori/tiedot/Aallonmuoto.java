package ohjelmoinnin.harkka.syntetisaattori.tiedot;

import net.beadsproject.beads.data.Buffer;

/**
 * Tämän enum-luokan avulla muutetaan suomenkielisiä aaltomuotoja Beadsin
 * Buffereiksi.
 */
public enum Aallonmuoto {

    KANTTI(Buffer.SQUARE), KOLMIO(Buffer.TRIANGLE), SINI(Buffer.SINE), KOHINA(Buffer.NOISE), SAHA(Buffer.SAW);

    private Buffer aalto;

    private Aallonmuoto(Buffer buffer) {
        this.aalto = buffer;
    }

    public Buffer getAalto() {
        return this.aalto;
    }
}

package ohjelmoinnin.harkka.syntetisaattori.sovelluslogiikka;

public enum Nuotti {

    C(262), D(294), E(330), F(349), G(392), A(440), B(494);

    private int taajuus;

    private Nuotti(int taajuus) {
        this.taajuus = taajuus;
    }

    public int getTaajuus() {
        return this.taajuus;
    }

}

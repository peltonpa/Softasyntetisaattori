package ohjelmoinnin.harkka.syntetisaattori.tiedot;

/**
 * Tämä enum-luokka muuttaa kirjaimia (CDEFGBA) taajuuksiksi, joilla kyseiset
 * nuotit soivat. Tarkoitus on aina hakea tällä enumilla perustaajuus, ja 
 * sitten kertoa tai jakaa tätä taajuutta kahdella oskillaattorin kulloisenkin
 * oktaavin perusteella.
 */

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

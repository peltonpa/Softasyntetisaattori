package ohjelmoinnin.harkka.syntetisaattori.sovelluslogiikka;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import ohjelmoinnin.harkka.syntetisaattori.sovelluslogiikka.Oskillaattori;
import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.data.Buffer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class OskillaattoriTest {

    private AudioContext ac = new AudioContext();

    public OskillaattoriTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void konstruktoriAsettaaOikeanBufferin() {
        Oskillaattori osc = new Oskillaattori(ac, "C", Buffer.SAW);

        assertEquals(osc.getAalto().getBuffer(), Buffer.SAW);
    }

    @Test
    public void konstruktoriAsettaaOikeanNuotin() {
        Oskillaattori osc = new Oskillaattori(ac, "G", Buffer.SAW);

        assertEquals(osc.getAalto().getFrequency(), 784f, 1);
    }

    @Test
    public void asetaNuottiAsettaaOikeanTaajuuden() {
        Oskillaattori osc = new Oskillaattori(ac, "C", Buffer.SINE);
        osc.asetaNuotti("F");

        assertEquals(osc.getAalto().getFrequency(), 699f, 1);
    }

    @Test
    public void asetaGainToimii() {
        Oskillaattori osc = new Oskillaattori(ac, "C", Buffer.SINE);
        osc.asetaGain(0.5f);

        assertEquals(osc.getGain().getGain(), 0.5f, 0);
    }

    @Test
    public void gainiaEiVoiAsettaaAlleNollan() {
        Oskillaattori osc = new Oskillaattori(ac, "C", Buffer.SINE);
        osc.asetaGain(-1f);

        assertEquals(osc.getGain().getGain(), 0f, 0);
    }

    @Test
    public void gainiEiVoiYlittaaYhta() {
        Oskillaattori osc = new Oskillaattori(ac, "C", Buffer.SINE);
        osc.asetaGain(200f);

        assertEquals(osc.getGain().getGain(), 1f, 0);
    }

    @Test
    public void hiljennaOskillaattoriToimii() {
        Oskillaattori osc = new Oskillaattori(ac, "C", Buffer.SINE);
        osc.asetaGain(1f);
        osc.poisPaalta();

        assertEquals(osc.onPaalla(), false);
    }

    @Test
    public void OnOffKytkinToimii() {
        Oskillaattori osc = new Oskillaattori(ac, "C", Buffer.SINE);
        osc.onOffKytkin();

        assertEquals(osc.onPaalla(), true);
    }

    @Test
    public void poisPaaltaToimii() {
        Oskillaattori osc = new Oskillaattori(ac, "C", Buffer.SINE);
        osc.onOffKytkin();
        osc.poisPaalta();

        assertEquals(osc.onPaalla(), false);
    }

    @Test
    public void alussaOskillaattoriPoisPaalta() {
        Oskillaattori osc = new Oskillaattori(ac, "C", Buffer.SINE);

        assertEquals(osc.onPaalla(), false);
    }

    @Test
    public void oskillaattorillaOnAalto() {
        Oskillaattori osc = new Oskillaattori(ac, "C", Buffer.SINE);

        assertNotNull(osc.getAalto());
    }

    @Test
    public void oktaavinNostoToimiiEikaYlitaViitta() {
        Oskillaattori osc = new Oskillaattori(ac, "C", Buffer.SINE);
        osc.nostaOktaavia();

        assertEquals(osc.getOktaavi(), 5);

        osc.nostaOktaavia();

        assertEquals(osc.getOktaavi(), 5);

    }

    @Test
    public void oktaavinLaskuToimiiEikaLaskeNollaan() {
        Oskillaattori osc = new Oskillaattori(ac, "C", Buffer.SINE);
        osc.laskeOktaavia();
        osc.laskeOktaavia();
        osc.laskeOktaavia();
        osc.laskeOktaavia();
        osc.laskeOktaavia();
        
        assertEquals(osc.getOktaavi(), 1);
    }
    
    @Test 
    public void wavePlayerOnKytkettyGainiin() {
        Oskillaattori osc = new Oskillaattori(ac, "C", Buffer.SINE);
        assertEquals(osc.getGain().getNumberOfConnectedUGens(0), 1); 
    }

    @Test
    public void bufferinVaihtoToimii() {
         Oskillaattori osc = new Oskillaattori(ac, "C", Buffer.SINE);
         osc.asetaAaltomuoto(Buffer.SAW);
         
         assertEquals(osc.getAalto().getBuffer(), Buffer.SAW);
    }
    
    @Test
    public void gaininAsetusKeskitasolleToimii() {
         Oskillaattori osc = new Oskillaattori(ac, "C", Buffer.SINE);
         osc.asetaGain(0.5f);
         
         assertEquals(osc.getGain().getGain(), 0.5f, 0);
    }
    
}

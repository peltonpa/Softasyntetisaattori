/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Sovelluslogiikka.Oskillaattori;
import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.data.Buffer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class OskilaattoriTest {
    
    private AudioContext ac = new AudioContext();
    
    public OskilaattoriTest() {
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
        Oskillaattori osc = new Oskillaattori(ac, "G");
        
        assertEquals(osc.getAalto().getFrequency(), 392f, 0.1);
    }
    
    @Test
    public void tyhjaNuottiJaBufferiKonstruktorissaAsettaaNuotiksiCJaBufferiksiSiniaallon() {
        Oskillaattori osc = new Oskillaattori(ac);
        
        assertEquals(osc.getAalto().getFrequency(), 262f, 0.1);
        assertEquals(osc.getAalto().getBuffer(), Buffer.SINE);
    }
    
    @Test
    public void asetaNuottiAsettaaOikeanTaajuuden() {
        Oskillaattori osc = new Oskillaattori(ac, "C");
        osc.asetaNuotti("F");
        
        assertEquals(osc.getAalto().getFrequency(), 349f, 0.1);
    }
    
    @Test
    public void lisaaAanenvoimakkuuttaToimii() {
        Oskillaattori osc = new Oskillaattori(ac, "C");
        osc.lisaaAanenvoimakkuutta();
        osc.lisaaAanenvoimakkuutta();
        osc.lisaaAanenvoimakkuutta();
        
        assertEquals(osc.getGain().getGain(), 4f, 0.1);
    }
    
    @Test
    public void laskeAanenvoimakkuuttaToimii() {
        Oskillaattori osc = new Oskillaattori(ac);
        osc.lisaaAanenvoimakkuutta();
        osc.laskeAanenvoimakkuutta();
        osc.laskeAanenvoimakkuutta();
        
        assertEquals(osc.getGain().getGain(), 0, 0.1);
    }
    
    @Test
    public void aanenVoimakkuuttaEiVoidaLaskeaNegatiiviseksi() {
        Oskillaattori osc = new Oskillaattori(ac);
        osc.laskeAanenvoimakkuutta();
        osc.laskeAanenvoimakkuutta();
        osc.laskeAanenvoimakkuutta();
        
        assertEquals(osc.getGain().getGain(), 0, 0.1);
    }
    
    @Test
    public void hiljennaOskillaattoriToimii() {
        Oskillaattori osc = new Oskillaattori(ac);
        osc.lisaaAanenvoimakkuutta();
        osc.lisaaAanenvoimakkuutta();
        osc.lisaaAanenvoimakkuutta();
        osc.hiljennaOskillaattori();
        
        assertEquals(osc.getGain().getGain(), 0, 0.1);
    }
    
    
    
}

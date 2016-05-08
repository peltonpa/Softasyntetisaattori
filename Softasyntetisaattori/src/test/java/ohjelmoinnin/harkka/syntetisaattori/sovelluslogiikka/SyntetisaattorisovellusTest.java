package ohjelmoinnin.harkka.syntetisaattori.sovelluslogiikka;



import java.util.HashMap;
import java.util.Map;
import ohjelmoinnin.harkka.syntetisaattori.sovelluslogiikka.Syntetisaattorisovellus;
import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.data.Buffer;
import ohjelmoinnin.harkka.syntetisaattori.tiedot.Taajuudet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class SyntetisaattorisovellusTest {
    
    AudioContext ac = new AudioContext();
    
    public SyntetisaattorisovellusTest() {
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
   public void konstruktoriLisaaKaikkiKolmeOskillaattoria() {
       Syntetisaattorisovellus syna = new Syntetisaattorisovellus(ac);
 
       assertEquals(syna.getOskillaattorit().size(), 3);
   }
   
   @Test
   public void konstruktoriInitialisoiOskillaattorienGainitMasteriinJaMasterinAudioContextiin() {
       Syntetisaattorisovellus syna = new Syntetisaattorisovellus(ac);
       
       assertEquals(syna.getMaster().getNumberOfConnectedUGens(0), 3);
   }
   
   @Test 
   public void asetaNuottiKaikilleAsettaaKaikilleSamanNuotin() {
       Syntetisaattorisovellus syna = new Syntetisaattorisovellus(ac);
       syna.asetaNuottiKaikille("D");
       
       assertEquals(syna.getOskillaattorit().get(0).getAalto().getFrequency(), 587.2f, 1);
       assertEquals(syna.getOskillaattorit().get(1).getAalto().getFrequency(), 587.2f, 1);
       assertEquals(syna.getOskillaattorit().get(2).getAalto().getFrequency(), 587.2f, 1);
   }
   
   @Test
   public void haeOskillaattoriIndeksillaToimii() {
       Syntetisaattorisovellus syna = new Syntetisaattorisovellus(ac);
       Oskillaattori osc = syna.haeOskillaattoriIndeksilla(2);
       
       assertNotNull(osc);
   }
   
   @Test
   public void soittoAvaaAcn() throws Exception {
       Syntetisaattorisovellus syna = new Syntetisaattorisovellus(ac);
       syna.soitto();
       
       assertEquals(ac.isRunning(), true);
   }
   
      @Test
   public void hiljennaSulkeeAcn() throws Exception {
       Syntetisaattorisovellus syna = new Syntetisaattorisovellus(ac);
       syna.soitto();
       syna.hiljenna();
       
       assertEquals(ac.isRunning(), false);
   }
   
   @Test
   public void setMasterGainToimiiKeskivaiheilla() {
       Syntetisaattorisovellus syna = new Syntetisaattorisovellus(ac);
       syna.setMasterGain(0.3f);
       
       assertEquals(syna.getMaster().getGain(), 0.3f, 0);
   }
   
   @Test
   public void setMasterGainEiYlitaYhta() {
       Syntetisaattorisovellus syna = new Syntetisaattorisovellus(ac);
       syna.setMasterGain(2f);
       
       assertEquals(syna.getMaster().getGain(), 1f, 0);
   }
   
   @Test
   public void masterGainEiLaskeAlleNollan() {
       Syntetisaattorisovellus syna = new Syntetisaattorisovellus(ac);
       syna.setMasterGain(-3f);
       
       assertEquals(syna.getMaster().getGain(), 0f, 0);
   }
   
}

package ohjelmoinnin.harkka.syntetisaattori.sovelluslogiikka;



import ohjelmoinnin.harkka.syntetisaattori.sovelluslogiikka.Syntetisaattorisovellus;
import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.data.Buffer;
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
       
       assertEquals(syna.getOskillaattorit().get(0).getAalto().getFrequency(), 294f, 0.1);
       assertEquals(syna.getOskillaattorit().get(1).getAalto().getFrequency(), 294f, 0.1);
       assertEquals(syna.getOskillaattorit().get(2).getAalto().getFrequency(), 294f, 0.1);
   }
   
   @Test
   public void haeOskillaattoriIndeksillaToimii() {
       Syntetisaattorisovellus syna = new Syntetisaattorisovellus(ac);
       Oskillaattori osc = syna.haeOskillaattoriIndeksilla(2);
       
       assertNotNull(osc);
   }
   
}

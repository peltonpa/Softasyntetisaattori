

import Sovelluslogiikka.Syntetisaattorisovellus;
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
   public void konstruktoriLisaaYhdenOskillaattorin() {
       Syntetisaattorisovellus syna = new Syntetisaattorisovellus(ac);
       
       assertEquals(syna.getOskillaattorit().size(), 1);
   }
   
   @Test
   public void oskillaattorinLisaysLisaaOskillaattorinListaanSyntetisaattorisovellukseen() {
       Syntetisaattorisovellus syna = new Syntetisaattorisovellus(ac);
       syna.lisaaOskillaattori(Buffer.SINE, "C");
       syna.lisaaOskillaattori(Buffer.SAW, "F");
       
       assertEquals(syna.getOskillaattorit().size(), 3);
   }
   
   @Test 
   public void asetaNuottiKaikilleAsettaaKaikilleSamanNuotin() {
       Syntetisaattorisovellus syna = new Syntetisaattorisovellus(ac);
       syna.lisaaOskillaattori(Buffer.SINE, "F");
       syna.lisaaOskillaattori(Buffer.SINE, "G");
       syna.asetaNuottiKaikille("D");
       
       assertEquals(syna.getOskillaattorit().get(0).getAalto().getFrequency(), 294f, 0.1);
       assertEquals(syna.getOskillaattorit().get(1).getAalto().getFrequency(), 294f, 0.1);
       assertEquals(syna.getOskillaattorit().get(2).getAalto().getFrequency(), 294f, 0.1);
   }
   
}

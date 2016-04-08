Alla on nähtävissä ohjelman alustava suunniteltu rakenne luokkakaaviona:

![Luokkakaavio](/alustava_luokkakaavio.png)

Luokat Gain, MasterGain ja WavePlayer ovat Beads-kirjaston luokkia, joita Syntetisaattorisovellus-luokka hallinnoi. Oma luokka Oskillaattori hallinnoi yhtä äänilähdettä (WavePlayer) ja ohjaa sen tuotoksen yhteen Gain-olioon, jotka kaikki ohjautuvat MasterGain-olioon, joka vuorostaan siirtyy Syntetisaattorisovellus-luokalle, joka tulostaa äänen ulos. 

Kuviteltu yksinkertainen esimerkki ohjelman toiminnasta olisi siis seuraavanlainen: käyttäjä käynnistää ohjelman, Syntetisaattorisovellus luo pari oskillaattoria Oskillaattori-luokasta. Oskillaattorit luovat kukin WavePlayer-olion, jotka tuottavat datana aaltoliikettä oskillaattorin yksittäisiin Gain-olioihin. Nämä Gain-oliot ohjautuvat MasterGainille, joka ohjautuu (tai sijaitsee) syntetisaattorisovellukselle/sovelluksessa. MasterGain tulostaa aaltodatan Beadsin AudioContext-luokalle joka antaa sen edelleen Javan äänioutputille, joka ohjaa signaalin esim. äänen ulostuloporttiin jossa aaltoliike muutetaan digitaalisesta analogiseksi sähkövirraksi joka voi soida kuulokkeista tai kaiuttimista. 

Myöhemmin kontrollia tullaan lisäämään Syntetisaattorisovellus-luokan avulla. Beads-kirjastot (.jar-tiedostoja) sijaitsevat Softasyntetisaattori/src/resources-hakemistossa.

Käyttäjät: kuka tahansa asiasta kiinnostunut

Kaikkien käyttäjien toiminnot:
  * Ohjelman käynnistys
  * Soittaminen
    * kuuluu ääntä jos kaiutin on päällä ja painaa oikeaa näppäintä
  * Ohjelman sulkeminen

Apuna käytetään Javalle tehtyä [Beads-kirjastoa](http://www.beadsproject.net/ "Beads").

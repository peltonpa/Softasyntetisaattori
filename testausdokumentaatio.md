Mitä en testannut automaattisesti:

Itse ääntä on tosi hankala JUnitin kautta testata, en tiedä olisiko joku tosi monimutkainen algoritmiplugini olemassa joka pystyisi tulkita että ulostuleva ääni todella on oikealla taajuudella. Siksi monet testit jäävät aika absraktille tasolle ja äänten ja nuottien toimivuus on testattu kuuntelemalla. Näitä on testattu käsin laskemalla oktaavia ylös ja alas vuorotellen ja soittamalla kaikkia nuotteja sekaisin ja mitään virheitä ei ole havaittu. Nuottien perustaajuudet generoidaan (osin kovakoodatusti) Taajuudet.java-luokassa, josta ne haetaan hashmapista.

Tiettyjä Beadsin kanssa toimivia yhteyksiä on saavutettavaan hyötyyn nähden aivan järjetöntä testata (esim. AudioContextiin ja Gaineihin liittyviä asioita) ja oletan että Beads toimii. Osaa näistä ominaisuuksista siis on testattu, osasta Pit vääntää mutantteja joita en saanut tapettua, mutta ominaisuudet kuitenkin toimivat ohjelmaa testatessa (esim. jos AudioContext ei toimisi, ei mitään ääntä kuuluisi). 


Bugit: 

-Jos nuotteja soittaa liian nopeasti (melko hidaskin tahti tuntuu riittävän...) tai monta päällekkäin, raportoi konsoli poikkeuksia joissain threadeissa. Ilmeisesti Beadsin AudioContext on sen verran hidas tai ohjelmani toteutus niin kömpelö että se ei ehdi hoitaa hommiaan Javan Audio IO:n kanssa. Tätä bugia olen yrittänyt korjata lisäämällä Thread.sleep()-komentoja sinne tänne, lopputulemana päätin jättää Kontrollerin KeyPressediin Thread.sleep(20) alkuun joka vähentää ainakin fiilispohjalta hiukan noita erroreita, toisaalta jos säiettä nukuttaa kauemmin rupeaa ohjelman käyttö olemaan liian verkkaista. Voi olla että koko ohjelman rakenteen tulisi olisi toisenlainen jottei kyseistä ongelmaa tulisi. Saattaa myös olla että ongelman saisi hoidettua kirjoittamalla yli metodeja Beadsin AudioContextissa, mutten lähtenyt sille tielle.

-Visuaalinen bugi, kun hiirtä liikuttelee koskettimien päällä, saattavat tummat koskettimet välillä joutua valkoisten alle. Koitettu vaikka millä päällekkäisillä focus-käskyillä eliminoida tuloksetta.

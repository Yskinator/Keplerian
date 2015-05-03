#Tuntikirjanpito

##13.3
* 45 min: projektin luominen edellisestä projektista tuttuun tapaan. 
* 1h: fysiikkamoottorin tarkemman rakenteen pohtimista. Johtopäätös: lisää taustatutkimusta tarvitaan. Yksinkertainen fysiikkasimulaatio on toki helppo tehdä, mutta aikaisemmat ratkaisuni eivät ole olleet kovinkaan elegantteja, ja Keplerin lakeihin pohjautuvassa simulaatiossa voimien ja impulssien merkitys jää pienemmäksi.

##27.3
* 1h: Netbeans ongelmien ratkomista. Tyhjät lähdekoodikansiot eivät siirtyneet gitin mukana, ja niiden uudelleen luominen Netbeansin kautta on yllättävän hankalaa.
* 4h: Fysiikkamoottorin implementoimista. Matikka toteutetaan 3D:nä, koska Keplerin lait ovat valmiiksi kolmessa ulottuvuudessa, eikä liene vaivan arvoista siirtää niitä kahdeen ulottuvuuteen - mikään kun ei estä pyörittämästä kappaleita samalla tasolla käyttäen samaista 3D matikkaa. Lisäksi jotkin operaatiot kuten vektoreiden ristitulo vaativat kolme ulottuvuutta, joten totesin parhaaksi vain käyttää vektoreita joiden z koordinaatti on 0.

##28.3
* 2-3h: Dokumentaation parantelua, pienten ongelmien siivoamista, pyörimiselle tuki ja kiertorataelementtien laskennan viimeistelyä.

##29.3
* 1h: Lisäoperaatioita vektoreille, korjauksia kaavoihin. 
 
Toistaiseksi kolme kuudesta elementistä lasketaan oikein. Koska tarkoituksena on toimia kahdessa ulottuvuudessa, kaikki kiertoradat ovat päiväntasaajan tasolla. Ikävä kyllä tämä on erikoistapaus, jossa noodi vektori n ei ole määritelty. Koska tätä käytetään longitude of the ascending noden ja argument of the periapsisin laskemiseen, näidenkin arvot saavat vanhan kunnon NaNin, minkä lisäksi true anomaly saa muuten vain väärän arvon oletettavasti kaavassa tekemäni virheen takia. Eli siis, vaihtoehtoisia kaavoja ja lisää säätämistä tarvitaan, mutta hyvin lähellä toimivaa olaan.

##6.4
* 30min: Aloitin pelimoottoria/käyttöliittymää ollessani nettiyhteyden ulottumattomissa.
* 1h: Testejä TwoBodySolverille.

##7.4
* 30min: Lisää testejä.

Elegantti ratkaisu usean eri kiertoradan testaukseen: testi xOrbit(), jossa x on kiertoradan tyyppi, kutsuu testOrbit(kiertoradanNumero) metodia, joka puolestaan kutsuu jokaisen kiertorataparametrin testiä yksi kerrallaan. Ikävä kyllä JUnit lopettaa testin ensimmäiseen "väärin" laskettuun parametriin, mikä tällä hetkellä on mikä tahansa parametri, johtuen lievästä heitosta saadun tuloksen ja mallina käyttämäni "oikean" arvon välillä - ilmeisesti 5 desimaalia on liikaa vaadittu. Lyhyesti sanottuna, kaunis koodini ei toimi, joka kiertorata tarvitsee sittenkin 6 kpl copy-paste testiä.

##8.4
* 30min: Lisäsin erikoiskäsittelyn joka huolehtii tapauksista, joissa noodivektori ei ole määritelty, eli käytännössä päiväntasaajan tasolla kulkevat kiertoradat, eli siis kaikki aikomamme 2d-käyttöliittymän kiertoradat.

##13.4
* 30min: Muokkasin TwoBodySolverin testejä siten, että jokainen kiertorataparametri testataan erikseen.
* NaN min: Vector3d:n isUndefined() metodi etsi NaN arvon saaneita vektorin komponentteja yhtäsuuruusoperaattorin '==' avulla, mutta NaN != NaN. Vaihdoin tilalle Double.isNaN():in.

##14.4
* 20min: Kävi ilmi, että olin koko ajan testeissäni verrannut asteita radiaaneihin ja ihmetellyt, mikä menee pieleen. Korjasin tämän, sekä lievensin testien tarkkuusvaatimuksia.
* 25min: Korjasin kahden viimeisen väärin lasketun kiertorataparametrin kaavat. Kaikki parametrit lasketaan nyt oikein.
* 30min: Lisäsin kiertoradan ennustamisen, sekä ajan kiertoratoihin. Aikaisemman kiertoradan avulla voidaan nyt laskea uudet parametrit mille tahansa ajanhetkelle.

##17.4
* 30min: Testejä Vector3d:lle.

Seuraavaksi joko kalastelen pisteitä piirtämällä (lopultakin) luokkakaavion, tai sitten implementoin sijainnin laskennan. Näyttäisi siltä, että joudun laskemaan numeerisesti true anomalyn mean anomalyn avulla, ja sen avulla sitten itse sijainnin ja nopeus vektorin.

Todettakoon tässä vaiheessa, että kiertorataparametrit ovat itselleni täysin uusi tuttavuus. Suurin haaste projektissa lieneekin nykyiset fysiikan taitoni ylittävien kaavojen omaksuminen.

##27.4

Alle 24h aikaa demoon, operaatio loppukiri:
* Laske true anomaly mean anomalysta numeerisesti.
* Implementoi sijainnin/nopeuden laskenta.
* Tee kiertoratasimulaatio: aurinko ja planeetta joka kiertää aurinkoa. Eli siis pelilooppi ja peliframework käyttöön.
* Tee käyttöliittymä; yksinkertainen ikkuna jossa simulaatio näkyy.

Aikaa aivan liian vähän, tehtävää aivan liian paljon. Challenge accepted!

while(!done)
{
    keepCoding();
}
eat();
sleep();

* ~9h(?): Kaikki yllämainittu on nyt toteutettu, mutta sijainti lasketaan väärin. Pahasti väärin. Yritän vielä hetken tutkia löydänkö vikaa. Jos en, menen nukkumaan ja katson aamulla uudestaan.


##28.4
* ~3h: Sijainnin laskenta korjattu. Nopeuden laskenta implementoitu. Käyttöliittymään lisätty pikaisesti ajan nopeutus/hidastus/suunnan muuttaminen ja zoomaus. Yhteensopivuus linux läppärin kanssa varmistettu.

Täten julistan operaatio loppukirin onnistuneeksi. Ohjelma osaa nyt simuloida kiertorataa, joten demossa on jotain näytettävää. Toki lopullista palautusta varten on vielä paljon tehtävää, käyttöliittymä kun on todella pikaisesti kokoon kyhätty ja muutenkin fysiikkamoottoria lukuun ottamatta ohjelma hieman puutteellinen, mutta demottavaa on, mikä on aikaisempaan nähden suuri parannus.

##3.5
* 1h: Loppubyrokratia.

Kuvauksessa antamani tavoitteet olivat

* Laske sijainti ajan ja kiertorataparamentrien avulla.
* Laske kiertorata sijainnin ja nopeuden avulla.
* Nopeuta, hidasta, tai käännä kokonaan ajan suunta.
* Selviä siirtymästä kappaleen vaikutuspiiristä toiseen.
* Ennusta ja piirrä kappaleen rataa.
* Mahdollisesti yksinkertainen käänny/kiihdytä raketti testausta varten.

Näistä kaikki paitsi käänny/kiihdytä raketti sekä vaikutuspiiristä toiseen siirtyminen saavutettiin, ja raketin toteuttaminen olisi ollut suhteellisen yksinkertaista. Kaiken kaikkiaan voin olla melko tyytyväinen. Ohjelman keplerian.physics osio on varsin mallikkaasti toteutettu, käyttäen hyväksi itselleni ennestään täysin tuntematonta matikkaa. Muuhun osaan ohjelmasta en ole täysin tyytyväinen - suunnitelmana oli uusio koko käyttöliittymä osio, mutta minkäs sille enää tässä vaiheessa mahtaa.
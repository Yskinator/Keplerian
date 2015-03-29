#Tuntikirjanpito

##13.3
* 45 min: projektin luominen edellisestä projektista tuttuun tapaan. 
* 1h: fysiikkamoottorin tarkemman rakenteen pohtimista. Johtopäätös: lisää taustatutkimusta tarvitaan. Yksinkertainen fysiikkasimulaatio on toki helppo tehdä, mutta aikaisemmat ratkaisuni eivät ole olleet kovinkaan elegantteja, ja Keplerin lakeihin pohjautuvassa simulaatiossa voimien ja impulssien merkitys jää pienemmäksi.

##27.3
* 1h: Netbeans ongelmien ratkomista. Tyhjät lähdekoodikansiot eivät siirtyneet gitin mukana, ja niiden uudelleen luominen Netbeansin kautta on yllättävän hankalaa.
* 4h: Fysiikkamoottorin implementoimista. Matikka toteutetaan 3D:nä, koska Keplerin lait ovat valmiiksi kolmessa ulottuvuudessa, eikä liene vaivan arvoista siirtää niitä kahdeen ulottuvuuteen - mikään kun ei estä pyörittämästä kappaleita samalla tasolla käyttäen samaista 3D matikkaa. Lisäksi jotkin operaatiot kuten vektoreiden ristitulo vaativat kolme ulottuvuutta, joten totesin parhaaksi vain käyttää vektoreita joiden z koordinaatti on 0.
* 

##28.3
* 2-3h: Dokumentaation parantelua, pienten ongelmien siivoamista, pyörimiselle tuki ja kiertorataelementtien laskennan viimeistelyä.

##29.3
* 1h: Lisäoperaatioita vektoreille, korjauksia kaavoihin. 
 
Toistaiseksi kolme kuudesta elementistä lasketaan oikein. Koska tarkoituksena on toimia kahdessa ulottuvuudessa, kaikki kiertoradat ovat päiväntasaajan tasolla. Ikävä kyllä tämä on erikoistapaus, jossa noodi vektori n ei ole määritelty. Koska tätä käytetään longitude of the ascending noden ja argument of the periapsisin laskemiseen, näidenkin arvot saavat vanhan kunnon NaNin, minkä lisäksi true anomaly saa muuten vain väärän arvon oletettavasti kaavassa tekemäni virheen takia. Eli siis, vaihtoehtoisia kaavoja ja lisää säätämistä tarvitaan, mutta hyvin lähellä toimivaa olaan.

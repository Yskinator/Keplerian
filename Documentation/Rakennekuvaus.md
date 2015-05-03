#Rakenne

##keplerian.physics

RigidBody kuvaa fyysist‰ kappaletta - se pit‰‰ sis‰ll‰‰n kaikki kappaleen fyysiset ominaisuudet, kuten sijainnin ja kappaleen sen hetkisen kiertoradan.

CelestialBody on erikoistapaus RigidBodysta - RigidBody voi olla mik‰ vain kappale, CelestialBody vain jotain, jota jokin muu voi kiert‰‰.

TwoBodySolverin teht‰v‰ on pyˆritell‰ kaavoja. Se laskee nopeuden ja sijainnin avulla kiertoradan, tai kiertoradan ja ajan avulla nopeuden tai sijainnin.

Orbit kuvaa kiertorataa. Se pit‰‰ sis‰ll‰‰n kiertorataparametrit, sek‰ niiden laskenta-ajan: t‰m‰n avulla voidaan laskea uusi kiertorata mill‰ tahansa ajanhetkell‰, ja siit‰ kappaleen senhetkinen nopeus.

Vector3d on yksinkertainen kolmiulotteinen double-tarkkuudella toimiva vektori-implementaatio. Arvelin nopeammaksi tehd‰ oman kuin etsi‰ valmiin.

###Miten toimivat yhdess‰:
* GameFrameworkin puolella Entity pit‰‰ sis‰ll‰‰n RigidBodyn, mik‰ kertoo kaiken mit‰ kappaleen fyysisist‰ ominaisuuksista pit‰‰ tiet‰‰. RigidBody voi olla tai olla olematta CelestialBody - vain toinen RigidBody v‰litt‰‰ miettiess‰‰n, voiko kappaletta kiert‰‰. 
* RigidBody tiet‰‰ kappaleen kiertoradan, joka on luonnolisestikin Orbit instanssi. 
* Aina kun kiertorata halutaan luoda tai siit‰ halutaan ulos hyˆdyllist‰ tietoa, k‰ytet‰‰n TwoBodySolveria.
* Vector3d:t‰ k‰ytet‰‰n fysiikassa.

##keplerian.gameFramework

Entity kuvaa mit‰ tahansa oliota. Se pit‰‰ sis‰ll‰‰n kaikki sen mahdollisesti sis‰lt‰m‰t ominaisuudet - sit‰ esitt‰v‰n kuvan nimen, sen RigidBodyn, sek‰ itse ohjelman generoiman kuvan kiertoratojen tapauksessa.

UserInterface on interface jonka kautta Scene kommunikoi k‰yttˆliittym‰n kanssa.

GUI on UserInterface, joka sattuu olemaan graafinen k‰yttˆliittym‰, toteutettu JSFML kirjaston avulla.

Scene on yksi simulaatiossa esiintyv‰ "ruutu", kuten menu tai pelin ainoa scene eli kiertoratasimulaatio.

GUIScene on Scene joka k‰yttˆˆ GUI UserInterfacenaan.

PlanetAndSunScene on GUIScene, joka lopulta n‰kyy ruudulla.

OrbitPlotterin ainoa teht‰v‰ on piirt‰‰ kiertorata.

##keplerian

Main luokka. Sis‰lt‰‰ peliloopin.
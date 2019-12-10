# Vaatimusmäärittely

## Johdanto

Rahoitusmarkkinat perustuvat oletukseen tehokkaista markkinoista. Tämä tarkoittaa sitä, että esimerkiksi kaikkien osakkeiden hinnat heijastaisivat täsmällisesti kaikkea kyseisen osakkeen hintaan liittyvää julkista tietoa. Toisin sanottuna osake olisi aina juuri niin hyvin hinnoiteltu, kuin se olemassa olevan julkisen informaation mukaan voi olla. Tutkitusti näin ei kuitenkaan aina ole vaan syntyy poikkeustilanteita, joita kutsutaan anomalioiksi. Hyvä ja napakka selitys anomalialle taloustieteessä löytyy esimerkiksi [wikipediasta](https://fi.wikipedia.org/wiki/Anomalia_(taloustiede)).

Anomalialla tarkoitetaan tilannetta, jossa ilman selkeää rationaalista syytä osakkeen hinnanvaihelussa toistuu jokin säännönmukaisuus, jota hyödyntämällä on mahdollisuus ylituottoon. Esimerkkinä anomalioista mainittakoon vaikka kuunvaihdeilmiö, jolla tarkoitetaan karkesti sitä, että kuukauden lopussa osakkeet ovat hieman halvempia, kuin kuukauden alussa. Aalto-yliopiston rahoituksen professori Matti Suominen avaa kuunvaihdeilmiötä Ylen pörssipäivän 29.1.2019 julkaistussa jaksossa Ajoittamisen taito ajassa 40:18. Jakso on kuuneltavissa [täältä](https://areena.yle.fi/1-50044492#autoplay=true).

Pörssinoteeratut rahastot (usein käytetään englanninkielistä lyhennettä ETF) ovat rahastoja, joiden osuuksilla voidaan käydä kauppaa pörssissä. ETF-rahastot siis omistavat osakkeita jonkin suunnitelman mukaisesti ja sijoittaja voi ostaa osuuksia rahastoista pörssin aukioloaikoina. Useat Suomessa toimivat osakevälittäjät tarjoavat kuukausisäästösopimuksia, joiden pohjalta palveluntarjoaja ostaa aina tiettynä päivänä asiakkaalle hänen määrittämänsä määrän haluamiaan ETF-rahastoa päivän markkinahintaan. Esimerkiksi Nordnetilla kauppapäivä on yleensä kuun viides päivä.

## Sovelluksen tarkoitus

Sovelluksen tarkoituksena on tarjota käyttäjälle helppokäyttöinen käyttöliitymä ETF-rahastojen pörssikurssien tarkasteluun yli ajan. Sovelluksessa on mahdollista hakea eri ETF-rahastojen avaushintoja esimerkiksi tiettyinä viikonpäivinä tai kuukausina ja vertailla näitä keskenään.

## Perusversion tarjoama toiminnallisuus

Sovellukseen on tallennettu määrättyjen ETF-rahastojen kurssitiedot .csv mudossa. Graafisen käyttöliittymän kautta käyttäjä pystyy muun muassa:

- valitsemaan vertailuun haluamansta ETF-rahastot **(Tehty)**
- valitsemaan vertailuun haluamansa ajanjaksot **(Tehty)**
  - (esim. kuukauden kolmen ensimmäisen päivän keskiarvo vs. kuukauden kolmen viimeisen päivän keskiarvo)
- tulostamaan sekä numeerisen, että graafisen esityksen haluamistaan vertailuista **(Tehty siten, että tulostaa numeerisen vertailun, graafinen tässä päivityksessä telakalla)**

## Jatkokehitysideoita

Ajan sallimissa rajoissa sovellusta voidaan laajentaa seuraavilla ominaisuuksilla

- pääkäyttäjä / peruskäyttäjä
- tietojen tallentaminen tietokantaan
- vertailujen tallentaminen
- Uuden kurssidatan hakeminen automaattisesti
- Uusien vertailtavien rahastojen lisääminen käyttöliittymän kautta
- Tilastolliset mallit vertailun tukena

## Disclaimer

Sovellus on tietojenkäsittelytieteiden harjoitustyö ja tavoitteena on harjoitella datankäsittelyä Javalla. Sovellus **ei ole** sijoitusneuvontaa, eikä sitä tule käyttää sijoituspäätösten tukena.

# Arkkitehtuuri

## Rakenne

Ohjelman rakenne koostuu kolmesta elementistä, käyttöliittymästä (ui), controllerista (controller), joka käsittelee käyttöliittymän kautta tehdyt haut ja malleista (models), jotka käsittävät datan parsimiseen tarvittavat työkalut, sekä merkittävimmät suoritsaikaiset datarakenteet.

<img src="https://github.com/Haimis/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/pakkauskaavio.png">

## Käyttöliittymä

Käyttöliittymä on yksisivuinen JavaFX:llä toteutettu käyttöliittymä. Käyttöliitymä koostuu kahdesta liukusäätimestä, valintaruuduista ja napista. Ohjelma tulostaa tekstiä suoraan perusnäkymään.

## Sovelluslogiikka

Käynnistettäessä sovellus lataa .csv tiedostoista tarvittavan kurssidatan ohjelman muistiin ja muuntaa ne päiväkohtaisista kurssitiedoista (DataPoint) Etf-mallisiksi tietorakenteiksi (Etf). Käyttöliittymä mukautuu sen myötä, mitkä tietorakenteet ladattiin onnistuneesti.

<img src="https://github.com/Haimis/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/datanLataaminen.png">

Käyttäjän luodessa vertailun välittää käyttöliittymä kontrollerille parametrit halutuista vertailuajanjaksoista, sekä vertailtavista Etfistä. Kontrolleri luo väliaikaistietorakenteen (SumTable), joka ylläpitää tietoa kuukaudensisäisistä vertailuarvoista. Kun kontrolleri on käynyt kaikki halutut rahastot jokaisen datapisteen osalta läpi palauttaa se tekstimuotoisen tulosteen käyttöliittymälle. Tuloste pitää sisällään vertailun tulokset.

<img src="https://github.com/Haimis/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/vertailu.png">

## Ohjelman rakenteelliset heikkoudet

- Rakenne ei ole mitenkään erityisen hienostunut ja refaktorointi saattaa olla haastavaa.
- Jotkut metodeista kutsuvat toisiaan ilman parametrejä, mikä tekee erityistapauksien (corner cases) testaamisesta hankalaa.
- Käyttöliittymän tarjoama datan esitysmuoto on hyvin suppea verrattuna taustalla olevaan laskentaan.
- Käyttöliittymä muodostuu käytännössä yhdestä pitkästä metodista.

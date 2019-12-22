# Testausdokumentti

Ohjeman testaus on tapahutnut pääasiallisesti automatisoiduin JUnit testein. JUnit testit kohdistuvat logics ja models pakkauksiin. Käyttöliittymää ei ole testattu automatisoidusti vaan pelkästään manuaalisesti.

## Testikattavuus

<img src="https://github.com/Haimis/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/testikattavuus.png">

Sovelluksen testikattavuusraportista käy ilmi testien käsittelemät koodirivit.
- Models paketin osalta testit eivät tarkasta epäonnistuneen datan lataamista. Tämä on tarkastettu manuaalisesti ja huomattu toimivaksi.
- Logics paketin osalta testit eivät kata kaikkia if-lauseiden haarautumia.

Testi on toteutettu kohtuullisesti ja ne testaavat sekä yksittäis- että monimutkaisempia integraatiotapauksia. Eriliaisia virheellisiä syötteitä tulisi kuitenkin testata vielä huomattavasti enemmän.

## Sovelluksen bugit
- Käyttäjän valitessa aikajakson loppupäivän ennen alkupäivää sovellus ei anna erillistä virheilmoitusta
- Sama toistuu, mikäli yhtäkään Etfää ei valita tarkasteluun
- Mikäli datan lataaminen tiedostosta ei onnistu, tulee virheilmoitus konsoliin

# Kurssilaskuri

Kurssilaskurilla voi verrata muutamine tunnettujen ETF-rahastojen päivän avauskursseja. Sovellusta voi käyttää kuukauden sisällä tapahtuvien anomaliaoiden etsimiseen. Voit syöttää laskurille kaksi vertailuajanjaksoa (esim. kuukauden ensimmäinen päivä ja koko kuukausi) ja valita haluamasi rahastot mukaan vertailuun. Sovellus vertailee kuukausitasolla rahaston päivittäisiä avaushintoja ja palauttaa kuukausittaisten hintaerojen keskiarvon. Esimerkkitapauksessamme siis teidon siitä, kuinka paljon edullisempiä tai kalliimpia valitut rahastot ovat aina kuun ensimmäisenä päivän, verrattuna kuukauden keskiarvoon.

## Dokumentaatio

Käyttöohje

[Vaatimusmäärittely](https://github.com/Haimis/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuuri](https://github.com/Haimis/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

Testausdokumentti

[Tuntikirjanpito](https://github.com/Haimis/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

## Releaset

Viikko 7

## Komentorivitoiminnot

### Ohjelman käynnistäminen

Ohjelma käynnistetään juurihakemistosta komennolla

```
mvn compile exec:java -Dexec.mainClass=kurssilaskuri.ui.Main
```

### Testaus

Testi ajetaan juurihakemistossa komennolla

```
mvn test
```

Testikattavuusraportti luodaan juurihakemistossa komennolla

```
mvn jacoco:report
```
Teskikattavuusraportti generoituu tiedostoon _/target/site/jacoco/index.html_ ja sitä voi tarkastella selaimella

### Checkstyle

Checkstyle-raportti luodaan juurihakemistossa komennolla

```
mvn jxr:jxr checkstyle:checkstyle
```
Checkstyle-raportti generoituu tiedostoon _/target/site/checkstyle.html_ ja sitä voi tarkastella selaimella

### jarin generointi

jar paketti luodaan komennolla 

```
mvn package
```
Paketti generoituu tiedostoon _/target/_ nimellä kurssilaskuri-1.0-SNAPSHOT.jar. Ohjelma käynnistyy, mutta **datan lukeminen ei jar paketissa toimi**

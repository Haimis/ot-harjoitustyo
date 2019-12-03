# Kurssilaskuri

Kurssilaskurilla voi verrata Euro Stoxx 50 UCITS ETF:n päivän avauskursseja. Toistaiseksi sovelluksella voi verrata kuukauden viiden ensimmäisen päivän keskiavauskurssia haluamansa kuukauden päivien keskiavauskurssiin syöttämällä ajanjakson alkupäivän ja loppupäivän niille varattuihin kenttiin graafisessa käyttöliittymässä. Laskenta suoritetaan painamalla nappia laske.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/Haimis/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuuri](https://github.com/Haimis/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Tuntikirjanpito](https://github.com/Haimis/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

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
Paketti generoituu tiedostoon _/target/_ nimellä kurssilaskuri-1.0-SNAPSHOT.jar. Ohjelma käynnistyy, mutta **datan lukeminen ei toistaiseksi toimi**

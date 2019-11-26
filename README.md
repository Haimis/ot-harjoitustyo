# Kurssilaskuri

Kurssilaskurilla voi verrata Euro Stoxx 50 UCITS ETF:n päivän avauskursseja. Toistaiseksi sovelluksella voi verrata kuukauden viiden ensimmäisen päivän keskiavauskurssia haluamansa kuukauden päivien keskiavauskurssiin syöttämällä ajanjakson alkupäivän ja loppupäivän.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/Haimis/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Tuntikirjanpito](https://github.com/Haimis/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

## Komentorivitoiminnot

### Ohjelman käynnistäminen

Ohjelma käynnistetään juurihakemistosta komennolla

```
mvn compile exec:java -Dexec.mainClass=kurssilaskuri.Ui
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

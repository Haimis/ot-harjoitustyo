#Arkkitehtuuri

## Pakkauskaavio

![pakkauskaavio](https://raw.githubusercontent.com/Haimis/ot-harjoitustyo/master/dokumentaatio/pakkauskaavio.png)

## Toiminnallisuudet

### Datan lataaminen käyttöön

Kun ohjelma käynnistetään luo käyttöliittymä uuden coursesService luokan, joka puolestaan luo uuden dataParser luokan ja pyytää sitä palauttamaan Listan datasta. DataParser lukee sille kovakoodatut csv. tiedostot ja paluttaa datan listana coursesService luokalle. Käyttöliittymän kautta voi nyt tehdä hakuja datasta.

![datan lataaminen](https://github.com/Haimis/ot-harjoitustyo/blob/master/dokumentaatio/datanLataaminen.png)

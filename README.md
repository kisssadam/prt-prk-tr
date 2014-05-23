Tanulmányi Rendszer
===================

Ez a program egy olyan primitív tanulmányi rendszert valósít meg, ami három nagyobb részre van bontva.
Ezek a részek a következőek:
* Hallgató
* Oktató
* Tanulmányi osztály

Mind a három résznek saját feladatai vannak, amit el tudnak végezni.

A tanulmányi osztály feladatai
------------------------------
* Szakok létrehozása.
* Hallgatók hozzáadása.
* Oktatók hozzáadása.
* Aktív félév meghirdetése.
* Tantárgy létrehozása, amit később meg lehet hirdetni.
* Tantárgyak meghirdetése a jelenlegi félévre.
* Gyakorlati csoportok hozzáadása egy meghirdetett tantárgyhoz.

Az oktatók feladatai
--------------------
* Aláírás beírása egy általa oktatott tantárgyra.
* Vizsgaidőpontok meghirdetése.
* Érdemjegy beírása a hallgatóknak.

A hallgatók feladatai
---------------------
* Beiratkozás a jelenleg meghirdetett félévre.
* Tantárgy felvétele gyakorlati csoporttal együtt.
* Vizsgajelentkezés.

Az alapértelmezett felhasználó adatai
--------------------------------------
Ezzel a felhasználóval el tudjuk érni a tanulmányi osztály, és innen kiindulva tudjuk használatba venni a programot.
* Felhasználónév: admin
* Jelszó: admin

Adatbázissal kapcsolatos dolgok
-------------------------------
* [Adatbázist inicializáló sql fájl.](tanulmanyi_rendszer.sql)
* Az adatbázis séma: ![A kép nem érhető el](https://raw.githubusercontent.com/kisssandoradam/prt-prk-tr/master/adatbazis_sema.png)


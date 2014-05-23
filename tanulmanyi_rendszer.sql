drop table prt_meghird_tant_gyak_csop;
drop table prt_hallgato_felvett_tantargy;
drop table prt_felvett_tantargy;
drop table prt_gyakorlati_csoport;
drop table prt_oktatott_tantargyak;
drop table prt_hallgato_felvett_vizsgai;
drop table prt_vizsga;
drop table prt_meghirdetett_tantargy;
drop table prt_hallgato;
drop table prt_felev;
drop table prt_tantargy_elofeltetelei;
drop table prt_tantargy;
drop table prt_tanulmanyi_osztaly;
drop table prt_szak;
drop table prt_oktato;

create table prt_felev (
  id number(5),
  szorg_eleje date,
  szorg_vege date,
  vizsg_eleje date,
  vizsg_vege date,
  aktualis_felev number(1),
  constraint pk_prt_felev primary key (id)
);

create table prt_szak (
  id number(5),
  nev varchar2(100),
  szint char(3),
  constraint pk_prt_szak primary key (id)
);

create table prt_tantargy (
  id number(5),
  tantargykod varchar2(20),
  nev varchar2(100),
  kredit number(3),
  szak_id number(5),
  constraint pk_prt_tantargy primary key (id),
  constraint fk_prt_szak foreign key (szak_id) references prt_szak
);

create table prt_tantargy_elofeltetelei (
  tantargy_id number(5),
  elofeltetel_id number(5),
  constraint fk_prt_tantargy foreign key (tantargy_id) references prt_tantargy,
  constraint fk_prt_elofeltetel foreign key (elofeltetel_id) references prt_tantargy
);

create table prt_tanulmanyi_osztaly (
  id number(5),
  vezeteknev varchar2(100),
  keresztnev varchar2(100),
  felhasznalonev varchar2(100),
  jelszo varchar2(100),
  szuletesnap date,
  constraint pk_prt_to primary key (id)
);

create table prt_oktato (
  id number(5),
  vezeteknev varchar2(100),
  keresztnev varchar2(100),
  felhasznalonev varchar2(100),
  jelszo varchar2(100),
  szuletesnap date,
  fizetes number(10),
  constraint pk_prt_oktato primary key (id)
);

create table prt_meghirdetett_tantargy (
  id number(5),
  tantargy_id number(5),
  oktato_id number(5),
  aktualis_felev_id number(5),
  eloadas_nap varchar2(20),
  eloadas_ora number(2),
  eloadas_perc number(2),
  eloadas_terem varchar2(100),
  constraint pk_meghirdetett_tantargy primary key (id),
  constraint fk_tantargy foreign key (tantargy_id) references prt_tantargy,
  constraint fk_oktato foreign key (oktato_id) references prt_oktato,
  constraint fk_felev foreign key (aktualis_felev_id) references prt_felev
);

create table prt_oktatott_tantargyak (
  id number(5),
  oktato_id number(5),
  meghirdetett_tantargy_id number(5),
  constraint pk_prt_okt_targy primary key (id),
  constraint fk_okt_targy_oktato foreign key (oktato_id) references prt_oktato,
  constraint fk_mt foreign key (meghirdetett_tantargy_id) references prt_meghirdetett_tantargy
);

create table prt_gyakorlati_csoport (
  id number(5),
  oktato_id number(5),
  terem varchar2(100),
  idopont_nap varchar2(20),
  idopont_ora number(2),
  idopont_perc number(2),
  constraint pk_gyakorlati_csoport primary key (id),
  constraint fk_gyak_csop_oktato foreign key (oktato_id) references prt_oktato
);

create table prt_meghird_tant_gyak_csop (
  id number(5),
  meghirdetett_tantargy_id number(5),
  gyakorlati_csoport_id number(5),
  constraint pk_mtgycs primary key (id),
  constraint fk_mtgycs_mt foreign key (meghirdetett_tantargy_id) references prt_meghirdetett_tantargy,
  constraint fk_mtgycs_gycs foreign key (gyakorlati_csoport_id) references prt_gyakorlati_csoport
);

create table prt_hallgato (
  id number(5),
  vezeteknev varchar2(100),
  keresztnev varchar2(100),
  felhasznalonev varchar2(100),
  jelszo varchar2(100),
  szuletesnap date,
  szak_id number(5),
  aktiv_felev_id number(5),
  constraint pk_hallgato primary key (id),
  constraint fk_hallgato_szak foreign key (szak_id) references prt_szak,
  constraint fk_hallgato_felev foreign key (aktiv_felev_id) references prt_felev
);

create table prt_vizsga (
  id number(5),
  meghirdetett_tantargy_id number(5),
  idopont date,
  terem varchar2(100),
  constraint pk_vizsga primary key (id),
  constraint fk_vizsga_mt foreign key (meghirdetett_tantargy_id) references prt_meghirdetett_tantargy
);

create table prt_hallgato_felvett_vizsgai (
  id number(5),
  vizsga_id number(5),
  hallgato_id number(5),
  constraint pk_prt_hfv primary key (id),
  constraint fk_hfv_vizsga foreign key (vizsga_id) references prt_vizsga,
  constraint fk_hfv_hallgato foreign key (hallgato_id) references prt_hallgato
);

create table prt_felvett_tantargy (
  id number(5),
  meghirdetett_tantargy_id number(5),
  felvett_gyakorlati_csoport_id number(5),
  alairas number(1),
  constraint pk_felvett_tantargy primary key (id),
  constraint fk_ft_mt foreign key (meghirdetett_tantargy_id) references prt_meghirdetett_tantargy,
  constraint fk_ft_fgycs foreign key (felvett_gyakorlati_csoport_id) references prt_gyakorlati_csoport
);

create table prt_hallgato_felvett_tantargy (
  id number(5),
  hallgato_id number(5),
  felvett_tantargy_id number(5),
  constraint pk_hft primary key (id),
  constraint fk_hft_hallgato foreign key (hallgato_id) references prt_hallgato,
  constraint fk_hft_felvett_tantargy foreign key (felvett_tantargy_id) references prt_felvett_tantargy
);

commit;

package tanulmanyiRendszer;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tanulmanyiRendszer.Szak.Szint;

/**
 * A tanulmányi osztályhoz tartozó feladatköröket tartalmazza.
 * @author adam
 *
 */
public class TanulmanyiOsztaly extends Felhasznalo {
	/**
	 * A következő tanulmányi osztály id-je.
	 */
	private static int nextId = 0;
	
	/**
	 * Ennek a segítségével lehet naplóüzeneteket létrehozni.
	 */
	private static Logger logger = LoggerFactory.getLogger(TanulmanyiOsztaly.class);
	
	/**
	 * @param id A tanulmányi osztály id-je.
	 * @param vezetéknév A tanulmányi osztály vezetékneve.
	 * @param keresztnév A tanulmányi osztály keresztneve.
	 * @param felhasználónév A tanulmányi osztály felhasználóneve.
	 * @param jelszó A tanulmányi osztály jelszava.
	 * @param születésnap A tanulmányi osztály születésnapja.
	 */
	public TanulmanyiOsztaly(int id, String vezetéknév, String keresztnév, String felhasználónév, String jelszó, Date születésnap) {
		super(id, vezetéknév, keresztnév, felhasználónév, jelszó, születésnap);
		++nextId;
		logger.trace("Új {} lett példányosítva: {}.", new Object[] {getClass().getName(), this});
	}
	
	/**
	 * @param vezetéknév A tanulmányi osztály vezetékneve.
	 * @param keresztnév A tanulmányi osztály keresztneve.
	 * @param felhasználónév A tanulmányi osztály felhasználóneve.
	 * @param jelszó A tanulmányi osztály jelszava.
	 * @param születésnap A tanulmányi osztály születésnapja.
	 */
	public TanulmanyiOsztaly(String vezetéknév, String keresztnév, String felhasználónév, String jelszó, Date születésnap) {
		this(nextId, vezetéknév, keresztnév, felhasználónév, jelszó, születésnap);
	}

	/**
	 * Automatikusan lezárja az aktuális félévet és beállítja az új félévet.
	 * 
	 * @param aktuálisFélév Az új aktuális félév a Központban.
	 */
	public void setAktuálisFélév(Felev aktuálisFélév) {
		félévLezárása();
		Kozpont.setAktuálisFélév(aktuálisFélév);
		logger.info("Új aktuális félév: {}", aktuálisFélév);
	}

	/**
	 * Hozzáad a központhoz, egy új félévet.
	 * 
	 * @param szorgalmiIdőszak A félév szorgalmi időszakja.
	 * @param vizsgaIdőszak A félév vizsgaidőszakja.
	 * @param legyenAktuálisFélév Ha <code>true</code>, akkor ez lesz
	 * az aktuális félév.
	 * @return Visszaadja a hozzáadott félévet.
	 * @throws TanulmanyiRendszerKivetel Ha nem sikerül felvenni az új félévet.
	 */
	public Felev újFélév(Idoszak szorgalmiIdőszak, Idoszak vizsgaIdőszak, boolean legyenAktuálisFélév)
			throws TanulmanyiRendszerKivetel {
		List<Felev> felevek = Kozpont.getFélévLista();
		Felev felev = new Felev(szorgalmiIdőszak, vizsgaIdőszak);
		if (felevek.contains(felev)) {
			logger.warn("Már van ilyen félév: {}.", felev);
			throw new TanulmanyiRendszerKivetel("Már van ilyen félév!");
		}
		felevek.add(felev);
		logger.info("Új félév lett hozzáadva: {}.", felev);
		if (legyenAktuálisFélév) {
			setAktuálisFélév(felev);
		}
		return felev;
	}

	/**
	 * Hozzáad a központhoz, egy új félévet, de nem lesz ez az
	 * aktuális félév.
	 * 
	 * @param szorgalmiIdőszak A félév szorgalmi időszakja.
	 * @param vizsgaIdőszak A félév vizsgaidőszakja.
	 * @return Visszaadja a hozzáadott félévet.
	 * @throws TanulmanyiRendszerKivetel Ha nem sikerül felvenni az új félévet.
	 */
	public Felev újFélév(Idoszak szorgalmiIdőszak, Idoszak vizsgaIdőszak) throws TanulmanyiRendszerKivetel {
		return újFélév(szorgalmiIdőszak, vizsgaIdőszak, false);
	}

	/**
	 * Lezár egy félévet úgy, hogy az összes hallgató félévét lezárja.
	 * Ha egy hallgatónak lezárunk egy félévet, akkor automatikusan elégtelent
	 * kap arra a vizsgáira, ahova még nincs beírva valamilyen érdemjegy.
	 */
	public void félévLezárása() {
		for (Hallgato hallgató : Kozpont.getHallgatóLista()) {
			hallgató.félévLezárása();
		}
		Kozpont.setAktuálisFélév(null);
		logger.info("Az aktuális félév lezárásra került.");
	}

	/**
	 * Hozzáad egy oktatót a központhoz.
	 * 
	 * @param vezetéknév Az oktató vezetékneve.
	 * @param keresztnév Az oktató keresztneve.
	 * @param felhasználónév Az oktató felhasználóneve.
	 * @param jelszó Az oktató jelszava.
	 * @param születésnap Az oktató születésnapja.
	 * @param fizetés Az oktató fizetése.
	 * @return Visszadja a hozzáadott oktatót.
	 * @throws TanulmanyiRendszerKivetel Ha nem sikerül hozzáadni az oktatót.
	 */
	public Oktato oktatóHozzáadása(String vezetéknév, String keresztnév, String felhasználónév,
			String jelszó, Date születésnap, int fizetés) throws TanulmanyiRendszerKivetel {
		List<Oktato> oktatok = Kozpont.getOktatóLista();

		Oktato oktato = new Oktato(vezetéknév, keresztnév, felhasználónév, jelszó, születésnap, fizetés);
		if (oktatok.contains(oktato)) {
			logger.warn("Már van ilyen oktató: {}", oktato);
			throw new TanulmanyiRendszerKivetel("Már van ilyen oktató!");
		}
		oktatok.add(oktato);
		logger.info("Új oktató lett hozzáadva: {}", oktato);
		return oktato;
	}

	/**
	 * Hozzáad egy szakot a központhoz.
	 * 
	 * @param név A szak neve.
	 * @param szint A szak szintje.
	 * @return Visszaadja a hozzáadott szakot.
	 * @throws TanulmanyiRendszerKivetel Ha nem sikerül hozzáadni a szakot.
	 */
	public Szak szakHozzáadása(String név, Szint szint) throws TanulmanyiRendszerKivetel {
		List<Szak> szakLista = Kozpont.getSzakLista();
		
		Szak szak = new Szak(név, szint);
		if (szakLista.contains(szak)) {
			logger.warn("Már van ilyen {} szak, ezért nem lehet hozzáadni!.", szak);
			throw new TanulmanyiRendszerKivetel("Már van ilyen szak!");
		}
		szakLista.add(szak);
		logger.info("Új szak lett felvéve: {}.", szak);
		return szak;
	}

	/**
	 * Hozzáad egy hallgatót a központhoz.
	 * 
	 * @param vezetéknév A hallgató vezetékneve.
	 * @param keresztnév A hallgató keresztneve.
	 * @param felhasználónév A hallgató felhasználóneve.
	 * @param jelszó A hallgató jelszava.
	 * @param születésnap A hallgató születésnapja.
	 * @param szak A hallgató szakja.
	 * @return Visszaadja a hozzáadott hallgatót.
	 * @throws TanulmanyiRendszerKivetel Ha nem sikerül hozzáadni a hallgatót.
	 */
	public Hallgato hallgatóHozzáadása(String vezetéknév, String keresztnév,
			String felhasználónév, String jelszó, Date születésnap, Szak szak) throws TanulmanyiRendszerKivetel {
		List<Hallgato> hallgatóLista = Kozpont.getHallgatóLista();
		
		Hallgato hallgato = new Hallgato(vezetéknév, keresztnév, felhasználónév, jelszó, születésnap, szak);
		if (hallgatóLista.contains(hallgato)) {
			logger.warn("Már van ilyen hallgató: {}.", hallgato);
			throw new TanulmanyiRendszerKivetel("Már van ilyen hallgató!");
		}
		
		hallgatóLista.add(hallgato);
		logger.info("Új hallgató lett hozzáadva: {}.", hallgato);
		return hallgato;
	}
	
	
	/**
	 * Hozzáad egy tantárgyat a központhoz.
	 * 
	 * @param tantargy Ez a tantárgy lesz hozzáadva a központhoz.
	 * @throws TanulmanyiRendszerKivetel Ha nem sikerül hozzáadni a tantárgyat.
	 */
	public void tantárgyHozzáadása(Tantargy tantargy) throws TanulmanyiRendszerKivetel {
		List<Tantargy> tantargyak = Kozpont.getTantárgyLista();
		
		if (tantargy == null) {
			logger.warn("null-t nem lehet hozzáadni a tantárgyak közé.");
			throw new TanulmanyiRendszerKivetel("null-t nem lehet hozzáadni a tantárgyak közé!");
		} else if (tantargyak.contains(tantargy)) {
			logger.warn("Már van ilyen {} tantárgy, ezért nem lehet hozzáadni!", tantargy);
			throw new TanulmanyiRendszerKivetel("Már van ilyen tantárgy!");
		} else {
			tantargyak.add(tantargy);
			logger.info("Új tantárgy lett hozzáadva: {}.", tantargy);
		}
	}

	/**
	 * Meghirdet egy tantárgyat a paraméterül kapott adatok alapján.
	 * 
	 * @param tantárgy Ez a tantárgy lesz meghirdetve.
	 * @param előadó Ez lesz a tantárgy előadója.
	 * @param aktuálisFélév Ebben a félévben lesz meghirdetve a tantárgy.
	 * @param előadásIdőpont Ebben az időpontban lesz az előadás.
	 * @param előadásTerem Ebben a teremben lesz az előadás.
	 * @return Visszaadja a meghirdetett tantárgyat.
	 * @throws TanulmanyiRendszerKivetel Ha nem sikerül meghirdetni a tárgyat.
	 */
	public MeghirdetettTantargy tantárgyMeghirdetése(Tantargy tantárgy, Oktato előadó,
			Felev aktuálisFélév, Idopont előadásIdőpont, String előadásTerem)
			throws TanulmanyiRendszerKivetel {
		
		MeghirdetettTantargy mt = new MeghirdetettTantargy(
				tantárgy, előadó, aktuálisFélév, előadásIdőpont, előadásTerem);
		
		if (Kozpont.getMeghirdetettTantárgyLista().contains(mt)) {
			logger.warn("Már van ilyen meghirdetett tantárgy: {}.", mt);
			throw new TanulmanyiRendszerKivetel("Már van ilyen meghirdetett tantárgy!");
		}
		Kozpont.getMeghirdetettTantárgyLista().add(mt);
		logger.info("Új tantárgy lett meghirdetve: {}", mt);
		return mt;
	}

	/**
	 * Hozzáad egy gyakorlati csoportot a paraméterül kapott adatok alapján.
	 * 
	 * @param meghirdetettTantargy Ehez a tárgyhoz lesz hozzáadva a gyakorlati csoport.
	 * @param gyakorlatvezető Ez lesz a gyakorlati csoport vezetője.
	 * @param terem Ebben a teremben lesz megtartva a gyakorlati óra.
	 * @param időpont Ebben az időpontban lesz megtartva a gyakorlati óra.
	 * @return Visszaadja a hozzáadott gyakorlati csoportot.
	 * @throws TanulmanyiRendszerKivetel Ha nem sikerül hozzáadni a gyakorlati csoportot.
	 */
	public GyakorlatiCsoport gyakorlatiCsoportHozzáadása(
			MeghirdetettTantargy meghirdetettTantargy, Oktato gyakorlatvezető, String terem,
			Idopont időpont)
			throws TanulmanyiRendszerKivetel {
		List<GyakorlatiCsoport> gyakorlatiCsoportok = meghirdetettTantargy.getGyakorlatiCsoportok();
		
		GyakorlatiCsoport gyakorlatiCsoport = new GyakorlatiCsoport(gyakorlatvezető, terem, időpont);
		if (gyakorlatiCsoportok.contains(gyakorlatiCsoport)) {
			logger.warn("Már van ilyen gyakorlati csoport: {}.", gyakorlatiCsoport);
			throw new TanulmanyiRendszerKivetel("Már van ilyen gyakorlati csoport!");
		}
		gyakorlatiCsoportok.add(gyakorlatiCsoport);
		logger.info("Új gyakorlati csoport lett hozzáadva: {}",gyakorlatiCsoport);
		return gyakorlatiCsoport;
	}
	
}

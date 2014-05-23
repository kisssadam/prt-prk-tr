package tanulmanyiRendszer;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		logger.debug("Új aktuális félév: {}", aktuálisFélév);
	}

	/**
	 * Hozzáad a központhoz, egy új félévet.
	 * 
	 * @param félév Az új félév ami hozzá lesz adva.
	 * @param legyenAktuálisFélév Ha <code>true</code>, akkor ez lesz
	 * az aktuális félév.
	 * @throws TanulmanyiRendszerKivetel Ha nem sikerül felvenni az új félévet.
	 */
	public void újFélév(Felev félév, boolean legyenAktuálisFélév)
			throws TanulmanyiRendszerKivetel {
		újFélév(félév);
		if (legyenAktuálisFélév) {
			Kozpont.setAktuálisFélév(félév);
		}
	}

	/**
	 * Hozzáad a központhoz, egy új félévet, de nem lesz ez az
	 * aktuális félév.
	 * 
	 * @param félév Az új félév ami hozzá lesz adva.
	 * @throws TanulmanyiRendszerKivetel Ha nem sikerül felvenni az új félévet.
	 */
	public void újFélév(Felev félév) throws TanulmanyiRendszerKivetel {
		List<Felev> félévLista = Kozpont.getFélévLista();
		if (félévLista.contains(félév)) {
			logger.warn("Már van ilyen félév meghirdetve: {}.", félév);
			throw new TanulmanyiRendszerKivetel("Már van ilyen félév meghirdetve!");
		} else {
			félévLista.add(félév);
		}
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
	 * Hozzáad egy oktató a központhoz.
	 * 
	 * @param oktató Ez az oktató lesz hozzáadva a központhoz.
	 * @throws TanulmanyiRendszerKivetel Ha nem sikerül hozzáadni a központhoz.
	 */
	public void oktatóHozzáadása(Oktato oktató) throws TanulmanyiRendszerKivetel {
		List<Oktato> oktatóLista = Kozpont.getOktatóLista();

		if (oktatóLista.contains(oktató)) {
			logger.warn("Már van ilyen oktató: {}", oktató);
			throw new TanulmanyiRendszerKivetel("Már van ilyen oktató!");
		} else {
			oktatóLista.add(oktató);
			logger.info("Új oktató lett hozzáadva: {}", oktató);
		}
	}

	/**
	 * Hozzáad egy szakot a központhoz.
	 * 
	 * @param szak Ez a szak lesz hozzáadva a központhoz.
	 * @throws TanulmanyiRendszerKivetel Ha nem sikerül hozzáadni a szakot a központhoz.
	 */
	public void szakHozzáadása(Szak szak) throws TanulmanyiRendszerKivetel {
		List<Szak> szakLista = Kozpont.getSzakLista();
		if (szakLista.contains(szak)) {
			logger.warn("Már van ilyen {} szak, ezért nem lehet hozzáadni!.", szak);
			throw new TanulmanyiRendszerKivetel("Már van ilyen szak!");
		} else {
			szakLista.add(szak);
			logger.info("Új szak lett felvéve: {}.", szak);
		}
	}

	/**
	 * Hozzáad egy hallgatót a központhoz.
	 * 
	 * @param hallgató Ez a hallgatót lesz hozzáadva a központhoz.
	 * @throws TanulmanyiRendszerKivetel Ha nem sikerül hozzáadni a hallgatót a központhoz.
	 */
	public void hallgatóHozzáadása(Hallgato hallgató)
			throws TanulmanyiRendszerKivetel {
		List<Hallgato> hallgatóLista = Kozpont.getHallgatóLista();
		if (hallgatóLista.contains(hallgató)) {
			throw new TanulmanyiRendszerKivetel("Már van ilyen hallgató!");
		} else {
			hallgatóLista.add(hallgató);
		}
	}
	
	
	/**
	 * Hozzáad egy tantárgyat a központhoz.
	 * 
	 * @param tantargy Ez a tantárgy lesz hozzáadva a központhoz.
	 * @throws TanulmanyiRendszerKivetel Ha nem sikerül hozzáadni a tantárgyat.
	 */
	public void tantárgyHozzáadása(Tantargy tantargy) throws TanulmanyiRendszerKivetel {
		List<Tantargy> tantargyak = Kozpont.getTantárgyLista();
		if (tantargyak.contains(tantargy)) {
			logger.warn("Már van ilyen {} tantárgy, ezért nem lehet hozzáadni!", tantargy);
			throw new TanulmanyiRendszerKivetel("Már van ilyen tantárgy!");
		} else {
			tantargyak.add(tantargy);
		}
	}

	/**
	 * Meghirdet egy tantárgyat az aktuális félévben.
	 * 
	 * @param meghirdetettTantárgy Ez a tantárgy lesz a meghirdetett tantárgy.
	 * @throws TanulmanyiRendszerKivetel Ha nem sikerül meghirdetni a tantárgyat.
	 */
	public void tantárgyMeghirdetése(MeghirdetettTantargy meghirdetettTantárgy)
			throws TanulmanyiRendszerKivetel {
		if (Kozpont.getMeghirdetettTantárgyLista().contains(meghirdetettTantárgy)) {
			throw new TanulmanyiRendszerKivetel("Már van ilyen meghirdetett tantárgy!");
		} else {
			Kozpont.getMeghirdetettTantárgyLista().add(meghirdetettTantárgy);
			logger.debug("Új tantárgy lett meghirdetve: {}", meghirdetettTantárgy);
		}
	}

	/**
	 * Hozzáad egy gyakorlati csoportot a paraméterül kapott
	 * meghirdetett tantárgyhoz.
	 * 
	 * @param meghirdetettTantargy Ehez a tantárgyhoz lesz hozzáadva a gyakorlati csoport.
	 * @param gyakorlatiCsoport	Ez a gyakorlati csoport lesz hozzáadva a tantárgyhoz.
	 * @throws TanulmanyiRendszerKivetel Ha nem sikerül a gyakorlati csoport hozzáadása.
	 */
	public void gyakorlatiCsoportHozzáadása(MeghirdetettTantargy meghirdetettTantargy, GyakorlatiCsoport gyakorlatiCsoport)
			throws TanulmanyiRendszerKivetel {
		List<GyakorlatiCsoport> gyakorlatiCsoportok = meghirdetettTantargy.getGyakorlatiCsoportok();
		if (gyakorlatiCsoportok.contains(gyakorlatiCsoport)) {
			throw new TanulmanyiRendszerKivetel("Már van ilyen gyakorlati csoport!");
		} else {
			gyakorlatiCsoportok.add(gyakorlatiCsoport);
			logger.debug("Új gyakorlati csoport lett hozzáadva: {}",gyakorlatiCsoport);
		}
	}
	
}

package tanulmanyiRendszer;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TanulmanyiOsztaly extends Felhasznalo {
	private int id;
	
	private static int nextId = 0;
	
	/**
	 * Ennek a segítségével lehet naplóüzeneteket létrehozni.
	 */
	private static Logger logger = LoggerFactory.getLogger(TanulmanyiOsztaly.class);
	
	public TanulmanyiOsztaly(int id, String vezetéknév, String keresztnév, String felhasználónév, String jelszó, Date születésnap) {
		super(vezetéknév, keresztnév, felhasználónév, jelszó, születésnap);
		this.id = id;
		++nextId;
		logger.trace("Új {} lett példányosítva: {}.", new Object[] {getClass().getName(), this});
	}
	
	public TanulmanyiOsztaly(String vezetéknév, String keresztnév, String felhasználónév, String jelszó, Date születésnap) {
		this(nextId, vezetéknév, keresztnév, felhasználónév, jelszó, születésnap);
	}

	/**
	 * Automatikusan lezárja az aktuális félévet.
	 * 
	 * @param aktuálisFélév
	 */
	public void setAktuálisFélév(Felev aktuálisFélév) {
		félévLezárása();
		Kozpont.setAktuálisFélév(aktuálisFélév);
		logger.debug("Új aktuális félév: {}", aktuálisFélév);
	}

	public void újFélév(Felev félév, boolean legyenAktuálisFélév)
			throws TanulmanyiRendszerKivetel {
		újFélév(félév);
		if (legyenAktuálisFélév) {
			Kozpont.setAktuálisFélév(félév);
		}
	}

	public void újFélév(Felev félév) throws TanulmanyiRendszerKivetel {
		List<Felev> félévLista = Kozpont.getFélévLista();
		if (félévLista.contains(félév)) {
			throw new TanulmanyiRendszerKivetel("Már van ilyen félév meghirdetve!");
		} else {
			félévLista.add(félév);
		}
	}

	public void félévLezárása() {
		for (Hallgato hallgató : Kozpont.getHallgatóLista()) {
			hallgató.félévLezárása();
		}
		Kozpont.setAktuálisFélév(null);
		logger.debug("Az aktuális félév lezárásra került.");
	}

	public void oktatóHozzáadása(Oktato oktató) throws TanulmanyiRendszerKivetel {
		List<Oktato> oktatóLista = Kozpont.getOktatóLista();

		if (oktatóLista.contains(oktató)) {
			logger.warn("Már van ilyen oktató: {}", oktató);
			throw new TanulmanyiRendszerKivetel("Már van ilyen oktató!");
		} else {
			oktatóLista.add(oktató);
			logger.debug("Új oktató lett hozzáadva: {}", oktató);
		}
	}

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

	public void hallgatóHozzáadása(Hallgato hallgató)
			throws TanulmanyiRendszerKivetel {
		List<Hallgato> hallgatóLista = Kozpont.getHallgatóLista();
		if (hallgatóLista.contains(hallgató)) {
			throw new TanulmanyiRendszerKivetel("Már van ilyen hallgató!");
		} else {
			hallgatóLista.add(hallgató);
		}
	}
	
	public void tantárgyHozzáadása(Tantargy tantargy) throws TanulmanyiRendszerKivetel {
		List<Tantargy> tantargyak = Kozpont.getTantárgyLista();
		if (tantargyak.contains(tantargy)) {
			logger.warn("Már van ilyen {} tantárgy, ezért nem lehet hozzáadni!", tantargy);
			throw new TanulmanyiRendszerKivetel("Már van ilyen tantárgy!");
		} else {
			tantargyak.add(tantargy);
		}
	}

	public void tantárgyMeghirdetése(MeghirdetettTantargy meghirdetettTantárgy)
			throws TanulmanyiRendszerKivetel {
		if (Kozpont.getMeghirdetettTantárgyLista().contains(meghirdetettTantárgy)) {
			throw new TanulmanyiRendszerKivetel("Már van ilyen meghirdetett tantárgy!");
		} else {
			Kozpont.getMeghirdetettTantárgyLista().add(meghirdetettTantárgy);
			logger.debug("Új tantárgy lett meghirdetve: {}", meghirdetettTantárgy);
		}
	}

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
	
	public int getId() {
		return id;
	}
	
}

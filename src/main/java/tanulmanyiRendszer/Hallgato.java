package tanulmanyiRendszer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Egy hallgatót reprezentál.
 * @author adam
 *
 */
public class Hallgato extends Felhasznalo {
	/**
	 * A hallgató szakja.
	 */
	private Szak szak;
	
	/**
	 * A hallgató erre a félévre van beiratkozva.
	 */
	private Felev aktívFélév;
	
	/**
	 * Ez a lista tárolja, hogy mely tárgyakat vette már fel a hallgató.
	 */
	private List<FelvettTantargy> felvettTantárgyak;
	
	/**
	 * Ez a lista tárolja, hogy mely vizsgákra jelentkezett a hallgató.
	 */
	private List<FelvettVizsga> felvettVizsgák;
	
	/**
	 * Ennek a segítségével lehet naplóüzeneteket létrehozni.
	 */
	private static Logger logger = LoggerFactory.getLogger(Hallgato.class);

	/**
	 * 
	 * @param vezetéknév A hallgató vezetékneve.
	 * @param keresztnév A hallgató keresztneve.
	 * @param felhasználónév A hallgató felhasználóneve.
	 * @param jelszó A hallgató jelszava.
	 * @param születésnap A hallgató születésnapja.
	 * @param szak A hallgató szakja.
	 */
	public Hallgato(String vezetéknév, String keresztnév,
			String felhasználónév, String jelszó, Date születésnap, Szak szak) {
		super(vezetéknév, keresztnév, felhasználónév, jelszó, születésnap);
		this.szak = szak;
		felvettTantárgyak = new ArrayList<>();
		felvettVizsgák = new ArrayList<>();
		logger.trace("Új {} lett példányosítva: {}.", new Object[] {getClass().getName(), this});
	}

	/**
	 * Visszaadja, hogy az akutálisan meghirdetett félévben mely vizsgákat
	 * vette már fel a hallgató.
	 * 
	 * @return Visszaadja, hogy az akutálisan meghirdetett félévben mely vizsgákat
	 * vette már fel a hallgató.
	 */
	public List<FelvettVizsga> getAktuálisFélévbenFelvettVizsgák() {
		List<FelvettVizsga> aktuálisFélévbenFelvettVizsgák = new ArrayList<>();

		for (FelvettVizsga felvettVizsga : felvettVizsgák) {
			if (felvettVizsga.getVizsga().getMeghirdetettTantargy().getAktuálisFélév().equals(Kozpont.getAktuálisFélév())) {
				aktuálisFélévbenFelvettVizsgák.add(felvettVizsga);
			}
		}

		return aktuálisFélévbenFelvettVizsgák;
	}

	/**
	 * Visszaad egy olyan listát, ami azokat az aktuális félévben
	 * {@link FelvettVizsga}-kat tartalmazza, amelyek a paraméterül
	 * kapott {@link MeghirdetettTantargy}-hoz tartoznak.
	 * 
	 * @param meghirdetettTantargy Ehhez a {@link MeghirdetettTantargy}-hoz tartozó
	 * {@link FelvettVizsga}-kat akarjuk megkapni.
	 * @return Visszaad egy olyan listát, ami azokat az aktuális félévben
	 * {@link FelvettVizsga}-kat tartalmazza, amelyek a paraméterül
	 * kapott {@link MeghirdetettTantargy}-hoz tartoznak.
	 */
	public List<FelvettVizsga> getAktuálisanMeghirdetettTantárgyhozTartozóFelvettVizsgák(MeghirdetettTantargy meghirdetettTantargy) {
		List<FelvettVizsga> aktuálisanMeghirdetettTantárgyhozTartozóFelvettVizsgák = new ArrayList<>();

		for (FelvettVizsga felvettVizsga : getAktuálisFélévbenFelvettVizsgák()) {
			if (felvettVizsga.getVizsga().getMeghirdetettTantargy().equals(meghirdetettTantargy)) {
				aktuálisanMeghirdetettTantárgyhozTartozóFelvettVizsgák.add(felvettVizsga);
			}
		}

		return aktuálisanMeghirdetettTantárgyhozTartozóFelvettVizsgák;
	}

	/**
	 * Ellenőrzi, hogy a hallgatónak van-e érvényes vizsgajelentkezése
	 * a paraméterül kapott {@link Vizsga}-ból. Akkor tekint érvényesnek
	 * egy vizsgajelentkezést, ha a hallgatónak van olyan felvett vizsgája,
	 * amire az oktató még nem írta be az érdemjegyet.
	 * 
	 * @param vizsga Ezt a vizsgát fogja vizsgálni a keresés során.
	 * @return <code>true</code> ha a hallgatónak van érvényes vizsgajelentkezése,
	 * <code>false</code> egyébként.
	 */
	private boolean vanÉrvényesVizsgajelentkezése(Vizsga vizsga) {		
		// kiszűri az adott félévhez és tantárgyhoz tartozó vizsgákat
		List<FelvettVizsga> aktuálisTantárgyVizsgái = getAktuálisanMeghirdetettTantárgyhozTartozóFelvettVizsgák(vizsga.getMeghirdetettTantargy());

		// megnézi, hogy az adott tantárgyhoz tartozó vizsgák közül van-e olyan,
		// amelyhez nincs érdemjegy beírva
		for (FelvettVizsga felvettVizsga : aktuálisTantárgyVizsgái) {
			if (felvettVizsga.getÉrdemjegy() == -1) {
				return true;
			}
		}

		// nem talált olyan vizsgajelenetkezést, ahol ne lenne beírva az érdemjegy
		return false;
	}
	
	/**
	 * Visszaad egy olyan listát, amely az aktuális félévben felvett tantárgyakat
	 * tartalmaza.
	 * 
	 * @return Visszaad egy olyan listát, amely az aktuális félévben felvett tantárgyakat
	 * tartalmaza.
	 */
	public List<FelvettTantargy> getAktuálisFélévbenFelvettTantárgyak() {
		List<FelvettTantargy> aktuálisFélévbenFelvettTantárgyak = new ArrayList<>();
		for (FelvettTantargy felvettTantargy : felvettTantárgyak) {
			if (felvettTantargy.getMeghirdetettTantárgy().getAktuálisFélév().equals(Kozpont.getAktuálisFélév())) {
				aktuálisFélévbenFelvettTantárgyak.add(felvettTantargy);
			}
		}
		return aktuálisFélévbenFelvettTantárgyak;
	}
	
	/**
	 * Visszaadja azt a {@link FelvettTantargy}-at, amely a paraméterül kapott
	 * {@link MeghirdetettTantargy}-at tartalmazza.
	 *  
	 * @param meghirdetettTantargy A {@link FelvettTantargy} meghirdetett tantárgya
	 * ezzel az objektummal lesz összehasonlítva.
	 * @return Visszaadja azt a {@link FelvettTantargy}-at, amely a paraméterül kapott
	 * {@link MeghirdetettTantargy}-at tartalmazza.
	 */
	private FelvettTantargy getAktualisFelevbenFelvettTantargy(MeghirdetettTantargy meghirdetettTantargy) {
		for (FelvettTantargy felvettTantargy : getAktuálisFélévbenFelvettTantárgyak()) {
			if (felvettTantargy.getMeghirdetettTantárgy().equals(meghirdetettTantargy)) {
				return felvettTantargy;
			}
		}
		return null;
	}
	
	/**
	 * Ellenőrzi, hogy a hallgatónak van-e aláírása, a paraméterül
	 * kapott {@link MeghirdetettTantargy}-ból.
	 * @param meghirdetettTantargy Ez az a tantárgy, amelyen az ellenőrzést végzi. 
	 * @return <code>true</code> ha a hallgatónak van aláírása a {@link MeghirdetettTantargy}-ból,
	 * egyébként <code>false</code>.
	 */
	private boolean vanAláírása(MeghirdetettTantargy meghirdetettTantargy) {
		FelvettTantargy felvettTantargy = getAktualisFelevbenFelvettTantargy(meghirdetettTantargy);
		if (felvettTantargy == null) {
			return false;
		}
		return felvettTantargy.vanAláírása();
	}
	
	/**
	 * Ezzel a metódussal a hallgató jelentkezni tud egy vizsgára.
	 * 
	 * @param vizsga Erre a vizsgára jelentkezik a hallgató,
	 * @throws TanulmanyiRendszerKivetel Ha nem lehet felvenni a vizsgát.
	 */
	public void vizsgajelentkezés(Vizsga vizsga) throws TanulmanyiRendszerKivetel {
		if (!vizsga.getMeghirdetettTantargy().getAktuálisFélév().equals(Kozpont.getAktuálisFélév())) {
			logger.warn("Nem lehet felvenni a {} vizsgát, mert nem az aktuális félévre[{}] van meghirdetve", new Object[] {vizsga, Kozpont.getAktuálisFélév()});
			throw new TanulmanyiRendszerKivetel("Nem lehet felvenni a vizsgát, mert nem az akutális félévre van meghirdetve.");
		}
		
		if (!vanAláírása(vizsga.getMeghirdetettTantargy())) {
			logger.warn("{} hallgatónak nincs aláírása a {} tantárgyból, ezért nem tud vizsgára jelentkezni.", new Object[] {});
			throw new TanulmanyiRendszerKivetel("Nincs aláírásod az adott tantárgyból, ezért nem tudsz ebből a tantárgyból vizsgára jelentkezni!");
		}
		
		if (vanÉrvényesVizsgajelentkezése(vizsga)) {
			logger.warn("{} hallgató már felvette a következő vizsgát: {}.", vizsga);
			throw new TanulmanyiRendszerKivetel("Ebből a tantárgyból már van érvényes vizsgajelentkezésed. Kérlek várd meg, amíg az oktató beírja az érdemjegyet!");
		}

		List<FelvettVizsga> szűrésUtániLista = getAktuálisanMeghirdetettTantárgyhozTartozóFelvettVizsgák(vizsga.getMeghirdetettTantargy());
		int vizsgafelvételekSzáma = szűrésUtániLista.size();
		if (vizsgafelvételekSzáma >= 3) {
			logger.warn("A {} félévben elérted a {} tantárgyhoz tartozó maximális vizsgajelentkezések számát!",
					new Object[] {Kozpont.getAktuálisFélév(), vizsga.getMeghirdetettTantargy()});
			throw new TanulmanyiRendszerKivetel("Az aktuális félévben elérted a maximális vizsgajelentkezések számát az adott tantárgyból!");
		}

		felvettVizsgák.add(new FelvettVizsga(vizsga));
		logger.info("{} hallgató új vizsgára jelentkezett: {}.", this, vizsga);
	}

	/**
	 * Beírja a paraméterül kapott vizsgára a paraméterül kapott érdemjegyet.
	 * @param felvettVizsga Erre a vizsgára kerül be az érdemjegy.
	 * @param érdemjegy Ez az érdemjegy kerül be a vizsgára.
	 */
	public void érdemjegyBeírása(FelvettVizsga felvettVizsga, int érdemjegy) {
		felvettVizsga.setÉrdemjegy(érdemjegy);
		logger.info("A {} vizsgára új érdemjegy került beírása: {}.", new Object[] {felvettVizsga, érdemjegy});
	}
	
	
	/**
	 * Visszaadja a hallgató érdemjegyét a paraméterül kapott tantárgyból.
	 * 
	 * @param felvettTantárgy Ennek a tantárgynak az érdemjegyét adja vissza.
	 * @return Visszaadja a hallgató érdemjegyét a paraméterül kapott tantárgyból.
	 */
	public int getÉrdemjegy(FelvettTantargy felvettTantárgy) {
		for (FelvettVizsga felvettVizsga : felvettVizsgák) {
			if (felvettVizsga.getVizsga().getMeghirdetettTantargy().equals(felvettTantárgy.getMeghirdetettTantárgy()) &&
				felvettVizsga.getÉrdemjegy() > 0) {
				return felvettVizsga.getÉrdemjegy();
			}
		}
		return -1;
	}

	/**
	 * Beiratkozik a {@link Kozpont} által nyílvántartott aktuális félévre.
	 * Ha a hallgatónak még van aktív féléve és be akar iratkozni egy újabbra,
	 * akkor a jelenleg aktív félévét automatikusan lezárja.
	 * 
	 * @throws TanulmanyiRendszerKivetel ha a {@link Kozpont}-ban nincs aktuális félév meghirdetve.
	 */
	public void beiratkozás() throws TanulmanyiRendszerKivetel {
		Felev aktuálisFélév = Kozpont.getAktuálisFélév();
		// ha nincs félév meghirdetve
		if (aktuálisFélév == null) {
			TanulmanyiRendszerKivetel trk = new TanulmanyiRendszerKivetel(
					"A központban nincs meghiretve egyetlen egy félév sem!");
			logger.error(trk.getMessage());
			throw trk;
		}
		// ha még van aktív féléve
		if (this.aktívFélév != null) {
			félévLezárása();
		}
		this.aktívFélév = aktuálisFélév;
	}

	/**
	 * Lezárja a hallgató aktív félévét. Ha a hallgatónak van olyan vizsgája,
	 * amiből még nincs érdemjegye, akkor automatikusan 1-est kap az adott
	 * tárgyból.
	 */
	public void félévLezárása() {
		// automatikusan megbuktatja a hallgatót.
		for (FelvettVizsga felvettVizsga : getAktuálisFélévbenFelvettVizsgák()) {
			if (felvettVizsga.getÉrdemjegy() == -1) {
				felvettVizsga.setÉrdemjegy(1);
			}
		}
		logger.info("{} hallgató {} féléve lezárásra került.", this, this.aktívFélév);
		this.aktívFélév = null;
	}

	/**
	 * Megvizsgálja, hogy a paraméterül kapott tantárgy előfeltételeit teljesít-e
	 * a hallgató, azaz teljesítette-e már a korábbi félévekben a tantárgy előfeltételeit.
	 * 
	 * @param tantárgy Ennek a tantárgynak az előfeltételei lesznek ellenőrizve.
	 * @return <code>true</code> ha a tantárgy előfeltételei teljesülnek, egyébként <code>false</code>.
	 */
	public boolean isTantárgyElőfeltételekTeljesülnek(FelvettTantargy tantárgy) {
		List<Tantargy> előfeltételek = tantárgy.getMeghirdetettTantárgy().getTantárgy().getElőfeltételek();
		int teljesültElőfeltételekSzáma = 0;
		for (Tantargy t : előfeltételek) {
			for (FelvettTantargy felvettTantárgy : felvettTantárgyak) {
				if (felvettTantárgy.getMeghirdetettTantárgy().getTantárgy().equals(t)) {
					if (getÉrdemjegy(felvettTantárgy) > 1) {
						++teljesültElőfeltételekSzáma;
					}
				}
			}
		}
		if (teljesültElőfeltételekSzáma == előfeltételek.size()) {
			logger.info("{} hallgató {} tantárgy előfeltételei teljesültek.", this, tantárgy);
			return true;
		} else {
			logger.info("{} hallgató {} tantárgy előfeltételei nem teljesültek.", this, tantárgy);
			return false;
		}
	}

	/**
	 * Megvizsgálja, hogy a hallgató teljesítette-e már a paraméterül kapott tantárgyat.
	 * Akkor tekint teljesítettnek egy tantárgyat, ha már van legalább 2-es érdemjegye belőle.
	 * 
	 * @param tantárgy Ezt a tantárgyat vizsgálja meg.
	 * @return <code>true</code> ha már teljesítette a tantárgyat, egyébként <code>false</code>.
	 */
	public boolean isTeljesítettTantárgy(FelvettTantargy tantárgy) {
		for (FelvettTantargy felvettTantárgy : felvettTantárgyak) {
			if (felvettTantárgy.equals(tantárgy) && getÉrdemjegy(felvettTantárgy) > 1) {
				logger.info("{} hallgató már teljesítette ezt tárgyat: {}.", this, tantárgy);
				return true;
			}
		}
		logger.info("{} hallgató még nem teljesítette ezt tárgyat: {}.", this, tantárgy);
		return false;
	}

	/**
	 * Visszaadja a paraméterül kapott tantárgy hallgató által felvételeinek a számát.
	 * 
	 * @param tantárgy Ezt a tantárgyat keresi a hallgató által felvett tantárgyak között.
	 * @return Ennyiszer vette fel a hallgató a paraméteürl kapott tantárgyat.
	 */
	public int tantárgyfelvételekSzáma(FelvettTantargy tantárgy) {
		int számláló = 0;
		for (FelvettTantargy ft : felvettTantárgyak) {
			if (ft.equals(tantárgy)) {
				++számláló;
			}
		}
		logger.info("{} hallgató {}-szor vette fel ezt a tárgyat: {}.", new Object[] { this, számláló, tantárgy.getMeghirdetettTantárgy().getTantárgy() });
		return számláló;
	}
	
	/**
	 * Hozzéadja a hallgató által felvett tantárgyaihoz a paraméterül kapott meghirdetett tantárgyat
	 * és a hozzá tartozó gyakorlati csoportot.
	 *  
	 * @param tantárgy A hallgató ezt a tantárgyat veszi fel.
	 * @param gyakorlatiCsoport A hallgató ezt a gyakorlati csoportot veszi fel.
	 * @throws TanulmanyiRendszerKivetel Ha nem lehet felvenni a tantárgyat.
	 */
	public void felveszTantárgy(MeghirdetettTantargy tantárgy, GyakorlatiCsoport gyakorlatiCsoport) throws TanulmanyiRendszerKivetel {
		FelvettTantargy újtantárgy = new FelvettTantargy(tantárgy, gyakorlatiCsoport);

		if (felvettTantárgyak.contains(újtantárgy)) {
			logger.warn("{} hallgató ezt a {} tárgyat már felvette ebben a félévben.", this, tantárgy);
			throw new TanulmanyiRendszerKivetel("Ebben a félévben, ezt a tárgyat már felvette.");
		}

		if (!isTantárgyElőfeltételekTeljesülnek(újtantárgy)) {
			logger.warn("{} tantárgy előfeltételei nem teljesültek!", tantárgy);
			throw new TanulmanyiRendszerKivetel("A tantárgy előfeltételei nem teljesültek!");
		}

		if (tantárgyfelvételekSzáma(újtantárgy) >= 3) {
			logger.warn("{} hallgató már háromszor is felvette a {} tárgyat, ezért nem lehet mégegyszer felvennie!", new Object[] { this, tantárgy });
			throw new TanulmanyiRendszerKivetel("A hallgató már háromszor is felvette ezt a tárgyat, ezért nem lehet mégegyszer felvennie!");
		}

		this.felvettTantárgyak.add(újtantárgy);
		logger.info("{} hallgató felvette a {} tantárgyat, {} gyakorlati csoporttal.", new Object[] { this, tantárgy, gyakorlatiCsoport });

		logger.debug("A hallgató ezeket a tárgyakat vette eddig fel:");
		for (FelvettTantargy ft : this.felvettTantárgyak) {
			logger.debug(ft.toString());
		}
	}
	
	
//	public void tantárgyLead(FelvettTantargy felvettTantargy) throws TanulmanyiRendszerKivetel {
//		if (!felvettTantargy.getMeghirdetettTantárgy().getAktuálisFélév().equals(Kozpont.getAktuálisFélév())) {
//			logger.warn("Ebben a félévben ezt a {} tantárgyat már nem lehet leadni.", felvettTantargy);
//			throw new TanulmanyiRendszerKivetel("Ebben a félévben ezt a tantárgyat már nem lehet leadni.");
//		}
//
//		if (!felvettTantárgyak.contains(felvettTantargy)) {
//			logger.warn("{} hallgató nem vette fel a {} tantárgyat, ezért nem is tudja leadni.", this, felvettTantargy);
//			throw new TanulmanyiRendszerKivetel("A hallgató nem vette fel ezt tantárgyat, ezért nem is tudja leadni.");
//		}
//
//		felvettTantárgyak.remove(felvettTantargy);
//		logger.info("{} hallgató leadta a {} tantárgyat.", this, felvettTantargy);
//	}
	
	/**
	 * A felhasználó {@link Felhasznalo#hashCode()}-ja és a {@link Szak#hashCode()}-ja szerint
	 * generálja a hashCode-ot.
	 * 
	 * @return Visszaadja a hallgató hashCode-ját.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((szak == null) ? 0 : szak.hashCode());
		return result;
	}

	/**
	 * Két hallgatót akkor tekint egyformának, ha a {@link Felhasznalo#equals(Object)}
	 * metódus szerint egyformák és ugyan az a szak-juk.
	 * 
	 * @param obj Ezzel az objektummal kerül összehasonlításra az aktuális példány.
	 * @return <code>true</code> ha a paraméterül kapott objektum megegyzik az
	 * aktuális példánnyal, <code>false</code> egyébként.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Hallgato)) {
			return false;
		}
		Hallgato other = (Hallgato) obj;
		if (szak == null) {
			if (other.szak != null) {
				return false;
			}
		} else if (!szak.equals(other.szak)) {
			return false;
		}
		return true;
	}

	/**
	 * Visszaadja a hallgató sztringreprezentációját.
	 * 
	 * @return Visszaadja a hallgató sztringreprezentációját.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(" szak: ").append(szak.toString());
		return sb.toString();
	}

	/**
	 * Visszaadja a hallgató aktív félévét.
	 * 
	 * @return Visszaadja a hallgató aktív félévét. 
	 */
	public Felev getAktívFélév() {
		return aktívFélév;
	}

	/**
	 * Visszaadja a hallgató szakját.
	 * 
	 * @return Visszaadja a hallgató szakját.
	 */
	public Szak getSzak() {
		return szak;
	}

	/**
	 * Visszaadja azt a listát, ami a hallgató által
	 * felvett vizsgákat tartalmazza.
	 * 
	 * @return Visszaadja azt a listát, ami a hallgató
	 * által felvett vizsgákat tartalmazza.
	 */
	public List<FelvettVizsga> getFelvettVizsgák() {
		return felvettVizsgák;
	}

	/**
	 * Visszaadja azt a listát, ami a hallgató által
	 * felvett tantárgyakat tartalmazza.
	 * 
	 * @return Visszaadja azt a listát, ami a hallgató
	 * által felvett tantárgyakat tartalmazza.
	 */
	public List<FelvettTantargy> getFelvettTantárgyak() {
		return felvettTantárgyak;
	}
	
}

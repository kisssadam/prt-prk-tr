package tanulmanyiRendszer;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Egy oktatót reprezentál.
 * @author adam
 *
 */
public class Oktato extends Felhasznalo {	
	/**
	 * Az oktató fizetése.
	 */
	private int fizetés;
	
	/**
	 * A soron következő oktató id-je.
	 */
	private static int nextId = 0;
	
	/**
	 * Ennek a segítségével lehet naplóüzeneteket létrehozni.
	 */
	private static Logger logger = LoggerFactory.getLogger(Oktato.class);
	
	/**
	 * @param id Az oktató id-je.
	 * @param vezetéknév Az oktató vezetékneve.
	 * @param keresztnév Az oktató keresztneve.
	 * @param felhasználónév Az oktató felhasználóneve.
	 * @param jelszó Az oktató jelszava.
	 * @param születésnap Az oktató születésnapja.
	 * @param fizetés Az oktató fizetése.
	 */
	public Oktato(int id, String vezetéknév, String keresztnév, String felhasználónév,
			String jelszó, Date születésnap, int fizetés) {
		super(id, vezetéknév, keresztnév, felhasználónév, jelszó, születésnap);
		++nextId;
		this.fizetés = fizetés;
		logger.trace("Új {} lett példányosítva: {}.", new Object[] {getClass().getName(), this});
	}
	
	/**
	 * @param vezetéknév Az oktató vezetékneve.
	 * @param keresztnév Az oktató keresztneve.
	 * @param felhasználónév Az oktató felhasználóneve.
	 * @param jelszó Az oktató jelszava.
	 * @param születésnap Az oktató születésnapja.
	 * @param fizetés Az oktató fizetése.
	 */
	public Oktato(String vezetéknév, String keresztnév, String felhasználónév, String jelszó,
			Date születésnap, int fizetés) {
		this(nextId, vezetéknév, keresztnév, felhasználónév, jelszó, születésnap, fizetés);
	}

	/**
	 * Beírja a hallgatónak arra a felvett tantárgyra az aláírást, aminek a meghirdetett tantárgya
	 * a paraméterül kapott meghirdetett tantárgy.
	 * 
	 * @param hallgato Ennek a hallgatónak lesz beírva az aláírás.
	 * @param meghirdetettTantargy Ennek a meghirdetett tantárgynak kell beírni az aláírást.
	 * @param aláírás Az aláírás értéke lehet <code>true</code> vagy <code>false</code>.
	 * @throws TanulmanyiRendszerKivetel Ha nem lehet beírni az aláírást.
	 */
	public void aláírásBeírása(Hallgato hallgato, MeghirdetettTantargy meghirdetettTantargy, boolean aláírás) throws TanulmanyiRendszerKivetel {
		for (FelvettTantargy felvettTantargy : hallgato.getFelvettTantárgyak()) {
			if (meghirdetettTantargy.getAktuálisFélév().equals(Kozpont.getAktuálisFélév()) &&
				felvettTantargy.getMeghirdetettTantárgy().equals(meghirdetettTantargy)) {
				felvettTantargy.setAláírás(aláírás);
				logger.info("{} hallgató {} tantárgyra a következő aláírást kapta: {}.", new Object[] {hallgato, felvettTantargy, aláírás});
				return;
			}
		}
		logger.warn("{} hallgatónak nem sikerült beírni a {} aláírást a {} tantárgyra.", new Object[] {hallgato, aláírás, meghirdetettTantargy});
		throw new TanulmanyiRendszerKivetel("Nem sikerült az aláírás beírása!");
	}

	/**
	 * Meghirdet egy vizsgát.
	 * 
	 * @param meghirdetettTantargy Ennek a tantárgynak lesz egy vizsgája meghirdetve.
	 * @param vizsga Maga a meghirdetendő vizsga.
	 * @throws TanulmanyiRendszerKivetel Ha nem lehet meghirdetni a vizsgát.
	 */
	public void vizsgahirdetés(MeghirdetettTantargy meghirdetettTantargy, Vizsga vizsga) throws TanulmanyiRendszerKivetel {
		if (!meghirdetettTantargy.getAktuálisFélév().equals(Kozpont.getAktuálisFélév())) {
			String hibaüzenet = "Csak a mostani félévre lehet vizsgát meghirdetni!";
			logger.warn(hibaüzenet);
			throw new TanulmanyiRendszerKivetel(hibaüzenet);
		}
		
		List<Vizsga> meghirdetettVizsgák = Kozpont.getMeghirdetettVizsgák();
		if (meghirdetettVizsgák.contains(vizsga)) {
			logger.warn("Már van ilyen {} vizsga hozzáadva a {} tantárgyhoz.", new Object[] {vizsga, meghirdetettTantargy});
			throw new TanulmanyiRendszerKivetel("Már van ilyen vizsga hozzáadva a tantárgyhoz!");
		}
		
		meghirdetettVizsgák.add(vizsga);
		logger.info("Új {} vizsga lett hozzáadva a {} tantárgyhoz.", new Object[] {vizsga, meghirdetettTantargy});
		return;
	}
	
	/**
	 * Beírja a paraméterül kapott vizsgára a paraméterül kapott érdemjegyet.
	 * 
	 * @param hallgato Ennek a hallgatónak a vizsgájára kerül be az érdemjegy.
	 * @param vizsga Erre a vizsgára kerül be az érdemjegy.
	 * @param érdemjegy Ez az érdemjegy kerül be a vizsgára.
	 * @throws TanulmanyiRendszerKivetel 
	 */
	public void érdemjegyBeírása(Hallgato hallgato, Vizsga vizsga, int érdemjegy) throws TanulmanyiRendszerKivetel {
		for (FelvettVizsga felvettVizsga : hallgato.getFelvettVizsgák()) {
			if (felvettVizsga.getVizsga().equals(vizsga)) {
				if (felvettVizsga.getÉrdemjegy() > 0) {
					continue;
				} else {
					felvettVizsga.setÉrdemjegy(érdemjegy);
					logger.info("A {} vizsgára új érdemjegy került beírása: {}.", new Object[] {vizsga, érdemjegy});
					return;
				}
			}
		}
		logger.warn("{} hallgatónak nincs olyan vizsgája, amire be lehetne írni az érdemjegyet: {}", new Object[] {hallgato, érdemjegy});
		throw new TanulmanyiRendszerKivetel("Nincs olyan vizsgája a hallgatónak, amire be lehetne írni az érdemjegyet!");
	}

	/**
	 * Visszaadja az oktató fizetését.
	 * 
	 * @return Visszaadja az oktató fizetését.
	 */
	public int getFizetés() {
		return fizetés;
	}

	/**
	 * Beállítja az oktató fizetését.
	 * 
	 * @param fizetés Az oktató új fizetése.
	 */
	public void setFizetés(int fizetés) {
		this.fizetés = fizetés;
	}

	/**
	 * A {@link Felhasznalo#hashCode()}-ja és a {@link Oktato#fizetés} -ja
	 * szerint generálódik.
	 * 
	 * @return Visszaadja a hashCode-ot.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + fizetés;
		return result;
	}

	/**
	 * Két oktató akkor tekinthető egyformának, ha
	 * a {@link Felhasznalo#equals(Object)} szerint és
	 * a {@link Oktato#fizetés} szerint it egyformák.
	 * 
	 * @param obj Ezzel az objektummal lesz összehasonlítva az aktuális példány.
	 * @return <code>true</code> ha megegyeznek, <code>false</code> egyébként.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Oktato other = (Oktato) obj;
		if (fizetés != other.fizetés)
			return false;
		return true;
	}

	/**
	 * Visszaadja az aktuális példány sztringreprezentációját.
	 * 
	 * @return Visszaadja az aktuális példány sztringreprezentációját.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(" fizetés: ").append(fizetés);
		return sb.toString();
	}
	
}

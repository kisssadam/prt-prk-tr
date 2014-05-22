package tanulmanyiRendszer;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Oktato extends Felhasznalo {
	private int id;
	private int fizetés;
	
	private static int nextId = 0;
	
	/**
	 * Ennek a segítségével lehet naplóüzeneteket létrehozni.
	 */
	private static Logger logger = LoggerFactory.getLogger(Oktato.class);
	
	public Oktato(int id, String vezetéknév, String keresztnév, String felhasználónév, String jelszó, Date születésnap, int fizetés) {
		super(vezetéknév, keresztnév, felhasználónév, jelszó, születésnap);
		this.id = id;
		++nextId;
		this.fizetés = fizetés;
		logger.trace("Új {} lett példányosítva: {}.", new Object[] {getClass().getName(), this});
	}
	
	public Oktato(String vezetéknév, String keresztnév, String felhasználónév, String jelszó, Date születésnap, int fizetés) {
		this(nextId, vezetéknév, keresztnév, felhasználónév, jelszó, születésnap, fizetés);
	}

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
	
	public int getId() {
		return id;
	}

	public int getFizetés() {
		return fizetés;
	}

	public void setFizetés(int fizetés) {
		this.fizetés = fizetés;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + fizetés;
		return result;
	}

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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(" fizetés: ").append(fizetés);
		return sb.toString();
	}
	
}

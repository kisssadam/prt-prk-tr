package tanulmanyiRendszer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Egy {@link Hallgato} által felvett tantárgyat reprezentál.
 * @author adam
 *
 */
public class FelvettTantargy {
	/**
	 * Ez tárolja, hogy melyik {@link MeghirdetettTantargy}-at vette fel a {@link Hallgato}.
	 */
	private MeghirdetettTantargy meghirdetettTantárgy;
	
	/**
	 * Ez tárolja, hogy a {@link Hallgato} rendelkezik-e aláírással ebből a tárgyból.
	 */
	private boolean aláírás;
	
	/**
	 * Ez tárolja, hogy melyik {@link GyakorlatiCsoport}-ot vette fel a {@link Hallgato}.
	 */
	private GyakorlatiCsoport felvettGyakorlatiCsoport;
	
	/**
	 * Ennek a segítségével lehet naplóüzeneteket létrehozni.
	 */
	private static Logger logger = LoggerFactory.getLogger(FelvettTantargy.class);
	
	/**
	 * 
	 * @param meghirdetettTantárgy A {@link Hallgato} ezt a {@link MeghirdetettTantargy}-at fogja felvenni.
	 * @param felvettGyakorlatiCsoport A {@link Hallgato} ezt a {@link GyakorlatiCsoport}-ot fogja felvenni.
	 * @throws TanulmanyiRendszerKivetel Ha nem sikerül valamilyen okból felvenni a tárgyat. 
	 */
	public FelvettTantargy(MeghirdetettTantargy meghirdetettTantárgy, GyakorlatiCsoport felvettGyakorlatiCsoport) throws TanulmanyiRendszerKivetel {
		this.meghirdetettTantárgy = meghirdetettTantárgy;
		this.aláírás = false;
		setFelvettGyakorlatiCsoport(felvettGyakorlatiCsoport);
		logger.trace("Új {} lett példányosítva: {}", new Object[] {getClass().getName(), this});
	}
	
	/**
	 * Visszaadja a {@link Hallgato} által felvett {@link MeghirdetettTantargy}-at.
	 * 
	 * @return Visszaadja a {@link Hallgato} által felvett {@link MeghirdetettTantargy}-at.
	 */
	public MeghirdetettTantargy getMeghirdetettTantárgy() {
		return meghirdetettTantárgy;
	}

	/**
	 * Lekérdezi, hogy van-e aláírása a {@link Hallgato}-nak ebből a tantárgyból.
	 * 
	 * @return <code>true</code>, ha van aláírása, egyékbént <code>false</code>.
	 */
	public boolean vanAláírása() {
		return aláírás;
	}
	
	/**
	 * Visszaadja a {@link Hallgato} által felvett {@link GyakorlatiCsoport}-ot.
	 * 
	 * @return Visszaadja a {@link Hallgato} által felvett {@link GyakorlatiCsoport}-ot.
	 */
	public GyakorlatiCsoport getFelvettGyakorlatiCsoport() {
		return felvettGyakorlatiCsoport;
	}

	/**
	 * Beállítja az aláírás értékét.
	 * 
	 * @param aláírás Az új aláírás értéke.
	 */
	public void setAláírás(boolean aláírás) {
		this.aláírás = aláírás;
		logger.debug("{} tantárgyra beírták a következő aláírást: {}", new Object[] {this, aláírás});
	}
	
	/**
	 * Beállítja a felvett {@link GyakorlatiCsoport}-ot.
	 * 
	 * @param felvettGyakorlatiCsoport Ezt a {@link GyakorlatiCsoport}-ot veszi fel a {@link Hallgato}.
	 * @throws TanulmanyiRendszerKivetel Ha nem sikerül valamilyen okból kifolyólag felvenni ezt a
	 * {@link GyakorlatiCsoport}-ot.
	 */
	public void setFelvettGyakorlatiCsoport(GyakorlatiCsoport felvettGyakorlatiCsoport) throws TanulmanyiRendszerKivetel {
		if (meghirdetettTantárgy.getGyakorlatiCsoportok().contains(felvettGyakorlatiCsoport)) {
			this.felvettGyakorlatiCsoport = felvettGyakorlatiCsoport;
			logger.debug("Új gyakorlati csoport lett felvéve: {}",
					felvettGyakorlatiCsoport);
		} else {
			TanulmanyiRendszerKivetel trk = new TanulmanyiRendszerKivetel(
					"Nincs ilyen gyakorlati csoport!");
			logger.debug("{} : {}", trk.getMessage(), felvettGyakorlatiCsoport);
			throw trk;
		}
	}
	
	/**
	 * A {@link FelvettTantargy} hashCode-ját adja vissza.
	 * 
	 * @return A {@link FelvettTantargy} hashCode-ja.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((meghirdetettTantárgy == null) ? 0 : meghirdetettTantárgy.hashCode());
		return result;
	}

	/**
	 * Két {@link FelvettTantargy} akkor egyforma, ha
	 * ugyan az a {@link MeghirdetettTantargy} tartozik hozzájuk.
	 * 
	 * @param obj Ezzel az objektummal kerül összehasonlításra az aktuális példány.
	 * @return <code>true</code> ha egyforma a paraméterül kapott objektum az akutális példánnyal,
	 * egyébként <code>false</code>.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof FelvettTantargy)) {
			return false;
		}
		FelvettTantargy other = (FelvettTantargy) obj;
		if (meghirdetettTantárgy == null) {
			if (other.meghirdetettTantárgy != null) {
				return false;
			}
		} else if (!meghirdetettTantárgy.equals(other.meghirdetettTantárgy)) {
			return false;
		}
		return true;
	}
	
	/**
	 * A {@link FelvettTantargy} sztringreprezentációját adja vissza.
	 * 
	 * @return A {@link FelvettTantargy} sztringreprezentációját adja vissza.
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FelvettTantargy [meghirdetettTantárgy=")
				.append(meghirdetettTantárgy).append(", aláírás=")
				.append(aláírás).append(", felvettGyakorlatiCsoport=")
				.append(felvettGyakorlatiCsoport).append("]");
		return builder.toString();
	}
	
}

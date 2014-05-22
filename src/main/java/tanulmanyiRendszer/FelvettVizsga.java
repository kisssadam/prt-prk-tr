package tanulmanyiRendszer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Egy {@link Hallgato} által felvett {@link Vizsga}-t reprezentál.
 * @author adam
 *
 */
public class FelvettVizsga {
	/**
	 * Ezt a {@link Vizsga}-át vette fel a {@link Hallgato}.
	 */
	private Vizsga vizsga;
	
	/**
	 * Ez tárolja a {@link Hallgato} által felvett vizsga érdemjegyét.
	 */
	private int érdemjegy;
	
	/**
	 * Ennek a segítségével lehet naplóüzeneteket létrehozni.
	 */
	private static Logger logger = LoggerFactory.getLogger(FelvettVizsga.class);
	
	/**
	 * 
	 * @param vizsga Ezt a {@link Vizsga}-t fogja felvenni a {@link Hallgato}.
	 */
	public FelvettVizsga(Vizsga vizsga) {
		this(vizsga, -1);
	}

	/**
	 * Tipikusan akkor kell használni, ha az adatbázisból olvassuk ki az adatokat.
	 * 
	 * @param vizsga Ezt a {@link Vizsga}-t fogja felvenni a {@link Hallgato}.
	 * @param érdemjegy A {@link Vizsga} érdemjegye.
	 */
	public FelvettVizsga(Vizsga vizsga, int érdemjegy) {
		this.vizsga = vizsga;
		this.érdemjegy = érdemjegy;
		logger.trace("Új {} lett példányosítva: {}.", new Object[] {getClass().getName(), this});
	}

	/**
	 * Visszaadja a vizsga érdemjegyét.
	 * 
	 * @return Visszaadja a vizsga érdemjegyét.
	 */
	public int getÉrdemjegy() {
		return érdemjegy;
	}

	/**
	 * Beállítja a vizsga érdemjegyét.
	 * 
	 * @param érdemjegy Az új érdemjegy.
	 */
	public void setÉrdemjegy(int érdemjegy) {
		this.érdemjegy = érdemjegy;
	}

	/**
	 * Visszaadja, hogy melyik {@link Vizsga}-át vette fel a {@link Hallgato}.
	 * 
	 * @return Visszaadja, hogy melyik {@link Vizsga}-át vette fel a {@link Hallgato}.
	 */
	public Vizsga getVizsga() {
		return vizsga;
	}
	
	/**
	 * @return Az aktuális példány hashCode-ját adja vissza.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vizsga == null) ? 0 : vizsga.hashCode());
		return result;
	}

	/**
	 * Két {@link FelvettVizsga} akkor tekinthető egyformának ha,
	 * ugyan azt a {@link Vizsga}-t tartalmazzák.
	 * 
	 * @param obj Ezzel az objektummal kerül összehasonlításra az aktuális példány.
	 * @return <code>true</code> ha az paraméterül kapott objektum megegyezik az
	 * akutális példánnyal, egyébként <code>false</code>.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof FelvettVizsga)) {
			return false;
		}
		FelvettVizsga other = (FelvettVizsga) obj;
		if (vizsga == null) {
			if (other.vizsga != null) {
				return false;
			}
		} else if (!vizsga.equals(other.vizsga)) {
			return false;
		}
		return true;
	}
	
}

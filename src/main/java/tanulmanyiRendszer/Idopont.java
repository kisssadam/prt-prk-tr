package tanulmanyiRendszer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Egy olyan időpontot reprezentál, aminek
 * van egy napja, órája és perce.
 * @author adam
 *
 */
public class Idopont {
	/**
	 * Ennek a segítségével lehet naplóüzeneteket létrehozni.
	 */
	private static Logger logger = LoggerFactory.getLogger(Idopont.class);
	
	/**
	 * A hét napjait tartalmazza.
	 * @author adam
	 *
	 */
	public static enum Napok {
		/**
		 * A napok vannak felsorolva magyar nyelven.
		 */
		Hétfő, Kedd, Szerda, Csütörtök, Péntek, Szombat, Vasárnap
	}

	/**
	 * Az időpont napját tárolja.
	 */
	private Napok nap;
	
	/**
	 * Az időpont óráját tárolja.
	 */
	private int óra;
	
	/**
	 * Az időpont percét tárolja.
	 */
	private int perc;

	/**
	 * @param nap Az időpont napja.
	 * @param óra Az időpont órája.
	 * @param perc Az időpont perce.
	 */
	public Idopont(Napok nap, int óra, int perc) {
		super();
		this.nap = nap;
		this.óra = óra;
		this.perc = perc;
		logger.trace("Új {} lett példányosítva: {}.", new Object[] {getClass().getName(), this});
	}

	/**
	 * Ezt a konstruktort meghívva, az időpont perce 0 lesz.
	 * @param nap Az időpont napja.
	 * @param óra Az időpont órája.
	 */
	public Idopont(Napok nap, int óra) {
		this(nap, óra, 0);
	}

	/**
	 * Visszaadja az időpont napját.
	 * 
	 * @return Visszaadja az időpont napját.
	 */
	public Napok getNap() {
		return nap;
	}

	/**
	 * Visszaadja az időpont óráját.
	 * 
	 * @return Visszaadja az időpont óráját.
	 */
	public int getÓra() {
		return óra;
	}

	/**
	 * Visszaadja az időpont percét.
	 * 
	 * @return Visszaadja az időpont percét.
	 */
	public int getPerc() {
		return perc;
	}

	/**
	 * Beállítja az időpont napját.
	 * 
	 * @param nap Erre a napra lesz beállítva az időpont napja.
	 */
	public void setNap(Napok nap) {
		this.nap = nap;
	}

	/**
	 * Beállítja az időpont óráját.
	 * 
	 * @param óra Erre az órára lesz beállítva az időpont órája.
	 */
	public void setÓra(int óra) {
		this.óra = óra;
	}

	/**
	 * Beállítja az időpont percét.
	 * 
	 * @param perc Erre a percre lesz beállítva az időpont perce.
	 */
	public void setPerc(int perc) {
		this.perc = perc;
	}

	/**
	 * Visszaadja az időpont sztringreprezentációját.
	 * 
	 * @return Visszaadja az időpont sztringreprezentációját.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(nap).append(" ");
		sb.append(óra).append(":");
		if (perc % 100 < 10) {
			sb.append(0);
		}
		sb.append(perc);
		return sb.toString();
	}

	/**
	 * A {@link Napok#hashCode()}-ja, az óra és a perc alapján generál egy hashCode-ot.
	 * 
	 * @return Visszaadja az időpont hashCode-ját.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + óra;
		result = prime * result + perc;
		result = prime * result + ((nap == null) ? 0 : nap.hashCode());
		return result;
	}

	/**
	 * Két időpont akkor tekinthető egyformának, ha
	 * a napjuk, órájuk és a percük is megegyezik.
	 * 
	 * @param obj Ezzel az objektummal kerül összehasonlításra az aktuális példány.
	 * @return <code>true</code> ha a paraméterül kapott objektum megegyezik az
	 * aktuális példánnyal, <code>false</code> egyébként.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Idopont)) {
			return false;
		}
		Idopont other = (Idopont) obj;
		if (óra != other.óra) {
			return false;
		}
		if (perc != other.perc) {
			return false;
		}
		if (nap != other.nap) {
			return false;
		}
		return true;
	}

}

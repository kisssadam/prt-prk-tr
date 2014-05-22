package tanulmanyiRendszer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Egy szakot reprezentál.
 * @author adam
 *
 */
public class Szak {
	/**
	 * A szak id-je.
	 */
	private int id;
	
	/**
	 * A szak neve.
	 */
	private String név;
	
	/**
	 * A szak szintje. (képzési szint)
	 */
	private Szint szint;
	
	/**
	 * A soron következő szak id-je.
	 */
	private static int nextId = 0;
	
	/**
	 * Ennek a segítségével lehet naplóüzeneteket létrehozni.
	 */
	private static Logger logger = LoggerFactory.getLogger(Szak.class);
	
	/**
	 * A szak lehetséges szintjei.
	 * @author adam
	 *
	 */
	public static enum Szint {
		/**
		 * Itt vannak felsorolva a lehetséges szintek.
		 */
		BSc, MSc
	};
	
	/**
	 * 
	 * @param id A szak id-je.
	 * @param név A szak neve.
	 * @param szint A szak szintje.
	 */
	public Szak(int id, String név, Szint szint) {
		super();
		this.id = id;
		++nextId;
		this.név = név;
		this.szint = szint;
		logger.trace("Új {} lett példányosítva: {}.", new Object[] {getClass().getName(), this});
	}
	
	/**
	 * 
	 * @param név A szak neve.
	 * @param szint A szak szintje.
	 */
	public Szak(String név, Szint szint) {
		this(nextId, név, szint);
	}
	
	/**
	 * Visszaadja a szak id-jét.
	 * 
	 * @return Visszaadja a szak id-jét.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Visszaadja a szak nevét.
	 * 
	 * @return Visszaadja a szak nevét.
	 */
	public String getNév() {
		return név;
	}

	/**
	 * Beállítja a szak nevét.
	 * 
	 * @param név Ez lesz a szak új neve.
	 */
	public void setNév(String név) {
		this.név = név;
	}

	/**
	 * Visszaadja a szak szintjét.
	 * 
	 * @return Visszaadja a szak szintjét.
	 */
	public Szint getSzint() {
		return szint;
	}

	/**
	 * Beállítja a szak szintjét.
	 * 
	 * @param szint Ez lesz a szak új szintje.
	 */
	public void setSzint(Szint szint) {
		this.szint = szint;
	}

	/**
	 * Visszaadja a szak sztringreprezentációját.
	 * 
	 * @return Visszaadja a szak sztringreprezentációját.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(név).append(" ");

		switch (szint) {
		case BSc:
			sb.append("BSc");
			break;
		case MSc:
			sb.append("MSc");
			break;
		}

		return sb.toString();
	}

	/**
	 * A {@link Szak#név#hashCode()} és a {@link Szak#szint#hashCode()}-ja
	 * szerint generálja a hashCode-ot.
	 * 
	 * @return visszaadja a szak hashCode-ját.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((név == null) ? 0 : név.hashCode());
		result = prime * result + ((szint == null) ? 0 : szint.hashCode());
		return result;
	}

	/**
	 * Két szak akkor tekinthető egyformának, ha
	 * ugyan az a nevük és a szintjük.
	 * 
	 * @param obj Az aktuális példány ezzel az objektummal lesz összehasonlítva.
	 * @return <code>true</code> ha a paraméterül kapott objektum megegyezik
	 * az aktuális példánnyal, <code>false</code> egyébként.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Szak)) {
			return false;
		}
		Szak other = (Szak) obj;
		if (név == null) {
			if (other.név != null) {
				return false;
			}
		} else if (!név.equals(other.név)) {
			return false;
		}
		if (szint != other.szint) {
			return false;
		}
		return true;
	}

}

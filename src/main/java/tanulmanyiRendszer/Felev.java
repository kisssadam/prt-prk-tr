package tanulmanyiRendszer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Egy egyetemi félévet ír le.
 * 
 * @author adam
 *
 */
public class Felev implements Comparable<Felev> {
	/**
	 * A félév id-je.
	 */
	private int id;
	
	/**
	 * A félév szorgalmi időszakja.
	 */
	private Idoszak szorgalmiIdőszak;
	
	/**
	 * A félév vizsga időszakja.
	 */
	private Idoszak vizsgaIdőszak;
	
	/**
	 * Ez az id, akkor lesz használva, ha a {@link #Felev(Idoszak, Idoszak)} konstruktor hívódik meg.
	 */
	private static int nextId = 0;
	
	/**
	 * Ennek a segítségével lehet naplóüzeneteket létrehozni.
	 */
	private static Logger logger = LoggerFactory.getLogger(Felev.class);
	
	/** 
	 * @param id A félév id-je.
	 * @param szorgalmiIdőszak A félév szorgalmi időszakja.
	 * @param vizsgaIdőszak A félév vizsga időszakja.
	 */
	public Felev(int id, Idoszak szorgalmiIdőszak, Idoszak vizsgaIdőszak) {
		super();
		this.szorgalmiIdőszak = szorgalmiIdőszak;
		this.vizsgaIdőszak = vizsgaIdőszak;
		this.id = id;
		++nextId;
		logger.trace("Új {} lett példányosítva: {}.", new Object[] {getClass().getName(), this});
	}
	
	/**
	 * @param szorgalmiIdőszak A félév szorgalmi időszakja.
	 * @param vizsgaIdőszak A félév vizsga időszakja.
	 */
	public Felev(Idoszak szorgalmiIdőszak, Idoszak vizsgaIdőszak) {
		this(nextId++, szorgalmiIdőszak, vizsgaIdőszak);
	}

	
	/**
	 * A félév szorgalmi időszakját adja vissza, ami egy {@link Idoszak} objektum.
	 * @return A félév szorgalmi időszakját adja vissza, ami egy {@link Idoszak} objektum.
	 */
	public Idoszak getSzorgalmiIdőszak() {
		return szorgalmiIdőszak;
	}

	/**
	 * A félév vizsga időszakját adja vissza, ami egy {@link Idoszak} objektum.
	 * @return A félév vizsga időszakját adja vissza, ami egy {@link Idoszak} objektum.
	 */
	public Idoszak getVizsgaIdőszak() {
		return vizsgaIdőszak;
	}
	
	/**
	 * A félév id-jét adja vissza.
	 * 
	 * @return A félév id-jét adja vissza.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Ha az aktuális objektum az aktuális félév ami meg van hirdetve, akkor true-t
	 * ad vissza, minden egyéb esetben pedig false-t.
	 * 
	 * @return <code>true</code> ha az objektum az aktuális félév a Központ szerint,
	 * minden más esetben <code>false</code>-t ad vissza.
	 */
	public boolean isAktuálisFélév() {
		if (this.equals(Kozpont.getAktuálisFélév())) {
			return true;
		}
		return false;
	}
	
	/**
	 * A szorgalmi időszak és a vizsga időszak {@link Idoszak#hashCode()} metódusát felhasználva
	 * állítja elő a hashCode-ot.
	 * 
	 * @return A félév hashcode-ja.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((szorgalmiIdőszak == null) ? 0 : szorgalmiIdőszak.hashCode());
		result = prime * result
				+ ((vizsgaIdőszak == null) ? 0 : vizsgaIdőszak.hashCode());
		return result;
	}

	/**
	 * Akkor tekint egyformának két félévet, ha a szorgalmi és a vizsga időszakjuk
	 * is egybe esik.
	 * @param obj Ezzel kerül összehasonlításra az akutális példány.
	 * @return <code>true</code> ha egyformák az objektumot, <code>false</code> egyébként.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Felev)) {
			return false;
		}
		Felev other = (Felev) obj;
		if (szorgalmiIdőszak == null) {
			if (other.szorgalmiIdőszak != null) {
				return false;
			}
		} else if (!szorgalmiIdőszak.equals(other.szorgalmiIdőszak)) {
			return false;
		}
		if (vizsgaIdőszak == null) {
			if (other.vizsgaIdőszak != null) {
				return false;
			}
		} else if (!vizsgaIdőszak.equals(other.vizsgaIdőszak)) {
			return false;
		}
		return true;
	}

	/**
	 * Visszaadja a {@link Felev} sztringreprezentációját.
	 * 
	 * @return Az {@link Felev} sztringreprezentációját adja vissza.
	 */
	@Override
	public String toString() {
		return "szorgalmi időszak: " + szorgalmiIdőszak.toString()
				+ "\nvizsgaidőszak: " + vizsgaIdőszak.toString();
	}

	/**
	 * Először a szorgalmi időszak, majd a vizsga időszak szerint hasonlítja
	 * össze az objektumokat, és növekvő sorrendet állít elő.
	 * 
	 * @param o Ezzel az {@link Felev}-vel lesz összehasonlítva az aktuális példány.
	 * @return < 0 ha az aktualis példány kisebb, mint a paraméterül kapott példány, 0 ha egyforma, > 1 egyébként.
	 */
	@Override
	public int compareTo(Felev o) {
		int result = szorgalmiIdőszak.compareTo(o.szorgalmiIdőszak);
		if (result == 0) {
			result = vizsgaIdőszak.compareTo(o.vizsgaIdőszak);
		}
		logger.debug("{} compareTo {} = {}", new Object[] {this, o, result});
		return result;
	}
}

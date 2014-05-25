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
	 * @throws TanulmanyiRendszerKivetel Ha a szorgalmi időszak a vizsgaidőszak után van.
	 */
	public Felev(int id, Idoszak szorgalmiIdőszak, Idoszak vizsgaIdőszak) throws TanulmanyiRendszerKivetel {
		if (szorgalmiIdőszak == null || vizsgaIdőszak == null) {
			logger.warn("Az időszak nem tartalmazhat null értéket.");
			throw new TanulmanyiRendszerKivetel("Az időszak nem tartalmazhat null értéket.");
		}
		if (szorgalmiIdőszak.getVége().after(vizsgaIdőszak.getEleje())) {
			logger.warn("A szorgalmi időszaknak hamarabb kell lennie, mint a vizsga időszaknak.");
			throw new TanulmanyiRendszerKivetel(
					"A szorgalmi időszaknak hamarabb kell lennie, mint a vizsga időszaknak.");
		}
		this.szorgalmiIdőszak = szorgalmiIdőszak;
		this.vizsgaIdőszak = vizsgaIdőszak;
		this.id = id;
		++nextId;
		logger.trace("Új {} lett példányosítva: {}.", new Object[] {getClass().getName(), this});
	}
	
	/**
	 * @param szorgalmiIdőszak A félév szorgalmi időszakja.
	 * @param vizsgaIdőszak A félév vizsga időszakja.
	 * @throws TanulmanyiRendszerKivetel  Ha a szorgalmi időszak a vizsga időszak után van.
	 */
	public Felev(Idoszak szorgalmiIdőszak, Idoszak vizsgaIdőszak) throws TanulmanyiRendszerKivetel {
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
		result = prime * result + szorgalmiIdőszak.hashCode();
		result = prime * result + vizsgaIdőszak.hashCode();
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
		if (!szorgalmiIdőszak.equals(other.szorgalmiIdőszak)) {
			return false;
		}
		if (!vizsgaIdőszak.equals(other.vizsgaIdőszak)) {
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

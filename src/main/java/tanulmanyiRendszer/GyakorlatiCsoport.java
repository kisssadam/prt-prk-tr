package tanulmanyiRendszer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Egy gyakorlati csoportot ír le.
 * @author adam
 *
 */
public class GyakorlatiCsoport {
	/**
	 * A gyakorlati csoport gyakorlatvezetője.
	 */
	private Oktato gyakorlatvezető;
	
	/**
	 * A gyakorlat ebben a teremben lesz megtartva.
	 */
	private String terem;
	
	/**
	 * A gyakorlat időpontja.
	 */
	private Idopont időpont;
	
	/**
	 * Ennek a segítségével lehet naplóüzeneteket létrehozni.
	 */
	private static Logger logger = LoggerFactory.getLogger(GyakorlatiCsoport.class);
	
	/**
	 * 
	 * @param gyakorlatvezető A gyakorlatat vezető {@link Oktato}.
	 * @param terem A gyakorlat ebben a teremben lesz megtarva.
	 * @param időpont A gyakorlat időpontja.
	 */
	public GyakorlatiCsoport(Oktato gyakorlatvezető, String terem,
			Idopont időpont) {
		super();
		this.gyakorlatvezető = gyakorlatvezető;
		this.terem = terem;
		this.időpont = időpont;
		logger.trace("Új {} lett példányosítva: {}.", new Object[] {getClass().getName(), this});
	}

	/**
	 * Visszaadja a gyakorlat időpontját.
	 * 
	 * @return Visszaadja a gyakorlat időponját.
	 */
	public Idopont getIdőpont() {
		return időpont;
	}
	
	/**
	 * Visszaadja a gyakorlati csoport vezetőjét.
	 * 
	 * @return Visszaadja a gyakorlati csoport vezetőjét.
	 */
	public Oktato getGyakorlatvezető() {
		return gyakorlatvezető;
	}

	/**
	 * Visszaadja, hogy a gyakorlat melyik teremben van megtartva.
	 * 
	 * @return Visszaadja, hogy a gyakorlat melyik teremben van megtartva.
	 */
	public String getTerem() {
		return terem;
	}

	/**
	 * Visszaadja a gyakorlati csoport hasjCode-ját.
	 * 
	 * @return Visszaadja a gyakorlati csoport hasjCode-ját.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((gyakorlatvezető == null) ? 0 : gyakorlatvezető.hashCode());
		result = prime * result + ((időpont == null) ? 0 : időpont.hashCode());
		result = prime * result + ((terem == null) ? 0 : terem.hashCode());
		return result;
	}

	/**
	 * Két gyakorlati csoport akkor tekinthető egyformának, ha
	 * egyforma a gyakorlatvezetőjük,
	 * ugyan abban az időpontban és teremben vannak megtartva.
	 * 
	 * @param obj Ezzel az objektummal kerül összehasonlításra az
	 * aktuális példány.
	 * @return <code>true</code> ha a paraméterül kapott objektum 
	 * megegyezik az aktuális példánnyal, <code>false</code> egyébként.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof GyakorlatiCsoport)) {
			return false;
		}
		GyakorlatiCsoport other = (GyakorlatiCsoport) obj;
		if (gyakorlatvezető == null) {
			if (other.gyakorlatvezető != null) {
				return false;
			}
		} else if (!gyakorlatvezető.equals(other.gyakorlatvezető)) {
			return false;
		}
		if (időpont == null) {
			if (other.időpont != null) {
				return false;
			}
		} else if (!időpont.equals(other.időpont)) {
			return false;
		}
		if (terem == null) {
			if (other.terem != null) {
				return false;
			}
		} else if (!terem.equals(other.terem)) {
			return false;
		}
		return true;
	}

	/**
	 * Visszaadja a gyakorlati csoport sztringreprezentációját.
	 * 
	 * @return Visszaadja a gyakorlati csoport sztringreprezentációját.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(100);
		sb.append(gyakorlatvezető).append(" ");
		sb.append(terem).append(" ");
		sb.append(időpont);
		return sb.toString();
	}
	
}

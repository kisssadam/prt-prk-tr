package tanulmanyiRendszer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Egy időszakot reprezentál, aminek van eleje és vége.
 * @author adam
 *
 */
public class Idoszak implements Comparable<Idoszak> {
	/**
	 * Ennek a segítségével lehet naplóüzeneteket létrehozni.
	 */
	private static Logger logger = LoggerFactory.getLogger(Idoszak.class);
	
	/**
	 * Az időszak eleje.
	 */
	private Date eleje;
	
	/**
	 * Az időszak vége.
	 */
	private Date vége;

	/**
	 * @param eleje Az időszak eleje.
	 * @param vége Az időszak vége.
	 * @throws TanulmanyiRendszerKivetel Ha az eleje később van, mint a vége.
	 */
	public Idoszak(Date eleje, Date vége) throws TanulmanyiRendszerKivetel {
		super();
		if (eleje.after(vége)) {
			logger.warn("Az időszak elejének hamarabb kell lennie a végénél!");
			throw new TanulmanyiRendszerKivetel("Az időszak elejének hamarabb kell lennie a végénél!");
		}
		this.eleje = eleje;
		this.vége = vége;
		logger.trace("Új {} lett példányosítva: {}.", new Object[] {getClass().getName(), this});
	}

	/**
	 * Visszaadja az időszak elejét.
	 * 
	 * @return Visszaadja az időszak elejét.
	 */
	public Date getEleje() {
		return eleje;
	}

	/**
	 * Visszaadja az időszak végét.
	 * 
	 * @return Visszaadja az időszak végét.
	 */
	public Date getVége() {
		return vége;
	}

	/**
	 * Visszaadja az időszak sztringreprezentációját.
	 * 
	 * @return Visszaadja az időszak sztringreprezentációját.
	 */
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd", new Locale(
				"hu", "HU"));
		return "(Időszak: " + sdf.format(eleje) + "-" + sdf.format(vége) + ")";
	}

	/**
	 * Az {@link Idoszak#eleje} és a
	 * {@link Idoszak#vége} alapján számolja ki.
	 * 
	 * @return Visszaadja az időszak hashCode-ját.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eleje == null) ? 0 : eleje.hashCode());
		result = prime * result + ((vége == null) ? 0 : vége.hashCode());
		return result;
	}

	/**
	 * Két időszak akkor tekinthető egyformának, ha az elejük és végük is
	 * megegyezik.
	 * 
	 * @param obj Ezzel az objektummal kerül összehasonlításra az aktuális példány.
	 * @return <code>true</code> ha az aktuális példány megyegyzik a paraméterül
	 * kapott objektummal, <code>false</code> egyébként.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Idoszak)) {
			return false;
		}
		Idoszak other = (Idoszak) obj;
		if (eleje == null) {
			if (other.eleje != null) {
				return false;
			}
		} else if (!eleje.equals(other.eleje)) {
			return false;
		}
		if (vége == null) {
			if (other.vége != null) {
				return false;
			}
		} else if (!vége.equals(other.vége)) {
			return false;
		}
		return true;
	}

	/**
	 * Először az időszakok eleje, majd a vége kerül összehasonlításra.
	 * 
	 * @param o Ezzel az időszakkal kerül összehasonlításra az aktuális példány.
	 * @return Növekvő sorrendet előállító értéket ad vissza.
	 */
	@Override
	public int compareTo(Idoszak o) {
		int result = eleje.compareTo(o.eleje);
		if (result == 0) {
			result = vége.compareTo(o.vége);
		}
		return result;
	}

}

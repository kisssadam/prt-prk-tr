package tanulmanyiRendszer;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Egy vizsgát ír le.
 * Tartalmaz egy meghirdetett tantárgyat,
 * egy időpontot és egy termet.
 * @author adam
 *
 */
public class Vizsga {
	/**
	 * Ehhez a tantárgyhoz tartozik a vizsga.
	 */
	private MeghirdetettTantargy meghirdetettTantargy;
	
	/**
	 * A vizsga időpontja.
	 */
	private Date időpont;
	
	/**
	 * A vizsga terme.
	 */
	private String terem;
	
	/**
	 * Ennek a segítségével lehet naplóüzeneteket létrehozni.
	 */
	private static Logger logger = LoggerFactory.getLogger(Vizsga.class);
	
	/**
	 * 
	 * @param meghirdetettTantargy Ehhez a tantárgyhoz tartozik a vizsga.
	 * @param időpont A vizsga időpontja.
	 * @param terem A vizsga terme.
	 */
	public Vizsga(MeghirdetettTantargy meghirdetettTantargy, Date időpont, String terem) {
		this.meghirdetettTantargy = meghirdetettTantargy;
		this.időpont = időpont;
		this.terem = terem;
		logger.trace("Új {} lett példányosítva: {}.", new Object[] {getClass().getName(), this});
	}

	/**
	 * Visszaadja a vizsga időpontját.
	 * 
	 * @return Visszaadja a vizsga időpontját.
	 */
	public Date getIdőpont() {
		return időpont;
	}

	/**
	 * Visszaadja a vizsga termét.
	 * 
	 * @return Visszaadja a vizsga termét.
	 */
	public String getTerem() {
		return terem;
	}

	/**
	 * Beállítja a vizsga időpontját.
	 * 
	 * @param időpont A vizsga új időpontja.
	 */
	public void setIdőpont(Date időpont) {
		this.időpont = időpont;
	}

	/**
	 * Beállítja a vizsga termét.
	 * 
	 * @param terem A vizsga új terme.
	 */
	public void setTerem(String terem) {
		this.terem = terem;
	}
	
	/**
	 * Visszaadja azt a meghirdetett tantárgyat, amihez a vizsga ki lett írva.
	 * 
	 * @return Visszaadja azt a meghirdetett tantárgyat, amihez a vizsga ki lett írva.
	 */
	public MeghirdetettTantargy getMeghirdetettTantargy() {
		return meghirdetettTantargy;
	}

	/**
	 * Az {@link Vizsga#időpont#hashCode()}-ja,
	 * a {@link Vizsga#meghirdetettTantargy#hashCode()}-ja és
	 * a {@link Vizsga#terem#hashCode()}-ja alapján határozódik meg.
	 * 
	 * @return Visszaadja az aktuális példány hashCode-ját.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((időpont == null) ? 0 : időpont.hashCode());
		result = prime
				* result
				+ ((meghirdetettTantargy == null) ? 0 : meghirdetettTantargy
						.hashCode());
		result = prime * result + ((terem == null) ? 0 : terem.hashCode());
		return result;
	}
	
	/**
	 * Két vizsga akkor tekinthető egyformának, ha
	 * ugyan abban az időpontban vannak,
	 * ugyan ahhoz a meghirdett tantárgyhoz lettek kiírva,
	 * és ugyan abban a teremben vannak.
	 * 
	 * @param obj Az aktuális példány ezzel lesz összehasonlítva.
	 * @return <code>true</code> ha az aktuális példány megegyzik a paraméterül
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
		if (!(obj instanceof Vizsga)) {
			return false;
		}
		Vizsga other = (Vizsga) obj;
		if (időpont == null) {
			if (other.időpont != null) {
				return false;
			}
		} else if (!időpont.equals(other.időpont)) {
			return false;
		}
		if (meghirdetettTantargy == null) {
			if (other.meghirdetettTantargy != null) {
				return false;
			}
		} else if (!meghirdetettTantargy.equals(other.meghirdetettTantargy)) {
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
	 * Visszaadja az aktuális példány sztringreprezentációját.
	 * 
	 * @return Visszaadja az aktuális példány sztringreprezentációját.
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Vizsga [meghirdetettTantargy=")
				.append(meghirdetettTantargy).append(", időpont=")
				.append(időpont).append(", terem=").append(terem).append("]");
		return builder.toString();
	}

	
}

package tanulmanyiRendszer;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tartalmazza a tantárgyat, előadót, félévet, előadás időpontját,
 * előadás helyét (terem) és a gyakorlati csoportokat.
 * @author adam
 *
 */
public class MeghirdetettTantargy {
	/**
	 * A tantárgy, ami meg lett hirdetve.
	 */
	private Tantargy tantárgy;
	
	/**
	 * A tantárgy előadója.
	 */
	private Oktato előadó;
	
	/**
	 * Ebben a félévben lett meghirdetve a tantárgy.
	 */
	private Felev aktuálisFélév;
	
	/**
	 * Az előadás időpontja.
	 */
	private Idopont előadásIdőpont;
	
	/**
	 * Az előadás terme.
	 */
	private String előadásTerem;
	
	/**
	 * A tantárgyhoz kapcsolódó gyakorlati csoportok listája.
	 */
	private List<GyakorlatiCsoport> gyakorlatiCsoportok;
	
	/**
	 * Ennek a segítségével lehet naplóüzeneteket létrehozni.
	 */
	private static Logger logger = LoggerFactory.getLogger(MeghirdetettTantargy.class);
	
	/**
	 * 
	 * @param tantárgy A meghirdetendő tantárgy.
	 * @param előadó Az az {@link Oktato}, aki az előadást tartja.
	 * @param aktuálisFélév	A félév, melyben meg lesz hirdetve a tantárgy.
	 * @param előadásIdőpont Az előadás időpontja.
	 * @param előadásTerem Az előadás ebben a teremben lesz megtarva.
	 */
	public MeghirdetettTantargy(Tantargy tantárgy, Oktato előadó,
			Felev aktuálisFélév, Idopont előadásIdőpont, String előadásTerem) {
		this.tantárgy = tantárgy;
		this.előadó = előadó;
		this.aktuálisFélév = aktuálisFélév;
		this.előadásIdőpont = előadásIdőpont;
		this.előadásTerem = előadásTerem;
		this.gyakorlatiCsoportok = new ArrayList<>();
		logger.trace("Új {} lett példányosítva: {}.", new Object[] {getClass().getName(), this});
	}

	/**
	 * Visszaadja, hogy melyik {@link Tantargy} lett meghirdetve.
	 * 
	 * @return Visszaadja, hogy melyik {@link Tantargy} lett meghirdetve.
	 */
	public Tantargy getTantárgy() {
		return tantárgy;
	}

	/**
	 * Visszaadja azt az {@link Oktato}-t, aki az előadást tartja.
	 * 
	 * @return Visszaadja azt az {@link Oktato}-t, aki az előadást tartja.
	 */
	public Oktato getElőadó() {
		return előadó;
	}

	/**
	 * Visszaadja, hogy melyik félévben lett meghirdetve a tantárgy.
	 * 
	 * @return Visszaadja, hogy melyik félévben lett meghirdetve a tantárgy.
	 */
	public Felev getAktuálisFélév() {
		return aktuálisFélév;
	}

	/**
	 * Visszaadja az előadás időpontját.
	 * 
	 * @return Visszaadja az előadás időpontját.
	 */
	public Idopont getElőadásIdőpont() {
		return előadásIdőpont;
	}

	/**
	 * Visszaadja az előadás termét {@link String}-ként.
	 * @return Visszaadja az előadás termét {@link String}-ként.
	 */
	public String getElőadásTerem() {
		return előadásTerem;
	}

	/**
	 * Visszaadja a tantárgyhoz tartozó gyakorlati csoportokat egy listában.
	 * 
	 * @return Visszaadja a tantárgyhoz tartozó gyakorlati csoportokat egy listában.
	 */
	public List<GyakorlatiCsoport> getGyakorlatiCsoportok() {
		return gyakorlatiCsoportok;
	}

	/**
	 * Az {@link MeghirdetettTantargy #aktuálisFélév},
	 * az {@link MeghirdetettTantargy#előadásIdőpont},
	 * az {@link MeghirdetettTantargy#előadásTerem}
	 * és a {@link MeghirdetettTantargy#tantárgy} alapján
	 * generálódik.
	 * 
	 * @return Visszaadja a {@link MeghirdetettTantargy} hashCode-ját.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((aktuálisFélév == null) ? 0 : aktuálisFélév.hashCode());
		result = prime * result
				+ ((előadásIdőpont == null) ? 0 : előadásIdőpont.hashCode());
		result = prime * result
				+ ((előadásTerem == null) ? 0 : előadásTerem.hashCode());
		result = prime * result + ((előadó == null) ? 0 : előadó.hashCode());
		result = prime * result
				+ ((tantárgy == null) ? 0 : tantárgy.hashCode());
		return result;
	}

	/**
	 * Két {@link MeghirdetettTantargy} akkor tekinthető egyformának, ha
	 * ugyan az a {@link Tantargy} lett meghirdetve,
	 * ugyan abban a {@link Felev}-ben,
	 * ugyan azok az előadás időpontjaik, termeik és
	 * ugyan az az előadó tanár tartja.
	 * 
	 * @param obj Ezzel az objektummal lesz összehasonlítva az aktuális példány.
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
		if (!(obj instanceof MeghirdetettTantargy)) {
			return false;
		}
		MeghirdetettTantargy other = (MeghirdetettTantargy) obj;
		if (aktuálisFélév == null) {
			if (other.aktuálisFélév != null) {
				return false;
			}
		} else if (!aktuálisFélév.equals(other.aktuálisFélév)) {
			return false;
		}
		if (előadásIdőpont == null) {
			if (other.előadásIdőpont != null) {
				return false;
			}
		} else if (!előadásIdőpont.equals(other.előadásIdőpont)) {
			return false;
		}
		if (előadásTerem == null) {
			if (other.előadásTerem != null) {
				return false;
			}
		} else if (!előadásTerem.equals(other.előadásTerem)) {
			return false;
		}
		if (előadó == null) {
			if (other.előadó != null) {
				return false;
			}
		} else if (!előadó.equals(other.előadó)) {
			return false;
		}
		if (tantárgy == null) {
			if (other.tantárgy != null) {
				return false;
			}
		} else if (!tantárgy.equals(other.tantárgy)) {
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
		builder.append("MeghirdetettTantargy [tantárgy=").append(tantárgy)
				.append(", előadó=").append(előadó).append(", aktuálisFélév=")
				.append(aktuálisFélév).append(", előadásIdőpont=")
				.append(előadásIdőpont).append(", előadásTerem=")
				.append(előadásTerem).append(", gyakorlatiCsoportok=")
				.append(gyakorlatiCsoportok).append("]");
		return builder.toString();
	}
}

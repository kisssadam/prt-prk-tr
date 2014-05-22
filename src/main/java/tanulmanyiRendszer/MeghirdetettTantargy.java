package tanulmanyiRendszer;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MeghirdetettTantargy {
	private Tantargy tantárgy;
	private Oktato előadó;
	private Felev aktuálisFélév;
	private Idopont előadásIdőpont;
	private String előadásTerem;
	private List<GyakorlatiCsoport> gyakorlatiCsoportok;
	
	/**
	 * Ennek a segítségével lehet naplóüzeneteket létrehozni.
	 */
	private static Logger logger = LoggerFactory.getLogger(MeghirdetettTantargy.class);
	
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

	public Tantargy getTantárgy() {
		return tantárgy;
	}

	public Oktato getElőadó() {
		return előadó;
	}

	public Felev getAktuálisFélév() {
		return aktuálisFélév;
	}

	public Idopont getElőadásIdőpont() {
		return előadásIdőpont;
	}

	public String getElőadásTerem() {
		return előadásTerem;
	}

	public List<GyakorlatiCsoport> getGyakorlatiCsoportok() {
		return gyakorlatiCsoportok;
	}

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

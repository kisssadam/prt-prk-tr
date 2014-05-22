package tanulmanyiRendszer;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Vizsga {
	private MeghirdetettTantargy meghirdetettTantargy;
	private Date időpont;
	private String terem;
	
	/**
	 * Ennek a segítségével lehet naplóüzeneteket létrehozni.
	 */
	private static Logger logger = LoggerFactory.getLogger(Vizsga.class);
	
	public Vizsga(MeghirdetettTantargy meghirdetettTantargy, Date időpont, String terem) {
		this.meghirdetettTantargy = meghirdetettTantargy;
		this.időpont = időpont;
		this.terem = terem;
		logger.trace("Új {} lett példányosítva: {}.", new Object[] {getClass().getName(), this});
	}

	public Date getIdőpont() {
		return időpont;
	}

	public String getTerem() {
		return terem;
	}

	public void setIdőpont(Date időpont) {
		this.időpont = időpont;
	}

	public void setTerem(String terem) {
		this.terem = terem;
	}
	
	public MeghirdetettTantargy getMeghirdetettTantargy() {
		return meghirdetettTantargy;
	}

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Vizsga [meghirdetettTantargy=")
				.append(meghirdetettTantargy).append(", időpont=")
				.append(időpont).append(", terem=").append(terem).append("]");
		return builder.toString();
	}

	
}

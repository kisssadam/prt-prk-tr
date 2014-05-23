package tanulmanyiRendszer;

import java.util.Date;

/**
 * Egy felhasználót ír le.
 * Ebből származik a {@link Hallgato}, {@link Oktato} és a {@link TanulmanyiOsztaly}.
 * @author adam
 *
 */
public abstract class Felhasznalo {
	/**
	 * A felhasználó id-je.
	 */
	private int id;
	
	/**
	 * A felhasználó vezetékneve.
	 */
	private String vezetéknév;
	
	/**
	 * A felhasználó keresztneve.
	 */
	private String keresztnév;
	
	/**
	 * A felhasználó felhasználóneve.
	 */
	private String felhasználónév;
	
	/**
	 * A felhasználó jelszava.
	 */
	private String jelszó;
	
	/**
	 * A felhasználó születésnapja.
	 */
	private Date születésnap;
	
	/**
	 * Példányosít egy {@link Felhasznalo} objektumot a megadott paraméterek alapján.
	 * 
	 * @param id A felhasználó id-je.
	 * @param vezetéknév A felhasználó vezetékneve.
	 * @param keresztnév A felhasználó keresztneve.
	 * @param felhasználónév A felhasználó felhasználóneve.
	 * @param jelszó A felhasználó jelszava.
	 * @param születésnap A felasználó születésnapja.
	 */
	public Felhasznalo(int id, String vezetéknév, String keresztnév, String felhasználónév,
			String jelszó, Date születésnap) {
		super();
		this.id = id;
		this.vezetéknév = vezetéknév;
		this.keresztnév = keresztnév;
		this.felhasználónév = felhasználónév;
		this.jelszó = jelszó;
		this.születésnap = születésnap;
	}
	
	/**
	 * Visszaadja a felhasználó {@link Felhasznalo#id}-jét.
	 * 
	 * @return Visszaadja a felhasználó {@link Felhasznalo#id}-jét.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Visszaadja a felhasználó jelszavát.
	 * 
	 * @return Visszaadja felhasználó jelszavát.
	 */
	public String getJelszó() {
		return jelszó;
	}
	
	/**
	 * Beállítja a felhasználó jelszavát.
	 * 
	 * @param jelszó A felhasználó új jelszava.
	 */
	public void setJelszó(String jelszó) {
		this.jelszó = jelszó;
	}
	
	/**
	 * Visszaadja a felhasználó vezetéknevét.
	 * 
	 * @return Visszaadja a felhasználó vezetéknevét.
	 */
	public String getVezetéknév() {
		return vezetéknév;
	}

	/**
	 * Visszaadja a felhasználó keresztnevét.
	 * 
	 * @return Visszaadja a felhasználó keresztnevét.
	 */
	public String getKeresztnév() {
		return keresztnév;
	}
	
	/**
	 * Visszaadja a felhasználó felhasználónevét.
	 * 
	 * @return Visszaadja a felhasználó felhasználónevét.
	 */
	public String getFelhasználónév() {
		return felhasználónév;
	}

	/**
	 * Visszaadja a felhasználó születésnapját.
	 * 
	 * @return Visszaadja a felhasználó születésnapját.
	 */
	public Date getSzületésnap() {
		return születésnap;
	}
	
	/**
	 * Visszaadja a felhasználó hashCode-ját.
	 * @return A felhasználó hashCode-ja.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((felhasználónév == null) ? 0 : felhasználónév.hashCode());
		result = prime * result
				+ ((keresztnév == null) ? 0 : keresztnév.hashCode());
		result = prime * result
				+ ((születésnap == null) ? 0 : születésnap.hashCode());
		result = prime * result
				+ ((vezetéknév == null) ? 0 : vezetéknév.hashCode());
		return result;
	}
	
	/**
	 * Két felhasználót akkor tekint egyformának, ha
	 * egyforma a felhasználónevük,
	 * egyforma a keresztnevük,
	 * egyforma a vezetéknevük és
	 * ugyan azon a napon születtek.
	 * 
	 * @param obj Ezzel az objektummal kerül összehasonlításra a felhasználó.
	 * @return <code>true</code> ha egyformák az objektumok, egyébként <code>false</code>.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Felhasznalo)) {
			return false;
		}
		Felhasznalo other = (Felhasznalo) obj;
		if (felhasználónév == null) {
			if (other.felhasználónév != null) {
				return false;
			}
		} else if (!felhasználónév.equals(other.felhasználónév)) {
			return false;
		}
		if (keresztnév == null) {
			if (other.keresztnév != null) {
				return false;
			}
		} else if (!keresztnév.equals(other.keresztnév)) {
			return false;
		}
		if (születésnap == null) {
			if (other.születésnap != null) {
				return false;
			}
		} else if (!születésnap.equals(other.születésnap)) {
			return false;
		}
		if (vezetéknév == null) {
			if (other.vezetéknév != null) {
				return false;
			}
		} else if (!vezetéknév.equals(other.vezetéknév)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Visszaadja az akutális felhasználó sztringreprezentációját.
	 * 
	 * @return Visszaadja az akutális felhasználó sztringreprezentációját.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(vezetéknév).append(" ");
		sb.append(keresztnév).append(" ");
		sb.append(felhasználónév).append(" ");
		sb.append(születésnap);
		return sb.toString();
	}

}

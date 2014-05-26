package tanulmanyiRendszer;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Általánosan leír egy tantárgyat.
 * @author adam
 *
 */
public class Tantargy {
	/**
	 * A tantárgy id-je.
	 */
	private int id;
	
	/**
	 * A tantárgy kódja.
	 */
	private String tantárgykód;
	
	/**
	 * A tantárgy neve.
	 */
	private String név;
	
	/**
	 * A tantárgy kreditje.
	 */
	private int kredit;
	
	/**
	 * A tantárgy szakja.
	 */
	private Szak szak;
	
	/**
	 * A tantárgy előfeltételei.
	 */
	private List<Tantargy> előfeltételek = new ArrayList<>();
	
	/**
	 * A soron következő tantárgy id-je.
	 */
	private static int nextId = 0;
	
	/**
	 * Ennek a segítségével lehet naplóüzeneteket létrehozni.
	 */
	private static Logger logger = LoggerFactory.getLogger(Tantargy.class);

	/**
	 * @param id A tantárgy id-je.
	 * @param tantárgykód A tantárgy kódja.
	 * @param név A tantárgy neve.
	 * @param kredit A tantárgy kreditje.
	 * @param szak A tantárgy szakja.
	 * @param előfeltételek A tantárgy előfeltételei.
	 */
	public Tantargy(int id, String tantárgykód, String név, int kredit, Szak szak, Tantargy[] előfeltételek) {
		super();
		this.id = id;
		++nextId;
		this.tantárgykód = tantárgykód;
		this.név = név;
		this.kredit = kredit;
		this.szak = szak;
		for (Tantargy előfeltétel : előfeltételek) {
			this.előfeltételek.add(előfeltétel);
		}
		logger.trace("Új {} lett példányosítva: {}.", new Object[] {getClass().getName(), this});
	}
	
	/**
	 * Ekkor nem lesz előfeltétele a tantárgynak. 
	 * 
	 * @param id A tantárgy id-je.
	 * @param tantárgykód A tantárgy kódja.
	 * @param név A tantárgy neve.
	 * @param kredit A tantárgy kreditje.
	 * @param szak A tantárgy szakja.
	 */
	public Tantargy(int id, String tantárgykód, String név, int kredit, Szak szak) {
		this(id, tantárgykód, név, kredit, szak, new Tantargy[0]);
	}
	
	/**
	 * Az id-je a {@link Tantargy#nextId} alapján lesz meghatározva.
	 * 
	 * @param tantárgykód A tantárgy kódja.
	 * @param név A tantárgy neve.
	 * @param kredit A tantárgy kreditje.
	 * @param szak A tantárgy szakja.
	 * @param előfeltételek A tantárgy előfeltételei.
	 */
	public Tantargy(String tantárgykód, String név, int kredit, Szak szak, Tantargy[] előfeltételek) {
		this(nextId, tantárgykód, név, kredit, szak, előfeltételek);
	}
	
	/**
	 * Az id-je a {@link Tantargy#nextId} alapján lesz meghatározva.
	 * 
	 * @param tantárgykód A tantárgy kódja.
	 * @param név A tantárgy neve.
	 * @param kredit A tantárgy kreditje.
	 * @param szak A tantárgy szakja.
	 * @param előfeltétel A tantárgy előfeltétele.
	 */
	public Tantargy(String tantárgykód, String név, int kredit, Szak szak, Tantargy előfeltétel) {
		this(nextId, tantárgykód, név, kredit, szak, new Tantargy[] { előfeltétel });
	}

	/**
	 * Ekkor nem lesz előfeltétele a tantárgynak, az id-je pedig a {@link Tantargy#nextId} alapján lesz meghatározva.
	 * 
	 * @param tantárgykód A tantárgy kódja.
	 * @param név A tantárgy neve.
	 * @param kredit A tantárgy kreditje.
	 * @param szak A tantárgy szakja.
	 */
	public Tantargy(String tantárgykód, String név, int kredit, Szak szak) {
		this(nextId, tantárgykód, név, kredit, szak, new Tantargy[0]);
	}
	
	/**
	 * Visszaadja a tantárgy id-jét.
	 * 
	 * @return Visszaadja a tantárgy id-jét.
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Visszaadja a tantárgy kódját.
	 * 
	 * @return Visszaadja a tantárgy kódját.
	 */
	public String getTantárgykód() {
		return tantárgykód;
	}

	/**
	 * Visszaadja a tantárgy nevét.
	 * 
	 * @return Visszaadja a tantárgy nevét.
	 */
	public String getNév() {
		return név;
	}

	
	/**
	 * Visszaadja a tantárgy kreditjét.
	 * 
	 * @return Visszaadja a tantárgy kreditjét.
	 */
	public int getKredit() {
		return kredit;
	}

	/**
	 * Visszaadja a tantárgy szakját.
	 * 
	 * @return Visszaadja a tantárgy szakját.
	 */
	public Szak getSzak() {
		return szak;
	}

	/**
	 * Visszaadja az tantárgy előfeltételeit egy listában.
	 * 
	 * @return Visszaadja az tantárgy előfeltételeit egy listában.
	 */
	public List<Tantargy> getElőfeltételek() {
		return előfeltételek;
	}
	
	/**
	 * A {@link Tantargy#kredit},
	 * a {@link Tantargy#név},
	 * a {@link Tantargy#szak},
	 * a {@link Tantargy#tantárgykód} szerint generálódik.
	 * 
	 * @return Visszaadja az aktuális példány hashCode-ját.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + kredit;
		result = prime * result + ((név == null) ? 0 : név.hashCode());
		result = prime * result + ((szak == null) ? 0 : szak.hashCode());
		result = prime * result
				+ ((tantárgykód == null) ? 0 : tantárgykód.hashCode());
		return result;
	}

	/**
	 * Két tantárgy akkor tekinthető egyformának, ha
	 * ugyan annyi kreditet érnek,
	 * ugyan az a nevük,
	 * ugyan az a szakjuk és
	 * ugyan az a tantárgykódjuk.
	 * 
	 *  @param obj Ezzel az objektummal lesz összehasonlítva az aktuális példány.
	 *  @return <code>true</code> ha egyformák, <code>false</code> egyébként.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Tantargy)) {
			return false;
		}
		Tantargy other = (Tantargy) obj;
		if (kredit != other.kredit) {
			return false;
		}
		if (név == null) {
			if (other.név != null) {
				return false;
			}
		} else if (!név.equals(other.név)) {
			return false;
		}
		if (szak == null) {
			if (other.szak != null) {
				return false;
			}
		} else if (!szak.equals(other.szak)) {
			return false;
		}
		if (tantárgykód == null) {
			if (other.tantárgykód != null) {
				return false;
			}
		} else if (!tantárgykód.equals(other.tantárgykód)) {
			return false;
		}
		return true;
	}

	/**
	 * Visszaadja a tantárgy sztringreprezentációját.
	 * 
	 * @return Visszaadja a tantárgy sztringreprezentációját.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(100);
		sb.append(tantárgykód).append(" | ");
		sb.append(név).append(" | ");
		sb.append("kredit: ").append(kredit);
		sb.append(" | szak: ").append(szak);
		if (előfeltételek.size() > 0) {
			sb.append(" | előfeltételek: ");
			for (int i = 0; i < előfeltételek.size(); i++) {
				sb.append(előfeltételek.get(i).getTantárgykód());
				sb.append(i + 1 == előfeltételek.size() ? "" : " ");
			}
		}
		return sb.toString();
	}
}

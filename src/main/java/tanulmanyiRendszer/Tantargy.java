package tanulmanyiRendszer;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tantargy {
	private int id;
	private String tantárgykód;
	private String név;
	private int kredit;
	private Szak szak;
	private List<Tantargy> előfeltételek = new ArrayList<>();
	
	private static int nextId = 0;
	
	/**
	 * Ennek a segítségével lehet naplóüzeneteket létrehozni.
	 */
	private static Logger logger = LoggerFactory.getLogger(Tantargy.class);

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
	
	public Tantargy(int id, String tantárgykód, String név, int kredit, Szak szak) {
		this(id, tantárgykód, név, kredit, szak, new Tantargy[0]);
	}
	
	public Tantargy(String tantárgykód, String név, int kredit, Szak szak, Tantargy[] előfeltételek) {
		this(nextId, tantárgykód, név, kredit, szak, előfeltételek);
	}
	
	public Tantargy(String tantárgykód, String név, int kredit, Szak szak, Tantargy előfeltétel) {
		this(nextId, tantárgykód, név, kredit, szak, new Tantargy[] { előfeltétel });
	}

	public Tantargy(String tantárgykód, String név, int kredit, Szak szak) {
		this(nextId, tantárgykód, név, kredit, szak, new Tantargy[0]);
	}
	
	public int getId() {
		return id;
	}
	
	public String getTantárgykód() {
		return tantárgykód;
	}

	public String getNév() {
		return név;
	}

	public int getKredit() {
		return kredit;
	}

	public Szak getSzak() {
		return szak;
	}

	public List<Tantargy> getElőfeltételek() {
		return előfeltételek;
	}

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

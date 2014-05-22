package tanulmanyiRendszer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Ez tárolja a program adatait.
 * @author adam
 *
 */
public abstract class Kozpont {
	/**
	 * Ennek a segítségével lehet naplóüzeneteket létrehozni.
	 */
	private static Logger logger = LoggerFactory.getLogger(Kozpont.class);;
	
	/**
	 * A program lokalizációs beállítása.
	 */
	private static Locale locale = new Locale("hu", "HU");
	
	/**
	 * Az aktuális félév, ami meg van hirdetve.
	 */
	private static Felev aktuálisFélév;
	
	/**
	 * A korábbi és az aktuális féléveket tartalmazza.
	 */
	private static List<Felev> félévLista;
	
	/**
	 * A program által ismert szakokat tartalmazza.
	 */
	private static List<Szak> szakLista;
	
	/**
	 * A tantárgyakat tartalmazza. Ezeket lehet meghirdetni.
	 */
	private static List<Tantargy> tantárgyLista;
	
	/**
	 * Az oktatókat tartalmazza.
	 */
	private static List<Oktato> oktatóLista;
	
	/**
	 * Az összes meghirdetett tantárgyat tartalmazza.
	 */
	private static List<MeghirdetettTantargy> meghirdetettTantárgyLista;
	
	/**
	 * A hallgatókat tartalmazza.
	 */
	private static List<Hallgato> hallgatóLista;
	
	/**
	 * A tanulmányi osztályon dolgozókat tartalmazza.
	 */
	private static List<TanulmanyiOsztaly> tanulmányiOsztályDolgozóLista;
	
	/**
	 * Az összes meghirdetett vizsgát tartalmazza.
	 */
	private static List<Vizsga> meghirdetettVizsgák;

	/**
	 * Az általános dátumokat ezzel lehet formázni. 
	 */
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd", Kozpont.getLocale());
	
	/**
	 * Valamilyen fontos esemény dátumát ezzel lehet formázni. Pl.: vizsga dátuma.
	 */
	private static SimpleDateFormat vizsgaIdőpontFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm", Kozpont.getLocale());

	/**
	 * Ez az éppen bejelentkezett felhasználó.
	 */
	private static Felhasznalo bejelentkezettFelhasználó;

	static {
		szakLista = new ArrayList<Szak>();
		félévLista = new ArrayList<>();
		tantárgyLista = new ArrayList<>();
		meghirdetettTantárgyLista = new ArrayList<>();
		oktatóLista = new ArrayList<>();
		hallgatóLista = new ArrayList<>();
		tanulmányiOsztályDolgozóLista = new ArrayList<>();
		meghirdetettVizsgák = new ArrayList<>();

		tanulmányiOsztályDolgozóLista.add(new TanulmanyiOsztaly("Keresztnév", "Vezetéknév", "admin", "admin", new Date()));
		oktatóLista.add(new Oktato("Kollár", "Lajos", "kollarl", "kollarl", new Date(0), 450000));
	}

	/**
	 * Visszadja az aktuálisan meghirdetett félévet.
	 * 
	 * @return Visszadja az aktuálisan meghirdetett félévet.
	 */
	public static Felev getAktuálisFélév() {
		return aktuálisFélév;
	}

	/**
	 * Visszaadja az összes meghirdetett vizsgát.
	 * 
	 * @return Egy listát ad vissza, ami az összes meghirdetett
	 * vizsgát tartalmazza.
	 */
	public static List<Vizsga> getMeghirdetettVizsgák() {
		return meghirdetettVizsgák;
	}

	/**
	 * Visszaadja az éppen bejelentkezett felhasználót.
	 * 
	 * @return Visszaadja az éppen bejelentkezett felhasználót.
	 */
	public static Felhasznalo getBejelentkezettFelhasználó() {
		return bejelentkezettFelhasználó;
	}

	/**
	 * Beállítja a bejelentkezett felhasználót.
	 * 
	 * @param felhasználó Ez lesz a bejelentkezett felhasználó.
	 */
	private static void setBejelentkezettFelhasználó(Felhasznalo felhasználó) {
		Kozpont.bejelentkezettFelhasználó = felhasználó;
		if (felhasználó == null) {
			logger.info("Kijelentkezés történt!");
		}
	}

	/**
	 * Kijelentkezteti a bejelentkezett felhasználót.
	 */
	public static void kijelentkezés() {
		Kozpont.setBejelentkezettFelhasználó(null);
	}
	
	/**
	 * Megkeresi azt a felhasználót, akinek egyezik a felhasználóneve és
	 * jelszava a paraméterül kapott felhasználónévvel és jelszóval.
	 * 
	 * @param felhasználónév Ezzel a felhasználónévvel próbál bejelentkezni a felhasználó.
	 * @param jelszó Ezzel a jelszóval próbál bejelentkezni a felhasználó.
	 * @return <code>null</code> ha nem sikerült bejelentkezni, egyébként a bejelentkezett
	 * felhasználóval tér vissza.
	 */
	public static Felhasznalo bejelentkezés(String felhasználónév, String jelszó) {
		for (Hallgato hallgato : Kozpont.hallgatóLista) {
			if (hallgato.getFelhasználónév().equals(felhasználónév)
					&& hallgato.getJelszó().equals(jelszó)) {
				Kozpont.setBejelentkezettFelhasználó(hallgato);
				logger.info("A bejelentkezett felhasználó: {}", hallgato);
				return getBejelentkezettFelhasználó();
			}
		}
		for (Oktato oktato : Kozpont.oktatóLista) {
			if (oktato.getFelhasználónév().equals(felhasználónév)
					&& oktato.getJelszó().equals(jelszó)) {
				Kozpont.setBejelentkezettFelhasználó(oktato);
				logger.info("A bejelentkezett felhasználó: {}", oktato);
				return getBejelentkezettFelhasználó();
			}
		}
		for (TanulmanyiOsztaly to : Kozpont.tanulmányiOsztályDolgozóLista) {
			if (to.getFelhasználónév().equals(felhasználónév)
					&& to.getJelszó().equals(jelszó)) {
				Kozpont.setBejelentkezettFelhasználó(to);
				logger.info("A bejelentkezett felhasználó: {}", to);
				return getBejelentkezettFelhasználó();
			}
		}
		logger.warn("Nem sikerült bejelentkezni!");
		return null;
	}

	/**
	 * Visszaadja az aktuális félévben meghirdetett tantárgyakat egy listában.
	 * 
	 * @return Visszaadja az aktuális félévben meghirdetett tantárgyakat egy listában.
	 */
	public static List<MeghirdetettTantargy> getAktuálisanMeghirdetettTantárgyLista() {
		List<MeghirdetettTantargy> aktuálisanMeghirdetettTantárgyLista = new ArrayList<>();
		for (MeghirdetettTantargy mt : meghirdetettTantárgyLista) {
			if (mt.getAktuálisFélév().equals(aktuálisFélév)) {
				aktuálisanMeghirdetettTantárgyLista.add(mt);
			}
		}
		return aktuálisanMeghirdetettTantárgyLista;
	}

	/**
	 * Visszaadja a hallgatókat tartalmazó listát.
	 * 
	 * @return Visszaadja a hallgatókat tartalmazó listát.
	 */
	public static List<Hallgato> getHallgatóLista() {
		return hallgatóLista;
	}

	/**
	 * Visszaadja az oktatókat tartalmazó listát.
	 * 
	 * @return Visszaadja az oktatókat tartalmazó listát.
	 */
	public static List<Oktato> getOktatóLista() {
		return oktatóLista;
	}

	/**
	 * Visszaadja a tantárgyakat tartalmazó listát.
	 * 
	 * @return Visszaadja a tantárgyakat tartalmazó listát.
	 */
	public static List<Tantargy> getTantárgyLista() {
		return tantárgyLista;
	}

	/**
	 * Visszaadja a meghirdetett tantárgyakat tartalmazó listát.
	 * 
	 * @return Visszaadja a meghirdetett tantárgyakat tartalmazó listát.
	 */
	public static List<MeghirdetettTantargy> getMeghirdetettTantárgyLista() {
		return meghirdetettTantárgyLista;
	}

	/**
	 * Visszaadja a tanulmányi osztályon dolgozók listáját.
	 * 
	 * @return Visszaadja a tanulmányi osztályon dolgozók listáját.
	 */
	public static List<TanulmanyiOsztaly> getTanulmányiOsztályDolgozóLista() {
		return tanulmányiOsztályDolgozóLista;
	}

	/**
	 * Beállítja az aktuális félévet a paraméterül kapott félévre.
	 * 
	 * @param aktuálisFélév Ez lesz az aktuális félév.
	 */
	public static void setAktuálisFélév(Felev aktuálisFélév) {
		Kozpont.aktuálisFélév = aktuálisFélév;
	}

	/**
	 * Visszaadja a szakokat tartalmazó listát.
	 * 
	 * @return Visszaadja a szakokat tartalmazó listát.
	 */
	public static List<Szak> getSzakLista() {
		return szakLista;
	}

	/**
	 * Visszaadja a féléveket tartalmazó listát.
	 * 
	 * @return Visszaadja a féléveket tartalmazó listát.
	 */
	public static List<Felev> getFélévLista() {
		return félévLista;
	}

	/**
	 * Visszaad egy {@link Locale}-t, ami a program lokalizációját tartalmazza.
	 * 
	 * @return Visszaad egy {@link Locale}-t, ami a program lokalizációját tartalmazza.
	 */
	public static Locale getLocale() {
		return locale;
	}

	/**
	 * Visszaadja azt a {@link SimpleDateFormat}-ot, amellyel általános dátumokat
	 * tudunk formázni.
	 * 
	 * @return Visszaadja azt a {@link SimpleDateFormat}-ot, amellyel általános
	 * dátumokat tudunk formázni.
	 */
	public static SimpleDateFormat getSimpleDateFormat() {
		return simpleDateFormat;
	}
	
	/**
	 * Visszaadja azt a {@link SimpleDateFormat}-ot, amellyel speciális igényű
	 * dátumokat tudunk formázni.
	 * 
	 * @return Visszaadja azt a {@link SimpleDateFormat}-ot, amellyel speciális
	 * igényű dátumokat tudunk formázni.
	 */
	public static SimpleDateFormat getVizsgaIdőpontFormat() {
		return vizsgaIdőpontFormat;
	}
	
}

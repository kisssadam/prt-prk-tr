package tanulmanyiRendszer;

/**
 * Ha a program "logikai" működése során valamilyen nem várt esemény történik,
 * akkor ezt a kivételt kell eldobni.
 * @author adam
 *
 */
public class TanulmanyiRendszerKivetel extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Ismeretlen hiba a program működése során.
	 */
	public TanulmanyiRendszerKivetel() {
		this("Ismeretlen hiba a program működése során");
	}

	/**
	 * 
	 * @param message Ez az üzenet fog megjelenni a szabványos hiba kimeneten.
	 */
	public TanulmanyiRendszerKivetel(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}

package tanulmanyiRendszer;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import tanulmanyiRendszer.Szak.Szint;

public class KozpontTest {
	
	@Before
	public void init() {
		Kozpont.init();
	}
	
	@Test
	public void bejelentkezésTest1() {
		Kozpont.getTanulmányiOsztályDolgozóLista().add(0, new TanulmanyiOsztaly("T", "O", "to", "to", new Date(0)));
		
		TanulmanyiOsztaly to = (TanulmanyiOsztaly) Kozpont.bejelentkezés("admin", "admin");
		assertNotNull(to);
		assertEquals("admin", to.getFelhasználónév());
		assertEquals("admin", to.getJelszó());
		
		to = (TanulmanyiOsztaly) Kozpont.bejelentkezés("bukjon", "el");
		assertNull(to);
		
		to = (TanulmanyiOsztaly) Kozpont.bejelentkezés("admin", "rossz");
		assertNull(to);
	}
	
	@Test
	public void bejelentkezésTest2() throws TanulmanyiRendszerKivetel {
		TanulmanyiOsztaly to = new TanulmanyiOsztaly("T", "O", "to", "to", new Date(0));
		to.hallgatóHozzáadása("Kiss", "Sándor Ádám", "kissadam", "pw", new Date(0), new Szak("pti", Szint.BSc));
		to.hallgatóHozzáadása("Nagy", "Tbiro", "nagytibor", "pw", new Date(0), new Szak("pti", Szint.BSc));
		
		Hallgato hallgato = (Hallgato) Kozpont.bejelentkezés("nagytibor", "pw");
		assertNotNull(hallgato);
		assertEquals("nagytibor", hallgato.getFelhasználónév());
		assertEquals("pw", hallgato.getJelszó());
		
		hallgato = (Hallgato) Kozpont.bejelentkezés("nagytibor", "rossz");
		assertNull(hallgato);
	}
	
	@Test
	public void bejelentkezésTest3() throws TanulmanyiRendszerKivetel {
		TanulmanyiOsztaly to = new TanulmanyiOsztaly("T", "O", "to", "to", new Date(0));
		to.oktatóHozzáadása("Mátyás", "Király", "mátyáskirály", "pw", new Date(0), 1);
		to.oktatóHozzáadása("Nagy", "Tibor", "nagytibor", "pw", new Date(0), 1);
		
		Oktato oktato = (Oktato) Kozpont.bejelentkezés("nagytibor", "pw");
		assertNotNull(oktato);
		assertEquals("nagytibor", oktato.getFelhasználónév());
		assertEquals("pw", oktato.getJelszó());
		
		oktato = (Oktato) Kozpont.bejelentkezés("nagytibor", "rossz");
		assertNull(oktato);
	}
	
}

package tanulmanyiRendszer;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TanulmanyiOsztalyTest {
	
	private static TanulmanyiOsztaly to;
	private static Felev felev;
	
	@BeforeClass
	public static void staticInit() throws TanulmanyiRendszerKivetel {
		to = new TanulmanyiOsztaly("T", "O", "to", "to", new Date());
		felev = new Felev(
				new Idoszak(new Date(0), new Date(1)),
				new Idoszak(new Date(2), new Date(3)));
	}
	
	@Before
	public void init() {
		to.félévLezárása();
		Kozpont.getFélévLista().clear();
	}
	
	@Test
	public void setAktualisFelevTest() {
		assertNull(Kozpont.getAktuálisFélév());
		to.setAktuálisFélév(felev);
		assertNotNull(Kozpont.getAktuálisFélév());
		assertSame(felev, Kozpont.getAktuálisFélév());
	}
	
	@Test(expected = TanulmanyiRendszerKivetel.class)
	public void ujFelevTest1() throws TanulmanyiRendszerKivetel {
		to.újFélév(felev);
		assertEquals(1, Kozpont.getFélévLista().size());
		to.újFélév(felev);
	}
	
	
	
}

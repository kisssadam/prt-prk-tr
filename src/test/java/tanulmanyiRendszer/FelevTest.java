package tanulmanyiRendszer;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class FelevTest {
	private Idoszak szorgalmi1;
	private Idoszak szorgalmi2;
	private Idoszak vizsga1;
	private Idoszak vizsga2;
	private Idoszak vizsga3;
	private Date aktualisDatum;
	
	@Before
	public void init() throws TanulmanyiRendszerKivetel {
		aktualisDatum = new Date();
		szorgalmi1 = new Idoszak(new Date(0), new Date(1));
		szorgalmi2 = new Idoszak(new Date(1), new Date(2));
		new Idoszak(new Date(2), aktualisDatum);
		vizsga1 = new Idoszak(new Date(0), new Date(1));
		vizsga2 = new Idoszak(new Date(1), new Date(2));
		vizsga3 = new Idoszak(new Date(2), aktualisDatum);
	}
	
	@Test(expected = TanulmanyiRendszerKivetel.class)
	public void konstruktorTest1() throws TanulmanyiRendszerKivetel {
		new Felev(szorgalmi1, vizsga1);
		fail("El kellett volna buknia.");
	}
	
	@Test
	public void konstruktorTest2() throws TanulmanyiRendszerKivetel {
		new Felev(szorgalmi1, vizsga2);
	}
	
	@Test(expected = TanulmanyiRendszerKivetel.class)
	public void konstruktorTest3() throws TanulmanyiRendszerKivetel {
		new Felev(szorgalmi1, szorgalmi1);
		fail("El kellett volna buknia.");
	}
	
	@Test(expected = TanulmanyiRendszerKivetel.class)
	public void konstruktorTest4() throws TanulmanyiRendszerKivetel {
		new Felev(null, vizsga2);
	}
	
	@Test(expected = TanulmanyiRendszerKivetel.class)
	public void konstruktorTest5() throws TanulmanyiRendszerKivetel {
		new Felev(szorgalmi1, null);
	}
	
	@Test
	public void getIdTest() throws TanulmanyiRendszerKivetel {
		Felev felev = new Felev(0, szorgalmi1, vizsga2);
		assertEquals(0, felev.getId());
	}
	
	@Test
	public void aktualisFelevTest() throws TanulmanyiRendszerKivetel {
		Felev felev = new Felev(szorgalmi1, vizsga2);
		
		assertEquals(false, felev.isAktuálisFélév());
		Kozpont.setAktuálisFélév(felev);
		assertEquals(true, felev.isAktuálisFélév());
		
		assertSame(felev, Kozpont.getAktuálisFélév());
	}
	
	@Test
	public void equalsTest() throws TanulmanyiRendszerKivetel {
		Felev egyik = new Felev(szorgalmi1, vizsga2);
		Felev masik = new Felev(szorgalmi1, vizsga2);
		Felev harmadik = new Felev(szorgalmi2, vizsga3);
		
		assertEquals(egyik, egyik);
		assertEquals(egyik, masik);
		assertNotEquals(egyik, harmadik);
		assertNotEquals(egyik, null);
		assertNotEquals(egyik, new Date(0));
	}
	
	@Test
	public void hashCodeTest() throws TanulmanyiRendszerKivetel {
		Felev egyik = new Felev(szorgalmi1, vizsga2);
		Felev masik = new Felev(szorgalmi1, vizsga2);
		Felev harmadik = new Felev(szorgalmi2, vizsga3);
		
		assertEquals(egyik.hashCode(), egyik.hashCode());
		assertEquals(egyik.hashCode(), masik.hashCode());
		assertNotEquals(egyik.hashCode(), harmadik.hashCode());
	}
	
	@Test(expected = AssertionError.class)
	public void compareToTest() throws TanulmanyiRendszerKivetel {
		Felev f1 = new Felev(szorgalmi1, vizsga2);
		Felev f2 = new Felev(szorgalmi1, vizsga2);
		Felev f3 = new Felev(szorgalmi2, vizsga3);
		
		List<Felev> egyik = new ArrayList<>();
		egyik.add(f1);
		egyik.add(f2);
		egyik.add(f3);
		
		List<Felev> masik = new ArrayList<>();
		masik.add(f1);
		masik.add(f2);
		masik.add(f3);
		
		Collections.sort(masik);
		assertArrayEquals(
				egyik.toArray(new Felev[0]),
				masik.toArray(new Felev[0]));
		fail("El kellett volna buknia.");
	}
}

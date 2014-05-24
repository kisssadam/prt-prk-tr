package tanulmanyiRendszer;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class IdoszakTest {
	
	@Test(expected = TanulmanyiRendszerKivetel.class)
	public void konstruktorTest1() throws TanulmanyiRendszerKivetel {
		new Idoszak(new Date(), new Date(0));
		fail("El kellett volna buknia.");
	}
	
	@Test()
	public void konstruktorTest2() throws TanulmanyiRendszerKivetel {
		new Idoszak(new Date(0), new Date());
	}
	
	@Test(expected = NullPointerException.class)
	public void konstruktorTest3() throws TanulmanyiRendszerKivetel {
		new Idoszak(null, null);
		fail("El kellett volna buknia.");
	}
	
	@Test()
	public void equalsTest() throws TanulmanyiRendszerKivetel {
		Date aktualisDatum = new Date();
		Idoszak egyik = new Idoszak(new Date(0), aktualisDatum);
		Idoszak masik = new Idoszak(new Date(0), aktualisDatum);
		Idoszak harmadik = new Idoszak(new Date(0), new Date(1));
		
		assertEquals(egyik, egyik);
		assertEquals(egyik, masik);
		assertNotEquals(egyik, harmadik);
		assertNotEquals(egyik, null);
	}
	
	@Test
	public void hashCodeTest() throws TanulmanyiRendszerKivetel {
		Date aktualisDatum = new Date();
		Idoszak egyik = new Idoszak(new Date(0), aktualisDatum);
		Idoszak masik = new Idoszak(new Date(0), aktualisDatum);
		Idoszak harmadik = new Idoszak(new Date(0), new Date(1));
		
		assertEquals(egyik, egyik);
		assertEquals(egyik.hashCode(), masik.hashCode());
		assertNotEquals(egyik, harmadik);
	}
	
	@Test(expected = AssertionError.class)
	public void compareToTest() throws TanulmanyiRendszerKivetel {
		Date aktualisDatum = new Date();
		Idoszak egyik = new Idoszak(new Date(0), aktualisDatum);
		Idoszak masik = new Idoszak(new Date(0), aktualisDatum);
		Idoszak harmadik = new Idoszak(new Date(0), new Date(1));
		
		List<Idoszak> nemRendezettIdoszakok = new ArrayList<>();
		
		nemRendezettIdoszakok.add(egyik);
		nemRendezettIdoszakok.add(masik);
		nemRendezettIdoszakok.add(harmadik);
		
		List<Idoszak> rendezettIdoszakok = new ArrayList<>();
		for (Idoszak idoszak : nemRendezettIdoszakok) {
			rendezettIdoszakok.add(idoszak);
		}
		Collections.sort(rendezettIdoszakok);
		
		assertArrayEquals(
				nemRendezettIdoszakok.toArray(new Idoszak[0]),
				rendezettIdoszakok.toArray(new Idoszak[0]));
		fail("El kellett volna buknia.");
	}
	
}

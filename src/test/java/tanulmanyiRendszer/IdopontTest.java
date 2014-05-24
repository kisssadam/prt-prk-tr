package tanulmanyiRendszer;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import tanulmanyiRendszer.Idopont.Napok;

public class IdopontTest {
	
	@Test
	public void konstruktorTest1() throws TanulmanyiRendszerKivetel {
		new Idopont(Napok.Csütörtök, 13);
		new Idopont(Napok.Péntek, 0, 0);
		new Idopont(Napok.Szombat, 23, 59);
	}
	
	@Test(expected = TanulmanyiRendszerKivetel.class)
	public void konstruktorTest2() throws TanulmanyiRendszerKivetel {
		new Idopont(null, 12);
	}
	
	@Test(expected = TanulmanyiRendszerKivetel.class)
	public void konstruktorTest3() throws TanulmanyiRendszerKivetel {
		new Idopont(Napok.Hétfő, 24);
	}
	
	@Test(expected = TanulmanyiRendszerKivetel.class)
	public void konstruktorTest4() throws TanulmanyiRendszerKivetel {
		new Idopont(Napok.Hétfő, -1);
	}
	
	@Test(expected = TanulmanyiRendszerKivetel.class)
	public void konstruktorTest5() throws TanulmanyiRendszerKivetel {
		new Idopont(Napok.Hétfő, 22, 60);
	}
	
	@Test(expected = TanulmanyiRendszerKivetel.class)
	public void konstruktorTest6() throws TanulmanyiRendszerKivetel {
		new Idopont(Napok.Hétfő, 22, -1);
	}
	
	@Test
	public void equalsTest() throws TanulmanyiRendszerKivetel {
		Idopont egyik = new Idopont(Napok.Hétfő, 12);
		Idopont masik = new Idopont(Napok.Hétfő, 12);
		Idopont harmadik = new Idopont(Napok.Kedd, 12);
		Idopont negyedik = new Idopont(Napok.Hétfő, 14);
		Idopont otodik = new Idopont(Napok.Hétfő, 14, 15);
		Idopont hatodik = new Idopont(Napok.Hétfő, 14, 15);
		Idopont hetedik = new Idopont(Napok.Hétfő, 14, 10);
		
		assertEquals(egyik, egyik);
		assertEquals(egyik, masik);
		assertNotEquals(egyik, harmadik);
		assertNotEquals(egyik, negyedik);
		assertNotEquals(egyik, null);
		assertNotEquals(egyik, new Date());
		
		assertEquals(otodik, otodik);
		assertEquals(otodik, hatodik);
		assertNotEquals(otodik, hetedik);
		assertNotEquals(otodik, null);
		assertNotEquals(otodik, new Date());
	}
	
	@Test
	public void hashCodeTest() throws TanulmanyiRendszerKivetel {
		List<Integer> összesLehetségesHashCode = new LinkedList<>();
		
		for (Idopont.Napok nap : Idopont.Napok.values()) {
			for (int i = 0; i < 24; ++i) {
				for (int j = 0; j < 60; ++j) {
					összesLehetségesHashCode.add(new Idopont(nap, i, j).hashCode());
				}
			}
		}
		
		Idopont idopont1 = new Idopont(Idopont.Napok.Hétfő, 0);
		Idopont idopont2 = new Idopont(Idopont.Napok.Hétfő, 0);
		Idopont idopont3 = new Idopont(Idopont.Napok.Péntek, 0);
		
		assertEquals(idopont1.hashCode(), idopont1.hashCode());
		assertEquals(idopont1.hashCode(), idopont2.hashCode());
		assertNotEquals(idopont1.hashCode(), idopont3.hashCode());
		assertNotEquals(idopont1.hashCode(), null);
		
		// Az összes lehetséges hashCode különbözik.
		Set<Integer> szűrtHashCodeok = new HashSet<>();
		szűrtHashCodeok.addAll(összesLehetségesHashCode);
		assertEquals(
				összesLehetségesHashCode.size(),
				szűrtHashCodeok.size());
	}
	
}

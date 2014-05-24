package tanulmanyiRendszer;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class IdopontTest {
	
	@Test
	public void hashCodeTest() {
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

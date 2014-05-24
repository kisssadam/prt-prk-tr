package tanulmanyiRendszer;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class FelhasznaloTest {
	
	private class FelhasznaloImpl extends Felhasznalo {

		public FelhasznaloImpl(int id, String vezetéknév, String keresztnév,
				String felhasználónév, String jelszó, Date születésnap) {
			super(id, vezetéknév, keresztnév, felhasználónév, jelszó, születésnap);
		}};
	
	@Test
	public void equalsTest() {
		FelhasznaloImpl f1 = new FelhasznaloImpl(1, "Kiss", "Adam", "kisssandoaradam", "adam", new Date(0));
		FelhasznaloImpl f2 = new FelhasznaloImpl(1, "Kiss", "Adam", "kisssandoaradam", "adam", new Date(0));
		FelhasznaloImpl f3 = new FelhasznaloImpl(1, "Kiss", "Adam", "kisssandoaradam", "adam", new Date(1));
		FelhasznaloImpl f4 = new FelhasznaloImpl(1, "Kiss", "Adam", "kisssandoaradam", "pw", new Date(0));
		FelhasznaloImpl f5 = new FelhasznaloImpl(1, "Kiss", "Adam", "felhasznalo", "adam", new Date(0));
		FelhasznaloImpl f6 = new FelhasznaloImpl(1, "Kiss", "Sándor", "kisssandoaradam", "adam", new Date(0));
		FelhasznaloImpl f7 = new FelhasznaloImpl(1, "Valami", "Adam", "kisssandoaradam", "adam", new Date(0));
		
		assertNotEquals(f1, null);
		assertEquals(f1, f2);
		assertNotEquals(f1, f3);
		assertEquals(f1, f4);	// a jelszó különbözik, de attól még egyformák
		assertNotEquals(f1, f5);
		assertNotEquals(f1, f6);
		assertNotEquals(f1, f7);
	}
	
	@Test
	public void hashCodeTest() {
		FelhasznaloImpl f1 = new FelhasznaloImpl(1, "Kiss", "Adam", "kisssandoaradam", "adam", new Date(0));
		FelhasznaloImpl f2 = new FelhasznaloImpl(1, "Kiss", "Adam", "kisssandoaradam", "adam", new Date(0));
		FelhasznaloImpl f3 = new FelhasznaloImpl(1, "Kiss", "Adam", "kisssandoaradam", "adam", new Date(1));
		FelhasznaloImpl f4 = new FelhasznaloImpl(1, "Kiss", "Adam", "kisssandoaradam", "pw", new Date(0));
		FelhasznaloImpl f5 = new FelhasznaloImpl(1, "Kiss", "Adam", "felhasznalo", "adam", new Date(0));
		FelhasznaloImpl f6 = new FelhasznaloImpl(1, "Kiss", "Sándor", "kisssandoaradam", "adam", new Date(0));
		FelhasznaloImpl f7 = new FelhasznaloImpl(1, "Valami", "Adam", "kisssandoaradam", "adam", new Date(0));
		
		assertNotEquals(f1.hashCode(), null);
		assertEquals(f1.hashCode(), f2.hashCode());
		assertNotEquals(f1.hashCode(), f3.hashCode());
		assertEquals(f1.hashCode(), f4.hashCode());	// a jelszó különbözik, de attól még egyformák
		assertNotEquals(f1.hashCode(), f5.hashCode());
		assertNotEquals(f1.hashCode(), f6.hashCode());
		assertNotEquals(f1.hashCode(), f7.hashCode());
	}
}

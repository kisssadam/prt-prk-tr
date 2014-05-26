package tanulmanyiRendszer;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import tanulmanyiRendszer.Szak.Szint;

public class SzakTest {

	@Test
	public void equalsTest() {
		Szak ptibsc = new Szak("pti", Szint.BSc);
		Szak ptimsc = new Szak("pti", Szint.MSc);
		Szak mibsc = new Szak("mi", Szint.BSc);
		Szak mimsc = new Szak("mi", Szint.MSc);

		assertEquals(ptibsc, ptibsc);
		assertNotEquals(ptibsc, null);
		assertNotEquals(ptibsc, new Date(10));
		assertNotEquals(ptibsc, ptimsc);
		assertNotEquals(ptibsc, mibsc);
		assertNotEquals(ptibsc, mimsc);

		ptibsc.setNév(null);
		assertNotEquals(ptibsc, ptimsc);
		assertEquals(ptibsc, ptibsc);

		assertNotEquals(ptimsc, ptibsc);

		ptimsc.setNév(null);
		assertNotEquals(ptibsc, ptimsc);
	}

	@Test
	public void hashCodeTest() {
		Szak ptibsc = new Szak("pti", Szint.BSc);
		Szak ptimsc = new Szak("pti", Szint.MSc);
		Szak mibsc = new Szak("mi", Szint.BSc);
		Szak mimsc = new Szak("mi", Szint.MSc);

		assertEquals(ptibsc.hashCode(), ptibsc.hashCode());
		assertNotEquals(ptibsc.hashCode(), null);
		assertNotEquals(ptibsc.hashCode(), new Date(10).hashCode());
		assertNotEquals(ptibsc.hashCode(), ptimsc.hashCode());
		assertNotEquals(ptibsc.hashCode(), mibsc.hashCode());
		assertNotEquals(ptibsc.hashCode(), mimsc.hashCode());

		ptibsc.setNév(null);
		assertNotEquals(ptibsc.hashCode(), ptimsc.hashCode());
		assertEquals(ptibsc.hashCode(), ptibsc.hashCode());

		assertNotEquals(ptimsc.hashCode(), ptibsc.hashCode());

		ptimsc.setNév(null);
		assertNotEquals(ptibsc.hashCode(), ptimsc.hashCode());
		
		ptibsc.setSzint(null);
		assertEquals(ptibsc, ptibsc);
		
		mibsc.setSzint(null);
		assertNotEquals(ptibsc.hashCode(), mibsc.hashCode());
	}

}

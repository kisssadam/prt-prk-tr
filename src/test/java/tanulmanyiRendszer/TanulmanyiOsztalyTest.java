package tanulmanyiRendszer;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tanulmanyiRendszer.Idopont.Napok;
import tanulmanyiRendszer.Szak.Szint;

public class TanulmanyiOsztalyTest {

	private static TanulmanyiOsztaly to;

	@BeforeClass
	public static void staticInit() throws TanulmanyiRendszerKivetel {
		to = new TanulmanyiOsztaly("T", "O", "to", "to", new Date());
	}

	@Before
	public void init() {
		to.félévLezárása();
		Kozpont.getFélévLista().clear();
		Kozpont.getOktatóLista().clear();
		Kozpont.getHallgatóLista().clear();
		Kozpont.getTantárgyLista().clear();
		Kozpont.getMeghirdetettTantárgyLista().clear();
	}

	@Test(expected = TanulmanyiRendszerKivetel.class)
	public void ujFelevTest1() throws TanulmanyiRendszerKivetel {
		Felev felev = to.újFélév(new Idoszak(new Date(0), new Date(1)),
				new Idoszak(new Date(2), new Date(3)));
		assertEquals(true, Kozpont.getFélévLista().contains(felev));
		to.újFélév(felev.getSzorgalmiIdőszak(), felev.getVizsgaIdőszak(), true);
	}
	
	@Test
	public void ujFelevTest2() throws TanulmanyiRendszerKivetel {
		to.újFélév(new Idoszak(new Date(0), new Date(1)),
				new Idoszak(new Date(2), new Date(3)), true);
	}

	@Test
	public void oktatoHozzaadasaTest1() throws TanulmanyiRendszerKivetel {
		Oktato oktato = to.oktatóHozzáadása("Jeszenszky", "Péter", "jeszy", "jeszy",
				new Date(), 1);
		assertSame(oktato, Kozpont.getOktatóLista().get(0));
	}

	@Test
	public void oktatoHozzaadasaTest3() throws TanulmanyiRendszerKivetel {
		Oktato[] o = new Oktato[] {
			new Oktato("Jeszenszky", "Péter", "jeszy", "jeszy",
					new Date(), 1),
			new Oktato("Jeszenszky", "Péter", "jeszy", "jeszy",
					new Date(), 2),
			new Oktato("Jeszenszky", "Péter", "jeszy75", "jeszy",
					new Date(), 1),
			new Oktato("Ismeretlen", "Péter", "jeszy", "jeszy",
					new Date(), 1),
			new Oktato("Jeszenszky", "Dávid", "jeszy", "jeszy",
					new Date(), 1)
		};

		for (int i = 0; i < o.length; i++) {
			to.oktatóHozzáadása(
					o[i].getVezetéknév(),
					o[i].getKeresztnév(),
					o[i].getFelhasználónév(),
					o[i].getJelszó(),
					o[i].getSzületésnap(),
					o[i].getFizetés());
		}
	}

	@Test
	public void szakHozzaadasaTest1() throws TanulmanyiRendszerKivetel {
		Szak szak = to.szakHozzáadása("pti", Szint.MSc);
		assertEquals(true, Kozpont.getSzakLista().contains(szak));
	}

	@Test(expected = TanulmanyiRendszerKivetel.class)
	public void szakHozzaadasaTest2() throws TanulmanyiRendszerKivetel {
		String név = "progmat";
		Szint szint = Szint.BSc;
		to.szakHozzáadása(név, szint);
		to.szakHozzáadása(név, szint);
	}

	@Test
	public void hallgatoHozzaadasaTest1() throws TanulmanyiRendszerKivetel {
		Hallgato hallgato = to.hallgatóHozzáadása("Kiss", "Adam", "adam",
				"adam", new Date(), new Szak("pti", Szak.Szint.BSc));
		assertEquals(true, Kozpont.getHallgatóLista().contains(hallgato));
	}

	@Test(expected = TanulmanyiRendszerKivetel.class)
	public void hallgatoHozzaadasaTest2() throws TanulmanyiRendszerKivetel {
		String vezetéknév = "Kiss";
		String keresztnév = "Sándor Ádám";
		String felhasználónév = "kisssandoradam";
		String jelszó = "pw";
		Date születésnap = new Date(1);
		Szak szak = new Szak("pti", Szak.Szint.BSc);

		to.hallgatóHozzáadása(vezetéknév, keresztnév, felhasználónév, jelszó,
				születésnap, szak);
		to.hallgatóHozzáadása(vezetéknév, keresztnév, felhasználónév, jelszó,
				születésnap, szak);
	}

	@Test(expected = TanulmanyiRendszerKivetel.class)
	public void tantargyHozzaadasaTest1() throws TanulmanyiRendszerKivetel {
		to.tantárgyHozzáadása(null);
	}

	@Test
	public void tantargyHozzaadasaTest2() throws TanulmanyiRendszerKivetel {
		Szak ptimsc = new Szak("pti", Szint.MSc);
		Tantargy tantargy = new Tantargy("kód", "név", 5, ptimsc);
		to.tantárgyHozzáadása(tantargy);
	}

	@Test
	public void tantargyHozzaadasaTest3() throws TanulmanyiRendszerKivetel {
		Szak ptibsc = new Szak("pti", Szint.BSc);
		Szak ptimsc = new Szak("pti", Szint.MSc);
		Tantargy tantargy1 = new Tantargy("kód", "név", 5, ptimsc);
		Tantargy tantargy2 = new Tantargy("kód", "név", 5, ptibsc);
		to.tantárgyHozzáadása(tantargy1);
		to.tantárgyHozzáadása(tantargy2);
	}

	@Test(expected = TanulmanyiRendszerKivetel.class)
	public void tantargyHozzaadasaTest4() throws TanulmanyiRendszerKivetel {
		Szak ptimsc = new Szak("pti", Szint.MSc);
		Tantargy tantargy = new Tantargy("kód", "név", 5, ptimsc);
		to.tantárgyHozzáadása(tantargy);
		to.tantárgyHozzáadása(tantargy);
	}

	@Test
	public void meghirdetettTantargyHozzaadasaTest1()
			throws TanulmanyiRendszerKivetel {
		Tantargy t1 = new Tantargy("INDK721L", "háló", 5, new Szak("pti",
				Szint.BSc));
		Oktato o1 = new Oktato("vez", "ker", "vezker", "jelszo", new Date(), 1);
		to.setAktuálisFélév(new Felev(new Idoszak(new Date(0), new Date(1)),
				new Idoszak(new Date(2), new Date(3))));
		to.tantárgyMeghirdetése(t1, o1, Kozpont.getAktuálisFélév(),
				new Idopont(Napok.Hétfő, 12), "IK-F0");
	}

	@Test(expected = TanulmanyiRendszerKivetel.class)
	public void meghirdetettTantargyHozzaadasaTest2()
			throws TanulmanyiRendszerKivetel {
		Tantargy t1 = new Tantargy("INDK721L", "háló", 5, new Szak("pti",
				Szint.BSc));
		Oktato o1 = new Oktato("vez", "ker", "vezker", "jelszo", new Date(), 1);
		to.setAktuálisFélév(new Felev(new Idoszak(new Date(0), new Date(1)),
				new Idoszak(new Date(2), new Date(3))));
		to.tantárgyMeghirdetése(t1, o1, Kozpont.getAktuálisFélév(),
				new Idopont(Napok.Hétfő, 12), "IK-F0");
		to.tantárgyMeghirdetése(t1, o1, Kozpont.getAktuálisFélév(),
				new Idopont(Napok.Hétfő, 12), "IK-F0");
	}

	@Test
	public void gyakorlatiCsoportHozzaadasaTest1()
			throws TanulmanyiRendszerKivetel {
		Tantargy t1 = new Tantargy("INDK721L", "háló", 5, new Szak("pti",
				Szint.BSc));
		Oktato o1 = new Oktato("vez", "ker", "vezker", "jelszo", new Date(), 1);
		to.setAktuálisFélév(new Felev(new Idoszak(new Date(0), new Date(1)),
				new Idoszak(new Date(2), new Date(3))));
		MeghirdetettTantargy mt = to.tantárgyMeghirdetése(t1, o1,
				Kozpont.getAktuálisFélév(), new Idopont(Napok.Hétfő, 12),
				"IK-F0");
		to.gyakorlatiCsoportHozzáadása(mt, o1, "IK-F03", new Idopont(
				Napok.Csütörtök, 10));
		to.gyakorlatiCsoportHozzáadása(mt, o1, "IK-F03", new Idopont(
				Napok.Csütörtök, 12));
	}

	@Test(expected = TanulmanyiRendszerKivetel.class)
	public void gyakorlatiCsoportHozzaadasaTest2()
			throws TanulmanyiRendszerKivetel {
		Tantargy t1 = new Tantargy("INDK721L", "háló", 5, new Szak("pti",
				Szint.BSc));
		Oktato o1 = new Oktato("vez", "ker", "vezker", "jelszo", new Date(), 1);
		to.setAktuálisFélév(new Felev(new Idoszak(new Date(0), new Date(1)),
				new Idoszak(new Date(2), new Date(3))));
		MeghirdetettTantargy mt = to.tantárgyMeghirdetése(t1, o1,
				Kozpont.getAktuálisFélév(), new Idopont(Napok.Hétfő, 12),
				"IK-F0");
		to.gyakorlatiCsoportHozzáadása(mt, o1, "IK-F03", new Idopont(
				Napok.Csütörtök, 10));
		to.gyakorlatiCsoportHozzáadása(mt, o1, "IK-F03", new Idopont(
				Napok.Csütörtök, 10));
	}

}

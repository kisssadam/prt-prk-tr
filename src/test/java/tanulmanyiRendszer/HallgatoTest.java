package tanulmanyiRendszer;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tanulmanyiRendszer.Idopont.Napok;
import tanulmanyiRendszer.Szak.Szint;

public class HallgatoTest {

	private Hallgato hallgato;
	private Felev felev;
	private TanulmanyiOsztaly to;
	private Tantargy tantargy1;
	private Oktato oktato1;
	private MeghirdetettTantargy mt;
	private GyakorlatiCsoport gycs;
	private Oktato oktato2;
	private Vizsga vizsga;
	private Szak ptibsc;
	private Tantargy tantargy2;
	private Idopont idopont;
	
	@Before
	public void init() throws TanulmanyiRendszerKivetel {
		Kozpont.init();
		ptibsc = new Szak("pti", Szint.BSc);
		hallgato = new Hallgato("Kiss", "Adam", "kissadam", "kissadam", new Date(0), ptibsc);
		felev = new Felev(new Idoszak(new Date(0), new Date(1)), new Idoszak(new Date(2), new Date(3)));
		to = new TanulmanyiOsztaly("T", "O", "to", "to", new Date());
		tantargy1 = new Tantargy("PRTKOD", "Progtech", 5, ptibsc);
		tantargy2 = new Tantargy("RFTKOD", "Rendszerfejlesztés", 5, ptibsc, tantargy1);
		oktato1 = new Oktato("Kollár", "Lajos", "kollarl", "kollarl", new Date(1), 1);
		oktato2 = new Oktato("Jeszenszky", "Péter", "jeszy", "jeszy", new Date(1), 1);
		mt = new MeghirdetettTantargy(tantargy1, oktato1, felev, new Idopont(Napok.Kedd, 12), "IK-F0");
		gycs = new GyakorlatiCsoport(oktato1, "IK-204", new Idopont(Napok.Szerda, 14));
		vizsga = new Vizsga(mt, new Date(1), "IK-F0");
		idopont = new Idopont(Napok.Hétfő, 12);
	}

	@Test(expected = TanulmanyiRendszerKivetel.class)
	public void beiratkozasTest1() throws TanulmanyiRendszerKivetel {
		hallgato.beiratkozás();
	}

	@Test
	public void beiratkozasTest2() throws TanulmanyiRendszerKivetel {
		Kozpont.setAktuálisFélév(felev);
		hallgato.beiratkozás();
	}

	@Test
	public void beiratkozasTest3() throws TanulmanyiRendszerKivetel {
		to.setAktuálisFélév(felev);

		hallgato.beiratkozás();
		assertEquals(felev, hallgato.getAktívFélév());

		Felev f2 = new Felev(new Idoszak(new Date(10), new Date(11)),
				new Idoszak(new Date(12), new Date(13)));
		to.setAktuálisFélév(f2);
		hallgato.beiratkozás();
	}

	@Test(expected = TanulmanyiRendszerKivetel.class)
	public void beiratkozasTest4() throws TanulmanyiRendszerKivetel {
		Kozpont.setAktuálisFélév(null);
		hallgato.beiratkozás();
	}

	@Test
	public void vanÉrvényesVizsgajelentkezéseTest1() throws TanulmanyiRendszerKivetel {
		to.újFélév(felev.getSzorgalmiIdőszak(), felev.getVizsgaIdőszak(), true);
		Kozpont.getAktuálisFélév().equals(felev);
		oktato1.vizsgahirdetés(mt, vizsga);
		assertEquals(false, hallgato.vanÉrvényesVizsgajelentkezése(vizsga));
	}
	
	@Test(expected = AssertionError.class)
	public void vanÉrvényesVizsgajelentkezéseTest2() throws TanulmanyiRendszerKivetel {
		to.újFélév(felev.getSzorgalmiIdőszak(), felev.getVizsgaIdőszak(), true);
		Kozpont.getAktuálisFélév().equals(felev);
		oktato1.vizsgahirdetés(mt, vizsga);
		assertEquals(true, hallgato.vanÉrvényesVizsgajelentkezése(vizsga));
	}
	
	@Test
	public void vanÉrvényesVizsgajelentkezéseTest3() throws TanulmanyiRendszerKivetel {
		to.újFélév(felev.getSzorgalmiIdőszak(), felev.getVizsgaIdőszak(), true);
		Kozpont.getAktuálisFélév().equals(felev);
		to.gyakorlatiCsoportHozzáadása(mt, gycs.getGyakorlatvezető(), gycs.getTerem(), gycs.getIdőpont());
		hallgato.felveszTantárgy(mt, gycs);
		oktato1.aláírásBeírása(hallgato, mt, true);
		oktato1.vizsgahirdetés(mt, vizsga);
		assertEquals(false, hallgato.vanÉrvényesVizsgajelentkezése(vizsga));
		hallgato.vizsgajelentkezés(vizsga);
		assertEquals(true, hallgato.vanÉrvényesVizsgajelentkezése(vizsga));
	}
	
	@Test
	public void vanÉrvényesVizsgajelentkezéseTest4() throws TanulmanyiRendszerKivetel {
		to.újFélév(felev.getSzorgalmiIdőszak(), felev.getVizsgaIdőszak(), true);
		Kozpont.getAktuálisFélév().equals(felev);
		to.gyakorlatiCsoportHozzáadása(mt, gycs.getGyakorlatvezető(), gycs.getTerem(), gycs.getIdőpont());
		hallgato.felveszTantárgy(mt, gycs);
		oktato1.aláírásBeírása(hallgato, mt, true);
		oktato1.vizsgahirdetés(mt, vizsga);
		assertEquals(false, hallgato.vanÉrvényesVizsgajelentkezése(vizsga));
		hallgato.vizsgajelentkezés(vizsga);
		oktato1.érdemjegyBeírása(hallgato, vizsga, 1);
		assertEquals(false, hallgato.vanÉrvényesVizsgajelentkezése(vizsga));
	}

	@Test
	public void isTeljesitettTantargyTest1() throws TanulmanyiRendszerKivetel {
		to.setAktuálisFélév(felev);
		to.gyakorlatiCsoportHozzáadása(mt, gycs.getGyakorlatvezető(), gycs.getTerem(), gycs.getIdőpont());
		hallgato.felveszTantárgy(mt, gycs);
		
		assertEquals(0, hallgato.getAktuálisFélévbenFelvettVizsgák().size());
		oktato1.aláírásBeírása(hallgato, mt, true);
		hallgato.vizsgajelentkezés(vizsga);
		assertEquals(1, hallgato.getAktuálisFélévbenFelvettVizsgák().size());
		assertEquals(false, hallgato.isTeljesítettTantárgy(tantargy1));
		assertEquals(false, hallgato.isTeljesítettTantárgy(new Tantargy("INDK721", "háló", 5, ptibsc)));
		oktato1.érdemjegyBeírása(hallgato, vizsga, 5);
		assertEquals(true, hallgato.isTeljesítettTantárgy(tantargy1));
	}

	@Test(expected = TanulmanyiRendszerKivetel.class)
	public void isTeljesitettTantargyTest2() throws TanulmanyiRendszerKivetel {
		to.gyakorlatiCsoportHozzáadása(mt, gycs.getGyakorlatvezető(), gycs.getTerem(), gycs.getIdőpont());
		hallgato.felveszTantárgy(mt, gycs);
		assertEquals(0, hallgato.getAktuálisFélévbenFelvettVizsgák().size());
		Vizsga vizsga = new Vizsga(mt, new Date(40013), "IK-F0");
		oktato1.aláírásBeírása(hallgato, mt, false);
		hallgato.vizsgajelentkezés(vizsga);
		assertEquals(1, hallgato.getAktuálisFélévbenFelvettVizsgák().size());
	}

	@Test
	public void isTantárgyElőfeltételekTeljesülnekTest1() throws TanulmanyiRendszerKivetel {
		to.tantárgyHozzáadása(tantargy1);
		to.gyakorlatiCsoportHozzáadása(mt, gycs.getGyakorlatvezető(), gycs.getTerem(), gycs.getIdőpont());
		assertEquals(true, hallgato.isTantárgyElőfeltételekTeljesülnek(tantargy1));
	}
	
	@Test(expected = TanulmanyiRendszerKivetel.class)
	public void isTantárgyElőfeltételekTeljesülnekTest2() throws TanulmanyiRendszerKivetel {
		to.tantárgyHozzáadása(tantargy1);
		to.tantárgyHozzáadása(tantargy2);
		
		to.tantárgyMeghirdetése(tantargy1, oktato1, felev, idopont, "IK-F0");
		to.gyakorlatiCsoportHozzáadása(mt, gycs.getGyakorlatvezető(), gycs.getTerem(), gycs.getIdőpont());
		to.félévLezárása();
		
		Felev felev2 = new Felev(new Idoszak(new Date(10), new Date(11)), new Idoszak(new Date(12), new Date(13)));
		to.újFélév(felev2.getSzorgalmiIdőszak(), felev2.getVizsgaIdőszak(), true);		
		
		MeghirdetettTantargy mt2 = to.tantárgyMeghirdetése(tantargy2, oktato1, felev2, idopont, "IK-F01");
		GyakorlatiCsoport gycs2 = to.gyakorlatiCsoportHozzáadása(mt2, gycs.getGyakorlatvezető(), gycs.getTerem(), gycs.getIdőpont());
		hallgato.beiratkozás();
		hallgato.felveszTantárgy(mt2, gycs2);
	}
	
	@Test
	public void isTantárgyElőfeltételekTeljesülnekTest3() throws TanulmanyiRendszerKivetel {
		to.tantárgyHozzáadása(tantargy1);
		to.tantárgyHozzáadása(tantargy2);
		to.újFélév(felev.getSzorgalmiIdőszak(), felev.getVizsgaIdőszak(), true);
		
		MeghirdetettTantargy mt1 = to.tantárgyMeghirdetése(tantargy1, oktato1, felev, idopont, "IK-F0");
		to.gyakorlatiCsoportHozzáadása(mt1, gycs.getGyakorlatvezető(), gycs.getTerem(), gycs.getIdőpont());
		
		hallgato.felveszTantárgy(mt1, gycs);
		oktato1.aláírásBeírása(hallgato, mt1, true);
		
		Vizsga vizsga1 = new Vizsga(mt1, new Date(12), "IK-F0");
		
		oktato1.vizsgahirdetés(mt1, vizsga1);
		hallgato.vizsgajelentkezés(vizsga1);
		oktato1.érdemjegyBeírása(hallgato, vizsga1, 5);
		to.félévLezárása();

		// a következő félévben felveszi a hallgató az előfeltételes tantárgyat.
		
		Felev felev2 = new Felev(new Idoszak(new Date(10), new Date(11)), new Idoszak(new Date(12), new Date(13)));
		to.újFélév(felev2.getSzorgalmiIdőszak(), felev2.getVizsgaIdőszak(), true);
		hallgato.beiratkozás();

		MeghirdetettTantargy mt2 = to.tantárgyMeghirdetése(tantargy2, oktato1, felev2, idopont, "IK-F01");
		GyakorlatiCsoport gycs2 = to.gyakorlatiCsoportHozzáadása(mt2, gycs.getGyakorlatvezető(), gycs.getTerem(), gycs.getIdőpont());
		hallgato.felveszTantárgy(mt2, gycs2);
	}
	
	@Test
	public void isTantárgyElőfeltételekTeljesülnekTest4() throws TanulmanyiRendszerKivetel {
		to.tantárgyHozzáadása(tantargy1);
		to.tantárgyHozzáadása(tantargy2);
		to.újFélév(felev.getSzorgalmiIdőszak(), felev.getVizsgaIdőszak(), true);
		
		MeghirdetettTantargy mt1 = to.tantárgyMeghirdetése(tantargy1, oktato1, felev, idopont, "IK-F0");
		to.gyakorlatiCsoportHozzáadása(mt1, gycs.getGyakorlatvezető(), gycs.getTerem(), gycs.getIdőpont());
		
		hallgato.felveszTantárgy(mt1, gycs);
		oktato1.aláírásBeírása(hallgato, mt1, true);
		
		Vizsga vizsga1 = new Vizsga(mt1, new Date(12), "IK-F0");
		
		oktato1.vizsgahirdetés(mt1, vizsga1);
		hallgato.vizsgajelentkezés(vizsga1);
		oktato1.érdemjegyBeírása(hallgato, vizsga1, 5);
		to.félévLezárása();

		// a következő félévben felveszi a hallgató az előfeltételes tantárgyat.
		
		Felev felev2 = new Felev(new Idoszak(new Date(10), new Date(11)), new Idoszak(new Date(12), new Date(13)));
		to.újFélév(felev2.getSzorgalmiIdőszak(), felev2.getVizsgaIdőszak(), true);
		hallgato.beiratkozás();

		MeghirdetettTantargy mt2 = to.tantárgyMeghirdetése(tantargy2, oktato1, felev2, idopont, "IK-F01");
		GyakorlatiCsoport gycs2 = to.gyakorlatiCsoportHozzáadása(mt2, gycs.getGyakorlatvezető(), gycs.getTerem(), gycs.getIdőpont());
		hallgato.felveszTantárgy(mt2, gycs2);
		
		oktato1.aláírásBeírása(hallgato, mt2, true);
		Vizsga vizsga2 = new Vizsga(mt2, new Date(13), "IK-F0");
		oktato1.vizsgahirdetés(mt2, vizsga2);
		hallgato.vizsgajelentkezés(vizsga2);
	}
	
	@Test
	public void isTantárgyElőfeltételekTeljesülnekTest5() throws TanulmanyiRendszerKivetel {
		to.tantárgyHozzáadása(tantargy1);
		to.tantárgyHozzáadása(tantargy2);
		to.újFélév(felev.getSzorgalmiIdőszak(), felev.getVizsgaIdőszak(), true);
		
		MeghirdetettTantargy mt1 = to.tantárgyMeghirdetése(tantargy1, oktato1, felev, idopont, "IK-F0");
		to.gyakorlatiCsoportHozzáadása(mt1, gycs.getGyakorlatvezető(), gycs.getTerem(), gycs.getIdőpont());
		
		hallgato.felveszTantárgy(mt1, gycs);
		oktato1.aláírásBeírása(hallgato, mt1, true);
		
		Vizsga vizsga1 = new Vizsga(mt1, new Date(12), "IK-F0");
		
		oktato1.vizsgahirdetés(mt1, vizsga1);
		hallgato.vizsgajelentkezés(vizsga1);
		oktato1.érdemjegyBeírása(hallgato, vizsga1, 5);
		to.félévLezárása();

		// a következő félévben felveszi a hallgató az előfeltételes tantárgyat.
		
		Felev felev2 = new Felev(new Idoszak(new Date(10), new Date(11)), new Idoszak(new Date(12), new Date(13)));
		to.újFélév(felev2.getSzorgalmiIdőszak(), felev2.getVizsgaIdőszak(), true);
		hallgato.beiratkozás();

		MeghirdetettTantargy mt2 = to.tantárgyMeghirdetése(tantargy2, oktato1, felev2, idopont, "IK-F01");
		GyakorlatiCsoport gycs2 = to.gyakorlatiCsoportHozzáadása(mt2, gycs.getGyakorlatvezető(), gycs.getTerem(), gycs.getIdőpont());
		hallgato.felveszTantárgy(mt2, gycs2);
		
		oktato1.aláírásBeírása(hallgato, mt2, true);
		Vizsga vizsga2 = new Vizsga(mt2, new Date(13), "IK-F0");
		oktato1.vizsgahirdetés(mt2, vizsga2);
		hallgato.vizsgajelentkezés(vizsga2);
		oktato1.érdemjegyBeírása(hallgato, vizsga2, 5);
		
		Tantargy tantargy3 = new Tantargy("t3", "prog1", 10, ptibsc, tantargy1);
		to.tantárgyHozzáadása(tantargy3);
		MeghirdetettTantargy mt3 = to.tantárgyMeghirdetése(tantargy3, oktato2, felev2, idopont, "IK-201");
		GyakorlatiCsoport gycs3 = to.gyakorlatiCsoportHozzáadása(mt3, oktato2, "IK-F02", idopont);
		hallgato.felveszTantárgy(mt3, gycs3);
		
		Vizsga vizsga3 = new Vizsga(mt3, new Date(13), "IK-F0");
		oktato2.vizsgahirdetés(mt3, vizsga3);
		oktato2.aláírásBeírása(hallgato, mt3, true);
		hallgato.vizsgajelentkezés(vizsga3);
		oktato2.érdemjegyBeírása(hallgato, vizsga3, 5);
	}
	
	@Test
	public void tantargyFelvetelekSzamaTest() throws TanulmanyiRendszerKivetel {
		to.tantárgyHozzáadása(tantargy1);
		to.újFélév(felev.getSzorgalmiIdőszak(), felev.getVizsgaIdőszak(), true);
		to.gyakorlatiCsoportHozzáadása(mt, gycs.getGyakorlatvezető(), gycs.getTerem(), gycs.getIdőpont());
		assertEquals(0, hallgato.tantárgyfelvételekSzáma(tantargy1));
		hallgato.felveszTantárgy(mt, gycs);
		assertEquals(1, hallgato.tantárgyfelvételekSzáma(tantargy1));
	}
	
	/**
	 * Négyszer próbál meg felvenni egy tárgyat.
	 * @throws TanulmanyiRendszerKivetel 
	 */
	@Test(expected = TanulmanyiRendszerKivetel.class)
	public void felveszTantárgyTest1() throws TanulmanyiRendszerKivetel {		
		String előadásTerem = "IK-F01";
		String terem = "IK-204";
		
		Tantargy tantargy = new Tantargy("KOD", "Tantargynév", 4, ptibsc);
		
		Felev felev1 = to.újFélév(new Idoszak(new Date(0), new Date(1)), new Idoszak(new Date(2), new Date(3)), true);
		hallgato.beiratkozás();
		MeghirdetettTantargy mt1 = new MeghirdetettTantargy(tantargy, oktato1, felev1, idopont, előadásTerem);
		GyakorlatiCsoport gycs1 = to.gyakorlatiCsoportHozzáadása(mt1, oktato1, terem, idopont);
		hallgato.felveszTantárgy(mt1, gycs1);
		to.félévLezárása();
		
		Felev felev2 = to.újFélév(new Idoszak(new Date(4), new Date(5)), new Idoszak(new Date(6), new Date(7)), true);
		hallgato.beiratkozás();
		MeghirdetettTantargy mt2 = new MeghirdetettTantargy(tantargy, oktato1, felev2, idopont, előadásTerem);
		GyakorlatiCsoport gycs2 = to.gyakorlatiCsoportHozzáadása(mt2, oktato1, terem, idopont);
		hallgato.felveszTantárgy(mt2, gycs2);
		to.félévLezárása();
		
		Felev felev3 = to.újFélév(new Idoszak(new Date(8), new Date(9)), new Idoszak(new Date(10), new Date(11)), true);
		hallgato.beiratkozás();
		MeghirdetettTantargy mt3 = new MeghirdetettTantargy(tantargy, oktato1, felev3, idopont, előadásTerem);
		GyakorlatiCsoport gycs3 = to.gyakorlatiCsoportHozzáadása(mt3, oktato1, terem, idopont);
		hallgato.felveszTantárgy(mt3, gycs3);
		to.félévLezárása();
		
		Felev felev4 = to.újFélév(new Idoszak(new Date(12), new Date(13)), new Idoszak(new Date(14), new Date(15)), true);
		hallgato.beiratkozás();
		MeghirdetettTantargy mt4 = new MeghirdetettTantargy(tantargy, oktato1, felev4, idopont, előadásTerem);
		GyakorlatiCsoport gycs4 = to.gyakorlatiCsoportHozzáadása(mt4, oktato1, terem, idopont);
		hallgato.felveszTantárgy(mt4, gycs4);
		to.félévLezárása();
	}
	
	@Test
	public void félévLezárásaTest1() throws TanulmanyiRendszerKivetel {
		Kozpont.setAktuálisFélév(felev);
		mt.getGyakorlatiCsoportok().add(gycs);
		hallgato.felveszTantárgy(mt, gycs);
		assertEquals(0, hallgato.getAktuálisFélévbenFelvettVizsgák().size());
		Vizsga vizsga = new Vizsga(mt, new Date(40013), "IK-F0");
		oktato1.aláírásBeírása(hallgato, mt, true);
		FelvettVizsga felvettVizsga = hallgato.vizsgajelentkezés(vizsga);
		assertEquals(1, hallgato.getAktuálisFélévbenFelvettVizsgák().size());
		assertEquals(-1, felvettVizsga.getÉrdemjegy());
		hallgato.félévLezárása();
		assertEquals(1, felvettVizsga.getÉrdemjegy());
	}

	@Test(expected = TanulmanyiRendszerKivetel.class)
	public void felveszTantargyTest1() throws TanulmanyiRendszerKivetel {
		hallgato.felveszTantárgy(null, null);
	}

	@Test(expected = TanulmanyiRendszerKivetel.class)
	public void felveszTantargyTest2() throws TanulmanyiRendszerKivetel {
		mt.getGyakorlatiCsoportok().add(gycs);
		hallgato.felveszTantárgy(mt, gycs);
		hallgato.felveszTantárgy(mt, gycs);
	}

	@Test(expected = TanulmanyiRendszerKivetel.class)
	public void felveszTantargyTest3() throws TanulmanyiRendszerKivetel {
		mt.getGyakorlatiCsoportok().add(gycs);
		hallgato.felveszTantárgy(mt, gycs);
		hallgato.felveszTantárgy(mt, gycs);
	}

	@Test
	public void getAktuálisanMeghirdetettTantárgyhozTartozóFelvettVizsgákTest() throws TanulmanyiRendszerKivetel {
		to.setAktuálisFélév(new Felev(new Idoszak(new Date(0), new Date(1)), new Idoszak(new Date(2), new Date(3))));
		mt.getGyakorlatiCsoportok().add(gycs);
		hallgato.felveszTantárgy(mt, gycs);

		List<FelvettVizsga> felvettVizsgák = hallgato.getAktuálisanMeghirdetettTantárgyhozTartozóFelvettVizsgák(mt);
		assertEquals(0, felvettVizsgák.size());
		Vizsga vizsga = new Vizsga(mt, new Date(40013), "IK-F0");
		oktato2.aláírásBeírása(hallgato, mt, true);
		oktato2.vizsgahirdetés(mt, vizsga);
		hallgato.vizsgajelentkezés(vizsga);
		felvettVizsgák = hallgato.getAktuálisanMeghirdetettTantárgyhozTartozóFelvettVizsgák(mt);
		assertEquals(1, felvettVizsgák.size());
	}
	
	@Test
	public void equalsTest() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Hallgato h1 = new Hallgato("vezetéknév", "keresztnév", "felhasználónév", "jelszó", new Date(1), ptibsc);
		Hallgato h2 = new Hallgato("vezetéknév", "keresztnév", "felhasználónév", "jelszó", new Date(1), new Szak("pti", Szint.MSc));
		Oktato o1 = new Oktato("vezetéknév", "keresztnév", "felhasználónév", "jelszó", new Date(1), 1);
		assertEquals(h1, h1);
		assertNotEquals(h1, null);
		assertNotEquals(h1, "alma");
		assertNotEquals(h1, o1);
		assertNotEquals(h1, h2);
	}
	
	@Test
	public void hashCodeTest() {
		Hallgato h1 = new Hallgato("vezetéknév", "keresztnév", "felhasználónév", "jelszó", new Date(1), ptibsc);
		Hallgato h2 = new Hallgato("vezetéknév", "keresztnév", "felhasználónév", "jelszó", new Date(1), new Szak("pti", Szint.MSc));
		Oktato o1 = new Oktato("vezetéknév", "keresztnév", "felhasználónév", "jelszó", new Date(1), 1);
		assertEquals(h1.hashCode(), h1.hashCode());
		assertNotEquals(h1.hashCode(), null);
		assertNotEquals(h1.hashCode(), "alma".hashCode());
		assertNotEquals(h1.hashCode(), o1.hashCode());
		assertNotEquals(h1.hashCode(), h2.hashCode());
	}
	
}

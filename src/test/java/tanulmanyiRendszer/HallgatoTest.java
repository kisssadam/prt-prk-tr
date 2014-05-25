package tanulmanyiRendszer;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import tanulmanyiRendszer.Idopont.Napok;
import tanulmanyiRendszer.Szak.Szint;

public class HallgatoTest {

	private Hallgato hallgato;
	private Felev felev;
	private TanulmanyiOsztaly to;

	@Before
	public void init() throws TanulmanyiRendszerKivetel {
		Kozpont.init();
		hallgato = new Hallgato("Kiss", "Adam", "kissadam", "kissadam",
				new Date(0), new Szak("pti", Szint.BSc));
		felev = new Felev(new Idoszak(new Date(0), new Date(1)), new Idoszak(
				new Date(2), new Date(3)));
		to = new TanulmanyiOsztaly("T", "O", "to", "to", new Date());
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
	public void testMethod1() throws TanulmanyiRendszerKivetel {
		Tantargy tantargy = new Tantargy("tkod", "tnev", 5, new Szak("pti",
				Szint.BSc));
		Oktato oktato = new Oktato("K", "L", "kl", "klpw", new Date(1), 1);
		Kozpont.setAktuálisFélév(felev);
		MeghirdetettTantargy mt = new MeghirdetettTantargy(tantargy, oktato,
				felev, new Idopont(Napok.Kedd, 12), "IK-F0");
		GyakorlatiCsoport gycs = new GyakorlatiCsoport(oktato, "IK-104",
				new Idopont(Napok.Vasárnap, 14));
		mt.getGyakorlatiCsoportok().add(gycs);
		hallgato.felveszTantárgy(mt, gycs);
		assertEquals(0, hallgato.getAktuálisFélévbenFelvettVizsgák().size());
		Vizsga vizsga = new Vizsga(mt, new Date(40013), "IK-F0");
		oktato.aláírásBeírása(hallgato, mt, true);
		FelvettVizsga felvettVizsga = hallgato.vizsgajelentkezés(vizsga);
		assertEquals(1, hallgato.getAktuálisFélévbenFelvettVizsgák().size());
		hallgato.érdemjegyBeírása(felvettVizsga, 5);
	}

	@Test(expected = TanulmanyiRendszerKivetel.class)
	public void testMethod2() throws TanulmanyiRendszerKivetel {
		Tantargy tantargy = new Tantargy("tkod", "tnev", 5, new Szak("pti",
				Szint.BSc));
		Oktato oktato = new Oktato("K", "L", "kl", "klpw", new Date(1), 1);
		Felev felev = new Felev(new Idoszak(new Date(0), new Date(1)),
				new Idoszak(new Date(2), new Date(3)));
		MeghirdetettTantargy mt = new MeghirdetettTantargy(tantargy, oktato,
				felev, new Idopont(Napok.Kedd, 12), "IK-F0");
		GyakorlatiCsoport gycs = new GyakorlatiCsoport(oktato, "IK-104",
				new Idopont(Napok.Vasárnap, 14));
		mt.getGyakorlatiCsoportok().add(gycs);
		hallgato.felveszTantárgy(mt, gycs);
		assertEquals(0, hallgato.getAktuálisFélévbenFelvettVizsgák().size());
		Vizsga vizsga = new Vizsga(mt, new Date(40013), "IK-F0");
		oktato.aláírásBeírása(hallgato, mt, false);
		hallgato.vizsgajelentkezés(vizsga);
		assertEquals(1, hallgato.getAktuálisFélévbenFelvettVizsgák().size());

	}

	@Test
	public void félévLezárásaTest1() throws TanulmanyiRendszerKivetel {
		Tantargy tantargy = new Tantargy("tkod", "tnev", 5, new Szak("pti",
				Szint.BSc));
		Oktato oktato = new Oktato("K", "L", "kl", "klpw", new Date(1), 1);
		Kozpont.setAktuálisFélév(felev);
		MeghirdetettTantargy mt = new MeghirdetettTantargy(tantargy, oktato,
				felev, new Idopont(Napok.Kedd, 12), "IK-F0");
		GyakorlatiCsoport gycs = new GyakorlatiCsoport(oktato, "IK-104",
				new Idopont(Napok.Vasárnap, 14));
		mt.getGyakorlatiCsoportok().add(gycs);
		hallgato.felveszTantárgy(mt, gycs);
		assertEquals(0, hallgato.getAktuálisFélévbenFelvettVizsgák().size());
		Vizsga vizsga = new Vizsga(mt, new Date(40013), "IK-F0");
		oktato.aláírásBeírása(hallgato, mt, true);
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
		Tantargy tantargy = new Tantargy("tkod", "tnev", 5, new Szak("pti",
				Szint.BSc));
		Oktato oktato = new Oktato("K", "L", "kl", "klpw", new Date(1), 1);
		Felev felev = new Felev(new Idoszak(new Date(0), new Date(1)),
				new Idoszak(new Date(2), new Date(3)));
		MeghirdetettTantargy mt = new MeghirdetettTantargy(tantargy, oktato,
				felev, new Idopont(Napok.Kedd, 12), "IK-F0");
		GyakorlatiCsoport gycs = new GyakorlatiCsoport(oktato, "IK-104",
				new Idopont(Napok.Vasárnap, 14));
		mt.getGyakorlatiCsoportok().add(gycs);
		hallgato.felveszTantárgy(mt, gycs);
		hallgato.felveszTantárgy(mt, gycs);
	}

	@Test(expected = TanulmanyiRendszerKivetel.class)
	public void felveszTantargyTest3() throws TanulmanyiRendszerKivetel {
		Tantargy tantargy = new Tantargy("tkod", "tnev", 5, new Szak("pti",
				Szint.BSc));
		Oktato oktato = new Oktato("K", "L", "kl", "klpw", new Date(1), 1);
		Felev felev = new Felev(new Idoszak(new Date(0), new Date(1)),
				new Idoszak(new Date(2), new Date(3)));
		MeghirdetettTantargy mt = new MeghirdetettTantargy(tantargy, oktato,
				felev, new Idopont(Napok.Kedd, 12), "IK-F0");
		GyakorlatiCsoport gycs = new GyakorlatiCsoport(oktato, "IK-104",
				new Idopont(Napok.Vasárnap, 14));
		mt.getGyakorlatiCsoportok().add(gycs);
		hallgato.felveszTantárgy(mt, gycs);
		hallgato.felveszTantárgy(mt, gycs);
	}

}

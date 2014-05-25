package tanulmanyiRendszer;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import tanulmanyiRendszer.Idopont.Napok;
import tanulmanyiRendszer.Szak.Szint;

public class FelvettTantargyTest {

	private Felev felev;
	private Oktato oktato;
	private Szak ptibsc;
	private Tantargy tantargy;
	private Idopont idopont;
	private MeghirdetettTantargy mt1;
	private GyakorlatiCsoport gycs;
	private FelvettTantargy ft1;
	private FelvettTantargy ft2;
	private MeghirdetettTantargy mt2;
	private FelvettTantargy ft3;

	@Before
	public void init() throws TanulmanyiRendszerKivetel {
		Kozpont.init();
		felev = new Felev(new Idoszak(new Date(0), new Date(1)), new Idoszak(new Date(2), new Date(3)));
		Kozpont.setAktuálisFélév(felev);
		oktato = new Oktato("Oktato", "Jozsef", "oktatjozsef", "pw", new Date(0), 1);
		ptibsc = new Szak("Programtervező Informatikus", Szint.BSc);
		tantargy = new Tantargy("INDK301", "Prog1", 5, ptibsc);
		idopont = new Idopont(Napok.Hétfő, 10);
		mt1 = new MeghirdetettTantargy(tantargy, oktato, felev, idopont, "IK-F0");
		mt2 = new MeghirdetettTantargy(tantargy, oktato, felev, idopont, "IK-F1");
		gycs = new GyakorlatiCsoport(oktato, "IK-F03", idopont);
		mt1.getGyakorlatiCsoportok().add(gycs);
		mt2.getGyakorlatiCsoportok().add(gycs);
		ft1 = new FelvettTantargy(mt1, gycs);
		ft2 = new FelvettTantargy(mt1, gycs);
		ft3 = new FelvettTantargy(mt2, gycs);
	}
	
	@Test
	public void equalsTest() {
		assertNotEquals(ft1, null);
		assertEquals(ft1, ft1);
		assertEquals(ft1, ft2);
		assertNotEquals(ft1, ft3);
		assertNotEquals(ft1, new Date(0));
	}
	
	@Test
	public void hashCodeTest() {
		assertNotEquals(ft1.hashCode(), null);
		assertEquals(ft1.hashCode(), ft1.hashCode());
		assertEquals(ft1.hashCode(), ft2.hashCode());
		assertNotEquals(ft1.hashCode(), ft3.hashCode());
		assertNotEquals(ft1.hashCode(), new Date(0).hashCode());
	}
	
	@Test
	public void gyakorlatiCsoportTest1(){
		assertEquals(gycs, ft1.getFelvettGyakorlatiCsoport());
	}
	
	@Test(expected = TanulmanyiRendszerKivetel.class)
	public void gyakorlatiCsoportTest2() throws TanulmanyiRendszerKivetel{
		GyakorlatiCsoport gycs2 = new GyakorlatiCsoport(oktato, "IK-106", idopont);
		ft1.setFelvettGyakorlatiCsoport(gycs2);
	}
	
}

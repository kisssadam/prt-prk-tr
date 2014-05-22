package db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tanulmanyiRendszer.Felev;
import tanulmanyiRendszer.Idoszak;
import tanulmanyiRendszer.Kozpont;
import tanulmanyiRendszer.Oktato;
import tanulmanyiRendszer.Szak;
import tanulmanyiRendszer.Szak.Szint;
import tanulmanyiRendszer.Tantargy;
import tanulmanyiRendszer.TanulmanyiOsztaly;

/**
 * Az adatbázisból való beolvasást segíti.
 * 
 * @author adam
 *
 */
public class DataLoader {
	/**
	 * Ennek a segítségével lehet naplóüzeneteket létrehozni.
	 */
	private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);
	
	/**
	 * Beolvassa az adatokat az adatbázisból.
	 * 
	 * @throws IOException ha nem tud kapcsolódni az adatbázishoz.
	 * @throws SQLException ha egy adatbázisbeli hiba adódik.
	 */
	public static void init() throws IOException, SQLException {
		logger.info("Adatok betöltése az adatbázisból.");
		
		initFelevLista();
		initSzakLista();
		initTantargyLista();
		initTanulmanyiOsztalyDolgozoLista();
		initOktatoLista();
		
		logger.info("Az adatok betöltése sikeres volt.");
	}
	
	/**
	 * Beolvassa a {@link Felev}-eket az adatbázisból.
	 * 
	 * @throws IOException ha nem tud kapcsolódni az adatbázishoz.
	 * @throws SQLException ha egy adatbázisbeli hiba adódik.
	 */
	private static void initFelevLista() throws IOException, SQLException {
		Connection conn = ConnectionHelper.getConnection();
		
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT id, szorg_eleje, szorg_vege, vizsg_eleje, vizsg_vege, aktualis_felev FROM prt_felev");
		
		while (rset.next()) {
			int id = rset.getInt("id");
			Idoszak szorgalmiIdőszak = new Idoszak(rset.getDate("szorg_eleje"), rset.getDate("szorg_vege"));
			Idoszak vizsgaIdőszak = new Idoszak(rset.getDate("vizsg_eleje"), rset.getDate("vizsg_vege"));
			
			Felev felev = new Felev(id, szorgalmiIdőszak, vizsgaIdőszak);
			Kozpont.getFélévLista().add(felev);
			
			if (rset.getInt("aktualis_felev") == 1) {
				Kozpont.setAktuálisFélév(felev);
			}
		}
		rset.close();
		stmt.close();
	}
	
	/**
	 * Beolvassa a {@link Szak}-okat az adatbázisból.
	 * 
	 * @throws SQLException ha egy adatbázisbeli hiba adódik.
	 * @throws IOException ha nem tud kapcsolódni az adatbázishoz.
	 */
	private static void initSzakLista() throws SQLException, IOException {
		Connection conn = ConnectionHelper.getConnection();
		
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT id, nev, szint FROM prt_szak");
		
		while (rset.next()) {
			int id = rset.getInt(1);
			String név = rset.getString(2);
			Szint szint = Szint.BSc;
			switch (rset.getString(3)) {
			case "BSc":
				szint = Szint.BSc;
				break;
			case "MSc":
				szint = Szint.MSc;
			}
			Kozpont.getSzakLista().add(new Szak(id, név, szint));
		}
		stmt.close();
	}
	
	/**
	 * Beolvassa a {@link Tantargy}-akat az adatbázisból.
	 * 
	 * @throws IOException ha nem tud kapcsolódni az adatbázishoz.
	 * @throws SQLException ha egy adatbázisbeli hiba adódik.
	 */
	private static void initTantargyLista() throws IOException, SQLException {
		Connection conn = ConnectionHelper.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement("SELECT id, tantargykod, nev, kredit, szak_id FROM prt_tantargy");
		ResultSet rset = pstmt.executeQuery();
		
		while (rset.next()) {
			int id = rset.getInt("id");
			String tantárgykód = rset.getString("tantargykod");
			String név = rset.getString("nev");
			int kredit = rset.getInt("kredit");
			Szak szak = getSzakById(rset.getInt("szak_id"));
			Kozpont.getTantárgyLista().add(new Tantargy(id, tantárgykód, név, kredit, szak));
		}
		rset.close();
		pstmt.close();
		
		pstmt = conn.prepareStatement("SELECT elofeltetel_id FROM prt_tantargy_elofeltetelei WHERE tantargy_id = ?");
		for (Tantargy tantargy : Kozpont.getTantárgyLista()) {
			pstmt.setInt(1, tantargy.getId());
			rset = pstmt.executeQuery();
			while (rset.next()) {
				tantargy.getElőfeltételek().add(getTantargyById(rset.getInt("elofeltetel_id")));
			}
		}
		rset.close();
		pstmt.close();
	}
	
	/**
	 * Beolvassa a {@link TanulmanyiOsztaly}-okat az adatbázisból.
	 * 
	 * @throws IOException ha nem tud kapcsolódni az adatbázishoz.
	 * @throws SQLException ha egy adatbázisbeli hiba adódik.
	 */
	private static void initTanulmanyiOsztalyDolgozoLista() throws IOException, SQLException {
		Connection conn = ConnectionHelper.getConnection();
		
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT id, vezeteknev, keresztnev, felhasznalonev, jelszo, szuletesnap FROM prt_tanulmanyi_osztaly");
		
		whileQuery: while (rset.next()) {
			int id = rset.getInt("id");
			String vezetéknév = rset.getString("vezeteknev");
			String keresztnév = rset.getString("keresztnev");
			String felhasználónév = rset.getString("felhasznalonev");
			String jelszó = rset.getString("jelszo");
			Date születésnap = rset.getDate("szuletesnap");
			
			TanulmanyiOsztaly to = new TanulmanyiOsztaly(id, vezetéknév, keresztnév, felhasználónév, jelszó, születésnap);
			
			// ellenőrzi, hogy már benne van-e a listában a jelenleg lekérdezett to-s dolgozó.
			for (TanulmanyiOsztaly tanulmanyiOsztaly : Kozpont.getTanulmányiOsztályDolgozóLista()) {
				if (tanulmanyiOsztaly.getId() == id) {
					continue whileQuery;
				}
			}
			
			// nem volt benne a listában, ezért hozzá kell adni
			Kozpont.getTanulmányiOsztályDolgozóLista().add(to);
		}
		rset.close();
		stmt.close();
	}
	
	/**
	 * Beolvassa az {@link Oktato}-kat az adatbázisból.
	 * 
	 * @throws IOException ha nem tud kapcsolódni az adatbázishoz.
	 * @throws SQLException ha egy adatbázisbeli hiba adódik.
	 */
	private static void initOktatoLista() throws IOException, SQLException {
		Connection conn = ConnectionHelper.getConnection();
		
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT id, vezeteknev, keresztnev, felhasznalonev, jelszo, szuletesnap, fizetes FROM prt_oktato");
		
		whileQuery: while (rset.next()) {
			int id = rset.getInt("id");
			String vezetéknév = rset.getString("vezeteknev");
			String keresztnév = rset.getString("keresztnev");
			String felhasználónév = rset.getString("felhasznalonev");
			String jelszó = rset.getString("jelszo");
			Date születésnap = rset.getDate("szuletesnap");
			int fizetés = rset.getInt("fizetes");
			
			Oktato oktato = new Oktato(id, vezetéknév, keresztnév, felhasználónév, jelszó, születésnap, fizetés);
			
			// megnézi, hogy az aktuálisan lekérdezett oktató benne van-e a listában
			for (Oktato o : Kozpont.getOktatóLista()) {
				if (o.getId() == oktato.getId()) {
					continue whileQuery;
				}
			}
			// ha nincs még hozzáadva a listához az oktató, akkor hozzá kell adni
			Kozpont.getOktatóLista().add(oktato);
		}
		rset.close();
		stmt.close();
	}
	
	/**
	 * Egy olyan {@link Szak}-ot ad vissza, melynek az id-je megegyezik a paraméterül kapott id-vel.
	 * 
	 * @param id Egy {@link Szak} id-je.
	 * @return Egy olyan {@link Szak}-ot ad vissza, melynek az id-je megegyezik a paraméterül kapott id-vel.
	 */
	private static Szak getSzakById(int id) {
		for (Szak szak : Kozpont.getSzakLista()) {
			if (szak.getId() == id) {
				return szak;
			}
		}
		logger.error("Nincs ilyen {} id-jű szak!", id);
		return null;
	}
	
	/**
	 * Visszaadja azt a {@link Tantargy}-at, melynek az id-je megegyezik a paraméterül kapott id-vel.
	 * 
	 * @param id Egy {@link Tantargy} id-je.
	 * @return Azt a {@link Tantargy}-at, melynek az id-je megegyezik a paraméterül kapott id-vel.
	 */
	private static Tantargy getTantargyById(int id) {
		for (Tantargy tantargy : Kozpont.getTantárgyLista()) {
			if (tantargy.getId() == id) {
				return tantargy;
			}
		}
		logger.error("Nincs ilyen {} id-jű tantárgy!", id);
		return null;
	}
}

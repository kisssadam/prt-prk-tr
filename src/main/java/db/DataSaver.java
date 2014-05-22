package db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tanulmanyiRendszer.Felev;
import tanulmanyiRendszer.Kozpont;
import tanulmanyiRendszer.Oktato;
import tanulmanyiRendszer.Szak;
import tanulmanyiRendszer.Tantargy;
import tanulmanyiRendszer.TanulmanyiOsztaly;

/**
 * A programban tárolt adatok lementésére szolgál.
 * 
 * @author adam
 *
 */
public class DataSaver {
	/**
	 * Ennek a segítségével lehet naplóüzeneteket létrehozni.
	 */
	private static final Logger logger = LoggerFactory.getLogger(DataSaver.class);
	
	/**
	 * Lementi a programban tárolt adatokat az adatbázisba.
	 * 
	 * @throws IOException ha nem tud kapcsolódni az adatbázishoz.
	 * @throws SQLException ha egy adatbázisbeli hiba adódik.
	 */
	public static void save() throws SQLException, IOException {
		logger.info("Adatok mentése az adatbázisba.");
		
		deleteDatasFrom("prt_tantargy_elofeltetelei");
		deleteDatasFrom("prt_tantargy");
		deleteDatasFrom("prt_szak");
		deleteDatasFrom("prt_felev");
		deleteDatasFrom("prt_tanulmanyi_osztaly");
		deleteDatasFrom("prt_oktato");
		
		saveFelevLista();
		saveSzakLista();
		saveTantargyLista();
		saveTanulmanyiOsztalyDolgozoLista();
		saveOktatoLista();
		
		ConnectionHelper.closeConnection();
		logger.info("Az adatok mentése sikeres volt.");
	}
	
	/**
	 * Lementi a {@link Felev}-eket az adatbázisba.
	 * 
	 * @throws IOException ha nem tud kapcsolódni az adatbázishoz.
	 * @throws SQLException ha egy adatbázisbeli hiba adódik.
	 */
	private static void saveFelevLista() throws SQLException, IOException {
		Connection conn = ConnectionHelper.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement("INSERT INTO prt_felev(id, szorg_eleje, szorg_vege, vizsg_eleje, vizsg_vege, aktualis_felev) VALUES(?,?,?,?,?,?)");
		for (Felev felev : Kozpont.getFélévLista()) {
			pstmt.setInt(1, felev.getId());
			pstmt.setDate(2, new Date(felev.getSzorgalmiIdőszak().getEleje().getTime()));
			pstmt.setDate(3, new Date(felev.getSzorgalmiIdőszak().getVége().getTime()));
			pstmt.setDate(4, new Date(felev.getVizsgaIdőszak().getEleje().getTime()));
			pstmt.setDate(5, new Date(felev.getVizsgaIdőszak().getVége().getTime()));
			
			if (felev.isAktuálisFélév()) {
				pstmt.setInt(6, 1);
			} else {
				pstmt.setInt(6, 0);
			}
			
			pstmt.executeUpdate();
		}
		pstmt.close();
	}
	
	/**
	 * Lementi a {@link Szak}-okat az adatbázisba.
	 * 
	 * @throws IOException ha nem tud kapcsolódni az adatbázishoz.
	 * @throws SQLException ha egy adatbázisbeli hiba adódik.
	 */
	private static void saveSzakLista() throws IOException, SQLException {
		Connection conn = ConnectionHelper.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement("INSERT INTO prt_szak(id, nev, szint) VALUES(?,?,?)");
		for (Szak szak : Kozpont.getSzakLista()) {
			pstmt.setInt(1, szak.getId());
			pstmt.setString(2, szak.getNév());
			pstmt.setString(3, szak.getSzint().toString());
			pstmt.executeUpdate();
		}
		pstmt.close();
	}
	
	/**
	 * Lementi a {@link Tantargy}-akat az adatbázisba.
	 * 
	 * @throws IOException ha nem tud kapcsolódni az adatbázishoz.
	 * @throws SQLException ha egy adatbázisbeli hiba adódik.
	 */
	private static void saveTantargyLista() throws IOException, SQLException {
		Connection conn = ConnectionHelper.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement("INSERT INTO prt_tantargy(id, tantargykod, nev, kredit, szak_id) VALUES(?,?,?,?,?)");
		for (Tantargy tantargy : Kozpont.getTantárgyLista()) {
			pstmt.setInt(1, tantargy.getId());
			pstmt.setString(2, tantargy.getTantárgykód());
			pstmt.setString(3, tantargy.getNév());
			pstmt.setInt(4, tantargy.getKredit());
			pstmt.setInt(5, tantargy.getSzak().getId());
			pstmt.executeUpdate();
		}
		pstmt.close();
		
		pstmt = conn.prepareStatement("INSERT INTO prt_tantargy_elofeltetelei(tantargy_id, elofeltetel_id) VALUES(?,?)");
		for (Tantargy tantargy : Kozpont.getTantárgyLista()) {
			pstmt.setInt(1, tantargy.getId());
			for (Tantargy előfeltétel : tantargy.getElőfeltételek()) {
				pstmt.setInt(2, előfeltétel.getId());
				pstmt.executeUpdate();
			}
		}
		pstmt.close();
	}
	
	
	/**
	 * Lementi a {@link TanulmanyiOsztaly}-okat az adatbázisba.
	 * 
	 * @throws IOException ha nem tud kapcsolódni az adatbázishoz.
	 * @throws SQLException ha egy adatbázisbeli hiba adódik.
	 */
	private static void saveTanulmanyiOsztalyDolgozoLista() throws IOException, SQLException {
		Connection conn = ConnectionHelper.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement("INSERT INTO prt_tanulmanyi_osztaly(id, vezeteknev, keresztnev, felhasznalonev, jelszo, szuletesnap) "
														+ "VALUES(?,?,?,?,?,?)");
		for (TanulmanyiOsztaly to : Kozpont.getTanulmányiOsztályDolgozóLista()) {
			pstmt.setInt(1, to.getId());
			pstmt.setString(2, to.getVezetéknév());
			pstmt.setString(3, to.getKeresztnév());
			pstmt.setString(4, to.getFelhasználónév());
			pstmt.setString(5, to.getJelszó());
			pstmt.setDate(6, new Date(to.getSzületésnap().getTime()));
			pstmt.executeUpdate();
		}
		pstmt.close();
	}
	
	/**
	 * Lementi az {@link Oktato}-kat az adatbázisba.
	 * 
	 * @throws IOException ha nem tud kapcsolódni az adatbázishoz.
	 * @throws SQLException ha egy adatbázisbeli hiba adódik.
	 */
	private static void saveOktatoLista() throws IOException, SQLException {
		Connection conn = ConnectionHelper.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(
				"INSERT INTO prt_oktato(id, vezeteknev, keresztnev, felhasznalonev, jelszo, szuletesnap, fizetes) VALUES(?,?,?,?,?,?,?)");
		for (Oktato oktato : Kozpont.getOktatóLista()) {
			pstmt.setInt(1, oktato.getId());
			pstmt.setString(2, oktato.getVezetéknév());
			pstmt.setString(3, oktato.getKeresztnév());
			pstmt.setString(4, oktato.getFelhasználónév());
			pstmt.setString(5, oktato.getJelszó());
			pstmt.setDate(6, new Date(oktato.getSzületésnap().getTime()));
			pstmt.setInt(7, oktato.getFizetés());
			pstmt.executeUpdate();
		}
		pstmt.close();
	}
	
	/**
	 * Törli a paraméterül kapott tábla tartalmát az adatbázisból.
	 * 
	 * @param tableName	ebből a táblából lesznek törölve az adatok az adatbázisban.
	 * @throws IOException ha nem tud kapcsolódni az adatbázishoz.
	 * @throws SQLException ha egy adatbázisbeli hiba adódik.
	 */
	private static void deleteDatasFrom(String tableName) throws IOException, SQLException {
		Statement deleteStatement = ConnectionHelper.getConnection().createStatement();
		logger.debug("A következő tábla tartalma lesz törölve: " + tableName);
		deleteStatement.executeUpdate("DELETE FROM " + tableName);
		deleteStatement.close();
	}
	
}

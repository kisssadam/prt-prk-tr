package db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Az adatábizhoz való kapcsolódást segíti.
 */
public class ConnectionHelper {	
	/**
	 * Ennek a segítségével lehet naplóüzeneteket létrehozni.
	 */
	private static final Logger logger = LoggerFactory.getLogger(ConnectionHelper.class);
	
	/**
	 * Az adatbázishoz való kapcsolódás url-je.
	 */
	private static final String url = "jdbc:oracle:thin:@db.inf.unideb.hu:1521:ora11g";
	
	/**
	 * Az adatbázishoz kapcsolódó felhasználó neve.
	 */
	private static final String dbFelhasználónév = "H_C2MQ5N";
	
	/**
	 * Az adatbázishoz kapcsolódó felhasználó jelszava.
	 */
	private static final String dbJelszó = "progtech";
	
	/**
	 * Az adatbázis kapcsolata.
	 */
	private static Connection conn;

	/**
	 * Csatlakozik a db.inf.unideb.hu adatbázishoz és visszaad egy ezt leíró {@link Connection} objektumot.
	 * 
	 * @param felhasználónév Az adatbázis kapcsolódó felhasználó felhasználóneve.
	 * @param jelszó Az adatbázis kapcsolódó felhasználó jelszava.
	 * @return Egy {@link Connection}-t ad vissza, ami a db.inf.unideb.hu-ra csatlakozik.
	 * @throws IOException ha nem tud kapcsolódni az adatbázishoz.
	 * @throws SQLException	ha egy adatbázisbeli hiba adódik.
	 */
	public static Connection getConnection(String felhasználónév, String jelszó)
			throws IOException, SQLException {
		if (conn == null || conn.isClosed()) {
			synchronized (ConnectionHelper.class) {
				if (conn == null) {
					conn = DriverManager.getConnection(url, felhasználónév, jelszó);
					logger.info("Új adatbázis kapcsolat jött létre.");
				}
			}
		}
		return conn;
	}
	
	/**
	 * Csatlakozik a db.inf.unideb.hu adatbázishoz és visszaad egy ezt leíró {@link Connection} objektumot.
	 * 
	 * @return Egy {@link Connection}-t ad vissza, ami a db.inf.unideb.hu-ra csatlakozik.
	 * @throws IOException ha nem tud kapcsolódni az adatbázishoz.
	 * @throws SQLException	ha egy adatbázisbeli hiba adódik.
	 */
	public static Connection getConnection() throws IOException, SQLException {
		return getConnection(dbFelhasználónév, dbJelszó);
	}
	
	/**
	 * Lezárja azt a {@code Connection} objektumot, amit a {@link #getConnection()} metódus ad vissza.
	 * 
	 * @throws SQLException ha egy adatbázisbeli hiba adódik.
	 */
	public static void closeConnection() throws SQLException {
		if (conn == null || conn.isClosed()) {
			logger.info("Az adatbázissal való kapcsolat már korábban bezárásra került.");
			return;
		}
		conn.close();
		conn = null;
		logger.info("Az adatbázissal való kapcsolat bezárásra került.");
	}
}

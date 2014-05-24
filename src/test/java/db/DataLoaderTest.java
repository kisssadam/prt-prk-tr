package db;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Test;

import tanulmanyiRendszer.TanulmanyiRendszerKivetel;

public class DataLoaderTest {
	
	@Test
	public void initTest() throws IOException, SQLException, TanulmanyiRendszerKivetel {
		DataLoader.init();
	}
	
	
	
	@After
	public void closeConnection() throws SQLException {
		ConnectionHelper.closeConnection();
	}
}

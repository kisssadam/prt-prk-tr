package db;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.Test;

public class DataSaverTest {
	
	@AfterClass
	public static void closeDBConnection() throws SQLException {
		ConnectionHelper.closeConnection();
	}
	
	@Test
	public void saveTest() throws SQLException, IOException {
		DataSaver.save();
	}
}

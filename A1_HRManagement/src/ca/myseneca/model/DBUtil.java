/**
* The DBUtil class implements utility methods that can
* be used to connect to oracle database Neptune, print related exceptions and warnings.
* @author  Ge Yang, Bohao Liu, Yan Liu
* @version 1.0
* @since   2016-03-12 
*/
package ca.myseneca.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class DBUtil {

	private Properties configProp = new Properties();

	public DBUtil() throws FileNotFoundException, IOException, InvalidPropertiesFormatException {
		super();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("database.properties");

		configProp.load(in);

	}

	public DBUtil(String propertiesFileName)
			throws FileNotFoundException, IOException, InvalidPropertiesFormatException {
		super();
		this.setProperties(propertiesFileName);
	}

	private void setProperties(String fileName)
			throws FileNotFoundException, IOException, InvalidPropertiesFormatException {
		this.configProp = new Properties();
		FileInputStream fis = new FileInputStream(fileName);
		configProp.load(fis);
	}
	public Connection ConnectOracleNeptune () throws SQLException{
		Connection conn = null;
		String connectionType=configProp.getProperty("CONNECTION_TYPE");
		if (connectionType.equals("oci")){
		String connectDescriptor = configProp.getProperty("ORACLE_DB_URL_OCI");
		String userName = configProp.getProperty("ORACLE_DB_USERNAME");
		String pw = configProp.getProperty("ORACLE_DB_PASSWORD");
		conn = DriverManager.getConnection(connectDescriptor, userName, pw);
		System.out.println("Connected to database using oci.");}
		else if (connectionType.equals("thin"))
		{
			String connectDescriptor = configProp.getProperty("ORACLE_DB_URL_THIN");
			String userName = configProp.getProperty("ORACLE_DB_USERNAME");
			String pw = configProp.getProperty("ORACLE_DB_PASSWORD");
			conn = DriverManager.getConnection(connectDescriptor, userName, pw);
			System.out.println("Connected to database using thin type.");
		}else{
			System.out.println("Sorry, unknown connection type.");
		}
		return conn;
	}
	public Connection ConnectOracleNeptuneWithOci() throws SQLException {
		Connection conn = null;
		String connectDescriptor = configProp.getProperty("ORACLE_DB_URL_OCI");
		String userName = configProp.getProperty("ORACLE_DB_USERNAME");
		String pw = configProp.getProperty("ORACLE_DB_PASSWORD");
		conn = DriverManager.getConnection(connectDescriptor, userName, pw);
		System.out.println("Connected to database");
		return conn;
	}

	public Connection ConnectOracleNeptuneWithThin() throws SQLException {
		Connection conn = null;
		String connectDescriptor = configProp.getProperty("ORACLE_DB_URL_THIN");
		String userName = configProp.getProperty("ORACLE_DB_USERNAME");
		String pw = configProp.getProperty("ORACLE_DB_PASSWORD");
		conn = DriverManager.getConnection(connectDescriptor, userName, pw);
		System.out.println("Connected to database");
		return conn;
	}

	public static void closeConnection(Connection connArg) {
		System.out.println("Releasing all open resources ...");
		try {
			if (connArg != null) {
				connArg.close();
				connArg = null;
			}
		} catch (SQLException sqle) {
			printSQLException(sqle);
		}
	}

	public static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

	public static void printWarnings(SQLWarning warning) throws SQLException {
		if (warning != null) {
			System.out.println("\n---Warning---\n");
			while (warning != null) {
				System.out.println("Message: " + warning.getMessage());
				System.out.println("SQLState: " + warning.getSQLState());
				System.out.print("Vendor error code: ");
				System.out.println(warning.getErrorCode());
				System.out.println("");
				warning = warning.getNextWarning();
			}
		}
	}

	public static void printBatchUpdateException(BatchUpdateException b) {
		System.err.println("----BatchUpdateException----");
		System.err.println("SQLState:  " + b.getSQLState());
		System.err.println("Message:  " + b.getMessage());
		System.err.println("Vendor:  " + b.getErrorCode());
		System.err.print("Update counts:  ");
		int[] updateCounts = b.getUpdateCounts();
		for (int i = 0; i < updateCounts.length; i++) {
			System.err.print(updateCounts[i] + "   ");
		}
	}

}

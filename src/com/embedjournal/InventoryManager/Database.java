package com.embedjournal.InventoryManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

class SQLite {
	public Connection connection = null;
	public boolean isConnected = false;
	
	public SQLite() {
		this("Inventory.DB");
	}

	public SQLite(String fileName) {
		// load the sqlite-JDBC driver using the current class loader
		ResultSet resultSet = null;
		Statement statement = null;
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + fileName);
			statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			resultSet = statement.executeQuery("SELECT EMPNAME FROM EMPLOYEEDETAILS");
			while (resultSet.next()) {
				System.out.println("EMPLOYEE NAME:" + resultSet.getString("EMPNAME"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.isConnected = true;
	}
	
	public ResultSet executeQuery(String query) {
		ResultSet resultSet = null;
		Statement statement = null;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			resultSet = statement.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultSet;
	}
	
	public int executeUpdate(String query) {
		int row = -1;
		Statement statement = null;
		try {
			statement = connection.createStatement();
			statement.setQueryTimeout(30); // set timeout to 30 sec.
			row = statement.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return row;
	}
}

public class Database extends SQLite {
	private static final String CREATE_INVENTORY_TABLE = 
			"CREATE TABLE IF NOT EXISTS INVENTORY (MFRPN PRIMARY KEY NOT NULL, " +
            "MFR TEXT, FOOTPRINT TEXT, DESCRIPTION TEXT, STOCK INT NOT NULL, " +
            "UNIQUE (ID, MFRPN) ON CONFLICT IGNORE);";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

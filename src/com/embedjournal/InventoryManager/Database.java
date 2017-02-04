package com.embedjournal.InventoryManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

class SQLite {

	private Connection connection = null;

	public SQLite(String fileName) throws SQLException {
		// load the SQLite-JDBC driver using the current class loader
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.out.print("SQLite Creating trouble");
			e.printStackTrace();
		}
		connection = DriverManager.getConnection("jdbc:sqlite:" + fileName);
	}

	public ResultSet executeQuery(String query) throws SQLException {
		Statement statement = connection.createStatement();
		statement.setQueryTimeout(30); // set timeout to 30 sec.
		return statement.executeQuery(query);
	}

	public int executeUpdate(String query) throws SQLException {
		int row = -1;
		Statement statement = null;
		statement = connection.createStatement();
		statement.setQueryTimeout(30); // set timeout to 30 sec.
		row = statement.executeUpdate(query);
		return row;
	}

	public PreparedStatement getPrepareStatement(String pst) throws SQLException {
		return connection.prepareStatement(pst);
	}
}

public class Database {

	private static final String DB_FILE_NAME = "res/Inventory.DB";
	private static final String TABLE_NAME = "INVENTORY";
	private static final String CREATE_INVENTORY_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
			+ Item.protoIdentManfpn + " PRIMARY KEY NOT NULL, " + Item.protoIdentManf + " TEXT, "
			+ Item.protoIdentFprint + " TEXT, " + Item.protoIdentDesc + " TEXT, " + Item.protoIdentStock
			+ " INT NOT NULL);";
	private static final String INSERT_ITEM = "INSERT INTO " + TABLE_NAME + " VALUES (?,?,?,?,?);";
	private static final String SELECT_ALL_ITEMS = "SELECT * FROM " + TABLE_NAME + ";";
	private static final String UPDATE_ITEM = "UPDATE " + TABLE_NAME + " SET " + Item.protoIdentManf + "=?, "
			+ Item.protoIdentFprint + "=?, " + Item.protoIdentDesc + "=?, " + Item.protoIdentStock + "=? "
			+ "WHERE " + Item.protoIdentManfpn + "=?;";
	
	public Database() throws Exception {
		SQLite database = new SQLite(DB_FILE_NAME);
		database.executeUpdate(CREATE_INVENTORY_TABLE);
	}

	public boolean updateItem(Item item) {
		boolean result = true;
		try {
			SQLite database = new SQLite(DB_FILE_NAME);
			PreparedStatement pst = database.getPrepareStatement(UPDATE_ITEM);
			pst.setString(1, item.getManufacturer());
			pst.setString(2, item.getFootPrint());
			pst.setString(3, item.getDescription());
			pst.setInt(4, item.getStock());
			pst.setString(5, item.getManufPno());
			pst.executeUpdate();
			pst.close();
		} catch (Exception ex) {
			result = false;
		}
		return result;
	}

	public boolean insertItem(Item item) {
		boolean result = true;
		try {
			SQLite database = new SQLite(DB_FILE_NAME);
			PreparedStatement pst = database.getPrepareStatement(INSERT_ITEM);
			pst.setString(1, item.getManufPno());
			pst.setString(2, item.getManufacturer());
			pst.setString(3, item.getFootPrint());
			pst.setString(4, item.getDescription());
			pst.setInt(5, item.getStock());
			pst.executeUpdate();
			pst.close();
		} catch (Exception ex) {
			result = false;
		}
		return result;
	}

	public int insertItemList(ArrayList<Item> itemList) {
		int num = 0;
		Iterator<Item> itemIterator = itemList.iterator();
		while (itemIterator.hasNext()) {
			if (this.insertItem(itemIterator.next()) == true)
				num++;
		}
		return num;
	}

	public ArrayList<Item> getItemList() {
		ArrayList<Item> itemList = new ArrayList<Item>();
		try {
			SQLite database = new SQLite(DB_FILE_NAME);
			ResultSet rs = database.executeQuery(SELECT_ALL_ITEMS);
			while (rs.next()) {
				String mpno = rs.getString(Item.protoIdentManfpn);
				String mp = rs.getString(Item.protoIdentManf);
				String fp = rs.getString(Item.protoIdentFprint);
				String des = rs.getString(Item.protoIdentDesc);
				int stock = rs.getInt(Item.protoIdentStock);
				Item item = new Item(mpno, mp, fp, des, stock);
				itemList.add(item);
			}
		} catch (Exception e) {
			System.out.println("Got to catch");
			e.printStackTrace();
		}
		return itemList;
	}

	public static void main(String[] args) {
		System.out.println(CREATE_INVENTORY_TABLE);
		System.out.println(INSERT_ITEM);
		System.out.println(SELECT_ALL_ITEMS);
		System.out.println(UPDATE_ITEM);
	}

}

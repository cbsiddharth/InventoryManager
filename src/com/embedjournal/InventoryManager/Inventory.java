package com.embedjournal.InventoryManager;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class Inventory {

	public int insertItemListToDB(ArrayList<Item> list) {
		Database db = null;
		try {
			db = new Database();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return db.insertItemList(list);
	}

	public ArrayList<Item> getItemListfromDB() {
		Database db = null;
		try {
			db = new Database();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return db.getItemList();
	}

	public void exportToCSV(String fileName) throws Exception {
		String[] entries = new String[5];
		ArrayList<Item> itemList = this.getItemListfromDB();
		CSVWriter writer = new CSVWriter(new FileWriter(fileName));
		writer.writeNext(Item.protoName);
		for (Item item : itemList) {
			entries[0] = item.getManufPno();
			entries[1] = item.getManufacturer();
			entries[2] = item.getFootPrint();
			entries[3] = item.getDescription();
			entries[4] = Integer.toString(item.getStock());
			writer.writeNext(entries);
		}
		writer.close();
	}

	public int importFromCSV(String fileName) throws Exception {
		CSVReader reader = new CSVReader(new FileReader(fileName), ',', '"', 1);
		String[] nextLine;
		ArrayList<Item> itemList = new ArrayList<Item>();
		while ((nextLine = reader.readNext()) != null) {
			// nextLine[] is an array of values from the line
			String mpno = nextLine[0];
			String mp = nextLine[1];
			String fp = nextLine[2];
			String des = nextLine[3];
			int stock = Integer.parseInt(nextLine[4]);
			Item item = new Item(mpno, mp, fp, des, stock);
			itemList.add(item);
		}
		reader.close();
		return insertItemListToDB(itemList);
	}

	public static void main(String[] args) {
		Inventory in = new Inventory();
		try {
			System.out.println("Importing from import.csv");
			System.out.println("Imported: " + in.importFromCSV("res/import.csv"));
			in.exportToCSV("res/export.csv");
			System.out.println("Exported to export.csv");
		} catch (Exception ex) {
			System.out.println("Err" + ex);
		}
	}
}

package com.embedjournal.InventoryManager;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.embedjournal.InventoryManager.model.Item;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class Inventory {
	
	private Database database = null;
	public Inventory(String dbFile) {
		try {
			this.database = new Database(dbFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int insertItemListToDB(List<Item> list) {
		return this.database.insertItemList(list);
	}
	
	public void deleteItemListFromDB(List<Item> list) {
		database.delteItemList(list);
	}

	public List<Item> getItemListfromDB() {
		return this.database.getItemList();
	}
	
	public boolean updateItem(Item item) {
		return this.database.updateItem(item);
	}

	public void exportToCSV(String fileName, List<Item> itemList) throws Exception {
		String[] entries = new String[5];
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
	
	public void exportInventoryToCSV(String fileName) {
		List<Item> itemList = this.getItemListfromDB();
		try {
			exportToCSV(fileName, itemList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Item> importFromCSV(String fileName) throws Exception {
		String[] nextLine;
		CSVReader reader = new CSVReader(new FileReader(fileName), ',', '"', 1);
		List<Item> itemList = new ArrayList<Item>();
		while ((nextLine = reader.readNext()) != null) {
			String mpno = nextLine[0];
			String mp = nextLine[1];
			String fp = nextLine[2];
			String des = nextLine[3];
			int stock = Integer.parseInt(nextLine[4]);
			Item item = new Item(mpno, mp, fp, des, stock);
			itemList.add(item);
		}
		reader.close();
		return itemList;
	}
	
	public int importInventoryFromCSV(String fileName) {
		int numInserted = 0;
		try {
			List<Item> itemList = importFromCSV(fileName);
			numInserted = insertItemListToDB(itemList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return numInserted;
	}
}

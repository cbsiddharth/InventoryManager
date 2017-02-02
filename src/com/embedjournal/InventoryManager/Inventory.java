package com.embedjournal.InventoryManager;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class Inventory {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	public void insertItemListToDB(ArrayList<Item> list) {
		// TODO insert all items to database
	}
	
	public ArrayList<Item> getItemListfromDB() {
		ArrayList<Item> itemList = new ArrayList<Item>();
		// TODO get items from DB and return
		return itemList;
	}
	
	public void exportToCSV(String fileName) throws Exception {
		ArrayList<Item> itemList = this.getItemListfromDB();
	     CSVWriter writer = new CSVWriter(new FileWriter(fileName), ',');
	     // feed in your array (or convert your data to an array)
		 Iterator<Item> itemIterator = itemList.iterator();
		 while (itemIterator.hasNext()) {
		     String[] entries = itemIterator.next().toString().split(",");
		     writer.writeNext(entries);
		 }
		 writer.close();
	}

	public void importFromCSV(String fileName) throws Exception {
		CSVReader reader = null;
		reader = new CSVReader(new FileReader(fileName));
		String [] nextLine;
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
	}
}

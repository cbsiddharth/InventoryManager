package com.embedjournal.InventoryManager;

public class Item {

	public static final String protoNameManfpn = "Manufacturer Part No";
	public static final String protoNameManf = "Manufacturer";
	public static final String protoNameFprint = "Foot Print";
	public static final String protoNameDesc = "Description";
	public static final String protoNameStock = "Stock";

	public static final String protoIdentManfpn = "MANFPN";
	public static final String protoIdentManf = "MANF";
	public static final String protoIdentFprint = "FPRINT";
	public static final String protoIdentDesc = "DESC";
	public static final String protoIdentStock = "STOCK";

	public static String[] prototype = { protoIdentManfpn, protoIdentManf, protoIdentFprint, protoIdentDesc,
			protoIdentStock };

	public static String[] protoName = { protoNameManfpn, protoNameManf, protoNameFprint, protoNameDesc,
			protoNameStock };

	private String manufPno, manufacturer, footPrint, description;
	private int stock;

	public Item() {
		this.manufPno = "";
		this.manufacturer = "";
		this.footPrint = "";
		this.description = "";
		this.stock = 0;
	}

	public Item(String serialItem) {
		String[] itemStrArray = serialItem.split(",");
		this.manufPno = itemStrArray[0].replaceAll("^ *\'|\' *$", "");
		this.manufacturer = itemStrArray[1].replaceAll("^ *\'|\' *$", "");
		this.footPrint = itemStrArray[2].replaceAll("^ *\'|\' *$", "");
		this.description = itemStrArray[3].replaceAll("^ *\'|\' *$", "");
		this.stock = Integer.parseInt(itemStrArray[4].replaceAll("^ *| *$", ""));
	}

	public Item(String manfpn, String manf, String fPrint, String desc, int stock) {
		this.manufPno = manfpn;
		this.manufacturer = manf;
		this.footPrint = fPrint;
		this.description = desc;
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "'" + manufPno + "', '" + manufacturer + "', '" + footPrint + "', '" + description + "', " + stock + " ";
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((footPrint == null) ? 0 : footPrint.hashCode());
		result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
		result = prime * result + ((manufPno == null) ? 0 : manufPno.hashCode());
		result = prime * result + stock;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (footPrint == null) {
			if (other.footPrint != null)
				return false;
		} else if (!footPrint.equals(other.footPrint))
			return false;
		if (manufacturer == null) {
			if (other.manufacturer != null)
				return false;
		} else if (!manufacturer.equals(other.manufacturer))
			return false;
		if (manufPno == null) {
			if (other.manufPno != null)
				return false;
		} else if (!manufPno.equals(other.manufPno))
			return false;
		if (stock != other.stock)
			return false;
		return true;
	}

	public static void main(String[] args) {
		Item i = new Item("a", "b", "c", "d", 0);
		System.out.println("Item: " + i.toString());
		Item j = new Item(i.toString());
		System.out.println("New Item: " + j.toString());
	}

	public String getManufPno() {
		return manufPno;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getFootPrint() {
		return footPrint;
	}

	public void setFootPrint(String footPrint) {
		this.footPrint = footPrint;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
}

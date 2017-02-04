package com.embedjournal.InventoryManager.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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

	private StringProperty manufPno, manufacturer, footPrint, description;
	private IntegerProperty stock;

	public Item() {
		this("", "", "", "", 0);
	}

	public Item(String manfpn, String manf, String fPrint, String desc, int stock) {
		this.manufPno = new SimpleStringProperty(manfpn);
		this.manufacturer = new SimpleStringProperty(manf);
		this.footPrint = new SimpleStringProperty(fPrint);
		this.description= new SimpleStringProperty(desc);
		this.stock = new SimpleIntegerProperty(stock);
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((footPrint == null) ? 0 : footPrint.hashCode());
		result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
		result = prime * result + ((manufPno == null) ? 0 : manufPno.hashCode());
		result = prime * result + stock.get();
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

	/* ManufPno */
	public StringProperty getPropertyManufPno() {
		return this.manufPno;
	}

	public String getManufPno() {
		return manufPno.get();
	}
	
	public void setManufPno(String manufPno) {
		this.manufPno.set(manufPno);
	}

	/* Manufacturer */
	public StringProperty getPropertyManufacturer() {
		return this.manufacturer;
	}

	public String getManufacturer() {
		return manufacturer.get();
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer.set(manufacturer);
	}

	/* FootPrint */
	public StringProperty getPropertyFootPrint() {
		return this.footPrint;
	}

	public String getFootPrint() {
		return footPrint.get();
	}

	public void setFootPrint(String footPrint) {
		this.footPrint.set(footPrint);
	}

	/* Description */
	public StringProperty getPropertyDescription() {
		return  this.description;
	}

	public String getDescription() {
		return description.get();
	}

	public void setDescription(String description) {
		this.description.set(description);
	}

	/* Stock */
	public IntegerProperty getPropertyStock() {
		return this.stock;
	}

	public int getStock() {
		return stock.get();
	}

	public void setStock(int stock) {
		this.stock.set(stock);
	}
}

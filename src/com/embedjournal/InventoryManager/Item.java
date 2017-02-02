package com.embedjournal.InventoryManager;

public class Item {
	public static final String protoNameManfpn = "Manufacturer Part No";
	public static final String protoNameManf   = "Manufacturer";
	public static final String protoNameFprint = "Foot Print";
	public static final String protoNameDesc   = "Description";
	public static final String protoNameStock  = "Stock";

	public static final String protoIdentManfpn = "MANFPN";
	public static final String protoIdentManf   = "MANF";
	public static final String protoIdentFprint = "FPRINT";
	public static final String protoIdentDesc   = "DESC";
	public static final String protoIdentStock  = "STOCK";

	public static String[] protoIdentifiers = { 
			protoIdentManfpn, protoIdentManf, protoIdentFprint, protoIdentDesc, protoIdentStock
	};
	public static String[] protoNames = {
			protoNameManfpn, protoNameManf, protoNameFprint, protoNameDesc, protoNameStock
	};

	private String protoManfpn, protoManf, protoFprint, protoDesc;
	private int protoStock;

	@Override
	public String toString() {
		return "'" + protoManfpn + "', '" + protoManf + "', '" + protoFprint
				+ "', '" + protoDesc + "', " + protoStock + " ";
	}

	public Item() {
		this.protoManfpn = ""; this.protoManf = "";
		this.protoFprint = ""; this.protoDesc = "";
		this.protoStock = 0;
	}

	public Item(String serialItem) {
		String[] itemStrArray = serialItem.split(",");
		this.protoManfpn = itemStrArray[0].replaceAll("^ *\'|\' *$", "");
		this.protoManf = itemStrArray[1].replaceAll("^ *\'|\' *$", "");
		this.protoFprint = itemStrArray[2].replaceAll("^ *\'|\' *$", "");
		this.protoDesc = itemStrArray[3].replaceAll("^ *\'|\' *$", "");
		this.protoStock = Integer.parseInt(itemStrArray[4].replaceAll("^ *| *$", ""));
	}
	
	public Item(String manfpn, String manf, String fPrint, String desc, int stock) {
		this.protoManfpn = manfpn; this.protoManf = manf;
		this.protoFprint = fPrint; this.protoDesc = desc;
		this.protoStock = stock;		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((protoDesc == null) ? 0 : protoDesc.hashCode());
		result = prime * result + ((protoFprint == null) ? 0 : protoFprint.hashCode());
		result = prime * result + ((protoManf == null) ? 0 : protoManf.hashCode());
		result = prime * result + ((protoManfpn == null) ? 0 : protoManfpn.hashCode());
		result = prime * result + protoStock;
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
		if (protoDesc == null) {
			if (other.protoDesc != null)
				return false;
		} else if (!protoDesc.equals(other.protoDesc))
			return false;
		if (protoFprint == null) {
			if (other.protoFprint != null)
				return false;
		} else if (!protoFprint.equals(other.protoFprint))
			return false;
		if (protoManf == null) {
			if (other.protoManf != null)
				return false;
		} else if (!protoManf.equals(other.protoManf))
			return false;
		if (protoManfpn == null) {
			if (other.protoManfpn != null)
				return false;
		} else if (!protoManfpn.equals(other.protoManfpn))
			return false;
		if (protoStock != other.protoStock)
			return false;
		return true;
	}
	
	public static void main(String[] args) {
		Item i = new Item("a", "b", "c", "d", 0);
		System.out.println("Item: " + i.toString());
		Item j = new Item(i.toString());
		System.out.println("New Item: "+ j.toString());
	}
}

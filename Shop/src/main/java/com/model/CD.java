package com.model;

import java.io.*;
import java.util.List;

public class CD implements Serializable {
	public static List<CD> CDs;

	private static final long serialVersionUID = 1L;
	public static final int ONE_PRODUCT = 1;
	int cdQuantity;
	String cdName;
	String cdType;
	String cdGenre;

	public CD() {
	}

	public CD(String name, String type, String genre) {
		cdName = name;
		cdType = type;
		cdGenre = genre;
	}

	public void setType(String type) {
		cdType = type;
	}

	public String getType() {
		return cdType;
	}

	public void setGenre(String genre) {
		cdGenre = genre;
	}

	public String getGenre() {
		return cdGenre;
	}

	public void setName(String name) {
		cdName = name;
	}

	public String getName() {
		return cdName;
	}

	public void setQuantity() {
		cdQuantity += ONE_PRODUCT;

	}

	public int getQuantity() {
		return cdQuantity;
	}

}

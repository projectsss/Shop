package com.model;

import java.util.ArrayList;

public class ShoppingCart {

	protected ArrayList<CD> items;

	public ShoppingCart() {
		items = new ArrayList<CD>();
	}

	public ArrayList<CD> getProducts() {
		return items;
	}

	public void addProduct(CD newItem) {

		for (int i = 0; i < items.size(); i++) {
			CD cd = (CD) items.get(i);
			if (cd.getName().equals(newItem.getName())) {
				cd.setQuantity();
				// items.set(i,cd);
				return;
			}
		}
		items.add(newItem);
		return;
	}

	public void removeProduct(int productIndex) {
		items.remove(productIndex);
	}

}

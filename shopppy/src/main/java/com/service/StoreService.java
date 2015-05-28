package com.service;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;

import com.model.CD;

public interface StoreService {

	public List<String> getGenre(String type);

	public List<String> getType();

	public Collection<CD> getCDsProductList() throws DataAccessException;

	public void getCDsProductList(String name) throws DataAccessException;

	public void getCDsProductList(String type, String genre)
			throws DataAccessException;

	public Collection<CD> getCDsNewProducts() throws DataAccessException;
}

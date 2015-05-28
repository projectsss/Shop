package com.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;

import com.model.CD;

public interface CdRepository {
	
	Collection<CD> getProductList() throws DataAccessException;

	void getProductList(String name) throws DataAccessException;

	void getProductList(String type, String genre) throws DataAccessException;

	Collection<CD> getNewProducts() throws DataAccessException;

}

package com.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.model.CD;
import com.repository.CategoriesRepository;
import com.repository.CdRepository;

public class StoreServiceImpl implements StoreService {

	private CdRepository cdRepository;

	private CategoriesRepository categoriesRepository;

	public StoreServiceImpl() {
	}

	@Autowired
	public StoreServiceImpl(CdRepository cdRepository,
			CategoriesRepository categoriesRepository) {
		this.cdRepository = cdRepository;
		this.categoriesRepository = categoriesRepository;
	}

	@Override
	public List<String> getGenre(String type) {
		return categoriesRepository.populateDropDownList(type);
	}

	@Override
	public Collection<CD> getCDsProductList() throws DataAccessException {
		return cdRepository.getProductList();
	}

	@Override
	public void getCDsProductList(String name) throws DataAccessException {
		cdRepository.getProductList(name);

	}

	@Override
	public void getCDsProductList(String type, String genre)
			throws DataAccessException {
		cdRepository.getProductList(type, genre);
	}

	@Override
	public Collection<CD> getCDsNewProducts() throws DataAccessException {
		return cdRepository.getNewProducts();
	}

	@Override
	public List<String> getType() {
		return categoriesRepository.populateDropDownListType();

	}

}

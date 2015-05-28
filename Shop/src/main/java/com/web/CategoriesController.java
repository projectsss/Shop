package com.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.StoreServiceImpl;

import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/populateDropDownList")
public class CategoriesController {
	private final StoreServiceImpl storeServiceImpl;

	@Autowired
	public CategoriesController(StoreServiceImpl storeServiceImpl) {
		this.storeServiceImpl = storeServiceImpl;
	}

	@RequestMapping(value = "/type/{type}", method = RequestMethod.GET)
	public @ResponseBody
	String populateDropDownList(@PathVariable("type") String type)
			throws JSONException {
		String genres = null;
		List<String> genresList = storeServiceImpl.getGenre(type);
		try {
			genres= new ObjectMapper().writeValueAsString(genresList);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		return genres.toString();
	}

	@RequestMapping(value = "/types", method = RequestMethod.GET)
	public @ResponseBody
	String populateDropDownListType() throws JSONException {
		List<String> typesList = storeServiceImpl.getType();
		String types = null;
		try {
			types = new ObjectMapper().writeValueAsString(typesList);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		return types.toString();
	}
}

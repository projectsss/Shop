package com.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.CD;
import com.model.PageInfo;
import com.model.Store;
import com.service.StoreServiceImpl;

@Controller
@RequestMapping("/getList")
public class CdController {
	public static final Integer FIRST_PAGE = 1;
	PageInfo pageInfo = new PageInfo();
	private final StoreServiceImpl storeServiceImpl;

	@Autowired
	public CdController(StoreServiceImpl storeServiceImpl) {
		this.storeServiceImpl = storeServiceImpl;
	}

	@RequestMapping(value = "/allProducts", method = { RequestMethod.POST,
			RequestMethod.GET })
	public @ResponseBody
	String getAllProductList(Model model) {
		if (Store.getProductList() != null) {
			Store.deleteProducts();
		}
		storeServiceImpl.getCDsProductList();
		return showProductList(FIRST_PAGE);
	}

	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
	public @ResponseBody
	String getProductList(@PathVariable("name") String name) {
		if (Store.getProductList() != null) {
			Store.deleteProducts();
		}
		storeServiceImpl.getCDsProductList(name);
		return showProductList(FIRST_PAGE);
	}

	@RequestMapping(value = "/type/{type}/genre/{genre}", method = RequestMethod.GET)
	public @ResponseBody
	String getProductList(@PathVariable("type") String type,
			@PathVariable("genre") String genre) {
		if (Store.getProductList() != null) {
			Store.deleteProducts();
		}
		storeServiceImpl.getCDsProductList(type, genre);
		return showProductList(FIRST_PAGE);
	}

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public @ResponseBody
	String showNewItems() {
		if (Store.getProductList() != null) {
			Store.deleteProducts();
		}
		storeServiceImpl.getCDsNewProducts();
		return showProductList(FIRST_PAGE);
	}

	@RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
	public @ResponseBody
	String showProductList(@PathVariable("page") Integer page) {
		String pageInform = "";
		JSONArray pageInformJSON = new JSONArray();
		List<CD> produtList = pageInfo.getPageList();
		pageInfo.setPageSize();
		pageInfo.setPage(page - 1);
		try {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("pageCount", pageInfo.getPageCount());
			model.put("page", page);
			model.put("productList",
					new ObjectMapper().writeValueAsString(produtList));
			pageInform = new ObjectMapper().writeValueAsString(model);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		pageInformJSON.put(pageInform);
		return pageInformJSON.toString();
	}
}

package com.web;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.CD;
import com.model.ShoppingCart;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController {
	ShoppingCart shoppingCart;

	@RequestMapping(value = "/addToCart", method = RequestMethod.POST)
	public @ResponseBody
	String addToCart(@RequestBody String[] products,
			HttpServletRequest request, HttpSession session) {
		shoppingCart = (ShoppingCart) request.getSession().getAttribute(
				"ShoppingCart");
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart();
			session.setAttribute("ShoppingCart", shoppingCart);
		}
		for (int i = 0, j = 1, k = 2; k < products.length; i += 3, j += 3, k += 3) {
			String type = products[i];
			String genre = products[j];
			String name = products[k];
			CD CDProduct = new CD(name, type, genre);
			CDProduct.setQuantity();
			shoppingCart.addProduct(CDProduct);
		}
		return "product(s) was(re) added";
	}

	@RequestMapping(value = "/getShoppingCart", method = RequestMethod.POST)
	public @ResponseBody
	String showShoppingCart(HttpServletRequest request, HttpSession session) {
		shoppingCart = (ShoppingCart) request.getSession().getAttribute(
				"ShoppingCart");
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCart();
		}
		List<CD> cartProductsList = shoppingCart.getProducts();
		String cartProducts = null;
		try {
			cartProducts = new ObjectMapper()
					.writeValueAsString(cartProductsList);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		return cartProducts.toString();
	}

	@RequestMapping(value = "/removeProduct/{index}", method = RequestMethod.POST)
	public @ResponseBody
	String removeProduct(Model model, @PathVariable("index") Integer index,
			HttpServletRequest request, HttpSession session) {
		shoppingCart = (ShoppingCart) request.getSession().getAttribute(
				"ShoppingCart");
		shoppingCart.removeProduct(index);
		return showShoppingCart(request, session);
	}
}

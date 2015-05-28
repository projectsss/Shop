shopApp.registerShoppingCart('shoppingCartController', function($http, $scope) {
	$scope.removeProduct = function(index) {
		if (index === null || index === undefined || index < 0) {
			alert("Index does not exist");
			return;
		}
		var pagelink = "cart/removeProduct/";
		var url = pagelink + index + ".htm";
		$http.post(url).success(function(data) {
			$scope.cartProducts = data;

		})
	}
	var url = "cart/getShoppingCart.htm";
	$http.post(url).success(function(data) {
		$scope.cartProducts = data;
	})
});
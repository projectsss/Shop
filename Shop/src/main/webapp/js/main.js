var shopApp = angular.module('shopApp', [ "ngRoute" ]);
shopApp.config(function($routeProvider, $controllerProvider) {
	shopApp.registerCategories = $controllerProvider.register;
	shopApp.registerProductList = $controllerProvider.register;
	shopApp.registerShoppingCart = $controllerProvider.register;
	shopApp.registerGetProductListByCategories = $controllerProvider.register;
	$routeProvider.when('/', {
		templateUrl : 'main.html',
		controller : 'mainController'
	}).when('/Catalogue', {
		templateUrl : 'productList.html',
		controller : 'productListController'
	}).when('/ShoppingCart', {
		templateUrl : 'shoppingCart.html',
		controller : 'shoppingCartController'
	}).when('/getProductList/page/:pageNumber', {
		templateUrl : 'productList.html',
		controller : 'productListGetPageController'
	})
});

shopApp.controller('mainController', function($scope, $http) {
	var url = "getList/main.htm";
	$http.get(url).success(function(data) {
		$scope.pageInform = JSON.parse(data);
		$scope.productList = JSON.parse($scope.pageInform.productList);
		$scope.pageCount = $scope.pageInform.pageCount;
		$scope.page = $scope.pageInform.page;
	})

});
shopApp.controller('messagesProperty', function($scope, $http) {
	var url = "properties/messages.properties";
	$http.get(url).success(function(response) {
		$scope.messages = response;
	})

});
shopApp.controller('mainProperty', function($scope, $http) {
	var url = "properties/main.properties";
	$http.get(url).success(function(data) {
		$scope.main = data;
	})

});

shopApp.controller('listOfProdutsProperty', function($scope, $http) {
	var url = "properties/listOfProduts.properties";
	$http.get(url).success(function(data) {
		$scope.listOfProduts = data;
	})

});
shopApp.controller('shoppingCartProperty', function($scope, $http) {
	var url = "properties/shoppingCart.properties";
	$http.get(url).success(function(data) {
		$scope.shoppingCart = data;
	})
});

shopApp.filter('makeRange', function() {
	return function(input) {
		var lowBound, highBound;
		switch (input.length) {
		case 1:
			lowBound = 0;
			highBound = parseInt(input[0]) - 1;
			break;
		case 2:
			lowBound = parseInt(input[0]);
			highBound = parseInt(input[1]);
			break;
		default:
			return input;
		}
		var result = [];
		for (var i = lowBound; i <= highBound; i++)
			result.push(i);
		return result;
	};
});

shopApp.controller('productListGetPageController', function($http, $scope,
		$routeParams) {

	var pageNumber = $routeParams.pageNumber;
	if (pageNumber === null || pageNumber === undefined || pageNumber === 0) {
		alert("Page does not exist");
		return;
	}
	var pagelink = 'getList/page/';
	var url = pagelink + pageNumber + ".htm";
	$http.get(url).success(function(data) {
		$scope.pageInform = data;
	})

});

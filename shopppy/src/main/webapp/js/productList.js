shopApp.registerProductList('productListController', function($http, $scope,
		$routeParams) {

	var url = "getList/allProducts.htm";
	$http.get(url).success(function(data) {
		$scope.pageInform = JSON.parse(data);
		$scope.productList = JSON.parse($scope.pageInform.productList);
		$scope.pageCount = $scope.pageInform.pageCount;
		$scope.page = $scope.pageInform.page;

	})
});
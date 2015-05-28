shopApp.registerGetPage('productListGetPageController', function($http, $scope,
		$routeParams) {

	var pageNumber = $routeParams.pageNumber;
	var pagelink = 'getList/page/';
	var url = pagelink + pageNumber + ".htm";
	$http.get(url).success(function(data) {
		$scope.pageInform = JSON.parse(data);
		$scope.productList = JSON.parse($scope.pageInform.productList);
		$scope.pageCount = $scope.pageInform.pageCount;
		$scope.page = $scope.pageInform.page;
	})
});
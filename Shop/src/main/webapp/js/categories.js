shopApp
		.registerCategories(
				'categoriesController',
				[
						'$scope',
						'$http',
						function($scope, $http) {
							var url = "populateDropDownList/types.htm";
							$http({
								method : 'get',
								url : url
							}).success(function(data) {
								$scope.types = data;
							});

							$scope.getGenres = function() {
								if ($scope.selectedType) {
									var type = $scope.selectedType;

									if (type === null || type === undefined) {
										alert("Type does not exist");
										return;
									}
									var url = 'populateDropDownList/type/'
											+ type + '.htm'
									$http.get(url).success(function(data) {
										$scope.genres = data;
									});
								}

							}

							$scope.getProductListDropDown = function() {

								if ($scope.selectedType && $scope.selectedGenre) {
									var type = $scope.selectedType;

									if (type === null || type === undefined) {
										alert("Type does not exist");
										return;
									}
									var genre = $scope.selectedGenre;
									if (genre === null || genre === undefined) {
										alert("Genre does not exist");
										return;
									}
									var pagelink = 'getList/type/';
									var url = pagelink + type + '/genre/'
											+ genre + '.htm'
									$http
											.get(url)
											.success(
													function(data) {
														$scope.pageInform = JSON
																.parse(data);
														$scope.productList = JSON
																.parse($scope.pageInform.productList);
														$scope.pageCount = $scope.pageInform.pageCount;
														$scope.page = $scope.pageInform.page;
														
													});
								}
							}
							$scope.getProductListSearch = function() {
								var name = $scope.selectedText;
								if (name === null || name === undefined) {
									alert("Name does not exist");
									return;
								}
								var pagelink = "getList/name/";
								var url = pagelink + name + ".htm";
								$http
										.get(url)
										.success(
												function(data) {
													$scope.pageInform = JSON
															.parse(data);
													$scope.productList = JSON
															.parse($scope.pageInform.productList);
													$scope.pageCount = $scope.pageInform.pageCount;
													$scope.page = $scope.pageInform.page;
												});
							}
							$scope.addToCart = function() {
								$scope.isChecked = {};
								var url = "cart/addToCart.htm";
								var products = [];
								for (var i = 0; i < $scope.productList.length; i++) {
									if ($scope.productList[i].isChecked) {
										type = $scope.productList[i].type;
										genre = $scope.productList[i].genre;
										name = $scope.productList[i].name;
										products.push(type, genre, name);
										$scope.productList[i].isChecked = false;
									}
								}
								var productsJSON = JSON.stringify(products);
								$http({
									method : 'post',
									url : url,
									data : productsJSON
								})
										.success(
												function() {
													alert("Product(s) was(re) successfully added to your cart");
												})
							}
						} ])
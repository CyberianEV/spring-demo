angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/store/api/v1';

    var currentPage = 1;

    $scope.loadProducts = function (page = 1) {
//        console.log("12355");
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
                title_part: $scope.filter ? $scope.filter.title_part : null,
                p: page
            }
        }).then(function (response) {
            $scope.ProductsList = response.data.content;
            currentPage = response.data.page.number + 1;
            var pages = [];
            for(var i = 1; i <= response.data.page.totalPages; i++) {
              pages.push(i);
            }
            $scope.totalPages = pages;
        });
    };

    $scope.deleteProduct = function (productId) {
        $http.delete(contextPath + '/products/' + productId)
            .then(function (response) {
                $scope.loadProducts(currentPage);
            });
    }

    $scope.loadProducts();
});
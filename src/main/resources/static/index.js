angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/store';

    $scope.loadProducts = function () {
        $http.get(contextPath + '/products')
            .then(function (response) {
                $scope.ProductsList = response.data;
            });
    };

    $scope.deleteProduct = function (productId) {
        $http.get(contextPath + '/products/delete/' + productId)
            .then(function (response) {
                $scope.loadProducts();
            });
    }

    $scope.filterByPrice = function () {
        $http({
            url: contextPath + '/products/filter',
            method: 'GET',
            params: {
                min: $scope.min,
                max: $scope.max
            }
        }).then(function (response) {
            $scope.ProductsList = response.data;
        });
    }

    $scope.loadProducts();
});
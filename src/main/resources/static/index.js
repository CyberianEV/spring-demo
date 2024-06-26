angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $rootScope, $http, $localStorage) {
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
            currentPage = response.data.number + 1;
            var pages = [];
            for(var i = 1; i <= response.data.totalPages; i++) {
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
    };

    $scope.loadCart = function () {
        $http.get(contextPath + '/cart')
            .then(function (response) {
                $scope.CartList = response.data.productsDtOInCart;
            });
    };

    $scope.addToCart = function (product) {
        console.log(product)
        $http({
            url: contextPath + '/cart',
            method: 'POST',
            data: product
        }).then(function (response) {
            $scope.loadCart();
        });
    };

    $scope.deleteProductFromCart = function (inCartId) {
        $http.delete(contextPath + '/cart/' + inCartId)
            .then(function (response) {
                $scope.loadCart();
        });
    };

    $scope.changeQuantityInCart = function (inCartId, delta) {
        $http({
            url: contextPath + '/cart',
            method: 'PUT',
            params: {
                id_in_cart: inCartId,
                delta: delta
            }
        }).then(function (response) {
            $scope.loadCart();
        });
    };

    $scope.tryToAuth = function () {
        $http.post('http://localhost:8189/store/auth', $scope.user)
            .then(function successCallback(response) {
                if  (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.springWebUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                    $scope.loadProducts(currentPage);
                    $scope.loadCart();
                }
            }, function errorCallback(response) {
                   alert('Failed to Authorized');
            });
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.springWebUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.clearUser = function () {
        delete $localStorage.springWebUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
    };

    $scope.showCurrentUserInfo = function () {
        $http.get(contextPath + '/profile')
            .then(function successCallback(response) {
                alert('MY NAME IS ' + response.data.username)
            }, function errorCallback(response) {
                alert('UNAUTHORIZED')
            }
        );
    };

    $scope.tryToSignUp = function () {
        $http.post('http://localhost:8189/store/signup', $scope.newUser)
            .then(function successCallback(response) {
                alert('User ' + response.data.username + ' ' + response.data.email + ' created')
                $scope.newUser.username = null;
                $scope.newUser.password = null;
                $scope.newUser.email = null;
            }, function errorCallback(response) {
                alert('Unable to create user, errors: ' + response.data.errors)
            }
        );
    };


    if  ($rootScope.isUserLoggedIn()) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.springWebUser.token;
    };

    $scope.loadProducts();
    $scope.loadCart();
});
angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8180/products/';

    $scope.getProducts = function () {
        $http({
            url: contextPath,
            method: 'GET'
        }).then(function (response) {
            $scope.products = response.data;
        });
    }

    $scope.remove = function (id) {
        $http({
            url: contextPath + id,
            method: 'DELETE'
        }).then(function () {
                $scope.getProducts();
            });
    }

    $scope.createProduct=function (){
        $http({
            url: contextPath,
            method: 'POST',
            params: {
                title: $scope.product.title,
                price: $scope.product.price
            }
        }).then(function successCallback(response) {
            alert('New product was created successful');
            $scope.product = null;
            $scope.getProducts();
        });
    }

    $scope.getProducts();

});
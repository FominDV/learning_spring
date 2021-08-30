angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/cloud/api/v1/products/';

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

    $scope.getProducts();

});
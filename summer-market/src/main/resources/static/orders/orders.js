angular.module('app').controller('ordersController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/summer';

    $scope.loadOrders = function () {
        $http({
            url: contextPath + '/api/v1/orders',
            method: 'GET'
        }).then(function (response) {
            $scope.orders = response.data;
            $scope.getOrderDetails(response.data[0])
        });
    }

    $scope.getOrderDetails = function (order) {
        $http({
            url: contextPath + '/api/v1/items',
            method: 'GET',
            params: {
                'order': order.id
            }
        }).then(function (response) {
            $scope.order = order;
            $scope.items = response.data;
        });
    }

    $scope.hasOrder = function () {
        if ($scope.order) {
            return true;
        } else {
            return false;
        }
    }

    $scope.loadOrders();
});
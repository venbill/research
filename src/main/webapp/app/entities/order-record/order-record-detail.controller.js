(function() {
    'use strict';

    angular
        .module('researchApp')
        .controller('OrderRecordDetailController', OrderRecordDetailController);

    OrderRecordDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'OrderRecord'];

    function OrderRecordDetailController($scope, $rootScope, $stateParams, previousState, entity, OrderRecord) {
        var vm = this;

        vm.orderRecord = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('researchApp:orderRecordUpdate', function(event, result) {
            vm.orderRecord = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

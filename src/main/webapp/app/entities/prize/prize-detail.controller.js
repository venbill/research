(function() {
    'use strict';

    angular
        .module('researchApp')
        .controller('PrizeDetailController', PrizeDetailController);

    PrizeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Prize'];

    function PrizeDetailController($scope, $rootScope, $stateParams, previousState, entity, Prize) {
        var vm = this;

        vm.prize = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('researchApp:prizeUpdate', function(event, result) {
            vm.prize = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

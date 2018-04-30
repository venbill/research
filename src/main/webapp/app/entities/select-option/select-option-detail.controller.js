(function() {
    'use strict';

    angular
        .module('researchApp')
        .controller('SelectOptionDetailController', SelectOptionDetailController);

    SelectOptionDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'SelectOption'];

    function SelectOptionDetailController($scope, $rootScope, $stateParams, previousState, entity, SelectOption) {
        var vm = this;

        vm.selectOption = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('researchApp:selectOptionUpdate', function(event, result) {
            vm.selectOption = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

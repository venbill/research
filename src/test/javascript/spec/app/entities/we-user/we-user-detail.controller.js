(function() {
    'use strict';

    angular
        .module('researchApp')
        .controller('WeUserDetailController', WeUserDetailController);

    WeUserDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'WeUser'];

    function WeUserDetailController($scope, $rootScope, $stateParams, previousState, entity, WeUser) {
        var vm = this;

        vm.weUser = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('researchApp:weUserUpdate', function(event, result) {
            vm.weUser = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

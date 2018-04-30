(function() {
    'use strict';

    angular
        .module('researchApp')
        .controller('CommitDetailController', CommitDetailController);

    CommitDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Commit'];

    function CommitDetailController($scope, $rootScope, $stateParams, previousState, entity, Commit) {
        var vm = this;

        vm.commit = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('researchApp:commitUpdate', function(event, result) {
            vm.commit = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

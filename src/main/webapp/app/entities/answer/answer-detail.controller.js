(function() {
    'use strict';

    angular
        .module('researchApp')
        .controller('AnswerDetailController', AnswerDetailController);

    AnswerDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Answer'];

    function AnswerDetailController($scope, $rootScope, $stateParams, previousState, entity, Answer) {
        var vm = this;

        vm.answer = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('researchApp:answerUpdate', function(event, result) {
            vm.answer = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();

(function() {
    'use strict';

    angular
        .module('researchApp')
        .controller('ResearchDetailController', ResearchDetailController);

    ResearchDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Research'];

    function ResearchDetailController($scope, $rootScope, $stateParams, previousState, entity, Research) {
        var vm = this;

        vm.research = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('researchApp:researchUpdate', function(event, result) {
            vm.research = result;
        });
        $scope.$on('$destroy', unsubscribe);




        getReceiveTypes();
        function getReceiveTypes() {
            Research.getReceiveTypes({},function (resp) {
                $scope.receiveTypes = resp.data;
                if(vm.research.receive==null||vm.research.receive==0){

                    vm.research.receive = $scope.receiveTypes[0]['code'];
                }
            } );
        }


        getPrizes();
        function getPrizes() {
            Research.getAllPrizes({},function (resp) {
                console.log(resp)
                $scope.prizes = resp.data;

            } );
        }



    }



})();

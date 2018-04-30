(function() {
    'use strict';

    angular
        .module('researchApp')
        .controller('ResearchDialogController', ResearchDialogController);

    ResearchDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Research'];

    function ResearchDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Research) {
        var vm = this;

        vm.research = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.research.id !== null) {
                Research.update(vm.research, onSaveSuccess, onSaveError);
            } else {
                Research.save(vm.research, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('researchApp:researchUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.createTime = false;
        vm.datePickerOpenStatus.updateTime = false;
        vm.datePickerOpenStatus.startTime = false;
        vm.datePickerOpenStatus.endTime = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }




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

(function() {
    'use strict';

    angular
        .module('researchApp')
        .controller('PrizeDialogController', PrizeDialogController);

    PrizeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Prize'];

    function PrizeDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Prize) {
        var vm = this;

        vm.prize = entity;
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
            if (vm.prize.id !== null) {
                Prize.update(vm.prize, onSaveSuccess, onSaveError);
            } else {
                Prize.save(vm.prize, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('researchApp:prizeUpdate', result);
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
    }
})();

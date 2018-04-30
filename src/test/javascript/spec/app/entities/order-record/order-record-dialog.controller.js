(function() {
    'use strict';

    angular
        .module('researchApp')
        .controller('OrderRecordDialogController', OrderRecordDialogController);

    OrderRecordDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'OrderRecord'];

    function OrderRecordDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, OrderRecord) {
        var vm = this;

        vm.orderRecord = entity;
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
            if (vm.orderRecord.id !== null) {
                OrderRecord.update(vm.orderRecord, onSaveSuccess, onSaveError);
            } else {
                OrderRecord.save(vm.orderRecord, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('researchApp:orderRecordUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.createTime = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();

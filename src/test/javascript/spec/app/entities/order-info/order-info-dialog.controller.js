(function() {
    'use strict';

    angular
        .module('researchApp')
        .controller('OrderInfoDialogController', OrderInfoDialogController);

    OrderInfoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'OrderInfo'];

    function OrderInfoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, OrderInfo) {
        var vm = this;

        vm.orderInfo = entity;
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
            if (vm.orderInfo.id !== null) {
                OrderInfo.update(vm.orderInfo, onSaveSuccess, onSaveError);
            } else {
                OrderInfo.save(vm.orderInfo, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('researchApp:orderInfoUpdate', result);
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

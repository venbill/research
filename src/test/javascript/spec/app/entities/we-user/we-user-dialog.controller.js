(function() {
    'use strict';

    angular
        .module('researchApp')
        .controller('WeUserDialogController', WeUserDialogController);

    WeUserDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'WeUser'];

    function WeUserDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, WeUser) {
        var vm = this;

        vm.weUser = entity;
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
            if (vm.weUser.id !== null) {
                WeUser.update(vm.weUser, onSaveSuccess, onSaveError);
            } else {
                WeUser.save(vm.weUser, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('researchApp:weUserUpdate', result);
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

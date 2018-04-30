(function() {
    'use strict';

    angular
        .module('researchApp')
        .controller('CommitDialogController', CommitDialogController);

    CommitDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Commit'];

    function CommitDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Commit) {
        var vm = this;

        vm.commit = entity;
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
            if (vm.commit.id !== null) {
                Commit.update(vm.commit, onSaveSuccess, onSaveError);
            } else {
                Commit.save(vm.commit, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('researchApp:commitUpdate', result);
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

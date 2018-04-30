(function() {
    'use strict';

    angular
        .module('researchApp')
        .controller('SelectOptionDialogController', SelectOptionDialogController);

    SelectOptionDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'SelectOption'];

    function SelectOptionDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, SelectOption) {
        var vm = this;

        vm.selectOption = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.selectOption.id !== null) {
                SelectOption.update(vm.selectOption, onSaveSuccess, onSaveError);
            } else {
                SelectOption.save(vm.selectOption, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('researchApp:selectOptionUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();

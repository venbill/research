(function() {
    'use strict';

    angular
        .module('researchApp')
        .controller('SelectOptionDeleteController',SelectOptionDeleteController);

    SelectOptionDeleteController.$inject = ['$uibModalInstance', 'entity', 'SelectOption'];

    function SelectOptionDeleteController($uibModalInstance, entity, SelectOption) {
        var vm = this;

        vm.selectOption = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            SelectOption.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

(function() {
    'use strict';

    angular
        .module('researchApp')
        .controller('WeUserDeleteController',WeUserDeleteController);

    WeUserDeleteController.$inject = ['$uibModalInstance', 'entity', 'WeUser'];

    function WeUserDeleteController($uibModalInstance, entity, WeUser) {
        var vm = this;

        vm.weUser = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            WeUser.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

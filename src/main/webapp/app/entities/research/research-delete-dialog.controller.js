(function() {
    'use strict';

    angular
        .module('researchApp')
        .controller('ResearchDeleteController',ResearchDeleteController);

    ResearchDeleteController.$inject = ['$uibModalInstance', 'entity', 'Research'];

    function ResearchDeleteController($uibModalInstance, entity, Research) {
        var vm = this;

        vm.research = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Research.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

(function() {
    'use strict';

    angular
        .module('researchApp')
        .controller('CommitDeleteController',CommitDeleteController);

    CommitDeleteController.$inject = ['$uibModalInstance', 'entity', 'Commit'];

    function CommitDeleteController($uibModalInstance, entity, Commit) {
        var vm = this;

        vm.commit = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Commit.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

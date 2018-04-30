(function() {
    'use strict';

    angular
        .module('researchApp')
        .controller('OrderRecordDeleteController',OrderRecordDeleteController);

    OrderRecordDeleteController.$inject = ['$uibModalInstance', 'entity', 'OrderRecord'];

    function OrderRecordDeleteController($uibModalInstance, entity, OrderRecord) {
        var vm = this;

        vm.orderRecord = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            OrderRecord.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();

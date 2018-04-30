(function() {
    'use strict';

    angular
        .module('researchApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('order-record', {
            parent: 'entity',
            url: '/order-record?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'researchApp.orderRecord.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/order-record/order-records.html',
                    controller: 'OrderRecordController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('orderRecord');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('order-record-detail', {
            parent: 'order-record',
            url: '/order-record/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'researchApp.orderRecord.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/order-record/order-record-detail.html',
                    controller: 'OrderRecordDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('orderRecord');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'OrderRecord', function($stateParams, OrderRecord) {
                    return OrderRecord.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'order-record',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('order-record-detail.edit', {
            parent: 'order-record-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/order-record/order-record-dialog.html',
                    controller: 'OrderRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['OrderRecord', function(OrderRecord) {
                            return OrderRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('order-record.new', {
            parent: 'order-record',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/order-record/order-record-dialog.html',
                    controller: 'OrderRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                orderId: null,
                                prizeId: null,
                                detailPrice: null,
                                researchId: null,
                                createTime: null,
                                userId: null,
                                addressId: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('order-record', null, { reload: 'order-record' });
                }, function() {
                    $state.go('order-record');
                });
            }]
        })
        .state('order-record.edit', {
            parent: 'order-record',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/order-record/order-record-dialog.html',
                    controller: 'OrderRecordDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['OrderRecord', function(OrderRecord) {
                            return OrderRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('order-record', null, { reload: 'order-record' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('order-record.delete', {
            parent: 'order-record',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/order-record/order-record-delete-dialog.html',
                    controller: 'OrderRecordDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['OrderRecord', function(OrderRecord) {
                            return OrderRecord.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('order-record', null, { reload: 'order-record' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

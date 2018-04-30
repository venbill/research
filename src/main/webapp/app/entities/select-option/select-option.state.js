(function() {
    'use strict';

    angular
        .module('researchApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('select-option', {
            parent: 'entity',
            url: '/select-option?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'researchApp.selectOption.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/select-option/select-options.html',
                    controller: 'SelectOptionController',
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
                    $translatePartialLoader.addPart('selectOption');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('select-option-detail', {
            parent: 'select-option',
            url: '/select-option/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'researchApp.selectOption.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/select-option/select-option-detail.html',
                    controller: 'SelectOptionDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('selectOption');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'SelectOption', function($stateParams, SelectOption) {
                    return SelectOption.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'select-option',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('select-option-detail.edit', {
            parent: 'select-option-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/select-option/select-option-dialog.html',
                    controller: 'SelectOptionDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['SelectOption', function(SelectOption) {
                            return SelectOption.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('select-option.new', {
            parent: 'select-option',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/select-option/select-option-dialog.html',
                    controller: 'SelectOptionDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                optionId: null,
                                questionId: null,
                                content: null,
                                isAnswer: null,
                                orderNo: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('select-option', null, { reload: 'select-option' });
                }, function() {
                    $state.go('select-option');
                });
            }]
        })
        .state('select-option.edit', {
            parent: 'select-option',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/select-option/select-option-dialog.html',
                    controller: 'SelectOptionDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['SelectOption', function(SelectOption) {
                            return SelectOption.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('select-option', null, { reload: 'select-option' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('select-option.delete', {
            parent: 'select-option',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/select-option/select-option-delete-dialog.html',
                    controller: 'SelectOptionDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['SelectOption', function(SelectOption) {
                            return SelectOption.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('select-option', null, { reload: 'select-option' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

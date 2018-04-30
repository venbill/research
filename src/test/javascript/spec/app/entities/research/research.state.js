(function() {
    'use strict';

    angular
        .module('researchApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('research', {
            parent: 'entity',
            url: '/research?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'researchApp.research.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/research/research.html',
                    controller: 'ResearchController',
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
                    $translatePartialLoader.addPart('research');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('research-detail', {
            parent: 'research',
            url: '/research/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'researchApp.research.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/research/research-detail.html',
                    controller: 'ResearchDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('research');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Research', function($stateParams, Research) {
                    return Research.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'research',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('research-detail.edit', {
            parent: 'research-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/research/research-dialog.html',
                    controller: 'ResearchDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Research', function(Research) {
                            return Research.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('research.new', {
            parent: 'research',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/research/research-dialog.html',
                    controller: 'ResearchDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                researchId: null,
                                name: null,
                                description: null,
                                picture: null,
                                receive: null,
                                publisher: null,
                                createTime: null,
                                updateTime: null,
                                startTime: null,
                                endTime: null,
                                prizeId: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('research', null, { reload: 'research' });
                }, function() {
                    $state.go('research');
                });
            }]
        })
        .state('research.edit', {
            parent: 'research',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/research/research-dialog.html',
                    controller: 'ResearchDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Research', function(Research) {
                            return Research.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('research', null, { reload: 'research' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('research.delete', {
            parent: 'research',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/research/research-delete-dialog.html',
                    controller: 'ResearchDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Research', function(Research) {
                            return Research.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('research', null, { reload: 'research' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

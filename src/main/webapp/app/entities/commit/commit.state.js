(function() {
    'use strict';

    angular
        .module('researchApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('commit', {
            parent: 'entity',
            url: '/commit?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'researchApp.commit.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/commit/commits.html',
                    controller: 'CommitController',
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
                    $translatePartialLoader.addPart('commit');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('commit-detail', {
            parent: 'commit',
            url: '/commit/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'researchApp.commit.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/commit/commit-detail.html',
                    controller: 'CommitDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('commit');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Commit', function($stateParams, Commit) {
                    return Commit.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'commit',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('commit-detail.edit', {
            parent: 'commit-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/commit/commit-dialog.html',
                    controller: 'CommitDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Commit', function(Commit) {
                            return Commit.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('commit.new', {
            parent: 'commit',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/commit/commit-dialog.html',
                    controller: 'CommitDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                commitId: null,
                                userId: null,
                                researchId: null,
                                createTime: null,
                                orderId: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('commit', null, { reload: 'commit' });
                }, function() {
                    $state.go('commit');
                });
            }]
        })
        .state('commit.edit', {
            parent: 'commit',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/commit/commit-dialog.html',
                    controller: 'CommitDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Commit', function(Commit) {
                            return Commit.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('commit', null, { reload: 'commit' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('commit.delete', {
            parent: 'commit',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/commit/commit-delete-dialog.html',
                    controller: 'CommitDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Commit', function(Commit) {
                            return Commit.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('commit', null, { reload: 'commit' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

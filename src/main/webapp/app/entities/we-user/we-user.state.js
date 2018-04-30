(function() {
    'use strict';

    angular
        .module('researchApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('we-user', {
            parent: 'entity',
            url: '/we-user?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'researchApp.weUser.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/we-user/we-users.html',
                    controller: 'WeUserController',
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
                    $translatePartialLoader.addPart('weUser');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('we-user-detail', {
            parent: 'we-user',
            url: '/we-user/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'researchApp.weUser.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/we-user/we-user-detail.html',
                    controller: 'WeUserDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('weUser');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'WeUser', function($stateParams, WeUser) {
                    return WeUser.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'we-user',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('we-user-detail.edit', {
            parent: 'we-user-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/we-user/we-user-dialog.html',
                    controller: 'WeUserDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['WeUser', function(WeUser) {
                            return WeUser.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('we-user.new', {
            parent: 'we-user',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/we-user/we-user-dialog.html',
                    controller: 'WeUserDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                openId: null,
                                nick: null,
                                name: null,
                                sex: null,
                                tel: null,
                                photo: null,
                                createTime: null,
                                status: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('we-user', null, { reload: 'we-user' });
                }, function() {
                    $state.go('we-user');
                });
            }]
        })
        .state('we-user.edit', {
            parent: 'we-user',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/we-user/we-user-dialog.html',
                    controller: 'WeUserDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['WeUser', function(WeUser) {
                            return WeUser.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('we-user', null, { reload: 'we-user' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('we-user.delete', {
            parent: 'we-user',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/we-user/we-user-delete-dialog.html',
                    controller: 'WeUserDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['WeUser', function(WeUser) {
                            return WeUser.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('we-user', null, { reload: 'we-user' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

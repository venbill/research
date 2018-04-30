(function() {
    'use strict';

    angular
        .module('researchApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('prize', {
            parent: 'entity',
            url: '/prize?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'researchApp.prize.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/prize/prizes.html',
                    controller: 'PrizeController',
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
                    $translatePartialLoader.addPart('prize');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('prize-detail', {
            parent: 'prize',
            url: '/prize/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'researchApp.prize.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/prize/prize-detail.html',
                    controller: 'PrizeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('prize');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Prize', function($stateParams, Prize) {
                    return Prize.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'prize',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('prize-detail.edit', {
            parent: 'prize-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/prize/prize-dialog.html',
                    controller: 'PrizeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Prize', function(Prize) {
                            return Prize.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('prize.new', {
            parent: 'prize',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/prize/prize-dialog.html',
                    controller: 'PrizeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                prizeId: null,
                                prizeType: null,
                                name: null,
                                description: null,
                                picture: null,
                                minPrice: null,
                                maxPrice: null,
                                goodPrice: null,
                                createTime: null,
                                createUser: null,
                                updateTime: null,
                                updateUser: null,
                                startTime: null,
                                endTime: null,
                                totalMoney: null,
                                totalNo: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('prize', null, { reload: 'prize' });
                }, function() {
                    $state.go('prize');
                });
            }]
        })
        .state('prize.edit', {
            parent: 'prize',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/prize/prize-dialog.html',
                    controller: 'PrizeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Prize', function(Prize) {
                            return Prize.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('prize', null, { reload: 'prize' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('prize.delete', {
            parent: 'prize',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/prize/prize-delete-dialog.html',
                    controller: 'PrizeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Prize', function(Prize) {
                            return Prize.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('prize', null, { reload: 'prize' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();

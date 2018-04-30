(function() {
    'use strict';
    angular
        .module('researchApp')
        .factory('Research', Research);

    Research.$inject = ['$resource', 'DateUtils'];

    function Research ($resource, DateUtils) {
        var resourceUrl =  'api/research/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.createTime = DateUtils.convertDateTimeFromServer(data.createTime);
                        data.updateTime = DateUtils.convertDateTimeFromServer(data.updateTime);
                        data.startTime = DateUtils.convertDateTimeFromServer(data.startTime);
                        data.endTime = DateUtils.convertDateTimeFromServer(data.endTime);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' },


            'getReceiveTypes': {
                method: 'GET',
                url:'api/research/receive/types',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    return angular.toJson(copy);
                }
            },
            'getAllPrizes': {
                method: 'GET',
                url:'api/prizes/all',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    return angular.toJson(copy);
                }
            }

        });
    }
})();

(function() {
    'use strict';
    angular
        .module('researchApp')
        .factory('WeUser', WeUser);

    WeUser.$inject = ['$resource', 'DateUtils'];

    function WeUser ($resource, DateUtils) {
        var resourceUrl =  'api/we-users/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.createTime = DateUtils.convertDateTimeFromServer(data.createTime);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();

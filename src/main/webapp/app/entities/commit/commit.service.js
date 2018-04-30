(function() {
    'use strict';
    angular
        .module('researchApp')
        .factory('Commit', Commit);

    Commit.$inject = ['$resource', 'DateUtils'];

    function Commit ($resource, DateUtils) {
        var resourceUrl =  'api/commits/:id';

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

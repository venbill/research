(function() {
    'use strict';
    angular
        .module('researchApp')
        .factory('Address', Address);

    Address.$inject = ['$resource', 'DateUtils'];

    function Address ($resource, DateUtils) {
        var resourceUrl =  'api/addresses/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.createTime = DateUtils.convertDateTimeFromServer(data.createTime);
                        data.updateTime = DateUtils.convertDateTimeFromServer(data.updateTime);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();

(function() {
    'use strict';
    angular
        .module('researchApp')
        .factory('Answer', Answer);

    Answer.$inject = ['$resource', 'DateUtils'];

    function Answer ($resource, DateUtils) {
        var resourceUrl =  'api/answers/:id';

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

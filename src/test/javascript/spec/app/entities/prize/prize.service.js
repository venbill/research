(function() {
    'use strict';
    angular
        .module('researchApp')
        .factory('Prize', Prize);

    Prize.$inject = ['$resource', 'DateUtils'];

    function Prize ($resource, DateUtils) {
        var resourceUrl =  'api/prizes/:id';

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
            'update': { method:'PUT' }
        });
    }
})();

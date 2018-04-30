(function() {
    'use strict';
    angular
        .module('researchApp')
        .factory('OrderRecord', OrderRecord);

    OrderRecord.$inject = ['$resource', 'DateUtils'];

    function OrderRecord ($resource, DateUtils) {
        var resourceUrl =  'api/order-records/:id';

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

(function() {
    'use strict';
    angular
        .module('researchApp')
        .factory('OrderInfo', OrderInfo);

    OrderInfo.$inject = ['$resource', 'DateUtils'];

    function OrderInfo ($resource, DateUtils) {
        var resourceUrl =  'api/order-infos/:id';

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

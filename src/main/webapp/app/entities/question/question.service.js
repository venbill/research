(function() {
    'use strict';
    angular
        .module('researchApp')
        .factory('Question', Question);

    Question.$inject = ['$resource', 'DateUtils'];

    function Question ($resource, DateUtils) {
        var resourceUrl =  'api/questions/:id';

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
            'update': { method:'PUT' },
            'getResearchQuestions': {
                method: 'GET',
                url:'api/questions/research/:id',
                transformRequest: function (data) {
                    var copy = angular.copy(data);
                    return angular.toJson(copy);
                }
            }
        });
    }
})();

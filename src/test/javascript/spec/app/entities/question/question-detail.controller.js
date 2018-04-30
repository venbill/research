(function () {
    'use strict';

    angular
        .module('researchApp')
        .controller('QuestionDetailController', QuestionDetailController);

    QuestionDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Question','Research'];

    function QuestionDetailController($scope, $rootScope, $stateParams, previousState, entity, Question,Research) {
        var vm = this;
        var researchId = $stateParams.id;

        console.log($stateParams)

        vm.question = entity;
        vm.previousState = previousState.name;


        var unsubscribe = $rootScope.$on('researchApp:questionUpdate', function (event, result) {
            vm.question = result;
        });
        $scope.$on('$destroy', unsubscribe);




        Research.get({"id":researchId},function (resp) {
            $scope.baseInfo = resp;

        } );


        getResearchQuestions();
        function getResearchQuestions() {
            Question.getResearchQuestions({'id': researchId}, function (resp) {
                $scope.questions = resp.data;
                console.log(resp)

            });
        }

        $scope.edit = function (i) {
            angular.forEach($scope.questions, function (item, j) {
                item['edit'] = false;
                if (i == j) {
                    item['edit'] = true;
                    $scope.currentQuestion = angular.copy(item);

                }
            })

        }


        //删除题目
        $scope.delete = function (i) {
            //调用删除接口

            //本地数据删除
        }


        var questionModified = function (question, questionModified) {
            if (question.id&&question.mainContent == questionModified.mainContent) {
                if (question.optionList.length == questionModified.optionList.length) {
                    var isModified = false;
                    angular.forEach(question.optionList, function (o, i) {
                        if (o.content != questionModified.optionList[i].content) {
                            isModified = true;
                        }
                    })
                    return isModified;
                }
            }
            return true
        }


        //修改题目
        $scope.submit = function (i) {
            //题干是否修改
            console.log($scope.questions[i])
            console.log($scope.currentQuestion)
            if (questionModified($scope.questions[i], $scope.currentQuestion)) {
                save();

            } else {
                $scope.cancel()
            }

            //选项个数比较

        }


        //取消编辑
        $scope.cancel = function () {
            var i = editIndex()
            $scope.currentQuestion = {}

            angular.forEach($scope.questions, function (item) {
                item['edit'] = false;
            })
            if(!$scope.questions[i].id){

                $scope.questions.splice(i,1)
            }

        }


        //添加按钮事件
        $scope.insertBtn = function () {
            if (!$scope.questions) {
                $scope.questions = []
            }
            $scope.currentQuestion = {
                "researchId": researchId,
                "mainContent": "",
                "orderNo":getMaxOrderNo($scope.questions)+1,
                "optionList": [{
                    // "content": "A、",
                    "content": "",
                    "orderNo": 1
                }, {
                    // "content": "B、",
                    "content": "",
                    "orderNo": 2
                }, {
                    // "content": "C、",
                    "content": "",
                    "orderNo": 3
                }, {
                    // "content": "D、",
                    "content": "",
                    "orderNo": 4
                }
                ]
            }

            $scope.questions.push(angular.copy($scope.currentQuestion))

            $scope.edit($scope.questions.length-1)
        }

        //判断是否在编辑状态
        $scope.isEdit = function () {
            var isEditStatus = false;
            angular.forEach($scope.questions, function (item) {
                if(isEditStatus||item['edit']){
                    isEditStatus = true;
                }

            })
            return isEditStatus;
        }

        var editIndex = function () {
            var index = -1;
            angular.forEach($scope.questions, function (item,i) {
                if(index<0||item['edit']){
                    index= i;
                }

            })
            return index;
        }






        function save () {

            if ($scope.currentQuestion.id !== null) {
                Question.update($scope.currentQuestion, onSaveSuccess, onSaveError);
            } else {
                Question.save($scope.currentQuestion, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            var index = editIndex();
            $scope.questions[index]=result
            $scope.cancel()
        }

        function onSaveError () {
            console.log('---->fail')

        }





        //添加选项
        $scope.addOption = function () {
            $scope.currentQuestion.optionList.push({
                // "content": "D、",
                "content": "",
                "orderNo": getMaxOrderNo($scope.currentQuestion.optionList)+1
            })
        }



        //删除选项
        $scope.deleteOption = function (i) {
            $scope.currentQuestion.optionList.splice(i,1)
        }

        //获取最大排序
        var getMaxOrderNo = function (list) {
            var max = 0;
            for (var item in list){
                if(item.orderNo&&item.orderNo>max){
                    max = item.orderNo
                }
            }
            return max
        }



        //是否可以提交题目
        $scope.cantSubmit = function () {
            if(!$scope.currentQuestion){
                return true;
            }
            if(!$scope.currentQuestion.mainContent||$scope.currentQuestion.mainContent==''){
                return true;
            }
            if(!$scope.currentQuestion.optionList||$scope.currentQuestion.optionList.length==0){
                return true;
            }else{
                for (var i=0;i<$scope.currentQuestion.optionList.length;i++){
                    if(!$scope.currentQuestion.optionList[i].content||$scope.currentQuestion.optionList[i].content==''){
                        return true;
                    }
                }
            }
            return false;

        }




    }
})();

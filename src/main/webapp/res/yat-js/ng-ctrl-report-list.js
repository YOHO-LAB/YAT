
myAppModule.controller('ng-ctrl-yat-content', function ($scope ,$rootScope , $http , $timeout , $interval, $cookies) {


    $('#pageLimitCi').bootstrapPaginator({
        "currentPage": 1,
        "totalPages": 1,
        "size":"normal",
        "bootstrapMajorVersion": 3,
        "alignment":"right",
        "numberOfPages":8,
        "itemTexts": function (type, page, current) {
            switch (type) {
                case "first": return "首页";
                case "prev": return "上一页";
                case "next": return "下一页";
                case "last": return "末页";
                case "page": return page;
            }
        },
        "onPageClicked": function (event, originalEvent, type, page){
            $scope.$apply(function () {
               $scope.page = page;
               $scope.getCiReport();
            });
        }
    });
    $('#pageLimitDebug').bootstrapPaginator({
        "currentPage": 1,
        "totalPages": 1,
        "size":"normal",
        "bootstrapMajorVersion": 3,
        "alignment":"right",
        "numberOfPages":8,
        "itemTexts": function (type, page, current) {
            switch (type) {
                case "first": return "首页";
                case "prev": return "上一页";
                case "next": return "下一页";
                case "last": return "末页";
                case "page": return page;
            }
        },
        "onPageClicked": function (event, originalEvent, type, page){
            $scope.$apply(function () {
                $scope.page2 = page;
                $scope.getDebugReport();
            });
        }
    });
    $scope.setTimeRange = function(reportType,range){
        if(range=='day'){
            if(reportType == 'ci'){
                $scope.timeName = "天";
            }else{
                $scope.timeName2 = "天";
            }
        }
        if(range=='week'){
            if(reportType == 'ci'){
                $scope.timeName = "周";
            }else{
                $scope.timeName2 = "周";
            }
        }
        if(range=='month'){
            if(reportType == 'ci'){
                $scope.timeName = "个月";
            }else{
                $scope.timeName2 = "个月";
            }
        }
        if(range=='year'){
            if(reportType == 'ci'){
                $scope.timeName = "年";
            }else{
                $scope.timeName2 = "年";
            }
        }
        if(reportType == 'ci'){
            $scope.page = 1;
            $scope.timeRange = range;
            $scope.getCiReport();
        }else{
            $scope.page2 = 1;
            $scope.timeRange2 = range;
            $scope.getDebugReport();
        }
    }
    $scope.getCiReport = function () {
        // $('#spinnersModal').modal('show');
        $http.post('http://'+window.location.host+'/yat/api/tc',
            {'method':'getCiReport','timeRange':$scope.timeRange , 'page':$scope.page,'count':10}
            ).success(function (data) {
            if(data.success){
                $scope.ciReportList = data.data;
                $scope.totalPage = data.totalPage;
                $scope.totalCount = data.totalCount;
                $('#pageLimitCi').bootstrapPaginator("setOptions",{'currentPage':$scope.page,'totalPages':data.totalPage});
            }else{
                alert("[Error]:"+data.data);
            }
            // $('#spinnersModal').modal('hide');
        });
    }
    $scope.getDebugReport = function () {
        // $('#spinnersModal').modal('show');
        $http.post('http://'+window.location.host+'/yat/api/tc',
            {'method':'getDebugReport','timeRange':$scope.timeRange2 , 'page':$scope.page2,'count':10}
        ).success(function (data) {
            if(data.success){
                $scope.debugReportList = data.data;
                $scope.totalPage2 = data.totalPage;
                $scope.totalCount2 = data.totalCount;
                $('#pageLimitDebug').bootstrapPaginator("setOptions",{'currentPage':$scope.page2,'totalPages':data.totalPage});
            }else{
                alert("[Error]:"+data.data);
            }
            // $('#spinnersModal').modal('hide');
        });
    }
    $scope.delDebugLog = function () {
        var userId = $scope.getUserId();
        if(userId == undefined){
            return;
        }
        if(confirm("确认删除调试记录？")){
            $('#spinnersModal').modal('show');
            $http.post('http://'+window.location.host+'/yat/api/tc',
                {'method':'delDebugLog','userId':userId}
            ).success(function (data) {
                if(data.success){
                    alert("[success]:已成功删除"+data.data+"条调试记录!");
                    $scope.getDebugReport();
                }else{
                    alert("[Error]:"+data.data);
                }
                $('#spinnersModal').modal('hide');
            });
        }
    }

    $scope.page = 1;
    $scope.totalPage = 0;
    $scope.totalCount = 0;
    $scope.page2 = 1;
    $scope.totalPage2 = 0;
    $scope.totalCount2 = 0;
    $scope.setTimeRange('ci','week');
    $scope.setTimeRange('debug','week');
});
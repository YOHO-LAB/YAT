myAppModule.controller('ng-ctrl-yat-content', function ($scope ,$rootScope , $http , $timeout , $interval, $cookies) {

    $scope.rightSidebar = function (isOpen) {
        $('#right-sidebar').toggleClass('sidebar-open' , isOpen);
    }

    $('#pageLimit').bootstrapPaginator({
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
                $scope.getCase();
            });
        }
    });
    $('#pageLimit2').bootstrapPaginator({
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
                $scope.diff();
            });
        }
    });
    $('input[name="daterange"]').daterangepicker({
        format: 'YYYY/MM/DD',
        startDate: '2018-01-01',
        endDate: '2018-12-31'
    },function(start, end) {
        // console.log(start.toISOString(), end.toISOString());
    });

    $scope.getAllTeam = function () {
        $http.post('http://'+window.location.host+'/yat/api/data',
            {'method':'getAllTeam'}
        ).success(function (data) {
            if(data.success){
                $scope.teamList = data.data;
                $scope.teamList.unshift({"id":-1,"name":"不限"});
                // $scope.triggerSelect2("select2_team",$scope.tc.teamId);
            }else{
                alert("[Error]:"+data.data);
            }
        });
    }
    $scope.getAllServiceByPrjId = function () {
        $http.post('http://'+window.location.host+'/yat/api/data',
            {'method':'getAllServiceByPrjId','projectId':$scope.global.prjId}
        ).success(function (data) {
            if(data.success){
                $scope.serviceList = data.data;
                $scope.serviceList.unshift({"id":-1,"name":"不限"});
                // $scope.triggerSelect2("select2_service",$scope.tc.serviceId);
            }else{
                alert("[Error]:"+data.data);
            }
        });
    }
    $scope.getDestinationEnvironment = function () {
        $http.post('http://'+window.location.host+'/yat/api/data',
            {'method':'getDestinationEnvironment','prjId':$scope.global.prjId,'envId':$scope.global.envId}
        ).success(function (data) {
            if(data.success){
                $scope.environmentList = data.data;
            }else{
                alert("[Error]:"+data.data);
            }
        });
    }

    $scope.getCase = function () {
        var search = {'s_teamId':$scope.s_teamId,'s_serviceId':$scope.s_serviceId,
            's_envId':$scope.s_envId,'s_status':$scope.s_status,
            's_url':$scope.trimVar($scope.s_url),'s_method':$scope.trimVar($scope.s_method),'s_note':$scope.trimVar($scope.s_note),
            's_create_user':$scope.trimVar($scope.s_create_user),'s_update_user':$scope.trimVar($scope.s_update_user),
            's_create_time':$scope.trimVar($scope.s_create_time),'s_update_time':$scope.trimVar($scope.s_update_time),
            'globalEnvironmentId':$scope.global.envId};
        // $('#spinnersModal').modal('show');
        $http.post('http://'+window.location.host+'/yat/api/tc',
            {'method':'getCase','search':JSON.stringify(search) , 'page':$scope.page,'count':20}
        ).success(function (data) {
            if(data.success){
                $scope.caseList = data.data;
                $scope.totalPage = data.totalPage;
                $scope.totalCount = data.totalCount;
                $scope.allSearchCaseIds = data.allSearchCaseIds;
                $('#pageLimit').bootstrapPaginator("setOptions",{'currentPage':$scope.page,'totalPages':data.totalPage});
            }else{
                alert("Error:"+data.data);
            }
            // $('#spinnersModal').modal('hide');
        });
    }

    $scope.executeSearchResult = function () {
        var userId = $scope.getUserId();
        if(userId == undefined){return;}
        if($scope.totalCount == undefined || $scope.totalCount == 0){
            alert("搜索结果中没有用例！");
            return;
        }
        var length = $scope.caseList.length;
        if(length == 0){
            alert("搜索结果中无用例！");
            return;
        }
        if(confirm("确认要执行当前搜索出来的"+$scope.totalCount+"条用例？")){
            $('#spinnersModal').modal('show');
            $http.post('http://'+window.location.host+'/yat/api/tc',
                {'method':'runDailyCiManually','userId':userId,'subject':'手动触发CI-调试执行搜索用例','testcaseIds':$scope.allSearchCaseIds}
            ).success(function (data) {
                if(data.success){
                    alert(data.data);
                }else{
                    $scope.errorInfo = "[Error]:"+data.data;
                }
                $('#spinnersModal').modal('hide');
            });
        }
    }
    $scope.copyTestcase = function () {
        $scope.getDestinationEnvironment();
        $scope.isCopyTc = true;
        $scope.caseList2 = [];
        $scope.page2 = 1;
    }
    $scope.diff = function () {
        if($scope.destEnvId == undefined || $scope.destEnvId == 0){
            alert("请先选择要对比的目标环境！");
            return;
        }
        $('#spinnersModal').modal('show');
        $http.post('http://'+window.location.host+'/yat/api/tc',
            {'method':'diffTestcaseByEnvId','fromEnvId':$scope.global.envId,'destEnvId':$scope.destEnvId,'page':$scope.page2,'count':10}
        ).success(function (data) {
            if(data.success){
                $scope.caseList2 = data.data;
                $scope.totalPage2 = data.totalPage;
                $scope.totalCount2 = data.totalCount;
                $scope.allDiffCaseIds = data.allDiffCaseIds;
                $('#pageLimit2').bootstrapPaginator("setOptions",{'currentPage':$scope.page2,'totalPages':data.totalPage2});
            }else{
                alert(data.data);
            }
            $('#spinnersModal').modal('hide');
        });
    }
    $scope.copyAll = function () {
        if($scope.caseList2.length > 0){
            if(confirm("确认要复制所有目标环境中缺少的用例？")){
                $('#spinnersModal').modal('show');
                $http.post('http://'+window.location.host+'/yat/api/tc',
                    {'method':'copyAllTestcase','allDiffCaseIds':$scope.allDiffCaseIds,'destEnvId':$scope.destEnvId}
                ).success(function (data) {
                    if(data.success){
                        $scope.diff();
                    }else{
                        alert(data.data);
                    }
                    $('#spinnersModal').modal('hide');
                });
            }
        }
    }
    $scope.copySingle = function (caseId) {
        if(caseId != undefined){
            if(confirm("确认要复制用例（id="+caseId+"）？")){
                $('#spinnersModal').modal('show');
                $http.post('http://'+window.location.host+'/yat/api/tc',
                    {'method':'copySingleTestcase','caseId':caseId,'destEnvId':$scope.destEnvId}
                ).success(function (data) {
                    if(data.success){
                        $scope.diff();
                    }else{
                        alert(data.data);
                    }
                    $('#spinnersModal').modal('hide');
                });
            }
        }
    }
    $(".select2_env_diff_destination").select2();
    $('.select2_env_diff_destination').on('select2:select', function (e) {
        $scope.$apply(function () {
            $scope.destEnvId = e.params.data.id;
        });
    });

    $(".select2_demo_1").select2();
    $('.select2_demo_1').on('select2:select', function (e) {
        var data = e.params.data;
    });

    $scope.global = $scope.getGlobalEnvId();
    $scope.caseStatus = [{"id":-1,"name":"不限"},{"id":0,"name":"删除"},{"id":1,"name":"未CI"},{"id":2,"name":"CI"}];
    $scope.s_status = -1;$scope.s_teamId = -1;$scope.s_serviceId = -1;$scope.s_envId = -1;
    $scope.page = 1;
    $scope.totalPage = 0;
    $scope.totalCount = 0;
    $scope.s_create_time = "2018/01/01 - 2088/12/31";
    $scope.s_update_time = "2018/01/01 - 2088/12/31";
    $scope.getCase();
    $scope.getAllTeam();
    $scope.getAllServiceByPrjId();
});
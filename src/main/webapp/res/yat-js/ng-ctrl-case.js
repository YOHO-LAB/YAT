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
    $scope.getAllEnvironment = function () {
        $http.post('http://'+window.location.host+'/yat/api/data',
            {'method':'getAllEnvironment'}
        ).success(function (data) {
            if(data.success){
                $scope.environmentList = data.data;
                $scope.environmentList.unshift({"id":-1,"name":"不限"});
                // $scope.triggerSelect2("select2_environment",$scope.tc.testEnvId);
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
                $('#pageLimit').bootstrapPaginator("setOptions",{'currentPage':$scope.page,'totalPages':data.totalPage});
            }else{
                $scope.errorInfo = "[Error]:"+data.data;
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
        var testcaseIds = "";
        for(var i=0;i<length;i++){
            testcaseIds += $scope.caseList[i].id;
            if(i < length-1){
                testcaseIds += ",";
            }
        }
        if(confirm("确认要执行当前搜索出来的"+$scope.totalCount+"条用例？")){
            $('#spinnersModal').modal('show');
            $http.post('http://'+window.location.host+'/yat/api/tc',
                {'method':'runDailyCiManually','userId':userId,'subject':'手动触发CI-调试执行搜索用例','testcaseIds':testcaseIds}
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

    $(".select2_demo_1").select2();
    $('.select2_demo_1').on('select2:select', function (e) {
        var data = e.params.data;
        console.log(data);
    });

    $scope.global = $scope.getGlobalEnvId();
    $scope.caseStatus = [{"id":-1,"name":"不限"},{"id":0,"name":"删除"},{"id":1,"name":"未CI"},{"id":2,"name":"CI"}];
    $scope.s_status = -1;$scope.s_teamId = -1;$scope.s_serviceId = -1;$scope.s_envId = -1;
    $scope.page = 1;
    $scope.totalPage = 0;
    $scope.totalCount = 0;
    $scope.s_create_time = "2018/01/01 - 2018/12/31";
    $scope.s_update_time = "2018/01/01 - 2018/12/31";
    $scope.getCase();
    $scope.getAllTeam();
    $scope.getAllServiceByPrjId();
    // $scope.getAllEnvironment();
});

myAppModule.controller('ng-ctrl-yat-content', function ($scope ,$rootScope , $http , $timeout , $interval, $cookies) {


    $('#pageLimit').bootstrapPaginator({
        "currentPage": 1,
        "totalPages": 100,
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
               $scope.getParam();
            });
        }
    });
    $scope.rightSidebar = function (isOpen) {
        $('#right-sidebar').toggleClass('sidebar-open' , isOpen);
    }

    $scope.getEnvironmentByProjectId = function () {
        $http.post('http://'+window.location.host+'/yat/api/data',
            {'method':'getEnvironmentByProjectId','projectId':$scope.global.prjId}
        ).success(function (data) {
            if(data.success){
                $scope.environmentList = data.data;
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

    $scope.getAllServiceByPrjId = function () {
        $http.post('http://'+window.location.host+'/yat/api/data',
            {'method':'getAllServiceByPrjId','projectId':$scope.global.prjId}
        ).success(function (data) {
            if(data.success){
                $scope.serviceList = data.data;
                $scope.triggerSelect2("select2_service",$scope.tc.serviceId);
            }else{
                alert("[Error]:"+data.data);
            }
        });
    }

    $scope.getAllDbByEnvId = function () {
        $http.post('http://'+window.location.host+'/yat/api/data',
            {'method':'getAllDbByEnvId','envId':$scope.global.envId}
        ).success(function (data) {
            if(data.success){
                $scope.dbList = data.data;
            }else{
                alert("[Error]:"+data.data);
            }
        });
    }
    $scope.getAllCaseByEnvId = function () {
        $http.post('http://'+window.location.host+'/yat/api/tc',
            {'method':'getAllCaseByEnvId','envId':$scope.global.envId}
        ).success(function (data) {
            if(data.success){
                $scope.caseList = data.data;
            }else{
                alert("[Error]:"+data.data);
            }
        });
    }
    $scope.checkBeforeSave =function () {
        if($scope.parameter.name.trim()==""){
            return "未填写参数名！";
        }
        if($scope.parameter.paramType == 1){
            if($scope.parameter.kvVal.trim()==""){
                return "未填写KEY-VALUE值！";
            }
        }
        if($scope.parameter.paramType == 2){
            if($scope.parameter.dbId==0){
                return "未选择数据库！";
            }
            if($scope.parameter.dbSql.trim()==""){
                return "未填写sql语句！";
            }
        }
        if($scope.parameter.paramType == 3){
            if($scope.parameter.tcId==0){
                return "未选择测试用例！";
            }
            if($scope.parameter.tcJsonPath.trim()=="" && $scope.parameter.tcLeft.trim()=="" && $scope.parameter.tcRight.trim()==""){
                return "取值条件不能全部为空！";
            }
        }
        return "";
    }
    $scope.clickSaveParam = function () {
        var userId = $scope.getUserId();
        if(userId == undefined){return;}
        var err = $scope.checkBeforeSave();
        if(err!=""){
            $scope.successMsg = "";
            $scope.errorMsg = "[Error]:"+err;
            return;
        };
        if($scope.parameter.id ==0){
            $('#spinnersModal').modal('show');
            $http.post('http://'+window.location.host+'/yat/api/data',
                {'method':'addParam','userId':userId,'parameter': JSON.stringify($scope.parameter)}
            ).success(function (data) {
                if(data.success){
                    $scope.initParam();
                    $scope.getParam();
                    $scope.successMsg = "[Pass]:新增参数 成功！";
                    $scope.errorMsg = "";
                }else{
                    $scope.successMsg = "";
                    $scope.errorMsg = "[Error]:"+data.data;
                }
                $('#spinnersModal').modal('hide');
            });
        }
        if($scope.parameter.id >0){
            $('#spinnersModal').modal('show');
            $http.post('http://'+window.location.host+'/yat/api/data',
                {'method':'modifyParam','userId':userId,'parameter': JSON.stringify($scope.parameter)}
            ).success(function (data) {
                if(data.success){
                    $scope.getParam();
                    $scope.successMsg = "[Pass]:修改参数 成功！";
                    $scope.errorMsg = "";
                }else{
                    $scope.successMsg = "";
                    $scope.errorMsg = "[Error]:"+data.data;
                }
                $('#spinnersModal').modal('hide');
            });
        }
    }
    
    $scope.getParam = function () {
        var search = {'s_env_id':$scope.global.envId,'s_param_name':$scope.trimVar($scope.s_param_name),
            's_param_note':$scope.trimVar($scope.s_param_note),
            'isDiff':$scope.openParamCopyDiv,'s_env_id_diff_destination':$scope.s_env_id_diff_destination, 'diffMiss':$scope.diffMiss};
        $('#spinnersModal').modal('show');
        $http.post('http://'+window.location.host+'/yat/api/data',
            {'method':'getParam','search':JSON.stringify(search) , 'page':$scope.page,'count':20}
            ).success(function (data) {
            if(data.success){
                $scope.paramList = data.data;
                $scope.totalPage = data.totalPage;
                $scope.totalCount = data.totalCount;
                $scope.missCount = data.missCount;
                $scope.moreCount = data.moreCount;
                $scope.clickAddParam();
                $('#pageLimit').bootstrapPaginator("setOptions",{'currentPage':$scope.page,'totalPages':data.totalPage});
            }else{
                $scope.errorInfo = "[Error]:"+data.data;
            }
            $('#spinnersModal').modal('hide');
        });
    }
    $scope.initParam = function () {
        $scope.parameter = {'id':0,'envId':$scope.global.envId,'name':'','note':'',
            'paramType':1,
            'kvVal':'',
            'dbId':0,'dbSql':'','dbColumn':'','dbGetValType':1,
            'tcId':0,'tcGetValType':1,'tcJsonPath':'','tcJsonMatchNum':1,
            'tcLeft':'','tcRight':'','tcLrMatchNum':1,'tcHeader':'','tcCookie':'',
            'addUserId':0,'addTime':'','updateUserId':'','updateTime':''};
    }
    $scope.clickAddParam = function () {
        $scope.initParam();
        $scope.triggerSelect2("select2_env",0);
        $scope.triggerSelect2("select2_service",0);
        $scope.triggerSelect2("select2_db",0);
        $scope.triggerSelect2("select2_case",0);
    }

    $scope.clickDelParam = function () {
        var userId = $scope.getUserId();
        if(userId == undefined){return;}
        if(confirm("确认删除<"+$scope.parameter.name+">参数？")){
            $('#spinnersModal').modal('show');
            $http.post('http://'+window.location.host+'/yat/api/data',
                {'method':'deleteParam','userId':userId,'paramId':$scope.parameter.id}
            ).success(function (data) {
                if(data.success){
                    $scope.getParam();
                    $scope.clickAddParam();
                }else{
                    alert("[Error]:"+data.data);
                }
                $('#spinnersModal').modal('hide');
            });
        }
    }
    $scope.clickTr = function (tr) {
        $scope.selectedTr = tr;
        $scope.parameter = JSON.parse(JSON.stringify(tr));
        // $scope.parameter.paramData = JSON.parse($scope.parameter.paramData);
        $scope.triggerSelect2("select2_env",$scope.parameter.envId);
        $scope.triggerSelect2("select2_service",$scope.parameter.serviceId);
        $scope.triggerSelect2("select2_db",$scope.parameter.dbId);
        $scope.triggerSelect2("select2_case",$scope.parameter.tcId);
    }
    // compare
    $scope.openCompareDiv = function () {
        $scope.tagStyle={'miss':{'border-color':'','background-color':'aliceblue'},'more':{'border-color':'','background-color':'aliceblue'}};
        $scope.triggerSelect2("select2_service_diff_destination",0);
        $scope.s_env_id_diff_destination = 0;
        $scope.openParamCopyDiv=true;
        $scope.getParam();
    }
    $scope.closeCompareDiv = function () {
        $scope.openParamCopyDiv=false;
        $scope.getParam();
    }
    $scope.clickTag = function (type) {
        if(type == 'miss'){
            $scope.diffMiss = true;
            $scope.tagStyle={'miss':{'border-color':'lightskyblue','background-color':'lavender'},'more':{'border-color':'','background-color':'aliceblue'}};
        }
        if(type == 'more'){
            $scope.diffMiss = false;
            $scope.tagStyle={'miss':{'border-color':'','background-color':'aliceblue'},'more':{'border-color':'lightskyblue','background-color':'lavender'}};
        }
        $scope.getParam();
    }
    $scope.diffParam = function () {
        if($scope.s_env_id_diff_destination==0){
            alert("请选择：目标 环境");
            return;
        }
        $scope.copyEnvIdOfDestination = $scope.s_env_id_diff_destination;
        $scope.clickTag('miss');
        $scope.getParam();
    }
    $scope.copyParam = function (isDel,id) {
        var userId = $scope.getUserId();
        if(userId == undefined){return;}
        if($scope.paramList.length == 0){
            alert("无参数！");
            return;
        }
        var msg;
        if(id == undefined){
            msg = "确认 全部一键复制？";
        }else{
            msg = "确认 单条复制？\n参数ID="+id;
        }
        if(confirm(msg)){
            $('#spinnersModal').modal('show');
            $http.post('http://'+window.location.host+'/yat/api/data',
                {'method':'copyParam','userId':userId,'isDelete':isDel,'paramId':id,
                    'sourceEnvId':$scope.global.envId,'destinationEnvId':$scope.copyEnvIdOfDestination}
            ).success(function (data) {
                if(data.success){
                    alert(data.data);
                    $scope.getParam();
                }else{
                    alert("[Error]:"+data.data);
                }
                $('#spinnersModal').modal('hide');
            });
        }
    }

    //debug
    $scope.debug = function () {
        var userId = $scope.getUserId();
        if(userId == undefined){return;}
        if($scope.parameter.id == 0){
            alert("未选择操作！");
            return;
        }
        $('#spinnersModal').modal('show');
        $http.post('http://'+window.location.host+'/yat/api/data',
            {'method':'debugParameter','userId':userId,'parameter':JSON.stringify($scope.parameter)}
        ).success(function (data) {
            if(data.success){
                $scope.rightSidebar(true);
                $scope.debugData = data.data;
            }else{
                alert("[Error]:"+data.data);
            }
            $('#spinnersModal').modal('hide');
        });
    }
    $scope.triggerSelect2 = function (className , val) {
        $('.'+className).val(val);
        $('.'+className).trigger('change');
    }
    $(".select2_env_search").select2();
    $('.select2_env_search').on('select2:select', function (e) {
        $scope.$apply(function () {
            $scope.s_env_id = e.params.data.id;
        });
    });
    $(".select2_service_search").select2();
    $('.select2_service_search').on('select2:select', function (e) {
        $scope.$apply(function () {
            $scope.s_service_id = e.params.data.id;
        });
    });
    $(".select2_env_diff_destination").select2();
    $('.select2_env_diff_destination').on('select2:select', function (e) {
        $scope.$apply(function () {
            $scope.s_env_id_diff_destination = e.params.data.id;
        });
    });
    $(".select2_env").select2();
    $('.select2_env').on('select2:select', function (e) {
        $scope.$apply(function () {
            $scope.parameter.envId = e.params.data.id;
        });
    });
    $(".select2_service").select2();
    $('.select2_service').on('select2:select', function (e) {
        $scope.$apply(function () {
            $scope.parameter.serviceId = e.params.data.id;
        });
    });
    $(".select2_db").select2();
    $('.select2_db').on('select2:select', function (e) {
        $scope.$apply(function () {
            $scope.parameter.dbId = e.params.data.id;
        });
    });
    $(".select2_case").select2();
    $('.select2_case').on('select2:select', function (e) {
        $scope.$apply(function () {
            $scope.parameter.tcId = e.params.data.id;
        });
    });

    $scope.global = $scope.getGlobalEnvId();
    $scope.page = 1;
    $scope.totalPage = 0;
    $scope.totalCount = 0;
    $scope.dbResGetType = 1;
    $scope.caseGetValType = 1;
    $scope.openParamCopyDiv=false;
    // $scope.getAllTeam();
    // $scope.getAllEnvironment();
    // $scope.getAllService();
    $scope.getDestinationEnvironment();
    $scope.getAllServiceByPrjId();
    $scope.getAllDbByEnvId();
    $scope.getAllCaseByEnvId();
    $scope.getParam();
    $scope.initParam();
});
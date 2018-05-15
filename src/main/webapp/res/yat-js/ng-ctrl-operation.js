
myAppModule.controller('ng-ctrl-yat-content', function ($scope ,$rootScope , $http , $timeout , $interval, $cookies) {


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
                $scope.getOps();
            });
        }
    });
    $scope.rightSidebar = function (isOpen) {
        $('#right-sidebar').toggleClass('sidebar-open' , isOpen);
    }
    $scope.setJavaCode=function(data){
        if($scope.cm==undefined || $scope.cm == null ){
            $scope.cm = CodeMirror.fromTextArea(document.getElementById("area-data"), {
                mode:'text/x-java',
                lineNumbers: true,
                matchBrackets: true,
                styleActiveLine: true,
                readOnly:false,
                lineSeparator:'\n'
            });
        }
        $scope.cm.setValue(data);
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
        if($scope.operation.name.trim()==""){
            return "未填写操作名！";
        }
        if($scope.operation.opsType == 1){
            if($scope.operation.dbId==0){
                return "未选择数据库！";
            }
            if($scope.operation.dbSql.trim()==""){
                return "未填写sql语句！";
            }
        }
        if($scope.operation.opsType == 2){
            if($scope.operation.httpUrl.trim()==""){
                return "未填写URL！";
            }
        }
        if($scope.operation.opsType == 3){
            if($scope.operation.tcId==0){
                return "未选择测试用例！";
            }
            if($scope.operation.tcGetValType=='1'){
                if($scope.operation.tcJsonPath.trim()==""){
                    return "未填写JsonPath！";
                }
                if(isNaN($scope.operation.tcJsonMatchNum)){
                    return "匹配第几个值 不是数字！";
                }
            }
            if($scope.operation.tcGetValType=='2'){
                if($scope.operation.tcLeft.trim()=="" && $scope.operation.tcRight.trim()==""){
                    return "左右边界不能全部为空！";
                }
                if(isNaN($scope.operation.tcLrMatchNum)){
                    return "匹配第几个值 不是数字！";
                }
            }
        }
        if($scope.operation.opsType == 4){
            if($scope.cm.getValue().trim()==""){
                return "未填写JAVA方法！";
            }
        }
        if($scope.operation.opsType == 5){
            if(isNaN($scope.operation.waitTime)){
                return "等待时间 不是数字！";
            }
        }
        return "";
    }
    $scope.convertJavaCodeToStr = function () {
        if($scope.NN($scope.cm)){
            // var dataArr = [];
            // for(var i=0;i<=$scope.cm.lastLine();i++){
            //     dataArr.push($scope.cm.getLine(i));
            // }
            // $scope.operation.javaCode = JSON.stringify(dataArr);
            $scope.operation.javaCode = $scope.cm.getValue();
        }
    }
    $scope.resetTcValList = function () {
        var arr = [];
        for(var i=0;i<$scope.operation.tcValList.length;i++){
            var header = $scope.operation.tcValList[i].h;
            var cookie = $scope.operation.tcValList[i].c;
            var jsonPath = $scope.operation.tcValList[i].j;
            var paramName = $scope.operation.tcValList[i].p;
            if($scope.operation.tcValList[i].h == undefined){$scope.operation.tcValList[i].h = ''};
            if($scope.operation.tcValList[i].c == undefined){$scope.operation.tcValList[i].c = ''};
            if($scope.operation.tcValList[i].j == undefined){$scope.operation.tcValList[i].j = ''};
            if($scope.operation.tcValList[i].p == undefined){$scope.operation.tcValList[i].p = ''};
            if($scope.operation.tcValList[i].h == "" &&
                $scope.operation.tcValList[i].c == "" &&
                $scope.operation.tcValList[i].j == "" &&
                $scope.operation.tcValList[i].p == ""){
                continue;
            }
            arr.push($scope.operation.tcValList[i]);
        }
        $scope.operation.tcValList = arr;
        $scope.operation.tcValList.push({'h':'','c':'','j':'','p':''});
    }
    $scope.clickSaveOps = function () {
        var userId = $scope.getUserId();
        if(userId == undefined){return;}
        var err = $scope.checkBeforeSave();
        if(err!=""){
            $scope.successMsg = "";
            $scope.errorMsg = "[Error]:"+err;
           return;
        };
        $scope.resetTcValList();
        if($scope.operation.opsType == 4){
            $scope.convertJavaCodeToStr();
        }
        if($scope.operation.id ==0){
            $('#spinnersModal').modal('show');
            $http.post('http://'+window.location.host+'/yat/api/data',
                {'method':'addOps','userId':userId,'operation': JSON.stringify($scope.operation)}
            ).success(function (data) {
                if(data.success){
                    $scope.initOps();
                    $scope.getOps();
                    $scope.successMsg = "[Pass]:新增操作 成功！";
                    $scope.errorMsg = "";
                }else{
                    $scope.successMsg = "";
                    $scope.errorMsg = "[Error]:"+data.data;
                }
                $('#spinnersModal').modal('hide');
            });
        }
        if($scope.operation.id >0){
            $('#spinnersModal').modal('show');
            $http.post('http://'+window.location.host+'/yat/api/data',
                {'method':'modifyOps','userId':userId,'operation': JSON.stringify($scope.operation)}
            ).success(function (data) {
                if(data.success){
                    $scope.getOps();
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

    $scope.getOps = function () {
        var search = {
            's_ops_id':$scope.trimVar($scope.s_ops_id),
            's_ops_name':$scope.trimVar($scope.s_ops_name),
            's_ops_note':$scope.trimVar($scope.s_ops_note),
            'envId':$scope.global.envId};
        $('#spinnersModal').modal('show');
        $http.post('http://'+window.location.host+'/yat/api/data',
            {'method':'getOps','search':JSON.stringify(search) , 'page':$scope.page,'count':20}
        ).success(function (data) {
            if(data.success){
                $scope.opsList = data.data;
                $scope.totalPage = data.totalPage;
                $scope.totalCount = data.totalCount;
                $('#pageLimit').bootstrapPaginator("setOptions",{'currentPage':$scope.page,'totalPages':data.totalPage});
                if(data.data.length > 0){
                    // $scope.clickTr(data.data[0]);
                }else{
                    $scope.clickAddOps();
                }
            }else{
                $scope.errorInfo = "[Error]:"+data.data;
            }
            $('#spinnersModal').modal('hide');
        });
    }
    $scope.initOps = function () {
        $scope.operation = {'id':0,'name':'','note':'','opsType':1,'envId':$scope.global.envId,
            'dbId':0,'dbSql':'','httpIsPost':false,'httpUrl':'','httpParam':'',
            'tcId':0,'tcValList':[{'h':'','c':'','j':'','p':''}],
            'waitTime':1,'javaCode':'','bshArgs':'',
            'addUserId':0,'addTime':'','updateUserId':'','updateTime':''};
    }
    $scope.clickAddOps = function () {
        $scope.initOps();
        $scope.setJavaCode("");
        $scope.triggerSelect2("select2_db",0);
        $scope.triggerSelect2("select2_case",0);
    }

    $scope.clickDelOps = function () {
        if(confirm("确认删除<"+$scope.operation.name+">参数？")){
            var userId = $scope.getUserId();
            if(userId == undefined){
                return;
            }
            $('#spinnersModal').modal('show');
            $http.post('http://'+window.location.host+'/yat/api/data',
                {'method':'deleteOps','userId':userId,'opsId':$scope.operation.id}
            ).success(function (data) {
                if(data.success){
                    $scope.getOps();
                    $scope.clickAddOps();
                }else{
                    alert("[Error]:"+data.data);
                }
                $('#spinnersModal').modal('hide');
            });
        }
    }
    $scope.clickTr = function (tr) {
        $scope.selectedTr = tr;
        $scope.operation = JSON.parse(JSON.stringify(tr));
        $scope.operation.tcValList = JSON.parse($scope.operation.tcValList);
        if($scope.operation.opsType == 4){
            $scope.getJavaCodeByOpsId();
        }
        $scope.triggerSelect2("select2_db",$scope.operation.dbId);
        $scope.triggerSelect2("select2_case",$scope.operation.tcId);
    }
    $scope.getJavaCodeByOpsId = function () {
        $http.post('http://'+window.location.host+'/yat/api/data',
            {'method':'getJavaCodeByOpsId','opsId':$scope.operation.id}
        ).success(function (data) {
            if(data.success){
                $scope.setJavaCode(data.data.code);
                $scope.operation.bshArgs = data.data.bshArgs;
            }else{
                alert("[Error]:"+data.data);
            }
        });
    }
    $scope.debug = function () {
        if($scope.operation.id == 0){
            alert("未选择操作！");
            return;
        }
        var userId = $scope.getUserId();
        if(userId == undefined){
            return;
        }

        $('#spinnersModal').modal('show');
        $http.post('http://'+window.location.host+'/yat/api/data',
            {'method':'debugOps','userId':userId,'operation':JSON.stringify($scope.operation)}
        ).success(function (data) {
            if(data.success){
                $scope.rightSidebar(true);
                $scope.logList = [];
                $scope.exampleWriteList = [];
                $scope.showSqlTable = false;
                if(data.type == 'sql-query'){
                    $scope.logList.push(data.sql);
                    $scope.thList = data.data.colNameArr;
                    $scope.trList = data.data.rowArr;

                    var thSize = $scope.thList.length;
                    for(var i=0;i<$scope.trList.length;i++){
                        for(var j=0;j<thSize;j++){
                            $scope.exampleWriteList.push($scope.thList[j]+"["+i+"] : "+$scope.trList[i][j]);
                        }
                    }
                    $scope.showSqlTable = true;
                }
                if(data.type == 'sql-update'){
                    $scope.logList.push(data.sql);
                    $scope.logList.push("更新："+data.data+"行");
                }
                if(data.type == 'tc'){
                    // $scope.logList = data.response;
                    $scope.debugTcCookieList = [];
                    $scope.debugTcHeaderList = [];
                    $scope.debugTcResponse = "";
                    for(var key in data.response){
                        var resp = data.response[key];
                        if(key == 'header' || key == 'cookie'){
                            for(var i=0;i<resp.length;i++){
                                var k = resp[i].name;
                                var v = resp[i].value;
                                if(key == 'header'){
                                    $scope.debugTcHeaderList.push({'k':k,'v':v});
                                }
                                if(key == 'cookie'){
                                    $scope.debugTcCookieList.push({'k':k,'v':v});
                                }
                            }
                        }
                        if(key == 'response'){
                            $scope.debugTcResponse = resp;
                        }
                    }
                    $scope.exampleWriteList = data.data;
                    $scope.showTcDiv = true;
                }
                if(data.type == 'java'){
                    $scope.logList.push(data.data);
                }
            }else{
                alert("[Error]:"+data.data);
            }
            $('#spinnersModal').modal('hide');
        });
    }
    $scope.addTcValTr = function (idx) {
        if($scope.operation.tcValList.length == idx+1){
            $scope.operation.tcValList.push({'h':'','c':'','j':'','p':''});
        }
    }
    $scope.triggerSelect2 = function (className , val) {
        $('.'+className).val(val);
        $('.'+className).trigger('change');
    }
    $(".select2_env_debug").select2();
    $('.select2_env_debug').on('select2:select', function (e) {
        $scope.$apply(function () {
            $scope.d_env_id = e.params.data.id;
        });
    });
    $(".select2_service_debug").select2();
    $('.select2_service_debug').on('select2:select', function (e) {
        $scope.$apply(function () {
            $scope.d_service_id = e.params.data.id;
        });
    });
    $(".select2_db").select2();
    $('.select2_db').on('select2:select', function (e) {
        $scope.$apply(function () {
            $scope.operation.dbId = e.params.data.id;
        });
    });
    $(".select2_case").select2();
    $('.select2_case').on('select2:select', function (e) {
        $scope.$apply(function () {
            $scope.operation.tcId = e.params.data.id;
        });
    });

    $scope.global = $scope.getGlobalEnvId();
    var urlOpId = $scope.getUrlParamValue("opId");
    if($scope.NN(urlOpId)){
        $scope.s_ops_id = urlOpId;
    }
    $scope.page = 1;
    $scope.totalPage = 0;
    $scope.totalCount = 0;
    $scope.dbResGetType = 1;
    $scope.caseGetValType = 1;
    $scope.getAllDbByEnvId();
    $scope.getAllCaseByEnvId();
    $scope.getOps();
    $scope.initOps();
    $scope.setJavaCode("");
});

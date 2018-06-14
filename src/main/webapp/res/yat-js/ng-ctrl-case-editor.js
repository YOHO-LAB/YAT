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

    $scope.openRightSidebar = function (ipt) {
        $scope.sbType = ipt;
        $scope.nowTab = ipt;
        $('#right-sidebar').toggleClass('sidebar-open' , true);
        // if(ipt == 'pre'){}
    }
    $scope.rightSidebar = function (isOpen) {
        $('#right-sidebar').toggleClass('sidebar-open' , isOpen);
    }

    $scope.getAllTeam = function () {
        $http.post('http://'+window.location.host+'/yat/api/data',
            {'method':'getAllTeam'}
        ).success(function (data) {
            if(data.success){
                $scope.teamList = data.data;
                if($scope.tc != undefined){
                    $scope.triggerSelect2("select2_team",$scope.tc.teamId);
                }
            }else{
                alert("[Error]:"+data.data);
            }
        });
    }
    $scope.getAllHostParamByEnvId = function () {
        $http.post('http://'+window.location.host+'/yat/api/data',
            {'method':'getAllHostParamByEnvId','envId':$scope.global.envId}
        ).success(function (data) {
            if(data.success){
                $scope.hostParamList = data.data;
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
                if($scope.tc != undefined){
                    $scope.triggerSelect2("select2_service",$scope.tc.serviceId);
                }
            }else{
                alert("[Error]:"+data.data);
            }
        });
    }
    $scope.getAllOpsByEnvId = function () {
        $http.post('http://'+window.location.host+'/yat/api/data',
            {'method':'getAllOpsByEnvId','envId':$scope.global.envId}
        ).success(function (data) {
            if(data.success){
                $scope.opsList = data.data;
            }else{
                alert("[Error]:"+data.data);
            }
        });
    }
    $scope.getOps = function () {
        var search = {'s_ops_id':'','s_ops_name':$scope.trimVar($scope.s_ops_name),'s_ops_note':$scope.trimVar($scope.s_ops_note),'envId':$scope.global.envId};
        $('#spinnersModal').modal('show');
        $http.post('http://'+window.location.host+'/yat/api/data',
            {'method':'getOps','search':JSON.stringify(search) , 'page':$scope.page,'count':20}
        ).success(function (data) {
            if(data.success){
                $scope.opsList = data.data;
                $scope.totalPage = data.totalPage;
                $scope.totalCount = data.totalCount;
                $('#pageLimit').bootstrapPaginator("setOptions",{'currentPage':$scope.page,'totalPages':data.totalPage});
            }else{
                $scope.errorInfo = "[Error]:"+data.data;
            }
            $('#spinnersModal').modal('hide');
        });
    }

    $scope.preOpsIdsOps = function (isAdd,id) {
        if(isAdd){
            $scope.tc.preOpsIds += id + ",";
        }else{
            var newIds = "";
            var idsArr = $scope.tc.preOpsIds.split(",");
            for(var i=0;i<idsArr.length;i++){
                if(idsArr[i].trim()!="" && idsArr[i]!=id){
                    newIds += idsArr[i] + ",";
                }
            }
            $scope.tc.preOpsIds = newIds;
        }
    }
    $scope.checkTrExist = function (list , tr) {
        for(var i=0;i<list.length;i++){
            if(list[i].id == tr.id){
                return true;
            }
        }
        return false;
    }
    $scope.addOps =function (tr) {
        if($scope.nowTab=='pre'){
            if(!$scope.checkTrExist($scope.preOpsList , tr)){
                $scope.preOpsList.push(tr);
            }
        }
        if($scope.nowTab=='after'){
            if(!$scope.checkTrExist($scope.afterOpsList , tr)){
                $scope.afterOpsList.push(tr);
            }
        }
        if($scope.nowTab=='post'){
            if(!$scope.checkTrExist($scope.postOpsList , tr)){
                $scope.postOpsList.push(tr);
            }
        }
    }
    $scope.moveTag = function (tab,direction,idx) {
        if(tab=='pre'){
            if(direction=='left'){
                if(idx>0){
                    var tmp = $scope.preOpsList[idx-1];
                    $scope.preOpsList[idx-1] = $scope.preOpsList[idx];
                    $scope.preOpsList[idx] = tmp;
                }
            }
            if(direction=='right'){
                if(idx<$scope.preOpsList.length-1){
                    var tmp = $scope.preOpsList[idx+1];
                    $scope.preOpsList[idx+1] = $scope.preOpsList[idx];
                    $scope.preOpsList[idx] = tmp;
                }
            }
        }
        if(tab=='after'){
            if(direction=='left'){
                if(idx>0){
                    var tmp = $scope.afterOpsList[idx-1];
                    $scope.afterOpsList[idx-1] = $scope.afterOpsList[idx];
                    $scope.afterOpsList[idx] = tmp;
                }
            }
            if(direction=='right'){
                if(idx<$scope.afterOpsList.length-1){
                    var tmp = $scope.afterOpsList[idx+1];
                    $scope.afterOpsList[idx+1] = $scope.afterOpsList[idx];
                    $scope.afterOpsList[idx] = tmp;
                }
            }
        }
        if(tab=='post'){
            if(direction=='left'){
                if(idx>0){
                    var tmp = $scope.postOpsList[idx-1];
                    $scope.postOpsList[idx-1] = $scope.postOpsList[idx];
                    $scope.postOpsList[idx] = tmp;
                }
            }
            if(direction=='right'){
                if(idx<$scope.postOpsList.length-1){
                    var tmp = $scope.postOpsList[idx+1];
                    $scope.postOpsList[idx+1] = $scope.postOpsList[idx];
                    $scope.postOpsList[idx] = tmp;
                }
            }
        }
    }
    $scope.delTag =function (tab,idx) {
        if(tab=='pre'){
            $scope.preOpsList.splice(idx,1);
        }
        if(tab=='after'){
            $scope.afterOpsList.splice(idx,1);
        }
        if(tab=='post'){
            $scope.postOpsList.splice(idx,1);
        }
    }
    $scope.checkBeforeSave =function () {
        if($scope.tc.teamId == 0){
            return "未选择团队！";
        }
        if($scope.tc.serviceId == 0){
            return "未选择服务名！";
        }
        if($scope.tc.testEnvId == 0){
            return "未选择测试环境！";
        }
        if($scope.tc.note.trim() == ""){
            return "未填写用例名！";
        }
        if($scope.tc.method.trim() == ""){
            return "未填写Method！";
        }
        // if($scope.tc.url.trim() == ""){
        //     return "未填写Url！";
        // }
        return "";
    }
    $scope.convertOpsToString = function () {
        var str = "";
        for(var i=0;i<$scope.preOpsList.length;i++){
            str += $scope.preOpsList[i].id+",";
        }
        $scope.tc.preOpsIds = str;
        str = "";
        for(var i=0;i<$scope.afterOpsList.length;i++){
            str += $scope.afterOpsList[i].id+",";
        }
        $scope.tc.afterTestOpsIds = str;
        str = "";
        for(var i=0;i<$scope.postOpsList.length;i++){
            str += $scope.postOpsList[i].id+",";
        }
        $scope.tc.postOpsIds = str;
    }
    $scope.convertCheckPointToString = function () {
        if($scope.jsonCheckList == undefined){
            $scope.jsonCheckList = [];
        }
        if($scope.paramCheckList == undefined){
            $scope.paramCheckList = [];
        }
        if($scope.headerList == undefined){
            $scope.headerList = [];
        }
        if($scope.cookieList == undefined){
            $scope.cookieList = [];
        }
        if($scope.getHttpResList == undefined){
            $scope.getHttpResList = [];
        }
        for(var i=0;i<$scope.jsonCheckList.length;i++){
            var k = $scope.jsonCheckList[i].k.trim();
            var ifList = $scope.jsonCheckList[i].if;
            if(k==''){
                $scope.jsonCheckList.splice(i,1);
                i--;
                continue;
            }
            if(ifList != undefined){
                if(ifList.length == 0){
                    $scope.jsonCheckList[i].if = undefined;
                }else{
                    for(var j=0;j<ifList.length;j++){
                        var k2 = ifList[j].k.trim();
                        if(k2==''){
                            ifList.splice(j,1);
                            j--;
                        }
                    }
                }
            }
        }
        for(var i=0;i<$scope.paramCheckList.length;i++){
            var k = $scope.paramCheckList[i].k.trim();
            if(k==''){
                $scope.paramCheckList.splice(i,1);
            }
        }
        $scope.tc.jsonCheck = JSON.stringify($scope.jsonCheckList);
        $scope.tc.dbCheck = JSON.stringify($scope.paramCheckList);

        $scope.tc.headerList = JSON.stringify($scope.headerList);
        $scope.tc.cookieList = JSON.stringify($scope.cookieList);
        $scope.tc.getHttpResList = JSON.stringify($scope.getHttpResList);
    }
    $scope.saveCase = function () {
        var err = $scope.checkBeforeSave();
        if(err!=""){
            $scope.successMsg = "";
            $scope.errorMsg = "[Error]:"+err;
            return;
        };
        $scope.convertOpsToString();
        $scope.convertCheckPointToString();
        if($scope.tc.id ==0){
            $('#spinnersModal').modal('show');
            $http.post('http://'+window.location.host+'/yat/api/tc',
                {'method':'addTestcase','userId':$scope.getUserId(),'tc': JSON.stringify($scope.tc),'ds':JSON.stringify($scope.ds)}
            ).success(function (data) {
                if(data.success){
                    location.href = location.pathname + "?caseId="+data.data;
                }else{
                    $scope.successMsg = "";
                    $scope.errorMsg = "[Error]:"+data.data;
                }
                $('#spinnersModal').modal('hide');
            });
        }
        if($scope.tc.id >0){
            $('#spinnersModal').modal('show');
            $http.post('http://'+window.location.host+'/yat/api/tc',
                {'method':'modifyTestcase','userId':$scope.getUserId(),'tc': JSON.stringify($scope.tc),'ds':JSON.stringify($scope.ds)}
            ).success(function (data) {
                if(data.success){
                    $scope.successMsg = "[Pass]:修改测试用例 成功！";
                    $scope.errorMsg = "";
                }else{
                    $scope.successMsg = "";
                    $scope.errorMsg = "[Error]:"+data.data;
                }
                $('#spinnersModal').modal('hide');
            });
        }
    }
    $scope.getDebugLog = function () {
        if(!$scope.NN($scope.runId)){
            $scope.debugLogError = "您还未调试！";
            return;
        }
        $http.post('http://'+window.location.host+'/yat/api/tc',
            {'method':'getDebugLog','runId': $scope.runId}
        ).success(function (data) {
            if(data.success){
                $scope.debugLogError = "";
                $scope.debugLogList= data.data;
            }else{
                $scope.debugLogError = data.data;
            }
        });
    }
    $scope.debugCase = function (dsIdx) {
        var userId = $scope.getUserId();
        if(userId == undefined){
            return;
        }
        $scope.openRightSidebar('log');
        var err = $scope.checkBeforeSave();
        if(err!=""){
            $scope.successMsg = "";
            $scope.errorMsg = "[Error]:"+err;
            return;
        };
        $scope.convertOpsToString();
        $scope.convertCheckPointToString();
        $('#spinnersModal').modal('show');
        $http.post('http://'+window.location.host+'/yat/api/tc',
            {'method':'debugTestcase','userId':userId,'caseId':$scope.tc.id,'dsIdx':dsIdx}
        ).success(function (data) {
            if(data.success){
                // $scope.uuid = data.data;
                $scope.runId = data.data;
                $scope.getDebugLog();
            }else{
                alert(data.data)
            }
            $('#spinnersModal').modal('hide');
        });
    }
    $scope.openReportPage = function () {
        if($scope.runId != undefined){
            window.open("report.html?reportId="+$scope.runId);
        }
    }
    $scope.delCase = function () {
        if(confirm("确认删除<"+$scope.tc.note+">用例？")){
            $('#spinnersModal').modal('show');
            $http.post('http://'+window.location.host+'/yat/api/tc',
                {'method':'deleteTestcase','userId':$scope.getUserId(),'id':$scope.tc.id}
            ).success(function (data) {
                if(data.success){
                    location.href = location.pathname + "?caseId="+$scope.tc.id;
                }else{
                    alert("[Error]:"+data.data);
                }
                $('#spinnersModal').modal('hide');
            });
        }
    }
    $scope.openSelectCompareTypeModal = function (li) {
        $scope.selectLi = li;
        $scope.compareType = li.t;
        $('#myModal_compare_type').modal('show');
    }
    $scope.selectCompareType = function () {
        $scope.selectLi.t = $scope.compareType;
        $('#myModal_compare_type').modal('hide');
    }
    $scope.addCheckPoint = function (ipt,ifControl,idx) {
        if($scope.jsonCheckList == undefined){
            $scope.jsonCheckList = [];
        }
        if($scope.paramCheckList == undefined){
            $scope.paramCheckList = [];
        }
        if(ipt == 'json'){
            if(ifControl){
                $scope.jsonCheckList.push({'if':[],'k':'','t':'==','v':''});
            }else{
                if(idx == undefined){
                    $scope.jsonCheckList.push({'k':'','t':'==','v':''});
                }else{
                    $scope.jsonCheckList[idx].if.push({'k':'','t':'==','v':''});
                }

            }
        }
        if(ipt == 'param'){
            $scope.paramCheckList.push({'k':'','t':'==','v':''});
        }
    }
    $scope.delCheckPoint = function (ipt,idx,ifControl,ifIdx) {
        if(ipt == 'json'){
            if(ifControl){
                $scope.jsonCheckList[idx].if.splice(ifIdx,1);
            }else{
                $scope.jsonCheckList.splice(idx,1);
            }
        }
        if(ipt == 'param'){
            $scope.paramCheckList.splice(idx,1);
        }
    }
    $scope.newCase = function () {
        location.href = location.pathname;
    }
    $scope.copyCase = function () {
        location.href = location.pathname + "?copyCaseId="+$scope.tc.id;
    }
    $scope.initUrlEditor = function () {
        $scope.ueHeaderList = [{'k':'','v':''}];
        $scope.ueCookieList = [{'k':'','v':''}];
        $scope.ueUrlParamList = [{'k':'','v':''}];
        $scope.ueParamDataList = [{'k':'','v':''}];
        $scope.ueGetHttpResList = [{'k':'','v':''}];
        $scope.ueIsFromData = true;
        $scope.ueRawData = '';
        $scope.ueUri = "";
        $scope.testHttpData = undefined;
    }
    $scope.usOperateTable = function (isAdd,type,idx) {
        if(isAdd){
            if(type == 'header'){
                if($scope.ueHeaderList.length == idx+1){
                    $scope.ueHeaderList.push({'k':'','v':''});
                }
            }
            if(type == 'cookie'){
                if($scope.ueCookieList.length == idx+1){
                    $scope.ueCookieList.push({'k':'','v':''});
                }
            }
            if(type == 'urlParam'){
                if($scope.ueUrlParamList.length == idx+1){
                    $scope.ueUrlParamList.push({'k':'','v':''});
                }
            }
            if(type == 'paramData'){
                if($scope.ueParamDataList.length == idx+1){
                    $scope.ueParamDataList.push({'k':'','v':''});
                }
            }
            if(type == 'getHttpRes'){
                if($scope.ueGetHttpResList.length == idx+1){
                    $scope.ueGetHttpResList.push({'k':'','v':''});
                }
            }
        }else{
            if(type == 'header'){
                $scope.ueHeaderList.splice(idx,1);
            }
            if(type == 'cookie'){
                $scope.ueCookieList.splice(idx,1);
            }
            if(type == 'urlParam'){
                $scope.ueUrlParamList.splice(idx,1);
            }
            if(type == 'paramData'){
                $scope.ueParamDataList.splice(idx,1);
            }
            if(type == 'getHttpRes'){
                $scope.ueGetHttpResList.splice(idx,1);
            }
        }
    }
    $scope.ueModelOpen = function (type) {
        $scope.ueModelType = type;
        if(type == 'header'){
            $scope.ueModelName = "导入头部";
        }
        if(type == 'cookie'){
            $scope.ueModelName = "导入Cookies";
        }
        if(type == 'urlParam'){
            $scope.ueModelName = "导入GET参数";
        }
        if(type == 'paramData'){
            $scope.ueModelName = "导入表单";
        }
        $('#myModal_urlEditor').modal('show');
    }
    $scope.ueModelSave = function () {
        if($scope.ueImportData != undefined){
            $scope.ueImportData = $scope.ueImportData.trim();
            if($scope.ueImportData != ""){
                var splitTag1 = "";
                var splitTag2 = "";
                var isJson = false;
                if($scope.ueModelType == 'header'){
                    splitTag1 = "\n";
                    splitTag2 = ":";
                }
                if($scope.ueModelType == 'cookie'){
                    splitTag1 = ";";
                    splitTag2 = "=";
                }
                if($scope.ueModelType == 'urlParam' || $scope.ueModelType == 'paramData'){
                    splitTag1 = "&";
                    splitTag2 = "=";
                    if($scope.ueImportData.startsWith("{") && $scope.ueImportData.endsWith("}")){
                        isJson = true;
                    }
                }
                var arr2 = [];
                if(isJson){
                    var inputData = undefined;
                    try {
                        inputData = JSON.parse($scope.ueImportData);
                        for(var k in inputData){
                            var v = inputData[k];
                            arr2.push({'k':k,'v':v});
                        }
                    }catch (err){
                        alert(err);
                    }
                }else{
                    var arr = $scope.ueImportData.split(splitTag1);
                    for(var i=0;i<arr.length;i++){
                        var idx = arr[i].indexOf(splitTag2);
                        var k = arr[i];
                        var v = "";
                        if(idx >= 0){
                            k = arr[i].substring(0,idx);
                            v = arr[i].substring(idx+1);
                        }
                        arr2.push({'k':k,'v':v});
                    }
                }
                arr2.push({'k':'','v':''});
                if($scope.ueModelType == 'header'){
                    $scope.ueHeaderList = arr2;
                }
                if($scope.ueModelType == 'cookie'){
                    $scope.ueCookieList = arr2;
                }
                if($scope.ueModelType == 'urlParam'){
                    $scope.ueUrlParamList = arr2;
                }
                if($scope.ueModelType == 'paramData'){
                    $scope.ueParamDataList = arr2;
                }
            }
        }
        $('#myModal_urlEditor').modal('hide');
    }
    $scope.ueEnsureAndReturn = function () {
        $scope.ueUri = $scope.ueUri.trim();
        var idx = $scope.ueUri.indexOf("?");
        if(idx >0){
            $scope.ueUri = $scope.ueUri.substring(0,idx);
        }
        var arr = [];
        for(var i=0;i<$scope.ueHeaderList.length;i++){
            var k = $scope.ueHeaderList[i].k.trim();
            if(k==""){
                continue;
            }
            arr.push($scope.ueHeaderList[i]);
        }
        $scope.headerList = arr;
        arr=[];
        for(var i=0;i<$scope.ueCookieList.length;i++){
            var k = $scope.ueCookieList[i].k.trim();
            if(k==""){
                continue;
            }
            arr.push($scope.ueCookieList[i]);
        }
        $scope.cookieList = arr;
        arr=[];
        for(var i=0;i<$scope.ueGetHttpResList.length;i++){
            var k = $scope.ueGetHttpResList[i].k.trim();
            var v = $scope.ueGetHttpResList[i].v.trim();
            if(k=="" || v==""){
                continue;
            }
            arr.push($scope.ueGetHttpResList[i]);
        }
        $scope.getHttpResList = arr;
        var s = "";
        for(var i=0;i<$scope.ueUrlParamList.length;i++){
            var k = $scope.ueUrlParamList[i].k.trim();
            var v = $scope.ueUrlParamList[i].v.trim();
            if(k==""){
                continue;
            }
            s += k+"="+v +"&";
        }
        s = s.substring(0,s.length-1);
        s= s.trim();
        if(s == ""){
            $scope.tc.url = $scope.ueUri
        }else{
            $scope.tc.url = $scope.ueUri + "?" +s;
        }
        if($scope.tc.isPost){
            if($scope.ueIsFromData){
                var ss = "";
                for(var i=0;i<$scope.ueParamDataList.length;i++){
                    var k = $scope.ueParamDataList[i].k.trim();
                    var v = $scope.ueParamDataList[i].v.trim();
                    if(k==""){
                        continue;
                    }
                    ss += k+"="+v +"&";
                }
                ss = ss.substring(0,ss.length-1);
                $scope.tc.parameters = ss;
            }else{
                $scope.tc.parameters = $scope.ueRawData;
            }
        } else{
            $scope.tc.parameters = "";
        }
        $scope.showUeEditor = false;
    }
    $scope.clickOpenUeEditor = function () {
        $scope.initUrlEditor();
        var idx = $scope.tc.url.indexOf("?");
        var urlParam = "";
        if(idx >=0){
            $scope.ueUri = $scope.tc.url.substring(0,idx);
            urlParam = $scope.tc.url.substring(idx+1);
        }else{
            $scope.ueUri = $scope.tc.url;
        }
        var array = [];
        if($scope.headerList != undefined){
            for(var i=0;i<$scope.headerList.length;i++){
                var k = $scope.headerList[i].k;
                var v = $scope.headerList[i].v;
                array.push({'k':k,'v':v});
            }
            $scope.ueHeaderList = array.concat($scope.ueHeaderList);
        }
        array = [];
        if($scope.cookieList != undefined){
            for(var i=0;i<$scope.cookieList.length;i++){
                var k = $scope.cookieList[i].k;
                var v = $scope.cookieList[i].v;
                array.push({'k':k,'v':v});
            }
            $scope.ueCookieList = array.concat($scope.ueCookieList);
        }
        array = [];
        if($scope.getHttpResList != undefined){
            for(var i=0;i<$scope.getHttpResList.length;i++){
                var k = $scope.getHttpResList[i].k;
                var v = $scope.getHttpResList[i].v;
                array.push({'k':k,'v':v});
            }
            $scope.ueGetHttpResList = array.concat($scope.ueGetHttpResList);
        }
        array = [];
        if(urlParam != ""){
            var arr = urlParam.split("&");
            for(var i=0;i<arr.length;i++){
                var idx2 = arr[i].indexOf("=");
                var k = "";
                var v = "";
                if(idx2 >= 0){
                    k = arr[i].substring(0,idx2);
                    v = arr[i].substring(idx2 + 1);
                }
                array.push({'k':k,'v':v});
            }
            $scope.ueUrlParamList = array.concat($scope.ueUrlParamList);
        }
        if($scope.tc.isPost){
            if(($scope.tc.parameters.startsWith("{") && $scope.tc.parameters.endsWith("}"))
            || ($scope.tc.parameters.startsWith("[") && $scope.tc.parameters.endsWith("]"))){
                $scope.ueIsFromData = false;
                $scope.ueRawData = $scope.tc.parameters;
            }else{
                $scope.ueIsFromData = true;
                var arr2 = $scope.tc.parameters.split("&");
                array = [];
                for(var i=0;i<arr2.length;i++){
                    var idx3 = arr2[i].indexOf("=");
                    var k = "";
                    var v = "";
                    if(idx3 >= 0){
                        k = arr2[i].substring(0,idx3);
                        v = arr2[i].substring(idx3 + 1);
                    }
                    array.push({'k':k,'v':v});
                }
                $scope.ueParamDataList = array.concat($scope.ueParamDataList);
            }
        }
        $scope.showUeEditor = true;
    }
    $scope.testSubmit = function () {
        var userId = $scope.getUserId();
        if(userId == undefined){
            return;
        }
        var preOpsIds = "";
        for(var i=0;i<$scope.preOpsList.length;i++){
            preOpsIds += $scope.preOpsList[i].id + ",";
        }
        var fullUrl = $scope.ueUri;
        var s = "";
        for(var i=0;i<$scope.ueUrlParamList.length;i++){
            var k = $scope.ueUrlParamList[i].k.trim();
            var v = $scope.ueUrlParamList[i].v.trim();
            if(k==""){
                continue;
            }
            s += k+"="+v + "&";
        }
        s = s.substring(0,s.length-1);
        fullUrl = fullUrl + "?" +s;
        var fullPostData = "";
        if($scope.tc.isPost){
            if($scope.ueIsFromData){
                var ss = "";
                for(var i=0;i<$scope.ueParamDataList.length;i++){
                    var k = $scope.ueParamDataList[i].k.trim();
                    var v = $scope.ueParamDataList[i].v.trim();
                    if(k==""){
                        continue;
                    }
                    ss += k+"="+v +"&";
                }
                ss = ss.substring(0,ss.length-1);
                fullPostData = ss;
            }else{
                fullPostData = $scope.ueRawData;
            }
        }
        var dsIdx = "";
        if($scope.ds.th.length>0 && $scope.ds.tr.length>0){
            dsIdx = prompt("输入此次调试需要用到的数据池数据序号,1-"+$scope.ds.tr.length,"1");
            if(isNaN(dsIdx)){
                alert("输入的数据不是数字！");
                return;
            }
            if(parseInt(dsIdx)<1 || parseInt(dsIdx)>$scope.ds.tr.length){
                alert("输入的数字不在1-"+$scope.ds.tr.length+"范围内");
                return;
            }
        }
        if(dsIdx == undefined){
            return;
        }
        $('#spinnersModal').modal('show');
        $http.post('http://'+window.location.host+'/yat/api/tc',
            {'method':'testRunHttp','userId':userId,'isPost':$scope.tc.isPost,
                'envId':$scope.tc.testEnvId,'hostParam':$scope.tc.hostParam,'url':fullUrl,'parameters':fullPostData,'preOpsIds':preOpsIds,
                'ueHeaderList':JSON.stringify($scope.ueHeaderList),
                'ueCookieList':JSON.stringify($scope.ueCookieList),
                'ueGetHttpResList':JSON.stringify($scope.ueGetHttpResList),
                'ds':JSON.stringify($scope.ds),'dsIdx':dsIdx
            }
        ).success(function (data) {
            if(data.success){
                $scope.testHttpData = data.data;
            }else{
                alert(data.data);
            }
            $('#spinnersModal').modal('hide');
        });
    }

    //ds
    $scope.clickDsLi = function (idx) {
        $scope.sDsIdx = idx;
    }
    $scope.editDsTh = function () {
        if($scope.sDsIdx != undefined){
            var s = $scope.ds.th[$scope.sDsIdx];
            var s2 = prompt("修改参数名",s);
            if(s2 != undefined){
                s2 = s2.trim();
                if(s2 != '' && s2 != s){
                    $scope.ds.th[$scope.sDsIdx] = s2;
                }else{
                    if(s2 == ''){
                        alert("参数名不能为空！");
                    }
                }
            }
        }
    }
    $scope.addDsTh = function () {
        var th = prompt("请输入参数名");
        if(th != undefined){
            th = th.trim();
            if(th != ''){
                $scope.ds.th.push(th);
                for(var i=0;i<$scope.ds.tr.length;i++){
                    $scope.ds.tr[i].push({'v':''});
                }
            }
        }
    }
    $scope.addDsTr = function () {
        if($scope.ds.th.length > 0){
            var arr = [];
            for(var i=0;i<$scope.ds.th.length;i++){
                arr.push({'v':''});
            }
            $scope.ds.tr.push(arr);
        }
    }
    $scope.delDsTh = function () {
        if($scope.sDsIdx != undefined && confirm("确认删除？")){
            $scope.ds.th.splice($scope.sDsIdx,1);
            for(var i=0;i<$scope.ds.tr.length;i++){
                $scope.ds.tr[i].splice($scope.sDsIdx,1);
            }
            if($scope.ds.th.length > 0){
                $scope.sDsIdx = 0
            }else{
                $scope.sDsIdx = undefined;
                $scope.ds.tr = [];
            }
        }
    }
    $scope.delDsTr = function (idx) {
        if(confirm("确认移除？")){
            $scope.ds.tr.splice(idx,1);
        }
    }
    $scope.moveDsTh = function (type) {
        if($scope.sDsIdx != undefined){
            var idx = $scope.sDsIdx;
            if(type == 'up'){
                if(idx > 0){
                    var tmp = $scope.ds.th[idx-1];
                    $scope.ds.th[idx-1] = $scope.ds.th[idx];
                    $scope.ds.th[idx] = tmp;
                    for(var i=0;i<$scope.ds.tr.length;i++){
                        var tmp2 = $scope.ds.tr[i][idx-1];
                        $scope.ds.tr[i][idx-1] = $scope.ds.tr[i][idx];
                        $scope.ds.tr[i][idx] = tmp2;
                    }
                    $scope.sDsIdx --;
                }
            }
            if(type == 'down'){
                if(idx < $scope.ds.th.length-1){
                    var tmp = $scope.ds.th[idx+1];
                    $scope.ds.th[idx+1] = $scope.ds.th[idx];
                    $scope.ds.th[idx] = tmp;
                    for(var i=0;i<$scope.ds.tr.length;i++){
                        var tmp2 = $scope.ds.tr[i][idx+1];
                        $scope.ds.tr[i][idx+1] = $scope.ds.tr[i][idx];
                        $scope.ds.tr[i][idx] = tmp2;
                    }
                    $scope.sDsIdx ++;
                }
            }
        }
    }
    $scope.moveDsTr = function (type,idx) {
        if(type == 'up'){
            if(idx > 0){
                var tmp = $scope.ds.tr[idx-1];
                $scope.ds.tr[idx-1] = $scope.ds.tr[idx];
                $scope.ds.tr[idx] = tmp;
            }
        }
        if(type == 'down'){
            if(idx < $scope.ds.tr.length-1){
                var tmp = $scope.ds.tr[idx+1];
                $scope.ds.tr[idx+1] = $scope.ds.tr[idx];
                $scope.ds.tr[idx] = tmp;
            }
        }
    }
    
    $scope.triggerSelect2 = function (className , val) {
        $('.'+className).val(val);
        $('.'+className).trigger('change');
    }
    $(".select2_team").select2();
    $('.select2_team').on('select2:select', function (e) {
        $scope.$apply(function () {
            $scope.tc.teamId = e.params.data.id;
        });
    });
    $(".select2_team_search").select2({width: '420px'});
    $('.select2_team_search').on('select2:select', function (e) {
        $scope.$apply(function () {
            $scope.s_team_id = e.params.data.id;
        });
    });
    $(".select2_service").select2();
    $('.select2_service').on('select2:select', function (e) {
        $scope.$apply(function () {
            $scope.tc.serviceId = e.params.data.id;
        });
    });

    $scope.global = $scope.getGlobalEnvId();
    $scope.caseId = $scope.getUrlParamValue("caseId");
    $scope.copyCaseId = $scope.getUrlParamValue("copyCaseId");
    if( ($scope.NN($scope.caseId) && $scope.caseId.trim()!="0") || ($scope.NN($scope.copyCaseId) && $scope.copyCaseId.trim()!="0")){
        var caseId = 0;
        if( $scope.NN($scope.caseId) && $scope.caseId.trim()!="0"){
            caseId = $scope.caseId;
        }
        if( $scope.NN($scope.copyCaseId) && $scope.copyCaseId.trim()!="0"){
            caseId = $scope.copyCaseId;
        }
        $('#spinnersModal').modal('show');
        $http.post('http://'+window.location.host+'/yat/api/tc',
            {'method':'getTestcaseById','id':caseId}
        ).success(function (data) {
            if(data.success){
                $scope.tc = data.data;
                if(data.data.testEnvId != $scope.global.envId){
                    alert("您不在此用例所在的环境【"+data.prjName+"】【"+data.envName+"】下，无法访问！\n");
                    $scope.gotoHome();
                }
                $scope.tc.testEnvId = $scope.global.envId;

                $scope.ds = {'th':[],'tr':[]};
                if($scope.NN(data.thList)){
                    $scope.ds.th = data.thList;
                }
                if($scope.NN(data.trList)){
                    $scope.ds.tr = data.trList;
                }
                if($scope.NN($scope.copyCaseId) && $scope.copyCaseId.trim()!="0"){
                    $scope.tc.id = 0;
                    $scope.testcaseId = 0;
                }else{
                    $scope.testcaseId = data.data.id;
                }
                $scope.preOpsList = data.preOpsList;
                $scope.afterOpsList = data.afterOpsList;
                $scope.postOpsList = data.postOpsList;
                $scope.jsonCheckList = JSON.parse($scope.tc.jsonCheck);
                $scope.paramCheckList = JSON.parse($scope.tc.dbCheck);
                $scope.headerList = JSON.parse($scope.tc.headerList);
                $scope.cookieList = JSON.parse($scope.tc.cookieList);
                $scope.getHttpResList = JSON.parse($scope.tc.getHttpResList);
                $scope.getAllTeam();
                $scope.getAllServiceByPrjId();
            }else{
                alert(data.data);
            }
            $('#spinnersModal').modal('hide');
        });
    }else{
        $scope.tc = {
            'id':0, 'teamId':0,'status':2,'serviceId':0,'testEnvId':0,'hostParam':'HOST',
            'method':'','isPost':false,'url':'','parameters':'','note':'',
            'cookieList':'','headerList':'','getHttpResList':'',
            'preOpsIds':'','afterTestOpsIds':'','postOpsIds':'',
            'httpCodeCheck':'200','containCheck':'','notContainCheck':'','jsonCheck':'','dbCheck':'',
            'addTime':'','addUserId':'0','updateTime':'','updateUserId':'0'
        };
        $scope.tc.testEnvId = $scope.global.envId;
        $scope.testcaseId = 0;
        $scope.preOpsList=[];
        $scope.afterOpsList=[];
        $scope.postOpsList=[];
        $scope.ds = {'th':[],'tr':[]};
    }
    $scope.page = 1;
    $scope.totalPage = 0;
    $scope.totalCount = 0;
    $scope.getAllHostParamByEnvId();
    $scope.getAllOpsByEnvId();
    $scope.getAllTeam();
    $scope.getAllServiceByPrjId();
    $scope.initUrlEditor();
});

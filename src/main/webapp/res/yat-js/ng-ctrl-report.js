myAppModule.filter("caseNameFilter" , function(){
    return function(trs , search_key ,stag , sortByEnv ,envId){
        if(search_key==undefined || search_key==null || search_key==""){
            search_key = ".";
        }
        if(trs != undefined && trs.length > 0){
            var newtr = [];
            for(var i=0 ; i<trs.length ; i++){
                if(sortByEnv){
                    if(trs[i].e != envId){
                        continue;
                    }
                }
                if(stag=='pass'){
                    if(trs[i].p!=1){
                        continue;
                    }
                }
                if(stag=='fail'){
                    if(trs[i].f!=1){
                        continue;
                    }
                }
                if(stag=='skip'){
                    if(trs[i].s!=1){
                        continue;
                    }
                }
                search_key = search_key.replace(new RegExp("\\*" ,"gm") , "\\*");
                if(
                    new RegExp(search_key).test(trs[i].caseName)
                ){
                    newtr.push(trs[i]);
                }
            }
            return newtr;
        }
    };
});
myAppModule.controller('ng-ctrl-yat-content', function ($scope ,$rootScope , $http , $timeout , $interval, $cookies) {

    $scope.getReportById = function (id) {
        $('#spinnersModal').modal('show');
        $http.post('http://'+window.location.host+'/yat/api/tc',
            {'method':'getReportById','id':id}
        ).success(function (data) {
            if(data.success){
                $scope.reportSummary = data.data;
                $scope.reportLog = data.log;
                $scope.reportUserName = data.userName;
                $scope.reportTotal = data.total;
                $scope.reportPass = data.pass;
                $scope.reportFail = data.fail;
                $scope.reportSkip = data.skip;
                $scope.reportPassRate = data.passRate;
                if(data.log.length > 0){
                    $scope.sLi = data.log[0];
                    $scope.getDetailReportLog(data.log[0].l);
                }
            }else{
                alert("[Error]:"+data.data);
            }
            $('#spinnersModal').modal('hide');
        });
    }
    $scope.clickLi = function (li) {
        $scope.sLi = li;
        $scope.getDetailReportLog(li.l);
    }
    $scope.getDetailReportLog = function (uuid) {
        $('#spinnersModal').modal('show');
        $http.post('http://'+window.location.host+'/yat/api/tc',
            {'method':'getDetailReportLog','uuid':uuid,'isCi':$scope.reportSummary.isCi}
        ).success(function (data) {
            if(data.success){
                $scope.logDetailArr = data.data;
            }else{
                alert("[Error]:"+data.data);
            }
            $('#spinnersModal').modal('hide');
        });
    }
    $scope.clickTag = function (ipt) {
        $scope.sTag = ipt;
        $scope.initTag();
        $scope.tagStyle[ipt]["border-color"] = 'lightskyblue';
    }
    $scope.initTag = function () {
        $scope.tagStyle = {"passRate":{'border-color':''},
            "pass":{'border-color':''},
            "skip":{'border-color':''},
            "fail":{'border-color':''},
            "total":{'border-color':''}
        };
    }
    $scope.changeSortByEnv = function () {
        if($scope.sortByEnv){
            var search = window.location.search.substring(1);
            var newSearch = '';
            if($scope.NN(search) && search.length > 0){
                var sArr = search.split('&');
                for(var i=0;i<sArr.length;i++){
                    if(sArr[i].startsWith('sortByEnvId')){
                        continue;
                    }
                    newSearch += sArr[i] + '&';
                }
            }
            if(newSearch.endsWith('&')){
                newSearch = newSearch.substring(0,newSearch.length-1);
            }
            location.href = location.origin + location.pathname + '?' + newSearch;
        }else{
            location.href = location.href + "&sortByEnvId=";
        }
    }

    var reportId = $scope.getUrlParamValue("reportId");
    var sortByEnvId = $scope.getUrlParamValue("sortByEnvId");
    if($scope.NN(reportId)){
        $scope.getReportById(reportId);
        $scope.clickTag('passRate');
        if(sortByEnvId == undefined){
            $scope.sortByEnv = false;
        }else{
            $scope.sortByEnv = true;
            $scope.global = $scope.getGlobalEnvId();
        }

    }

});

$(function () {
    $('.scroll_content').slimscroll({
        height: '800px'
    });
});
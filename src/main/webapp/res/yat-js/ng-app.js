
var myAppModule = angular.module("ng-app-yat" , ['ngCookies'], function($httpProvider) {
    // Use x-www-form-urlencoded Content-Type
    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';

    /**
     * The workhorse; converts an object to x-www-form-urlencoded serialization.
     * @param {Object} obj
     * @return {String}
     */
    var param = function(obj) {
        var query = '', name, value, fullSubName, subName, subValue, innerObj, i;

        for(name in obj) {
            value = obj[name];

            if(value instanceof Array) {
                for(i=0; i<value.length; ++i) {
                    subValue = value[i];
                    fullSubName = name + '[' + i + ']';
                    innerObj = {};
                    innerObj[fullSubName] = subValue;
                    query += param(innerObj) + '&';
                }
            }
            else if(value instanceof Object) {
                for(subName in value) {
                    subValue = value[subName];
                    fullSubName = name + '[' + subName + ']';
                    innerObj = {};
                    innerObj[fullSubName] = subValue;
                    query += param(innerObj) + '&';
                }
            }
            else if(value !== undefined && value !== null)
                query += encodeURIComponent(name) + '=' + encodeURIComponent(value) + '&';
        }

        return query.length ? query.substr(0, query.length - 1) : query;
    };

    // Override $http service's default transformRequest
    $httpProvider.defaults.transformRequest = [function(data) {
        return angular.isObject(data) && String(data) !== '[object File]' ? param(data) : data;
    }];
});


//filter
myAppModule.filter("trustAsResourceUrl" , ['$sce' , function($sce){
    return function(url){
        return $sce.trustAsResourceUrl(url);
    };
}]);
myAppModule.filter("kioskFilter" , function($filter){
    return function(input){
        if(input!=undefined && input!=null && input!=""){
            return input.replace("kiosk&","");
        }else{
            return "";
        }
    };
});
myAppModule.filter("durationFilter" , function($filter){
    return function(input , startTime , endTime){
        if(input!=undefined && input!=null && input!="" && startTime!=undefined && startTime!=null && startTime!=""){
            var endMS = endTime ;
            if(endTime!=undefined && endTime!=null && endTime!=""){
                endMS = endTime ;
            }else{
                endMS = Date.now() ;
            }
            var s = (endTime - startTime)/1000;
            if(s< 60){
                return s.toFixed(2)+"秒";
            }else{
                if(s < 3600){
                    return (s/60).toFixed(2) + "分钟";
                }else{
                    if(s < 86400){
                        return (s/3600).toFixed(2) + "小时";
                    }else{
                        return (s/86400).toFixed(2) + "天";
                    }
                }
            }
        }else{
            return "";
        }
    };
});
myAppModule.filter("timeFilter" , function($filter){
    return function(input){
        if(input!=undefined && input!=null && input!=""){
            return $filter("date")(input, "yyyy-MM-dd HH:mm:ss");
        }else{
            return "";
        }
    };
});
myAppModule.filter("preJsonFilter" , function($filter){
    return function(input,title){
        if(title!=undefined && title=='完成HTTP请求'){
            if(input!=undefined && input!=null){
                input = input.trim();
                if(input!="" && ((input.startsWith("{")&&input.endsWith("}")) || (input.startsWith("[")&&input.endsWith("]")))){
                    return JSON.stringify(JSON.parse(input),null,2);
                }else{
                    return input;
                }
            }
        }
        return "";
    };
});
myAppModule.filter("parseJsonFilter" , function($filter){
    return function(input,title){
        if(input!=undefined && input!=null){
            input = input.trim();
            if(input!="" && ((input.startsWith("{")&&input.endsWith("}")) || (input.startsWith("[")&&input.endsWith("]")))){
                return JSON.stringify(JSON.parse(input),null,2);
            }
        }
        return input;
    };
});

myAppModule.controller('ng-ctrl-yat', function ($scope , $http , $timeout , $interval ,  $cookies) {
    $scope.NN = function(input){
        if(typeof(input) == 'string'){
            if(input !=undefined && input != null && input.trim() != ''){
                return true;
            }
        }else{
            if(input !=undefined && input != null){
                return true;
            }
        }
        return false;
    }
    $scope.trimVar = function (input) {
        var ret = '';
        if(typeof(input) == 'string'){
            if(input !=undefined && input != null){
                ret = input.trim();
            }
        }
        return ret;
    }
    $scope.getUrlParamValue = function(name){
        var search = window.location.search;
        if($scope.NN(search) && search.length > 0){
            var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
            var r = search.substr(1).match(reg);
            if(r!=null){
                return  unescape(r[2]);
            }
        }
        return null;
    }
    $scope.getUserId = function () {
        var user = $cookies.getObject("user");
        if(user == undefined || user == null){
            alert("【Error】：未登录，请先登录！");
        }else{
            return user.id;
        }
    }
    $scope.getGlobalEnvId = function () {
        var globalProjectId = localStorage.getItem("yatProjectId");
        var globalEnvironmentId = localStorage.getItem("yatEnvironmentId");
        if(!$scope.NN(globalProjectId) || !$scope.NN(globalEnvironmentId)){
            alert("请选择一个项目-环境！");
            $scope.gotoHome();
        }else{
            return {'prjId':globalProjectId,'envId':globalEnvironmentId};
        }
    }
    $scope.gotoHome = function () {
        location.href = "http://"+location.host+"/yat/pages/index.html";
    }
});

$(function(){
    // $('.scroll_content_log').slimscroll({
    //     height: '250px',
    //     start : 'bottom'
    // });
});
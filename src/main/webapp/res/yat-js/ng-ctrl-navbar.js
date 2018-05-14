myAppModule.controller('ng-ctrl-yat-nav', function ($scope ,$rootScope , $http , $timeout , $interval , $cookies) {
    $scope.login = function () {
        $('#spinnersModal').modal('show');
        $http.post('http://'+window.location.host+'/yat/api/user/post',{'method':'login','username': $scope.loginUsername, 'password': $scope.loginPassword}).success(function (data) {
            if(data.success){
                $scope.login_info = "";
                var expireDate = new Date();
                expireDate.setDate(expireDate.getDate() + 0.5);
                $cookies.putObject('user',data.data,{'expires': expireDate.toUTCString()});
                $scope.loginUser = data.data;
                $scope.isLogin = true;
                $('#loginModal').modal('hide');
            }else{
                $scope.login_info = "登录失败！" + data.data;
            }
            $('#spinnersModal').modal('hide');
        });
    }

    $scope.startDailyCi = function () {
        if(!$scope.isLogin){
            alert("请先登录");
            return;
        }
        // if($scope.loginUser.id!=1){
        //     alert("您没有执行权限，请联系管理员！");
        //     return;
        // }
        if(confirm("确认触发一次DailyCi?")){
            $('#spinnersModal').modal('show');
            $http.post('http://'+window.location.host+'/yat/api/tc',{'method':'runDailyCiManually','userId':$scope.loginUser.id,'subject':'手动触发CI','testcaseIds':''}).success(function (data) {
                if(data.success){
                    alert("[Passed]"+data.data);
                }else{
                    alert("[Failed]"+data.data);
                }
                $('#spinnersModal').modal('hide');
            });
        }
    }
    $scope.logout = function () {
        $cookies.remove('user');
        $scope.isLogin = false;
    }
    $scope.doFeedback=function () {
        if($scope.feedbackType == undefined){
            $scope.errorInfo = "请选择一个反馈类型";
            return;
        }
        if($scope.feedbackData == undefined || $scope.feedbackData.trim()==''){
            $scope.errorInfo = "反馈内容不能为空";
            return;
        }
        $scope.errorInfo = "";
        $('#spinnersModal').modal('show');
        console.log($scope.loginUser.id);
        $http.post('http://'+window.location.host+'/yat/api/user/post',{'method':'feedback','userId': $scope.loginUser.id, 'feedbackType':$scope.feedbackType,'feedbackData': $scope.feedbackData}).success(function (data) {
            $('#spinnersModal').modal('hide');
            if(data.success){
                $('#feedbackModal').modal('hide');
                // $scope.feedbackType = undefined;
                $scope.feedbackData = undefined;
                alert("【提交成功】感谢您的反馈！");
            }else{
                alert("【提交失败】"+data.data);
            }
        });
    }
    $scope.getAllEnvironment = function () {
        $http.post('http://'+window.location.host+'/yat/api/data',
            {'method':'getAllEnvironment'}
        ).success(function (data) {
            if(data.success){
                $scope.navEnvironmentList = data.data;
                if($scope.globalEnvironmentId == null){
                    // if(data.data.length > 0){
                    //     $scope.navEnvId = data.data[0].id;
                    //     $scope.navPrjId = data.data[0].projectId;
                    //     $scope.navProjectName = data.data[0].projectName;
                    //     localStorage.setItem("yatEnvironmentId",$scope.navEnvId);
                    //     localStorage.setItem("yatProjectId",data.data[0].projectId);
                    // }
                }else{
                    $scope.navEnvId = parseInt($scope.globalEnvironmentId);
                    var p = $scope.getProjectNameByEnvId($scope.navEnvId);
                    $scope.navPrjId = p.id;
                    $scope.navProjectName = p.name;
                }
            }else{
                alert("[Error]:"+data.data);
            }
        });
    }
    $scope.changeNavEnv = function () {
        localStorage.setItem("yatEnvironmentId",$scope.navEnvId);
        $scope.navProjectName = $scope.getProjectNameByEnvId($scope.navEnvId).name;
        location.reload(true);
    }
    $scope.getProjectNameByEnvId = function (envId) {
        var p = {'id':0,'name':''};
        for(var i=0;i<$scope.navEnvironmentList.length;i++){
            if($scope.navEnvironmentList[i].id == envId){
                var prjId = $scope.navEnvironmentList[i].projectId;
                var prjName = $scope.navEnvironmentList[i].projectName;
                localStorage.setItem("yatProjectId",$scope.navEnvironmentList[i].projectId);
                p.id = prjId;
                p.name = prjName;
                return p;
            }
        }
    }
    var user = $cookies.getObject("user");
    if(user == undefined || user == null){
        $scope.isLogin = false;
    }else{
        $scope.loginUser = user;
        $scope.isLogin = true;
    }
    $scope.globalEnvironmentId = localStorage.getItem("yatEnvironmentId");
    $scope.getAllEnvironment();
});
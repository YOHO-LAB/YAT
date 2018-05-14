
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
               $scope.getTeam();
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
    $scope.checkBeforeSave = function () {
        if(!$scope.NN($scope.teamName)){
            return "[Error]:数据库名称必须唯一且不能为空！";
        }
        if(!$scope.NN($scope.db_ip)){
            return "[Error]:数据库IP不能为空！";
        }
        if(!$scope.NN($scope.db_port)){
            return "[Error]:数据库PORT不能为空！";
        }
        if(!$scope.NN($scope.db_dbName)){
            return "[Error]:数据库DB_NAME不能为空！";
        }
        if(!$scope.NN($scope.db_user)){
            return "[Error]:数据库登录用户名不能为空！";
        }
        if(!$scope.NN($scope.db_password)){
            return "[Error]:数据库登录密码不能为空！";
        }
        return "";
    }

    $scope.addTeam = function () {
        var err = $scope.checkBeforeSave();
        if(err!=""){
            $scope.errorInfo = "[Error]:"+err;
            return;
        };
        $('#spinnersModal').modal('show');
        $http.post('http://'+window.location.host+'/yat/api/data',
            {'method':'addDb','userId':$scope.getUserId(),'teamName': $scope.teamName, 'teamNote':$scope.teamNote,
                'db_ip':$scope.db_ip,'db_port':$scope.db_port,'db_dbName':$scope.db_dbName,
                'db_user':$scope.db_user,'db_password':$scope.db_password,'envId':$scope.global.envId}
            ).success(function (data) {
            if(data.success){
                $scope.getTeam();
                $('#editModal').modal('hide');
            }else{
                $scope.errorInfo = "[Error]:"+data.data;
            }
            $('#spinnersModal').modal('hide');
        });
    }
    
    $scope.getTeam = function () {
        var search = {'s_team_name':$scope.trimVar($scope.s_team_name),'s_team_note':$scope.trimVar($scope.s_team_note),
            's_db_ip':$scope.trimVar($scope.s_db_ip),'s_db_port':$scope.trimVar($scope.s_db_port),'s_db_name':$scope.trimVar($scope.s_db_name),
            's_create_user':$scope.trimVar($scope.s_create_user),'s_update_user':$scope.trimVar($scope.s_update_user),
            's_create_time':$scope.trimVar($scope.s_create_time),'s_update_time':$scope.trimVar($scope.s_update_time),
            'envId':$scope.global.envId};
        $('#spinnersModal').modal('show');
        $http.post('http://'+window.location.host+'/yat/api/data',
            {'method':'getDb','search':JSON.stringify(search) , 'page':$scope.page,'count':20}
            ).success(function (data) {
            if(data.success){
                $scope.teamList = data.data;
                $scope.totalPage = data.totalPage;
                $scope.totalCount = data.totalCount;
                $('#pageLimit').bootstrapPaginator("setOptions",{'currentPage':$scope.page,'totalPages':data.totalPage});
            }else{
                $scope.errorInfo = "[Error]:"+data.data;
            }
            $('#spinnersModal').modal('hide');
        });
    }
    $scope.clickAddTeam = function () {
        $scope.editTitle = "新增数据库";
        $scope.editButtonAdd = true;
        $scope.teamName = "";
        $scope.teamNote = "";
        $scope.db_ip = "";
        $scope.db_port = "";
        $scope.db_dbName = "";
        $scope.db_user = "";
        $scope.db_password = "";
        $scope.errorInfo = "";
    }
    $scope.clickModifyTeam = function (tr) {
        $scope.editTitle = "修改数据库";
        $scope.editButtonAdd = false;
        $scope.teamName = tr.name;
        $scope.teamNote = tr.note;
        $scope.db_ip = tr.ip;
        $scope.db_port = tr.port;
        $scope.db_dbName = tr.dbName;
        $scope.db_user = tr.userName;
        $scope.db_password = tr.passWord;
        $scope.errorInfo = "";
        $scope.editTr = tr;
    }
    $scope.modifyTeam = function () {
        var err = $scope.checkBeforeSave();
        if(err!=""){
            $scope.errorInfo = "[Error]:"+err;
            return;
        };
        $('#spinnersModal').modal('show');
        $http.post('http://'+window.location.host+'/yat/api/data',
            {'method':'modifyDb','userId':$scope.getUserId(),'teamName': $scope.teamName,
                'teamNote':$scope.teamNote,'teamId':$scope.editTr.id,'db_ip':$scope.db_ip,'db_port':$scope.db_port,'db_dbName':$scope.db_dbName,
                'db_user':$scope.db_user,'db_password':$scope.db_password,'envId':$scope.global.envId}
        ).success(function (data) {
            if(data.success){
                $scope.getTeam();
                $('#editModal').modal('hide');
            }else{
                $scope.errorInfo = "[Error]:"+data.data;
            }
            $('#spinnersModal').modal('hide');
        });
    }
    $scope.deleteTeam = function (tr) {
        if(confirm("确认删除<"+tr.name+">数据库？")){
            $('#spinnersModal').modal('show');
            $http.post('http://'+window.location.host+'/yat/api/data',
                {'method':'deleteDb','userId':$scope.getUserId(),'teamId':tr.id}
            ).success(function (data) {
                if(data.success){
                    $scope.getTeam();
                }else{
                    alert("[Error]:"+data.data);
                }
                $('#spinnersModal').modal('hide');
            });
        }
    }


    $scope.global = $scope.getGlobalEnvId();
    $scope.page = 1;
    $scope.totalPage = 0;
    $scope.totalCount = 0;
    $scope.s_create_time = "2018/01/01 - 2018/12/31";
    $scope.s_update_time = "2018/01/01 - 2018/12/31";
    $scope.getTeam();
});
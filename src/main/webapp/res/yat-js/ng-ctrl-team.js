
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

    $scope.addTeam = function () {
        var userId = $scope.getUserId();
        if(userId == undefined){return;}
        if(!$scope.NN($scope.teamName)){
            $scope.errorInfo = "[Error]:团队名必须唯一且必能为空！";
            return;
        }
        $('#spinnersModal').modal('show');
        $http.post('http://'+window.location.host+'/yat/api/data',
            {'method':'addTeam','userId':userId,'teamName': $scope.teamName, 'teamNote':$scope.teamNote}
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
            's_create_user':$scope.trimVar($scope.s_create_user),'s_update_user':$scope.trimVar($scope.s_update_user),
            's_create_time':$scope.trimVar($scope.s_create_time),'s_update_time':$scope.trimVar($scope.s_update_time),};
        $('#spinnersModal').modal('show');
        $http.post('http://'+window.location.host+'/yat/api/data',
            {'method':'getTeam','search':JSON.stringify(search) , 'page':$scope.page,'count':20}
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
        $scope.editTitle = "新增团队";
        $scope.editButtonAdd = true;
        $scope.teamName = "";
        $scope.teamNote = "";
        $scope.errorInfo = "";
    }
    $scope.clickModifyTeam = function (tr) {
        $scope.editTitle = "修改团队";
        $scope.editButtonAdd = false;
        $scope.teamName = tr.name;
        $scope.teamNote = tr.note;
        $scope.errorInfo = "";
        $scope.editTr = tr;
    }
    $scope.modifyTeam = function () {
        var userId = $scope.getUserId();
        if(userId == undefined){return;}
        if(!$scope.NN($scope.teamName)){
            $scope.errorInfo = "[Error]:团队名必须唯一且必能为空！";
            return;
        }
        $('#spinnersModal').modal('show');
        $http.post('http://'+window.location.host+'/yat/api/data',
            {'method':'modifyTeam','userId':userId,'teamName': $scope.teamName, 'teamNote':$scope.teamNote,'teamId':$scope.editTr.id}
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
        var userId = $scope.getUserId();
        if(userId == undefined){return;}
        if(confirm("确认删除<"+tr.name+">团队？")){
            $('#spinnersModal').modal('show');
            $http.post('http://'+window.location.host+'/yat/api/data',
                {'method':'deleteTeam','userId':userId,'teamId':tr.id}
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
    $scope.page = 1;
    $scope.totalPage = 0;
    $scope.totalCount = 0;
    $scope.s_create_time = "2018/01/01 - 2088/12/31";
    $scope.s_update_time = "2018/01/01 - 2088/12/31";
    $scope.getTeam();
});
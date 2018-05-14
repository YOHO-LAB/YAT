
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
               $scope.getProject();
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

    $scope.addProject = function () {
        var userId = $scope.getUserId();
        if(userId == undefined){return;}
        if(!$scope.NN($scope.teamName)){
            $scope.errorInfo = "[Error]:项目名必须唯一且必能为空！";
            return;
        }
        $('#spinnersModal').modal('show');
        $http.post('http://'+window.location.host+'/yat/api/data',
            {'method':'addProject','userId':userId,'teamName': $scope.teamName, 'teamNote':$scope.teamNote}
            ).success(function (data) {
            if(data.success){
                // $scope.getProject();
                $('#editModal').modal('hide');
                location.reload(true);
            }else{
                $scope.errorInfo = "[Error]:"+data.data;
            }
            $('#spinnersModal').modal('hide');
        });
    }
    
    $scope.getProject = function () {
        var search = {'s_team_name':$scope.trimVar($scope.s_team_name),'s_team_note':$scope.trimVar($scope.s_team_note),
            's_create_user':$scope.trimVar($scope.s_create_user),'s_update_user':$scope.trimVar($scope.s_update_user),
            's_create_time':$scope.trimVar($scope.s_create_time),'s_update_time':$scope.trimVar($scope.s_update_time),};
        $('#spinnersModal').modal('show');
        $http.post('http://'+window.location.host+'/yat/api/data',
            {'method':'getProject','search':JSON.stringify(search) , 'page':$scope.page,'count':20}
            ).success(function (data) {
            if(data.success){
                $scope.projectList = data.data;
                $scope.totalPage = data.totalPage;
                $scope.totalCount = data.totalCount;
                $('#pageLimit').bootstrapPaginator("setOptions",{'currentPage':$scope.page,'totalPages':data.totalPage});
            }else{
                $scope.errorInfo = "[Error]:"+data.data;
            }
            $('#spinnersModal').modal('hide');
        });
    }
    $scope.clickAddProject = function () {
        $scope.editTitle = "新增项目";
        $scope.editButtonAdd = true;
        $scope.teamName = "";
        $scope.teamNote = "";
        $scope.errorInfo = "";
    }
    $scope.clickModifyProject = function (tr) {
        $scope.editTitle = "修改项目";
        $scope.editButtonAdd = false;
        $scope.teamName = tr.name;
        $scope.teamNote = tr.note;
        $scope.errorInfo = "";
        $scope.editTr = tr;
    }
    $scope.modifyProject = function () {
        var userId = $scope.getUserId();
        if(userId == undefined){return;}
        if(!$scope.NN($scope.teamName)){
            $scope.errorInfo = "[Error]:项目名必须唯一且必能为空！";
            return;
        }
        $('#spinnersModal').modal('show');
        $http.post('http://'+window.location.host+'/yat/api/data',
            {'method':'modifyProject','userId':userId,'teamName': $scope.teamName, 'teamNote':$scope.teamNote,'teamId':$scope.editTr.id}
        ).success(function (data) {
            if(data.success){
                // $scope.getProject();
                $('#editModal').modal('hide');
                location.reload(true);
            }else{
                $scope.errorInfo = "[Error]:"+data.data;
            }
            $('#spinnersModal').modal('hide');
        });
    }
    $scope.deleteProject = function (tr) {
        var userId = $scope.getUserId();
        if(userId == undefined){return;}
        if(confirm("确认删除<"+tr.name+">项目？")){
            $('#spinnersModal').modal('show');
            $http.post('http://'+window.location.host+'/yat/api/data',
                {'method':'deleteProject','userId':userId,'teamId':tr.id}
            ).success(function (data) {
                if(data.success){
                    // $scope.getProject();
                    location.reload(true);
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
    $scope.s_create_time = "2018/01/01 - 2018/12/31";
    $scope.s_update_time = "2018/01/01 - 2018/12/31";
    $scope.getProject();
});
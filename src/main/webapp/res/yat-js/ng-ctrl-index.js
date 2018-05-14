myAppModule.controller('ng-ctrl-yat-content', function ($scope ,$rootScope , $http , $timeout , $interval, $cookies) {

    $scope.initSparkline = function (data) {
        $("#sparkline1").sparkline(data, {
            type: 'line',
            width: '100%',
            height: '50',
            lineColor: '#1ab394',
            fillColor: "transparent"
        });
    };

    $scope.flotDashboard = function (data) {
        var data1 = [];
        for(var i=0;i<data.length;i++){
            data1.push([new Date(data[i].year, data[i].month, data[i].day),data[i].val]);
        }
        $("#flot-dashboard5-chart").length && $.plot($("#flot-dashboard5-chart"), [
                data1
            ],
            {
                series: {
                    lines: {
                        show: false,
                        fill: true
                    },
                    splines: {
                        show: true,
                        tension: 0.4,
                        lineWidth: 1,
                        fill: 0.4
                    },
                    points: {
                        radius: 1,
                        show: true
                    },
                    shadowSize: 2
                },
                grid: {
                    hoverable: true,
                    clickable: true,
                    borderWidth: 2,
                    color: 'transparent'
                },
                colors: ["#1ab394", "#1C84C6"],
                xaxis:{
                    mode : "time",
                    timeformat: "%m-%d"
                },
                yaxis: {
                },
                tooltip: true,
                tooltipOpts: {
                    content: "日期: %x, 成功率: %y%"
                }

            }
        );
    }
    $scope.getSummaryOfCase = function () {
        $('#spinnersModal').modal('show');
        $http.post('http://'+window.location.host+'/yat/api/tc',
            {'method':'getSummaryOfCase'}
        ).success(function (data) {
            if(data.success){
                $scope.initSparkline(data.data);
                $scope.caseTotal = data.total;
                $scope.caseIncrease = data.increase;
                $scope.caseCiPercent = data.ciPercent;
                $scope.caseCi = data.ci;
            }else{
                alert("[Error]:"+data.data);
            }
            $('#spinnersModal').modal('hide');
        });
    }
    $scope.getSummaryOfCi = function () {
        $('#spinnersModal').modal('show');
        $http.post('http://'+window.location.host+'/yat/api/tc',
            {'method':'getSummaryOfCi'}
        ).success(function (data) {
            if(data.success){
                $scope.flotDashboard(data.data);
            }else{
                alert("[Error]:"+data.data);
            }
            $('#spinnersModal').modal('hide');
        });
    }

    $scope.getSummaryOfTeamCase = function () {
        $('#spinnersModal').modal('show');
        $http.post('http://'+window.location.host+'/yat/api/tc',
            {'method':'getSummaryOfTeamCase'}
        ).success(function (data) {
            if(data.success){
                $scope.thList = data.thList;
                $scope.trList = data.trList;
            }else{
                alert("[Error]:"+data.data);
            }
            $('#spinnersModal').modal('hide');
        });
    }

    $scope.getSummaryOfLastFiveCi = function () {
        $('#spinnersModal').modal('show');
        $http.post('http://'+window.location.host+'/yat/api/tc',
            {'method':'getSummaryOfLastFiveCi'}
        ).success(function (data) {
            if(data.success){
                $scope.lastFiveCiList = data.data;
            }else{
                alert("[Error]:"+data.data);
            }
            $('#spinnersModal').modal('hide');
        });
    }


    $scope.getSummaryOfCase();
    $scope.getSummaryOfCi();
    $scope.getSummaryOfTeamCase();
    $scope.getSummaryOfLastFiveCi();
});
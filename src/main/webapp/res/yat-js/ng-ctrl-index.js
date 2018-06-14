myAppModule.filter("sumFilter" , function(){
    return function(input){
        if(input!=undefined && input!=null){
            var sum =0;
            for(var i=0;i<input.length;i++){
                if(!isNaN(input[i])){
                    sum += parseInt(input[i]);
                }
            }
            return sum;
        }else{
            return 0;
        }
    };
});
myAppModule.filter("sumFilter2" , function(){
    return function(input,idx){
        if(input != undefined){
            var sum =0;
            for(var i=0;i<input.length;i++){
                if(!isNaN(input[i][idx+1])){
                    sum += parseInt(input[i][idx+1]);
                }
            }
            return sum;
        }else{
            return 0;
        }
    };
});
myAppModule.filter("sumFilter3" , function(){
    return function(input){
        if(input != undefined){
            var sum =0;
            for(var i=0;i<input.length;i++){
                for(var j=1;j<input[i].length;j++){
                    if(!isNaN(input[i][j])){
                        sum += parseInt(input[i][j]);
                    }
                }
            }
            return sum;
        }else{
            return 0;
        }
    };
});
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
    $scope.getSummaryOfCaseNum = function () {
        $('#spinnersModal').modal('show');
        $http.post('http://'+window.location.host+'/yat/api/tc',
            {'method':'getSummaryOfCaseNum'}
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
    $scope.getSummaryOfCiPassRate = function () {
        $('#spinnersModal').modal('show');
        $http.post('http://'+window.location.host+'/yat/api/tc',
            {'method':'getSummaryOfCiPassRate'}
        ).success(function (data) {
            if(data.success){
                $scope.flotDashboard(data.data);
            }else{
                alert("[Error]:"+data.data);
            }
            $('#spinnersModal').modal('hide');
        });
    }

    $scope.getSummaryOfCase = function () {
        $('#spinnersModal').modal('show');
        $http.post('http://'+window.location.host+'/yat/api/tc',
            {'method':'getSummaryOfCase'}
        ).success(function (data) {
            if(data.success){
                $scope.tbList = data.data;
            }else{
                alert("[Error]:"+data.data);
            }
            $('#spinnersModal').modal('hide');
        });
    }


    $scope.getSummaryOfCaseNum();
    $scope.getSummaryOfCiPassRate();
    $scope.getSummaryOfCase();
});
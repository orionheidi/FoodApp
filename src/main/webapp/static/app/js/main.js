var foodApp = angular.module('foodApp', ['ngRoute']);

foodApp.config(['$routeProvider', function($routeProvider) {
    $routeProvider
    .when('/foods', {
        templateUrl : '/static/app/html/partial/foods.html'
    })
    .when('/addFood/:id', {
        templateUrl : '/static/app/html/partial/food.html'
    })
    .otherwise({
        redirectTo: '/'
    });
}]);

foodApp.controller('foodsController', function($scope, $http, $location){
    var paramsConfig = {};
   $scope.currentPage = 0;
   $scope.totalPages = 0;

   $scope.configureParameters = function(){
        paramsConfig = { params:{} };

        if($scope.currentPage){
            paramsConfig.params.page = $scope.currentPage;
        }

        if($scope.filterFood){
            paramsConfig.params.name = $scope.filterFood;
            return filterByFood();
        }else if($scope.filterType){
            paramsConfig.params.input = $scope.filterType;
            return filterByType();
        }

        loadFoods();
    }

    $scope.saveOrEdit = function(){
        if($scope.food)
            if($scope.food.id)
                edit();
            else
                save();
        else
            console.log('You must choose a type.');
    }

    var save = function(){
        if(!$scope.food.type)
            $scope.food.type = {};

        $http.post('/api/foods', $scope.food).then(function(response){
            console.log('(Add Food) Succes:', response.status, response.statusText);
            loadFoods();
            $scope.food = {};
        }, function(response){
            console.log('(Add Food) Error:', response.status, response.statusText);
        });
    }

    var edit = function(){
       $http.put('/api/foods/' + $scope.food.id, $scope.food).then(function(response){
            console.log('(Edit Food) Succes:', response.status, response.statusText);
            loadFoods();
            $scope.food = {};
        }, function(response){
            console.log('(Edit Food) Error:', response.status, response.statusText);
        });
    }

    $scope.changePage = function(i){
        if(($scope.currentPage > 0 && i < 0) || (i > 0 && $scope.currentPage < $scope.totalPages)){
            $scope.currentPage += i;
            $scope.configureParameters();
        }
    }

    var loadFoods = function(){
        $http.get('/api/foods', paramsConfig).then(function(response){
            console.log('(Load Foods) Succes:', response.status, response.statusText);
            $scope.foods = response.data;
            $scope.totalPages = response.headers('totalPages');
        }, function(response){
            console.log('(Load Foods) Error:', response.status, response.statusText);
        });
    }

    var loadTypes = function(){
        $http.get('/api/types').then(function(response){
            console.log('(Load Types) Succes:', response.status, response.statusText);
            $scope.types = response.data;
        }, function(response){
            console.log('(Load Types) Error:', response.status, response.statusText);
        });
    }

    var filterByFood = function(){
        $http.get('/api/foods/filterByFood', paramsConfig).then(function(response){
            console.log('(Filter By Food) Succes:', response.status, response.statusText);
            $scope.foods = response.data;
            $scope.totalPages = response.headers('totalPages');
        }, function(response){
            console.log('(Filter By Food) Error:', response.status, response.statusText);
        });
    }

    var filterByType = function(){
        $http.get('/api/foods/filterByType', paramsConfig).then(function(response){
            console.log('(Filter By Type) Succes:', response.status, response.statusText);
            $scope.foods = response.data;
            $scope.totalPages = response.headers('totalPages');
        }, function(response){
            console.log('(Filter By Type) Error:', response.status, response.statusText);
        });
    }

    $scope.delete = function(id){
        $http.delete('/api/foods/' + id).then(function(response){
            console.log('(Delete Food) Success:', response.status, response.statusText);
            loadFoods();
        }, function(response){
            console.log('(Delete Food) Error:', response.status, response.statusText);
        });
    }

    $scope.setForUpdate = function(food){
        $scope.food = angular.copy(food);
    }

    $scope.setForUpdateOnNewPage = function(id){
        $location.path('/addFood/' + id);
    }

    loadFoods();
    loadTypes();
});

foodApp.controller('foodController', function($scope, $http, $location, $routeParams){
    var loadFoods = function(){
        $http.get('/api/foods/' + $routeParams.id).then(function(response){
            $scope.food = response.data;
        });
    }

    var loadTypes = function(){
        $http.get('/api/types').then(function(response){
            $scope.types = response.data;
        });
    }

    loadFoods();
    loadTypes();

    $scope.save = function(){
        $http.put('/api/foods/' + $routeParams.id, $scope.food).then(function(response){
            $location.url('/foods');
        });
    }
});
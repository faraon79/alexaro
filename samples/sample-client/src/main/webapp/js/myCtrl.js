app.controller('myCtrl', ['$rootScope', '$scope', '$http', function ($rootScope, $scope, $http) {

	$scope.crossPath = "pictures/cross.png";
	$scope.wheelPath = "pictures/wheel.png";

	$scope.path = new Array(9);
	$scope.isVisible = [false, false, false, false, false, false, false, false, false];

	$scope.tableData = [0, 0, 0, 0, 0, 0, 0, 0, 0];

	function isEnd() {
		var end = true;
		for (i = 0; i < $scope.isVisible.length; i++) {
			end &= $scope.isVisible[i];
		}
		return end;
	}

	$scope.itemClick = function (event) {

		$scope.error = "";
		$scope.tips = "";

		if (isEnd()){
			$scope.tips = "Tip: click to play again.";
			return;
		}

		if ($scope.tableData[event.target.id] != 0) {
			$scope.tips = "Tip: click on empty field.";
			return;
		}

		$scope.isVisible[event.target.id] = true;
		$scope.path[event.target.id] = $scope.crossPath;

		$scope.tableData[event.target.id] = 2;

		if (isEnd()){
			return;
		}

		var requestData = {
			data: []
		};
		for (i = 0; i < $scope.tableData.length; i++) {
			requestData.data.push($scope.tableData[i])
		}

		$http.post('http://localhost:8080/ann-rest/service/tictactoe',
			requestData,
			{headers: {'Content-Type': 'application/json'}})
			.then(function (response) {

				var max = 0;
				var indexToUpdate = 0;

				for (i = 0; i < response.data.data.length; i++) {
					if (response.data.data[i] > max) {
						max = response.data.data[i];
						indexToUpdate = i;
					}
				}

				if ($scope.isVisible[indexToUpdate]) {
					$scope.error = "Sorry. Something went wrong.";
					return;
				}

				$scope.tableData[indexToUpdate] = 1;
				$scope.isVisible[indexToUpdate] = true;
				$scope.path[indexToUpdate] = $scope.wheelPath;
			});
	};

	$scope.cleanUp = function () {
		for (var i = 0; i < $scope.isVisible.length; i++) {
			$scope.isVisible[i] = false;
			$scope.tableData[i] = 0;
			$scope.error = "";
		}
	};
}]);
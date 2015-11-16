/**
 * @ngdoc function
 * @name app.IMADluxUI.controller:TopologyController
 * @description
 * # TopologyController
 * Controller of the topology page
 */

define([
		'app/IMADluxUI/IMADluxUI.module',
		'app/IMADluxUI/components/topology/topologyRestconfService'], 
		function(IMADluxUIApp) {
			'use strict';
			// angular.module('app.IMADluxUI')
			//		.controller('TopologyController', ['$scope', '$timeout', 'TopologyService',
			//			function ($scope, $timeout, TopologyService) {

			IMADluxUIApp.register.controller('TopologyController', ['$scope', '$rootScope', 'NetworkTopologySvc', function($scope, $rootScope, NetworkTopologySvc) {

				// create NeXt Application. NOTE: nx is not angluar service hence can't be in dependency list. Just use it here.
				// initialize a new application instance
				var app = new nx.ui.Application();
				/* TopologyContainer is a nx.ui.Component object that can contain much more things than just a nx.graphic.Topology object. We can 'inject' a topology instance inside and interact with it easily
				*/
				var topologyContainer = new TopologyContainer();
				// topology instance was made in TopologyContainer, but we can invoke its members through 'topology' variable for convenience
				var topology = topologyContainer.topology();
				//assign the app to the <div>
				app.container(document.getElementById('next-app'));

				// run only once, otherwise multiple app are loaded!!!
				// <div ng-controller="TopologyCtrl" ng-init="init()"> 
				$scope.init = function () {
					// ... then attach the topology to the app instance
					topology.attach(app);
				};

				// get topology data using TopologyService, which return a promise
				//test: $scope.topology = TopologyService.query();
				NetworkTopologySvc.getTestData()
					.then(function (data) {
						test: $scope.topology = data;
						// process ODL topology's JSON to turn it to next json
						topologyData = odl2next(data);
						// feed topology object with nodes and links...
						topology.data(topologyData);
						topology.fit();
						topology.adaptToContainer(); 
					});

				$scope.line = {
					labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
					series: ['Series A', 'Series B'],
					data: [
						[65, 59, 80, 81, 56, 55, 40],
						[28, 48, 40, 19, 86, 27, 90]
					],
					onClick: function (points, evt) {
						console.log(points, evt);
					}
				};


			}]);
		});

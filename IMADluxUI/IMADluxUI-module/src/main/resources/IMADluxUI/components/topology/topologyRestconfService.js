define(['app/IMADluxUI/IMADluxUI.module'],function(IMADluxUIApp) {
	'use strict';

	/*
	//angular.module('myApp', ['ngResource'])
	//.service('TopologyResource', ['$resource', 
	//		function ($resource) {
	IMADluxUIApp.register.service('TopologyResource', ['$resource', function($resource, ENV) {
		//return $resource('/app/data/flow-1.json', {}, {
		//	query: {method:'GET', isArray:false}
		//});
		return $resource('/app/data/flow-1.json');
	}]);

	//angular.module('myApp')
	//.service('TopologyService', ['TopologyResource', 
	IMADluxUIApp.register.service('TopologyService', ['TopologyResource', function(TopologyResource, ENV) {
		this.get = function() {
			return TopologyResource.get().$promise;
		}
	}]);
	*/

	IMADluxUIApp.register.factory('TopologyRestangular', function(Restangular, ENV) {
		return Restangular.withConfig(function(RestangularConfig) {
			RestangularConfig.setBaseUrl(ENV.getBaseURL("MD_SAL"));
			//RestangularConfig.setBaseUrl('app/IMADluxUI/assets/data/flow-1.json');
		});
	});

	IMADluxUIApp.register.factory('NetworkTopologySvc', function(TopologyRestangular) {
		var svc = {
			base: function() {
				return TopologyRestangular.one('restconf').one('operational').one('network-topology:network-topology');
			},
			test: function() {
				svc.data = svc.base().one("topology","flow:1").get();
				return svc.data;
			},
			data: null,
			TOPOLOGY_CONST: {
				HT_SERVICE_ID:"host-tracker-service:id",
				IP:"ip",
				HT_SERVICE_ATTPOINTS:"host-tracker-service:attachment-points",
				HT_SERVICE_TPID:"host-tracker-service:tp-id",
				NODE_ID:"node-id",
				SOURCE_NODE:"source-node",
				DEST_NODE:"dest-node",
				SOURCE_TP:"source-tp",
				DEST_TP:"dest-tp",
				ADDRESSES:"addresses",
				HT_SERVICE_ADDS:"host-tracker-service:addresses",
				HT_SERVICE_IP:"host-tracker-service:ip"
			}
		};

		svc.getCurrentData = function() {
			return svc.data;
		};

		svc.getTestData = function() {
			return svc.test();
		};

		svc.getNode = function(node,cb) {
			return;
		};

		return svc;
	});

});

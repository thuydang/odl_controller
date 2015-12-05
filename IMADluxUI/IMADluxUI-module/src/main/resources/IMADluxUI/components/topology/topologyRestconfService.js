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

	IMADluxUIApp.register.factory('ImaTopologyRestangular', function(Restangular, ENV) {
		return Restangular.withConfig(function(RestangularConfig) {
			RestangularConfig.setBaseUrl(ENV.getBaseURL("MD_SAL"));
			//RestangularConfig.setBaseUrl('app/IMADluxUI/assets/data/flow-1.json');
		});
	});

	IMADluxUIApp.register.factory('ImaTopologySvc', function(ImaTopologyRestangular) {
		var svc = {
			base: function() {
				return ImaTopologyRestangular.one('restconf').one('operational').one('network-topology:network-topology');
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

		getData: function() {
			svc.data = svc.base().one("topology","flow:1").get();
			return svc.data;
		},

		svc.getNode = function(node,cb) {
			return;
		};

		return svc;
	});

	/*
	 *	Websocket Service
	 */
	IMADluxUIApp.register.factory('OdlWebsocketSvc', function(ImaTopologyRestangular, $q, $rootScope, ENV) {
		    // We return this object to anything injecting our service
		var svc = {
			createOldStream: function() {
				ImaTopologyRestangular.customPOST(
						JSON.stringify(
							{
								'input': {
									'path': '/toaster:toaster/toaster:toasterStatus',
									'sal-remote-augment:datastore': 'OPERATIONAL',
									'sal-remote-augment:scope': 'ONE'
								}
							}
							), // body
						'restconf/operations/sal-remote:create-data-change-event-subscription', // path
						{}, // custom param
						{Content-Type=application/xml, Accept=application/xml} // custom header
						);
			},
			subscribeStream: function() {
				return svc.data;
			},
			createWebsocket: function(wsUrl) {
				svc.ws = new WebSocket(wsUrl);
				svc.ws.onopen = function(){  
					console.log("Socket has been opened!");  
				};
			},
			notificationListener: function() {
				return svc.data;
			},
			ws: null
		
		};

		/*
    // Keep all pending requests here until they get responses
    var callbacks = {};
    // Create a unique callback ID to map requests to responses
    var currentCallbackId = 0;
    // Create our websocket object with the address to the websocket
    var ws = new WebSocket("ws://localhost:8000/socket/");
    
    ws.onopen = function(){  
        console.log("Socket has been opened!");  
    };
    
    ws.onmessage = function(message) {
        listener(JSON.parse(message.data));
    };

    function sendRequest(request) {
      var defer = $q.defer();
      var callbackId = getCallbackId();
      callbacks[callbackId] = {
        time: new Date(),
        cb:defer
      };
      request.callback_id = callbackId;
      console.log('Sending request', request);
      ws.send(JSON.stringify(request));
      return defer.promise;
    }

    function listener(data) {
      var messageObj = data;
      console.log("Received data from websocket: ", messageObj);
      // If an object exists with callback_id in our callbacks object, resolve it
      if(callbacks.hasOwnProperty(messageObj.callback_id)) {
        console.log(callbacks[messageObj.callback_id]);
        $rootScope.$apply(callbacks[messageObj.callback_id].cb.resolve(messageObj.data));
        delete callbacks[messageObj.callbackID];
      }
    }
    // This creates a new callback ID for a request
    function getCallbackId() {
      currentCallbackId += 1;
      if(currentCallbackId > 10000) {
        currentCallbackId = 0;
      }
      return currentCallbackId;
    }

    // Define a "getter" for getting customer data
    svc.getCustomers = function() {
      var request = {
        type: "get_customers"
      }
      // Storing in a variable for clarity on what sendRequest returns
      var promise = sendRequest(request); 
      return promise;
    }
		*/
    return svc;

	});

});

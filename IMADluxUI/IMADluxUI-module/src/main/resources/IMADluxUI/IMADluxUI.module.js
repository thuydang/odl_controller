/*
 * Copyright (c) 2015 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

define(['angularAMD', 'app/routingConfig', 'app/core/core.services', 'Restangular', 'ocLazyLoad', 'common/config/env.module'], function(ng) {
  var IMADluxUIApp = angular.module('app.IMADluxUI', ['app.core', 'ui.router.state','restangular', 'oc.lazyLoad', 'config']);

  IMADluxUIApp.config(function($stateProvider, $compileProvider, $controllerProvider, $provide, NavHelperProvider, $translateProvider) {
		// If your module is required by the main application, you will need to register your 
		// angular components because the parent DLUX app will be already bootstraped. 
		// Without the following code, DLUX won't see your components on the runtime.
		//
		// Note : If your module is only use by an other module, you don't have to do this step. 
    IMADluxUIApp.register = {
      controller : $controllerProvider.register,
      directive : $compileProvider.directive,
      factory : $provide.factory,
      service : $provide.service

    };

		// To be able to add item to the navigation menu, the module requires the NavHelperProvider 
		// parameter in the configuration method. This helper has a method to easily append contain 
		// into the menu. The first parameter is 
		// an id who refer as the level of your menu and the second is a object. 
    //NavHelperProvider.addControllerUrl('app/IMADluxUI/IMADluxUI.controller');
    NavHelperProvider.addControllerUrl('app/IMADluxUI/components/topology/topologyController');
    NavHelperProvider.addToMenu('IMADluxUI', {
     "link" : "#/IMADluxUI",
     "active" : "main.IMADluxUI",
     "title" : "IMA Controller UI",
     "icon" : "",  // Add navigation icon css class here
     "page" : {
        "title" : "IMA Topology",
        "description" : "IMADluxUI"
     }
    });

		// The next step is to set up the route for our module. This part is also done in the 
		// configuration method of the module. We have to add $stateProvider as a parameter. 
    var access = routingConfig.accessLevels;

    $stateProvider.state('main.IMADluxUI', {
        url: 'IMADluxUI',
        access: access.admin,
        views : {
            'content' : {
                //templateUrl: 'src/app/IMADluxUI/module.index.html',
                templateUrl: 'src/app/IMADluxUI/components/topology/topology.html',
                //controller: 'IMADluxUICtrl'
                controller: 'TopologyController'
            }
        },
				resolve: {
					/*
					loadMyFile:['$ocLazyLoad', function( $ocLazyLoad ) {
						return $ocLazyLoad.load({
							name:'topology_ima',
							files:[
								'src/app/IMADluxUI/assets/libs/next-ui/js/next.js',
								'src/app/IMADluxUI/assets/libs/next-ui/css/next.css',
							]
						}).then(function success(args) {
							console.log('success');
							return args;
						}, function error(err) {
							console.log(err);
							return err;
						});
					}],
					*/
					loadNextUI:['$ocLazyLoad', function( $ocLazyLoad ) {
						return $ocLazyLoad.load({
							//name:'nextUI',
							files:[
								'src/app/IMADluxUI/assets/libs/next-ui/js/next.js',
								'src/app/IMADluxUI/assets/libs/next-ui/css/next.css',
							]
						}).then(function success(args) {
							console.log('success');
							return args;
						}, function error(err) {
							console.log(err);
							return err;
						});
					}],
					loadNextUIModule:['$ocLazyLoad', 'loadNextUI', function( $ocLazyLoad ) {
						return $ocLazyLoad.load({
							files:[
								'src/app/IMADluxUI/components/topology/next-modules/data.js',
								'src/app/IMADluxUI/components/topology/next-modules/topology.js',
							]
						}).then(function success(args) {
							console.log('success');
							return args;
						}, function error(err) {
							console.log(err);
							return err;
						});
					}]
				}
		});

	});

	return IMADluxUIApp;
});

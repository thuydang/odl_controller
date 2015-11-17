// define empty topology data object
var topologyData = {};
// function transforms odl respond to next json format
var odl2next = function (data) {
	// create alias
	var topology = data.topology[0];
	//console.log(topology.node);
	//console.log(typeof topology.node !== 'undefined'
	//		    ? topology.node.length : 'data is null or undefined.');
	
	// prepare stub for results
	var topologyResult = {nodes: [], links: []};
	// process nodes
	if (typeof topology.node !== 'undefined'){
	for (var i = 0; i < topology.node.length; i++) {
		var node = {};
		// node name
		if (topology.node[i].hasOwnProperty('node-id')) {
			node.name = topology.node[i]['node-id'];
		}
		// ... other actions with node object ...
		// add the node to the result object
		topologyResult.nodes.push(node);
	}
	}
	// processing links
	if (typeof topology.link !== 'undefined'){
	for (i = 0; i < topology.link.length; i++) {
		var link = {
			id: topology.link[i]['link-id'],
			source: topology.link[i].source['source-node'],
			target: topology.link[i].destination['dest-node']
		};
		// add the link to the result object
		topologyResult.links.push(link);
	}
	}
	return topologyResult;
};

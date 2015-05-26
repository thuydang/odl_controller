package de.dailab.nemo.ima.controller.app;

import org.opendaylight.openflowplugin.api.OFConstants;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev100924.Uri;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev100924.MacAddress;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.action.DropActionCaseBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.action.OutputActionCaseBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.action.output.action._case.OutputActionBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.list.Action;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.list.ActionBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.action.types.rev131112.action.list.ActionKey;
import org.opendaylight.yang.gen.v1.urn.opendaylight.address.tracker.rev140617.address.node.connector.Addresses;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.FlowCapableNode;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.FlowId;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.tables.Table;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.tables.TableKey;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.tables.table.Flow;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.tables.table.FlowBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.inventory.rev130819.tables.table.FlowKey;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.service.rev130819.AddFlowInputBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.service.rev130819.AddFlowOutput;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.service.rev130819.FlowTableRef;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.service.rev130819.SalFlowService;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.FlowCookie;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.FlowModFlags;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.FlowRef;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.OutputPortValues;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.flow.InstructionsBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.flow.Match;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.flow.MatchBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.instruction.instruction.ApplyActionsCaseBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.instruction.instruction.apply.actions._case.ApplyActions;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.instruction.instruction.apply.actions._case.ApplyActionsBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.instruction.list.Instruction;
import org.opendaylight.yang.gen.v1.urn.opendaylight.flow.types.rev131026.instruction.list.InstructionBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeConnectorId;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeRef;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.Nodes;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.node.NodeConnector;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.Node;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.nodes.NodeKey;
import org.opendaylight.yang.gen.v1.urn.opendaylight.inventory.rev130819.NodeId;
import org.opendaylight.yang.gen.v1.urn.opendaylight.l2.types.rev130827.EtherType;
import org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.ethernet.match.fields.EthernetSourceBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.ethernet.match.fields.EthernetDestinationBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.ethernet.match.fields.EthernetTypeBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.model.match.types.rev131026.match.EthernetMatchBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.packet.ethernet.rev140528.KnownEtherType;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

import de.dailab.nemo.ima.controller.app.FlowWriterService;

/**
 * Manage flows installed on each switch.
 * 
 */
public class FlowManagerImpl implements FlowManager {
	protected static final Logger LOG = LoggerFactory
			.getLogger(FlowManagerImpl.class);
	private static final byte[] ETH_TYPE_IPV4 = new byte[] { 0x08, 0x00 };
	private final long IPV4_ETHER_TYPE = Long.valueOf(0x0800);
	private final long ARP_ETHER_TYPE = Long.valueOf(0x0806);

	private SalFlowService salFlowService;
	private FlowWriterService flowWriterService;
	private final ExecutorService flowWriterExecutor = Executors.newCachedThreadPool();

	private final short DEFAULT_FLOW_TABLE_ID = 0;
	private final int DEFAULT_FLOW_PRIORITY = 0;
	private final int DEFAULT_FLOW_IDLE_TIMEOUT = 0;
	private final int DEFAULT_FLOW_HARD_TIMEOUT = 0;

	private AtomicLong flowPriorityInc = new AtomicLong(3);
	private AtomicLong flowIdInc = new AtomicLong();
	private AtomicLong flowCookieInc = new AtomicLong(0x2b00000000000000L);
	private short flowTableId;
	private int flowPriority;
	private int flowIdleTimeout;
	private int flowHardTimeout;


	@Override
	public void setSalFlowService(SalFlowService salFlowService) {
    this.salFlowService = salFlowService;
	}

	public void setFlowWriterService(FlowWriterService flowWriterService) {
    this.flowWriterService = flowWriterService;
	}

	@Override
	public void addInitialFlows(InstanceIdentifier<Node> nodeId) {
		// TODO Auto-generated method stub
		Preconditions.checkNotNull(salFlowService, "salFlowService should not be null.");
		flowWriterExecutor.submit(new InitialFlowWriterProcessor(nodeId));

	}

	/**
	 * Write flow to forward packet having Mac in @param addrs using @param nodeConnector.
	 * @param node should be this node.
	 */
	@Override
	public void writeForwardToMacFlow(Node node, NodeConnector nodeConnector,
			Addresses addrs) {
		flowWriterExecutor.submit(new ForwardToMacFlowWriterProcessor(node, nodeConnector, addrs));

	}
	/**
	 * Same as above
	 */
	@Override
	public void writeForwardToMacFlow(NodeId nodeId, NodeConnectorId nodeConnectorId,
			MacAddress aMac) {
		flowWriterExecutor.submit(new ForwardToMacFlowWriterProcessor(nodeId, nodeConnectorId, aMac));
	}

	private class ForwardToMacFlowWriterProcessor implements Runnable {
		private NodeId nodeId;
		private MacAddress aMac;
		private NodeConnectorId nodeConnectorId;

		public ForwardToMacFlowWriterProcessor(Node node, NodeConnector nodeConnector, Addresses addrs) {
			this.nodeId = node.getId();
			this.nodeConnectorId = nodeConnector.getId();
			this.aMac = addrs.getMac();
		}

		public ForwardToMacFlowWriterProcessor(NodeId nodeId,
				NodeConnectorId nodeConnectorId, MacAddress aMac) {
			this.nodeId = nodeId;
			this.nodeConnectorId = nodeConnectorId;
			this.aMac = aMac;
		}

		@Override
		public void run() {

			if(nodeId == null) {
				return;
			}

			writeFlow();

		}

		private void writeFlow() {
			Flow flow = createFlow(nodeConnectorId, aMac, (int) flowPriorityInc.getAndIncrement());
			writeFlowToSwitch(nodeId, flow);

			LOG.debug("++++ Added flows for node {}, nodeConnectorId: {}, MAC addrs {} ", nodeId, nodeConnectorId, aMac);
		}

		// ForwardToMacFlow
		private Flow createFlow(NodeConnectorId nodeConnectorId, MacAddress aMac, int priority) {
			// start building flow
			FlowBuilder flow = new FlowBuilder() //
			.setTableId(flowTableId) //
			.setFlowName("forward" + aMac);

			// use its own hash code for id.
			flow.setId(new FlowId(Long.toString(flow.hashCode())));

			// Create Match
	        EthernetDestinationBuilder ethDestBuilder = new EthernetDestinationBuilder();
	        ethDestBuilder.setAddress(new MacAddress(aMac));
	        EthernetTypeBuilder ethTypeBuilder = new EthernetTypeBuilder();
	        ethTypeBuilder.setType(new EtherType(IPV4_ETHER_TYPE));
	        
			EthernetMatchBuilder ethernetMatch = new EthernetMatchBuilder();
	        ethernetMatch.setEthernetDestination(ethDestBuilder.build());
	        ethernetMatch.setEthernetType(ethTypeBuilder.build());
	        
			MatchBuilder matchBuilder = new MatchBuilder();
	        matchBuilder.setEthernetMatch(ethernetMatch.build());

	        Match match = matchBuilder.build();

			Action outputAction = new ActionBuilder() //
			.setOrder(0)
			.setAction( new OutputActionCaseBuilder()
						.setOutputAction(new OutputActionBuilder()
						//.setOutputNodeConnector(new Uri(nodeConnector.getId().getValue()))
						.setMaxLength(0xffff) //
                        .setOutputNodeConnector(nodeConnectorId) //
						.build())
	                .build())
			.build();

			// Create an Apply Action
			ApplyActions applyActions = new ApplyActionsBuilder().setAction(ImmutableList.of(outputAction))
					.build();

			// Wrap our Apply Action in an Instruction
			Instruction applyActionsInstruction = new InstructionBuilder() //
			.setOrder(0)
			.setInstruction(new ApplyActionsCaseBuilder()//
			.setApplyActions(applyActions) //
			.build()) //
			.build();

			// Put our Instruction in a list of Instructions
			flow
			.setMatch(match) //
			.setInstructions(new InstructionsBuilder() //
			.setInstruction(ImmutableList.of(applyActionsInstruction)) //
			.build()) //
			.setPriority(priority) //
			.setBufferId(OFConstants.OFP_NO_BUFFER) //
			.setHardTimeout(flowHardTimeout) //
			.setIdleTimeout(flowIdleTimeout) //
			.setCookie(new FlowCookie(BigInteger.valueOf(flowCookieInc.getAndIncrement())))
			.setFlags(new FlowModFlags(false, false, false, false, false));

			return flow.build();

		}

		/**
		 * Starts and commits data change transaction which
		 * modifies provided flow path with supplied body.
		 */
		private Future<RpcResult<AddFlowOutput>> writeFlowToSwitch(NodeId nodeId, Flow flow) {
			InstanceIdentifier<Node> nodeInstanceId = InstanceIdentifier.<Nodes>builder(Nodes.class)
					.<Node, NodeKey>child(Node.class, new NodeKey(nodeId)).build();
			InstanceIdentifier<Table> tableInstanceId = nodeInstanceId.<FlowCapableNode>augmentation(FlowCapableNode.class)
					.<Table, TableKey>child(Table.class, new TableKey(flowTableId));
			InstanceIdentifier<Flow> flowPath = tableInstanceId
					.<Flow, FlowKey>child(Flow.class, new FlowKey(new FlowId(String.valueOf(flowIdInc.getAndIncrement()))));

			final AddFlowInputBuilder builder = new AddFlowInputBuilder(flow)
			.setNode(new NodeRef(nodeInstanceId))
			.setFlowTable(new FlowTableRef(tableInstanceId))
			.setFlowRef(new FlowRef(flowPath))
			.setTransactionUri(new Uri(flow.getId().getValue()));
			return salFlowService.addFlow(builder.build());
		}
	}

	
  /**
   * A private class to process the node updated event in separate thread. Allows to release the
   * thread that invoked the data node updated event. Avoids any thread lock it may cause.
   */
  private class InitialFlowWriterProcessor implements Runnable {
    private InstanceIdentifier<Node> nodeId;

    public InitialFlowWriterProcessor(InstanceIdentifier<Node> nodeId) {
      this.nodeId = nodeId;
    }

    @Override
    public void run() {

      if(nodeId == null) {
        return;
      }

      addInitialFlows(nodeId);

    }

    /**
     * Adds a flow, which drops all packets, on the specifide node.
     * @param nodeId The node to install the flow on.
     */
    public void addInitialFlows(InstanceIdentifier<Node> nodeId) {
      LOG.debug("Adding initial flows for node {} ", nodeId);

      InstanceIdentifier<Table> tableId = getTableInstanceId(nodeId);
      InstanceIdentifier<Flow> flowId = getFlowInstanceId(tableId);

      //add drop all flow
      //writeFlowToController(nodeId, tableId, flowId, createDropAllFlow(flowTableId, flowPriority));
      //add fwd to controller flow
      writeFlowToController(nodeId, tableId, flowId, createForwardToControllerFlow(flowTableId, flowPriority));
      //add fwd arp to controller flow
      //writeFlowToController(nodeId, tableId, flowId, createAllArpToControllerFlow(flowTableId, 1));

      LOG.debug("Added initial flows for node {} ", nodeId);
    }

    private InstanceIdentifier<Table> getTableInstanceId(InstanceIdentifier<Node> nodeId) {
      // get flow table key
      TableKey flowTableKey = new TableKey(flowTableId);

      return nodeId.builder()
          .augmentation(FlowCapableNode.class)
          .child(Table.class, flowTableKey)
          .build();
    }

    private InstanceIdentifier<Flow> getFlowInstanceId(InstanceIdentifier<Table> tableId) {
      // generate unique flow key
      FlowId flowId = new FlowId(String.valueOf(flowIdInc.getAndIncrement()));
      FlowKey flowKey = new FlowKey(flowId);
      return tableId.child(Flow.class, flowKey);
    }

    /**
     * Adds a flow, which sends packets to the controller, to the specified node.
     * @param nodeId The node to write the flow on.
     */
    private Flow createForwardToControllerFlow(Short tableId, int priority) {
    	// start building flow
    	FlowBuilder toCtrlFlow = new FlowBuilder() //
    	.setTableId(tableId) //
    	.setFlowName("fwdtocntrl");

    	// use its own hash code for id.
    	toCtrlFlow.setId(new FlowId(Long.toString(toCtrlFlow.hashCode())));

    	Match match = new MatchBuilder()
    	.build();

    	// Create an Apply Action
    	ApplyActions applyActions = new ApplyActionsBuilder().setAction(ImmutableList.of(getSendToControllerAction()))
    			.build();

    	// Wrap our Apply Action in an Instruction
    	Instruction applyActionsInstruction = new InstructionBuilder() //
    	.setOrder(0)
    	.setInstruction(new ApplyActionsCaseBuilder()//
    	.setApplyActions(applyActions) //
    	.build()) //
    	.build();

    	// Put our Instruction in a list of Instructions
    	toCtrlFlow
    	.setMatch(match) //
    	.setInstructions(new InstructionsBuilder() //
    	.setInstruction(ImmutableList.of(applyActionsInstruction)) //
    	.build()) //
    	.setPriority(priority) //
    	.setBufferId(OFConstants.OFP_NO_BUFFER) //
    	.setHardTimeout(flowHardTimeout) //
    	.setIdleTimeout(flowIdleTimeout) //
    	.setCookie(new FlowCookie(BigInteger.valueOf(flowCookieInc.getAndIncrement())))
    	.setFlags(new FlowModFlags(false, false, false, false, false));

    	return toCtrlFlow.build();

    }


    /**
     * Adds a flow, which sends all ARP packets to the controller, to the specified node.
     * @param nodeId The node to write the flow on.
     */
    private Flow createAllArpToControllerFlow(Short tableId, int priority) {
    	// start building flow
    	FlowBuilder arpFlow = new FlowBuilder() //
    	.setTableId(tableId) //
    	.setFlowName("arptocntrl");

    	// use its own hash code for id.
    	arpFlow.setId(new FlowId(Long.toString(arpFlow.hashCode())));
    	EthernetMatchBuilder ethernetMatchBuilder = new EthernetMatchBuilder()
    	.setEthernetType(new EthernetTypeBuilder()
    	.setType(new EtherType(Long.valueOf(KnownEtherType.Arp.getIntValue()))).build());

    	Match match = new MatchBuilder()
    	.setEthernetMatch(ethernetMatchBuilder.build())
    	.build();

    	// Create an Apply Action
    	ApplyActions applyActions = new ApplyActionsBuilder().setAction(ImmutableList.of(getSendToControllerAction()))
    			.build();

    	// Wrap our Apply Action in an Instruction
    	Instruction applyActionsInstruction = new InstructionBuilder() //
    	.setOrder(0)
    	.setInstruction(new ApplyActionsCaseBuilder()//
    	.setApplyActions(applyActions) //
    	.build()) //
    	.build();

    	// Put our Instruction in a list of Instructions
    	arpFlow
    	.setMatch(match) //
    	.setInstructions(new InstructionsBuilder() //
    	.setInstruction(ImmutableList.of(applyActionsInstruction)) //
    	.build()) //
    	.setPriority(priority) //
    	.setBufferId(OFConstants.OFP_NO_BUFFER) //
    	.setHardTimeout(flowHardTimeout) //
    	.setIdleTimeout(flowIdleTimeout) //
    	.setCookie(new FlowCookie(BigInteger.valueOf(flowCookieInc.getAndIncrement())))
    	.setFlags(new FlowModFlags(false, false, false, false, false));

    	return arpFlow.build();

    }

    private Action getSendToControllerAction() {
    	Action sendToController = new ActionBuilder()
    	.setOrder(0)
    	.setKey(new ActionKey(0))
    	.setAction(new OutputActionCaseBuilder()
    	.setOutputAction(new OutputActionBuilder()
    	.setMaxLength(0xffff)
    	.setOutputNodeConnector(new Uri(OutputPortValues.CONTROLLER.toString()))
    	.build())
    	.build())
    	.build();

    	return sendToController;
    }

    /**
     * Drop all packet.
     * @param tableId
     * @param priority
     * @return
     */
    private Flow createDropAllFlow(Short tableId, int priority) {

      // start building flow
      FlowBuilder dropAll = new FlowBuilder() //
          .setTableId(tableId) //
          .setFlowName("dropall");

      // use its own hash code for id.
      dropAll.setId(new FlowId(Long.toString(dropAll.hashCode())));

      Match match = new MatchBuilder().build();


      Action dropAllAction = new ActionBuilder() //
          .setOrder(0)
          .setAction(new DropActionCaseBuilder().build())
          .build();

      // Create an Apply Action
      ApplyActions applyActions = new ApplyActionsBuilder().setAction(ImmutableList.of(dropAllAction))
          .build();

      // Wrap our Apply Action in an Instruction
      Instruction applyActionsInstruction = new InstructionBuilder() //
          .setOrder(0)
          .setInstruction(new ApplyActionsCaseBuilder()//
              .setApplyActions(applyActions) //
              .build()) //
          .build();

      // Put our Instruction in a list of Instructions
      dropAll
          .setMatch(match) //
          .setInstructions(new InstructionsBuilder() //
              .setInstruction(ImmutableList.of(applyActionsInstruction)) //
              .build()) //
          .setPriority(priority) //
          .setBufferId(OFConstants.OFP_NO_BUFFER) //
          .setHardTimeout(flowHardTimeout) //
          .setIdleTimeout(flowIdleTimeout) //
          .setCookie(new FlowCookie(BigInteger.valueOf(flowCookieInc.getAndIncrement())))
          .setFlags(new FlowModFlags(false, false, false, false, false));

      return dropAll.build();
    }

    private Future<RpcResult<AddFlowOutput>> writeFlowToController(InstanceIdentifier<Node> nodeInstanceId,
                                                                   InstanceIdentifier<Table> tableInstanceId,
                                                                   InstanceIdentifier<Flow> flowPath,
                                                                   Flow flow) {
      final AddFlowInputBuilder builder = new AddFlowInputBuilder(flow);
      builder.setNode(new NodeRef(nodeInstanceId));
      builder.setFlowRef(new FlowRef(flowPath));
      builder.setFlowTable(new FlowTableRef(tableInstanceId));
      builder.setTransactionUri(new Uri(flow.getId().getValue()));
      return salFlowService.addFlow(builder.build());
    }
  }

}
